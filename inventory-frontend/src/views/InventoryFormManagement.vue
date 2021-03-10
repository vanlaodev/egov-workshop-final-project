<template>
  <b-card :title="$t('inventoryFormManagement')" class="shadow-sm">
    <b-form inline class="d-flex flex-row flex-nowrap align-items-center my-3">
      <b-input-group class="flex-grow-1">
        <template #prepend>
          <b-input-group-text><b-icon icon="search" /></b-input-group-text>
        </template>
        <b-form-input
          type="search"
          v-model="filter"
          :placeholder="$t('search')"
          autofocus
        ></b-form-input>
        <b-input-group-append v-if="filter">
          <b-button @click="filter = ''">
            {{ $t("clear") }}
          </b-button>
        </b-input-group-append>
      </b-input-group>
      <a href="#" @click.prevent="goToCreateInventoryForm" class="ml-3">
        <b-icon variant="success" icon="plus-circle" class="h2 m-0"></b-icon>
      </a>
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
    >
      <template #cell(Action)="data">
        <b-button variant="link" size="sm" @click="editMaster(data.item.id)">
          <b-icon icon="pencil-fill" aria-hidden="true"></b-icon>
          {{ $t("edit") }}
        </b-button>
        <b-button
          variant="link"
          size="sm"
          @click="showConfirmDeleteMasterModal(data.item.id)"
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
      :table-props="{ bordered: true }"
    ></b-skeleton-table>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalRows"
      :per-page="perPage"
      size="md"
      class="mb-0"
    ></b-pagination>
    <message-dialog :ctx="msgDialogCtx"></message-dialog>
    <confirm-dialog :ctx="confirmDialogCtx"></confirm-dialog>
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
          key: "name",
          label: this.$t("title"),
          sortable: true,
        },
        {
          key: "from",
          label: this.$t("from"),
          sortable: true,
        },
        {
          key: "to",
          label: this.$t("to"),
          sortable: true,
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
          key: "Action",
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
          deptId: this.loggedInUser.dept.id,
        });
        this.items = masters
          .map((m) => {
            return {
              id: m.id,
              name: m.title,
              from: m.fromTime,
              to: m.endTime,
              status: m.status,
            };
          })
          .sort((m1, m2) => m2.id - m1.id);
        this.currentPage = 1;
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      } finally {
        this.loadingTable = false;
      }
    },
    async showConfirmDeleteMasterModal(selectedId) {
      const confirmed = await this.showConfirmDialog(
        this.$t("fmt_msg_confirmDeleteInventoryMaster", { id: selectedId })
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
    editMaster(inventoryfromid) {
      this.$router.push({
        name: "EditInventoryForm",
        params: { id: inventoryfromid },
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