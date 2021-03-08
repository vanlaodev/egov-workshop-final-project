<template>
  <div>
    <div class="form-group">
      <div class="row">
        <div class="col-sm-11">
          <b-input-group>
            <template #prepend>
              <b-input-group-text><b-icon icon="search" /></b-input-group-text>
            </template>
            <b-form-input
              type="search"
              v-model="filter"
              :placeholder="$t('search')"
            ></b-form-input>
            <b-input-group-append v-if="filter">
              <b-button @click="filter = ''">
                {{ $t("clear") }}
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </div>
        <div class="col-sm-1" style="text-align: center">
          <a
            href="#"
            class="editcolor a-btn-slide-text"
            @click.prevent="goToCreateInventoryForm"
          >
            <b-icon icon="plus-circle" class="h1"></b-icon>
          </a>
        </div>
      </div>
    </div>

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
        <a
          href="#"
          class="editcolor a-btn-slide-text"
          @click.prevent="editObject(data.item.id)"
        >
          <b-icon icon="pencil-square" class="h3 mr-2"></b-icon> </a
        >&nbsp;
        <a
          href="#"
          class="deletecolor a-btn-slide-text"
          @click.prevent="clickDelete(data.item.id)"
        >
          <b-icon icon="trash-fill" class="h3"></b-icon>
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
      <div class="d-block text-center">
        <h3>
          <b-icon
            icon="x-circle-fill"
            style="width: 80px; height: 80px; color: rgb(184, 11, 11)"
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
        @click="deleteObject"
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
  margin-top: 5px;
}
</style>

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
    this.loadInventoryMaster();
  },

  methods: {
    async loadInventoryMaster() {
      try {
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
      } catch (err) {
        // TODO: show err dialog
        showErrorAlert(err);
      }
    },
    async deleteInventoryMaster(id) {
      try {
        await this.$api.inventoryApi.deleteMaster(id);
      } catch (err) {
        // TODO: show err dialog
        showErrorAlert(err);
      }
    },
    clickDelete: function (data_id) {
      this.selectedId = data_id;
      this.$refs["delete-modal"].show();
    },
    hideModal() {
      this.$refs["delete-modal"].hide();
    },
    async deleteObject() {
      try {
        await this.deleteInventoryMaster(this.selectedId);
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
    editObject: function (inventoryfromid) {
      //this.$router.replace('EditInventoryForm');
      this.$router.push({
        name: "EditInventoryForm",
        params: { id: inventoryfromid },
      });
    },
    goToCreateInventoryForm() {
      this.$router.replace({ name: "CreateInventoryForm" });
    },
    onFiltered() {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.currentPage = 1;
    },
  },
  props: {},
};
</script>