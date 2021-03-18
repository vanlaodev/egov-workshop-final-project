<template>
  <div>
    <b-card :title="$t('editInventoryForm')" class="shadow-sm">
      <b-form
        @submit.prevent="savedata"
        v-if="originalMaster && !loading"
        class="mt-3"
      >
        <div class="row">
          <b-form-group :label="$t('id')" label-for="input-id" class="col-md-6">
            <b-form-input
              id="input-id"
              :value="originalMaster.id"
              readonly
            ></b-form-input>
          </b-form-group>
          <b-form-group
            :label="$t('department')"
            label-for="input-dept"
            v-if="selectedDept"
            class="col-md-6"
          >
            <b-form-input
              id="input-dept"
              :value="selectedDept.text"
              readonly
            ></b-form-input>
          </b-form-group>
        </div>

        <div class="row">
          <b-form-group
            :label="$t('title')"
            label-for="input-title"
            class="col-md-6"
          >
            <b-form-input
              id="input-title"
              v-model="title"
              required
              autofocus
            ></b-form-input>
          </b-form-group>

          <b-form-group
            :label="$t('status')"
            label-for="select-status"
            class="col-md-6"
          >
            <select
              id="select-status"
              class="form-control"
              v-model="selectedStatus"
            >
              <option
                v-bind:key="status.value"
                v-for="status in statusList"
                :value="status.value"
              >
                {{ status.text }}
              </option>
            </select>
          </b-form-group>
        </div>

        <div class="row">
          <b-form-group
            :label="$t('from')"
            label-for="dtp-from"
            class="col-md-6"
          >
            <b-form-datepicker
              id="dtp-from"
              v-model="dtpFrom"
              :min="minDtpFrom"
              @input="onDtpFromUpdated"
            ></b-form-datepicker>
          </b-form-group>
          <b-form-group :label="$t('to')" label-for="dtp-to" class="col-md-6">
            <b-form-datepicker
              id="dtp-to"
              v-model="dtpTo"
              :min="minDtpTo"
            ></b-form-datepicker>
          </b-form-group>
        </div>

        <b-form-group :label="$t('remark')" label-for="input-remark">
          <b-form-textarea id="input-remark" v-model="remark"></b-form-textarea>
        </b-form-group>

        <b-button
          :disabled="saving"
          type="submit"
          variant="primary"
          class="mr-2"
          >{{ $t("save") }}</b-button
        >
        <b-button
          :disabled="saving"
          @click.prevent="backToinquiry"
          variant="secondary"
          >{{ $t("cancel") }}</b-button
        >
      </b-form>
      <div v-else-if="loading" class="mt-4">
        <strong>{{ $t("loading") }}...</strong>
      </div>

      <confirm-dialog :ctx="confirmDialogCtx"></confirm-dialog>
      <message-dialog :ctx="msgDialogCtx"></message-dialog>
    </b-card>
    <b-card
      :title="$t('inventoryOfAssets')"
      class="shadow-sm mt-3"
      v-if="!loading"
    >
      <b-button variant="success" @click="openModal" class="mb-2 mr-2">
        <b-icon icon="plus-circle" aria-hidden="true"></b-icon>
        {{ $t("add") }}
      </b-button>
      <b-table
        responsive
        hover
        :items="details"
        :fields="detailsFields"
        class="mt-3"
        :sort-by.sync="detailsSortBy"
        :sort-desc.sync="detailsSortDesc"
      >
        <template #cell(actions)="data">
          <b-button
            v-if="canEditDetails && data.item.canEdit()"
            variant="link"
            size="sm"
            @click="showConfirmDeleteDetailModal(data.item.id)"
            class="p-0"
          >
            <b-icon icon="trash-fill" aria-hidden="true"></b-icon>
            {{ $t("delete") }}
          </b-button>
        </template>
      </b-table>
    </b-card>

    <b-modal id="insert-modal" size="lg" :title="$t('add')">
      <b-table
        responsive
        hover
        :items="adddetails"
        :fields="detailsFields"
        class="mt-3"
        :sort-by.sync="detailsSortBy"
        :sort-desc.sync="detailsSortDesc"
      >
        <template #cell(actions)>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label class="form-check-label" for="flexCheckDefault">
              {{ $t("add") }}
            </label>
          </div>
        </template>
      </b-table>
    </b-modal>
  </div>
</template>


<script>
import * as dayjs from "dayjs";
import { mapState } from "vuex";
import MessageDialog from "../components/MessageDialog";
import ConfirmDialog from "../components/ConfirmDialog";

