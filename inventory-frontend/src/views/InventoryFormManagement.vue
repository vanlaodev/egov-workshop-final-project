<template>
  <div>
    <div>
      <p></p>
      <input
        type="text"
        v-model="filter_name"
        :placeholder="placeholder_description"
        class="form-control"
      />
      <p></p>
      <b-table
        striped
        hover
        :items="filteredRows"
        :fields="fields"
        :current-page="currentPage"
        :per-page="perPage"
      >
        <template #cell(Action)="data">
          <a
            href="#"
            class="editcolor a-btn-slide-text"
            @click.prevent="editObject(data.item.id)"
          >
            <b-icon icon="pencil-square" class="h3 mb-2"></b-icon> </a
          >&nbsp;
          <a
            href="#"
            class="deletecolor a-btn-slide-text"
            @click.prevent="clickConfirm(data.index, data.item.id)"
          >
            <b-icon icon="trash-fill" class="h3 mb-2"></b-icon>
          </a>
        </template>
      </b-table>

      <b-pagination
        v-model="currentPage"
        :total-rows="totalRows"
        :per-page="perPage"
        size="md"
      ></b-pagination>

      <b-modal ref="delete-modal" id="delete-modal" hide-footer hide-header>
        <div class="d-block text-center delete-title">
          <h3>
            {{ $t("msg_InventoryFormManagement_deleteModal") }}&nbsp;{{
              selectedId
            }}&nbsp;?
          </h3>
        </div>
        <b-button
          class="mt-2"
          variant="outline-success"
          block
          @click="deleteObject"
          >{{ $t("confirm") }}</b-button
        >
        <b-button
          class="mt-3 close-button"
          variant="outline-danger"
          block
          @click="hideModal"
          >{{ $t("cancel") }}</b-button
        >
      </b-modal>
    </div>
  </div>
</template>

<style>
.deletecolor {
  color: rgb(184, 11, 11);
}
.deletecolor:hover,
.deletecolor:active,
.deletecolor:focus {
  color: rgb(184, 11, 11);
}
.editcolor {
  color: rgba(3, 94, 11, 0.76);
}
.editcolor:hover,
.editcolor:active,
.editcolor:focus {
  color: rgba(3, 94, 11, 0.76);
}
#delete-modal.delete-title {
  margin-bottom: 25px;
}
#delete-modal.close-button {
  margin-top: 5px !important;
}
</style>


<script>
// import { BIconXCircle } from 'bootstrap-vue'
//import JSAlert from "js-alert";
export default {
  name: "InventoryFormManagement",
  components: {
    // BIconXCircle,
  },
  data() {
    return {
      fields: [
        {
          key: "id",
          label: `${this.$t("inventoryid")}`,
          sortable: true,
        },
        {
          key: "name",
          label: `${this.$t("item")}`,
          sortable: true,
        },
        {
          key: "from",
          label: `${this.$t("from")}`,
          sortable: true,
        },
        {
          key: "to",
          label: `${this.$t("to")}`,
          sortable: true,
        },
        {
          key: "Action",
          label: `${this.$t("editordelete")}`,
        },
      ],
      items: [
        {
          id: 1,
          name: "關於2021年DOI的盤點項目",
          from: "01/02/2021",
          to: "28/02/2021",
        },
        {
          id: 2,
          name: "關於2021年DAF的盤點項目",
          from: "05/02/2021",
          to: "28/02/2021",
        },
        {
          id: 3,
          name: "關於2021年ABC的盤點項目",
          from: "05/02/2021",
          to: "28/02/2021",
        },
        {
          id: 4,
          name: "關於2021年CDE的盤點項目",
          from: "05/02/2021",
          to: "28/02/2021",
        },
        {
          id: 5,
          name: "關於2021年FGH的盤點項目",
          from: "01/02/2021",
          to: "27/02/2021",
        },
        {
          id: 6,
          name: "關於2021年IJK的盤點項目",
          from: "06/02/2021",
          to: "27/02/2021",
        },
        {
          id: 7,
          name: "關於2021年LMN的盤點項目",
          from: "03/02/2021",
          to: "26/02/2021",
        },
        {
          id: 8,
          name: "關於2021年OPQ的盤點項目",
          from: "01/02/2021",
          to: "28/02/2021",
        },
        {
          id: 9,
          name: "關於2021年RST的盤點項目",
          from: "01/02/2021",
          to: "28/02/2021",
        },
      ],
      totalRows: 1,
      currentPage: 1,
      perPage: 5,
      filter_name: "",
      selectedIndex: 0,
      selectedId: 0,
      placeholder_description: `${this.$t(
        "InventoryFormManagement_placeholder"
      )}`,
    };
  },

  computed: {
    filteredRows: function () {
      var filter_name = this.filter_name.toLowerCase();

      return this.filter_name.trim() !== ""
        ? this.items.filter(function (d) {
            return d.name.toLowerCase().indexOf(filter_name) > -1;
          })
        : this.items;
    },
  },
  mounted() {
    this.totalRows = this.items.length;
  },

  methods: {
    clickConfirm: function (data_index, data_id) {
      this.selectedIndex = data_index;
      this.selectedId = data_id;
      //console.log(this.selectedId);
      this.$refs["delete-modal"].show();
    },
    hideModal() {
      this.$refs["delete-modal"].hide();
    },
    deleteObject() {
      let index = this.selectedIndex;
      for (let z = 0; z < this.items.length; z++)
        if (
          this.items[z].name ==
          this.filteredRows[(this.currentPage - 1) * this.perPage + index].name
        ) {
          this.$delete(this.items, z);
          break;
        }
      this.$refs["delete-modal"].hide();
    },
    editObject: function (inventoryfromid) {
      //this.$router.replace('EditInventoryForm');
      this.$router.push({
        name: "EditInventoryForm",
        params: { id: inventoryfromid },
      });
    },
  },
  props: {},
};
</script>