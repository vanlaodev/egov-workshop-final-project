<template>
  <div>
    <div>
      <!-- <p></p> -->
      <div class="form-group">
        <div class="row">
          <div class="col-sm-9">
            <b-input-group>
              <b-form-input type="search" v-model="filter" :placeholder='$t("InventoryFormManagement_placeholder")'></b-form-input>
              <b-input-group-append>
                <b-button :disabled="!filter" @click="filter = ''">
                  {{
                  $t("clear")
                  }}
                </b-button>
              </b-input-group-append>
            </b-input-group>
          </div>
          <div class="col-sm-3" style="text-align: right">
            <b-button
              type="button"
              variant="primary"
              class="mr-2"
              @click.prevent="goToCreateInventoryForm"
            >{{ $t("createInventoryForm") }}</b-button>
          </div>
        </div>
      </div>
      <!-- <p></p> -->

      <b-table
        striped
        hover
        :items="items"
        :fields="fields"
        :current-page="currentPage"
        :per-page="perPage"
        :filter="filter"
        @filtered="onFiltered"
      >
        <template #cell(Action)="data">
          <a href="#" class="editcolor a-btn-slide-text" @click.prevent="editObject(data.item.id)">
            <b-icon icon="pencil-square" class="h3 mb-2"></b-icon>
          </a>&nbsp;
          <a
            href="#"
            class="deletecolor a-btn-slide-text"
            @click.prevent="clickDelete(data.index, data.item.id)"
          >
            <b-icon icon="trash-fill" class="h3 mb-2"></b-icon>
          </a>
        </template>
      </b-table>

      <b-pagination v-model="currentPage" :total-rows="totalRows" :per-page="perPage" size="md"></b-pagination>

      <b-modal ref="delete-modal" id="delete-modal" hide-footer hide-header>
        <div class="d-block text-center">
          <h3>
            <b-icon icon="x-circle-fill" style="width: 80px; height: 80px; color: rgb(184, 11, 11)"></b-icon>
          </h3>
        </div>
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
        >{{ $t("confirm") }}</b-button>
        <b-button
          class="mt-3 close-button"
          variant="outline-danger"
          block
          @click="hideModal"
        >{{ $t("cancel") }}</b-button>
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
      items: [
        {
          id: 1,
          name: "關於2021年DOI的盤點項目",
          from: "01/02/2021",
          to: "28/02/2021"
        },
        {
          id: 2,
          name: "關於2021年DAF的盤點項目",
          from: "05/02/2021",
          to: "28/02/2021"
        },
        {
          id: 3,
          name: "關於2021年ABC的盤點項目",
          from: "05/02/2021",
          to: "28/02/2021"
        },
        {
          id: 4,
          name: "關於2021年CDE的盤點項目",
          from: "05/02/2021",
          to: "28/02/2021"
        },
        {
          id: 5,
          name: "關於2021年FGH的盤點項目",
          from: "01/02/2021",
          to: "27/02/2021"
        },
        {
          id: 6,
          name: "關於2021年IJK的盤點項目",
          from: "06/02/2021",
          to: "27/02/2021"
        },
        {
          id: 7,
          name: "關於2021年LMN的盤點項目",
          from: "03/02/2021",
          to: "26/02/2021"
        },
        {
          id: 8,
          name: "關於2021年OPQ的盤點項目",
          from: "01/02/2021",
          to: "28/02/2021"
        },
        {
          id: 9,
          name: "關於2021年RST的盤點項目",
          from: "01/02/2021",
          to: "28/02/2021"
        }
      ],
      totalRows: 1,
      currentPage: 1,
      perPage: 5,
      filter_name: [],
      selectedIndex: 0,
      selectedId: 0,
      filter: null
    };
  },

  computed: {
    fields() {
      return [
        {
          key: "id",
          label: this.$t("inventoryid"),
          sortable: true
        },
        {
          key: "name",
          label: this.$t("item"),
          sortable: true
        },
        {
          key: "from",
          label: this.$t("from"),
          sortable: true
        },
        {
          key: "to",
          label: this.$t("to"),
          sortable: true
        },
        {
          key: "Action",
          label: this.$t("editordelete")
        }
      ];
    }
  },
  mounted() {
    this.totalRows = this.items.length;
    this.filter_name = this.items;
  },

  methods: {
    clickDelete: function(data_index, data_id) {
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
          this.items[z].id ==
          this.filter_name[(this.currentPage - 1) * this.perPage + index].id
        ) {
          this.$delete(this.items, z);
          this.totalRows = this.items.length;
          break;
        }
      this.$refs["delete-modal"].hide();
    },
    editObject: function(inventoryfromid) {
      //this.$router.replace('EditInventoryForm');
      this.$router.push({
        name: "EditInventoryForm",
        params: { id: inventoryfromid }
      });
    },
    goToCreateInventoryForm() {
      this.$router.replace({ name: "CreateInventoryForm" });
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.filter_name = filteredItems;
      this.currentPage = 1;
    }
  },
  props: {}
};
</script>