export default {
  components: {
    MessageDialog,
    ConfirmDialog,
  },
  data() {
    return {
      originalMaster: null,
      title: "",
      dtpFrom: dayjs().format("YYYY-MM-DD"),
      dtpTo: dayjs().format("YYYY-MM-DD"),
      selectedStatus: "",
      remark: "",
      saving: false,
      loading: false,
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
      details: [],
      detailsSortBy: "assetId",
      detailsSortDesc: false,
      adddetails: [],
    };
  },
  computed: {
    ...mapState(["deptList"]),
    minDtpFrom() {
      return dayjs().format("YYYY-MM-DD");
    },
    minDtpTo() {
      return this.dtpFrom;
    },
    selectedDept() {
      if (!this.originalMaster) return null;
      return this.deptList.find((d) => d.value == this.originalMaster.deptId);
    },
    statusList() {
      return [
        { value: "ACTIVE", text: this.$t("active") },
        { value: "INVALID", text: this.$t("invalid") },
      ];
    },
    canEditDetails() {
      return true;
    },
    detailsFields() {
      return [
        {
          key: "id",
          label: this.$t("id"),
          sortable: true,
        },
        {
          key: "assetId",
          label: this.$t("assetId"),
          sortable: true,
        },
        {
          key: "brand",
          label: this.$t("brand"),
          sortable: true,
        },
        {
          key: "description",
          label: this.$t("description"),
          sortable: true,
        },
        {
          key: "remark",
          label: this.$t("remark"),
          sortable: true,
          formatter: (value, key, item) => {
            switch (this.$i18n.locale) {
              case "en":
                return item.remarkPt;
              default:
                return item.remarkZh;
            }
          },
        },
        {
          key: "result",
          label: this.$t("result"),
          sortable: true,
          formatter: (value, key, item) => {
            return !item.actionResult
              ? this.$t("notInventoried")
              : item.actionResult;
          },
        },
        {
          key: "actions",
          label: this.$t("actions"),
        },
      ];
    },
  },
  mounted() {
    this.getInventoryMaster();
  },
  methods: {
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
    async showConfirmDeleteDetailModal(selectedId) {
      const detail = this.details.find((x) => x.id == selectedId);
      const confirmed = await this.showConfirmDialog(
        this.$t("fmt_msg_confirmDeleteInventoryDetail", detail)
      );
      if (confirmed) {
        await this.deleteDetail(selectedId);
      }
    },
    async deleteDetail(selectedId) {
      try {
        await this.$api.inventoryApi.deleteDetail(selectedId);
        for (let z = 0; z < this.details.length; z++) {
          if (this.details[z].id == selectedId) {
            this.$delete(this.details, z);
            break;
          }
        }
        this.$root.$bvToast.toast(this.$t("msg_operationSuccess"), {
          title: this.$t("message"),
          variant: "success",
          autoHideDelay: 2000,
          solid: true,
        });
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      }
    },
    async getInventoryMaster() {
      const masterId = this.$route.params.id;
      if (masterId) {
        if (this.loading) return;
        try {
          this.loading = true;
          const master = await this.$api.inventoryApi.getMasterById(masterId);
          if (master) {
            this.title = master.title;
            this.remark = master.remark;
            this.selectedStatus = this.statusList.find(
              (d) => d.value == master.status
            ).value;
            this.dtpFrom = master.fromTime.format("YYYY-MM-DD");
            this.dtpTo = master.endTime.format("YYYY-MM-DD");
            const details = await this.$api.inventoryApi.searchDetail({
              masterId: masterId,
            });
            this.details = details == null ? [] : details;
            this.originalMaster = master;
          } else {
            await this.showMsgDialog(this.$t("msg_recordNotFound"));
            this.backToinquiry();
          }
        } catch (err) {
          await this.showMsgDialog(err, this.$t("error"));
          this.backToinquiry();
        } finally {
          this.loading = false;
        }
      } else {
        this.backToinquiry();
      }
    },
    onDtpFromUpdated() {
      if (this.dtpFrom > this.dtpTo) {
        this.dtpTo = this.dtpFrom;
      }
    },
    async savedata() {
      if (this.saving) return;
      try {
        this.saving = true;
        await this.$api.inventoryApi.updateMaster({
          id: this.originalMaster.id,
          deptId: this.originalMaster.deptId,
          status: this.selectedStatus,
          fromTime: dayjs(this.dtpFrom).format("YYYY/MM/DD"),
          endTime: dayjs(this.dtpTo).format("YYYY/MM/DD"),
          title: this.title,
          remark: this.remark,
        });
        this.$root.$bvToast.toast(this.$t("msg_operationSuccess"), {
          title: this.$t("message"),
          variant: "success",
          autoHideDelay: 2000,
          solid: true,
        });
        this.backToinquiry();
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      } finally {
        this.saving = false;
      }
    },
    backToinquiry() {
      this.$router.replace({ name: "InventoryFormManagement" });
    },
    openModal() {
      this.$bvModal.show("insert-modal");
      this.loadAddData();
    },
    loadAddData() {

    }
  },
};
</script>
