<template>
  <b-card :title="$t('logs')" class="shadow-sm">
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
    </b-form>

    <b-table
      v-if="!loading"
      responsive
      :items="items"
      :fields="fields"
      :current-page="currentPage"
      :per-page="perPage"
      :filter="filter"
      @filtered="onFiltered"
      striped
      small
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
    >
      <template #cell(actions)="row">
        <b-button @click="row.toggleDetails" size="sm">{{
          row.detailsShowing ? $t("hideDetails") : $t("showDetails")
        }}</b-button>
      </template>
      <template #row-details="row">
        <b-table-simple small responsive
          ><b-thead head-variant="dark">
            <b-tr>
              <b-th>{{ $t("input") }}</b-th>
              <b-th>{{ $t("output") }}</b-th>
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr>
              <b-td
                ><code>
                  <pre>{{ row.item.input }}</pre>
                </code></b-td
              >
              <b-td
                ><code>
                  <pre>{{ row.item.output }}</pre>
                </code></b-td
              >
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </template>
    </b-table>
    <b-skeleton-table
      v-else
      :rows="4"
      :columns="5"
      :table-props="{ bordered: false }"
    ></b-skeleton-table>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalRows"
      :per-page="perPage"
      size="md"
      class="mb-0"
    ></b-pagination>
    <message-dialog :ctx="msgDialogCtx"></message-dialog>
  </b-card>
</template>

<script>
import * as dayjs from "dayjs";
import MessageDialog from "../components/MessageDialog";

export default {
  components: {
    MessageDialog,
  },
  data() {
    return {
      searchMasterId: null,
      searchMasterTitle: null,
      searchStartDate: null,
      searchEndDate: null,
      items: [],
      loading: false,
      msgDialogCtx: {
        visible: false,
        title: "",
        message: "",
        resolve: null,
      },
      currentPage: 1,
      perPage: 10,
      filter: null,
      sortBy: "createDate",
      sortDesc: true,
    };
  },
  computed: {
    totalRows() {
      return this.items.length;
    },
    fields() {
      return [
        {
          key: "createDate",
          label: this.$t("time"),
          sortable: true,
        },
        {
          key: "inventoryForm",
          label: this.$t("inventoryForm"),
          sortable: true,
          formatter: (value, key, item) => {
            return item.masterTitle
              ? `${item.masterId} (${item.masterTitle})`
              : item.masterId;
          },
        },
        {
          key: "functionName",
          label: this.$t("action"),
          sortable: true,
        },
        {
          key: "userName",
          label: this.$t("user"),
          sortable: true,
        },
        {
          key: "actions",
          label: this.$t("actions"),
        },
      ];
    },
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
    async searchLogs() {
      if (this.loading) return;
      try {
        this.loading = true;
        this.items = await this.$api.inventoryApi.searchLog({
          masterId: this.searchMasterId,
          masterTitle: this.searchMasterTitle,
          startDate: dayjs(this.searchStartDate).format("YYYY/MM/DD"),
          endDate: dayjs(this.searchEndDate).format("YYYY/MM/DD"),
        });
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      } finally {
        this.loading = false;
      }
    },
    onFiltered() {
      this.currentPage = 1;
    },
  },
  mounted() {
    this.searchLogs();
  },
};
</script>