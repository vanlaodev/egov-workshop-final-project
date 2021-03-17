<template>
  <b-card class="shadow-sm">
    <div class="view-container" v-if="master && !loadingMasterWithDetails">
      <header>
        <h1>{{ master.title }}</h1>
        <button v-if="isAdmin" class="add-button" v-on:click="clickAddItem()">
          <b-icon icon="plus" variant="success"></b-icon>
        </button>
      </header>
      <div class="content-container">
        <div class="inventory-form-time">
          {{ master.fromTime.format("DD/MM/YYYY") }} -
          {{ master.endTime.format("DD/MM/YYYY") }}
        </div>
        <div class="inventory-form-des">{{ master.remark }}</div>
        <div v-if="isAdmin" class="inventory-filter-container">
          <b-row class="filter-row">
            <b-col class="filter-col" cols="auto">
              <div class="filter-title">{{ $t("search") }}:</div>
            </b-col>
          </b-row>
          <b-row class="filter-row">
            <b-col class="filter-col" cols="3">
              <input
                v-on:keypress="searchChange"
                v-model="filterNo"
                placeholder="物品編號"
              />
            </b-col>
            <b-col class="filter-col" cols="3">
              <input
                v-on:keypress="searchChange"
                v-model="filterUserNo"
                placeholder="員工編號"
              />
            </b-col>
            <b-col class="filter-col" cols="auto">
              <b-form-select
                v-on:change="searchChangeNotDebounce"
                v-model="statusSelected"
                :options="statusOptions"
                class="status-select"
              ></b-form-select>
            </b-col>
            <b-col class="filter-col" cols="auto">
              <b-form-select
                v-on:change="searchChangeNotDebounce"
                v-model="departmentSelected"
                :options="departmentOptions"
                class="department-select"
              ></b-form-select>
            </b-col>
          </b-row>
        </div>
        <div class="inventory-list-container">
          <div class="no-find-item" v-if="details.length == 0">
            沒有找到物品
          </div>
          <ul v-if="details && details.length > 0">
            <li>
              <b-row class="inventory-row title">
                <b-col md="2"> 物品編號 </b-col>
                <b-col md="6"> 物品名稱 </b-col>
                <b-col md="2"> 員工 </b-col>
                <b-col md="2"> </b-col>
              </b-row>
            </li>
            <li v-for="detail in details" :key="detail.id">
              <b-row class="inventory-row">
                <b-col class="inventory-col-no" md="2">
                  {{ detail.assetId }}
                </b-col>
                <b-col md="6">
                  {{ detail.description }}
                </b-col>
                <b-col md="2">
                  {{ detail.userName }}
                </b-col>
                <b-col class="action-button-col" md="2" sm="12">
                  <button
                    class="check-button"
                    @click="showConfirmDetailModal(detail)"
                    :disabled="detail.checked"
                  >
                    <span v-if="detail.checked">已確認</span>
                    <span v-else>確認</span>
                  </button>
                </b-col>
              </b-row>
            </li>
          </ul>
          <div
            v-if="details && details.length > 0"
            class="inventory-pagination-container"
          >
            <b-pagination
              v-model="currentPage"
              :total-rows="details.length"
              :per-page="perPage"
              class="inventory-pagination"
              @change="pageCallback"
              first-number
              last-number
            >
            </b-pagination>
          </div>
        </div>
      </div>
    </div>
    <div v-else-if="loadingMasterWithDetails">{{ $t("loading") }}...</div>
    <b-modal
      ref="search-modal"
      id="search-modal"
      size="lg"
      hide-footer
      hide-header
    >
      <div class="d-block text-center search-title">
        <h3>新增物品</h3>
      </div>
      <search-inventory v-on:clickAdd="addItem"></search-inventory>
      <b-button
        class="mt-3 search close-button"
        variant="outline-danger"
        block
        @click="hideModal"
        >取消</b-button
      >
    </b-modal>
    <confirm-dialog :ctx="confirmDialogCtx"></confirm-dialog>
    <message-dialog :ctx="msgDialogCtx"></message-dialog>
  </b-card>
