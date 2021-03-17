<template>
  <b-card :title="$t('inventoryFormManagement')" class="shadow-sm">
    <b-form inline class="mt-3 mb-1">
      <b-button
        variant="success"
        @click="goToCreateInventoryForm"
        class="mb-2 mr-2"
      >
        <b-icon icon="plus-circle" aria-hidden="true"></b-icon>
        {{ $t("add") }}
      </b-button>
      <b-button
        variant="primary"
        @click="loadInventoryMasters"
        class="mb-2 mr-2"
      >
        <b-icon icon="arrow-repeat" aria-hidden="true"></b-icon>
        {{ $t("refresh") }}
      </b-button>
    </b-form>

    <b-form inline class="d-flex flex-row flex-nowrap align-items-center mb-3">
      <b-input-group class="flex-grow-1">
        <template #prepend>
          <b-input-group-text><b-icon icon="filter" /></b-input-group-text>
        </template>
        <b-form-input
          type="search"
          v-model="filter"
          :placeholder="$t('filter')"
          autofocus
        ></b-form-input>
      </b-input-group>
    </b-form>

    <b-table
      v-if="!loadingTable"
      responsive
      hover
      :items="items"
      :fields="fields"
      :current-page="currentPage"
      :per-page="perPage"
      :filter="filter"
      @filtered="onFiltered"
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
    >
      <template #cell(actions)="data">
        <b-button
          variant="link"
          size="sm"
          @click="editMaster(data.item.id)"
          class="p-0 mr-3"
        >
          <b-icon icon="pencil-fill" aria-hidden="true"></b-icon>
          {{ $t("edit") }}
        </b-button>
        <b-button
          variant="link"
          size="sm"
          @click="showConfirmDeleteMasterModal(data.item.id)"
          class="p-0 mr-3"
        >
          <b-icon icon="trash-fill" aria-hidden="true"></b-icon>
          {{ $t("delete") }}
        </b-button>
      </template>
    </b-table>
    <b-skeleton-table
      v-else
      :rows="4"
      :columns="6"
      :table-props="{ bordered: false }"
    ></b-skeleton-table>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalRows"
      :per-page="perPage"
      size="md"
      class="mb-0"
    ></b-pagination>

    <confirm-dialog :ctx="confirmDialogCtx"></confirm-dialog>
    <message-dialog :ctx="msgDialogCtx"></message-dialog>
  </b-card>
</template>

<script>
import { mapState } from "vuex";
import MessageDialog from "../components/MessageDialog";
import ConfirmDialog from "../components/ConfirmDialog";

export default {
  name: "InventoryFormManagement",
  components: {
    MessageDialog,
    ConfirmDialog,
  },
  data() {
    return {
      items: [],
      currentPage: 1,
      perPage: 10,
      filter: null,
      loadingTable: false,
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
      sortBy: "id",
      sortDesc: true,
    };
  },
  computed: {
    ...mapState(["loggedInUser"]),
    fields() {
      return [
        {
          key: "id",
          label: this.$t("id"),
          sortable: true,
        },
        {
          key: "title",
          label: this.$t("title"),
          sortable: true,
        },
        {
          key: "fromTime",
          label: this.$t("from"),
          sortable: true,
          formatter: (value) => {
            return value.format("YYYY-MM-DD");
          },
        },
        {
          key: "endTime",
          label: this.$t("to"),
          sortable: true,
          formatter: (value) => {
            return value.format("YYYY-MM-DD");
          },
        },
        {
          key: "status",
          label: this.$t("status"),
          formatter: (value) => {
            let status;
            switch (value) {
              case "ACTIVE":
                status = this.$t("active");
                break;
              case "INVALID":
                status = this.$t("invalid");
                break;
              default:
                status = value;
                break;
            }
            return status;
          },
          sortable: true,
        },
        {
          key: "actions",
          label: this.$t("actions"),
        },
      ];
    },
    totalRows() {
      return this.items.length;
    },
  },
  mounted() {
    this.loadInventoryMasters();
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
    async loadInventoryMasters() {
      if (this.loadingTable) return;
      try {
        this.loadingTable = true;
        const masters = await this.$api.inventoryApi.searchMaster({
          deptId: this.loggedInUser.deptId,
        });
        this.items = masters;
        this.currentPage = 1;
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      } finally {
        this.loadingTable = false;
      }
    },
    async showConfirmDeleteMasterModal(selectedId) {
      const master = this.items.find((x) => x.id == selectedId);
      const confirmed = await this.showConfirmDialog(
        this.$t("fmt_msg_confirmDeleteInventoryMaster", {
          id: selectedId,
          title: master.title,
        })
      );
      if (confirmed) {
        await this.deleteMaster(selectedId);
      }
    },
    async deleteMaster(selectedId) {
      try {
        await this.$api.inventoryApi.deleteMaster(selectedId);
        for (let z = 0; z < this.items.length; z++) {
          if (this.items[z].id == selectedId) {
            this.$delete(this.items, z);
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
    editMaster(inventoryFormId) {
      this.$router.push({
        name: "EditInventoryForm",
        params: { id: inventoryFormId },
      });
    },
    goToCreateInventoryForm() {
      this.$router.push({ name: "CreateInventoryForm" });
    },
    onFiltered() {
      this.currentPage = 1;
    },
  },
};
</script>