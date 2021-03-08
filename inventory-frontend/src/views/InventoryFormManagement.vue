<template>
  <b-card :title="$t('inventoryFormManagement')">
    <b-form inline class="d-flex flex-row flex-nowrap align-items-center">
      <b-input-group class="flex-grow-1 my-3">
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
      <a href="#" @click.prevent="goToCreateInventoryForm">
        <b-icon
          variant="success"
          icon="plus-circle"
          class="h2 m-0 ml-3"
        ></b-icon>
      </a>
      <!--       <a href="#" @click.prevent="loadInventoryMasters">
        <b-icon
          variant="primary"
          icon="arrow-clockwise"
          :animation="loadingTable ? 'spin' : ''"
          class="h2 m-0 ml-2"
        ></b-icon>
      </a> -->
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
        <b-button
          variant="secondary"
          size="sm"
          @click="editMaster(data.item.id)"
          class="mr-2"
        >
          <b-icon icon="pencil-fill" aria-hidden="true"></b-icon>
          {{ $t("edit") }}
        </b-button>
        <b-button
          variant="danger"
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

    <b-modal ref="delete-modal" id="delete-modal" hide-footer hide-header>
      <div class="d-block text-center">
        <h3>
          <b-icon
            variant="danger"
            icon="x-circle-fill"
            style="width: 80px; height: 80px"
          ></b-icon>
        </h3>
      </div>
      <div class="d-block text-center delete-title">
        <h3>
          {{ $t("fmt_msg_confirmDeleteInventoryMaster", { id: selectedId }) }}
        </h3>
      </div>
      <b-button
        class="mt-3"
        variant="outline-success"
        block
        @click="deleteMaster"
        >{{ $t("confirm") }}</b-button
      >
      <b-button
        class="mt-2 close-button"
        variant="outline-danger"
        block
        @click="hideModal"
        >{{ $t("cancel") }}</b-button
      >
    </b-modal>
  </b-card>
</template>

<script>
import { showErrorAlert } from "../utils/helpers";
import { mapState } from "vuex";

export default {
  name: "InventoryFormManagement",
  components: {},
  data() {
    return {
      items: [],
      currentPage: 1,
      perPage: 10,
      selectedId: 0,
      filter: null,
      loadingTable: false,
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
    async loadInventoryMasters() {
      if (this.loadingTable) return;
      try {
        this.loadingTable = true;
        const masters = await this.$api.inventoryApi.searchMaster({
          deptId: this.loggedInUser.dept.id,
        });
        this.items = masters
          .map((m) => {
            let status;
            switch (m.status) {
              case "ACTIVE":
                status = this.$t("active");
                break;
              case "INACTIVE":
                status = this.$t("inactive");
                break;
              default:
                status = m.status;
                break;
            }
            return {
              id: m.id,
              name: m.title,
              from: m.fromTime,
              to: m.endTime,
              status: status,
            };
          })
          .sort((m1, m2) => m2.id - m1.id);
        this.currentPage = 1;
      } catch (err) {
        // TODO: show err dialog
        showErrorAlert(err);
      } finally {
        this.loadingTable = false;
      }
    },
    showConfirmDeleteMasterModal: function (data_id) {
      this.selectedId = data_id;
      this.$refs["delete-modal"].show();
    },
    hideModal() {
      this.$refs["delete-modal"].hide();
    },
    async deleteMaster() {
      try {
        await this.$api.inventoryApi.deleteMaster(this.selectedId);
        for (let z = 0; z < this.items.length; z++)
          if (this.items[z].id == this.selectedId) {
            this.$delete(this.items, z);
            break;
          }
      } catch (err) {
        // TODO: show err dialog
        showErrorAlert(err);
      } finally {
        this.$refs["delete-modal"].hide();
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
  props: {},
};
</script>