</template>

<style lang="scss" scoped>
@import "../assets/scss/take-inventory-detail.scss";
</style>

<script>
import lodash from "lodash";
import SearchInventory from "../components/SearchInventory";
import { mapState } from "vuex";
import MessageDialog from "../components/MessageDialog";
import ConfirmDialog from "../components/ConfirmDialog";

export default {
  data() {
    return {
      msgDialogCtx: {
        visible: false,
        title: "",
        message: "",
        resolve: null,
      },
      confirmDialogCtx: {
        visible: false,
        title: "",
        message: "",
        resolve: null,
      },
      master: null,
      details: [],
      isAdmin: true,
      filterNo: "",
      filterUserNo: "",
      perPage: 10,
      currentPage: 1,
      statusSelected: 0,
      departmentSelected: 0,
      statusOptions: [
        { value: 0, text: "全部" },
        { value: 1, text: "已確認" },
        { value: 2, text: "未確認" },
      ],
      departmentOptions: [
        { value: 0, text: "全部" },
        { value: 1, text: "部門1" },
        { value: 2, text: "部門2" },
        { value: 3, text: "部門3" },
      ],
      loadingMasterWithDetails: false,
    };
  },
  computed: {
    ...mapState(["loggedInUser"]),
  },
  methods: {
    async showConfirmDetailModal(detail) {
      const confirmed = await this.showConfirmDialog(
        `您是否確認已盤點物品/資產${detail.assetId}-${detail.description}？`
      );
      if (confirmed) {
        try {
          await this.$api.inventoryApi.updateDetail(
            Object.assign({}, detail, { actionResult: "done" })
          );
          detail.checked = true;
        } catch (err) {
          await this.showMsgDialog(err, this.$t("error"));
        }
      }
    },
    showMsgDialog(message, title) {
      return new Promise((resolve) => {
        this.msgDialogCtx.title = title;
        this.msgDialogCtx.message = message;
        this.msgDialogCtx.resolve = resolve;
        this.msgDialogCtx.visible = true;
      });
    },
    showConfirmDialog(message, title) {
      return new Promise((resolve) => {
        this.confirmDialogCtx.title = title;
        this.confirmDialogCtx.message = message;
        this.confirmDialogCtx.resolve = resolve;
        this.confirmDialogCtx.visible = true;
      });
    },
    async loadMasterWithDetails() {
      const masterId = this.$route.params.id;
      if (masterId) {
        if (this.loadingMasterWithDetails) return;
        try {
          this.loadingMasterWithDetails = true;
          const master = await this.$api.inventoryApi.getMasterById(masterId);
          if (master) {
            if (!master.canTakeInventory()) throw this.$t("msg_invalidState");
            this.master = master;
            const details = await this.$api.inventoryApi.searchDetail({
              masterId: masterId,
            });
            this.details = !details ? [] : details;
          } else {
            await this.showMsgDialog(this.$t("msg_recordNotFound"));
          }
        } catch (err) {
          await this.showMsgDialog(err, this.$t("error"));
        } finally {
          this.loadingMasterWithDetails = false;
        }
      } else {
        await this.showMsgDialog(this.$t("msg_recordNotFound"));
      }
    },
    clickAddItem: function () {
      this.$refs["search-modal"].show();
    },
    hideModal() {
      this.$refs["search-modal"].hide();
    },
    pageCallback() {
      console.log(this.currentPage);
    },
    addItem(itemNo) {
      console.log("addItem", itemNo);
      this.$refs["search-modal"].hide();
    },
    searchChangeNotDebounce() {
      console.log(this.filterNo);
    },
    searchChange: lodash.debounce(function () {
      this.searchChangeNotDebounce();
    }, 1000),
  },
  mounted() {
    this.loadMasterWithDetails();
  },
  components: {
    SearchInventory,
    MessageDialog,
    ConfirmDialog,
  },
};
</script>