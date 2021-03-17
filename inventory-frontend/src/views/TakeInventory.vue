<template>
  <b-card title="正在盤單清單" class="shadow-sm">
    <div class="view-container" v-if="!loading">
      <div class="content-container">
        <div class="inventory-master-list-container">
          <ul v-if="masters && masters.length > 0">
            <li v-for="master in masters" :key="master.id">
              <button class="link-button" v-on:click="clickMasterLink(master)">
                <div class="inventory-master-content">
                  <div class="inventory-master-content-name button-row">
                    {{ master.title }}
                  </div>
                  <div class="inventory-master-content-time button-row">
                    {{ master.fromTime.format("DD/MM/YYYY") }} -
                    {{ master.endTime.format("DD/MM/YYYY") }}
                  </div>
                </div>
              </button>
            </li>
          </ul>
          <h5 v-else>當前沒有與您相關的盤點工作</h5>
        </div>
      </div>
    </div>
    <div v-else>{{ $t("loading") }}...</div>
    <message-dialog :ctx="msgDialogCtx"></message-dialog>
  </b-card>
</template>

<style lang="scss" scoped>
.view-container {
  h1 {
    font-size: 2rem;
  }
  .content-container {
    padding: 10px 5px;
    .inventory-master-list-container {
      padding: 20px 20px;
      margin-top: 30px;
      ul {
        list-style-type: none;
        margin: 0;
        li {
          display: block;
          text-align: center;
          padding: 10px 0;
        }
      }
      .link-button {
        background: #eeeeee;
        border: 1px solid #c5c5c5;
        padding: 5px 10px;
        box-shadow: 2px 2px 3px 1px rgba(0, 0, 0, 0.1);
        padding: 20px 20%;
        .inventory-master-content {
          .button-row {
            padding: 5px 0;
            font-size: 1.4rem;
          }
          .inventory-master-content-time {
            font-size: 1.2rem;
            color: #e83981;
          }
        }
      }
    }
  }
}
#confirm-modal {
  .confirm-title {
    margin-bottom: 25px;
  }
  .close-button {
    margin-top: 5px !important;
  }
}
</style>

<script>
import { mapState } from "vuex";
import MessageDialog from "../components/MessageDialog";

export default {
  components: {
    MessageDialog,
  },
  data() {
    return {
      loading: false,
      userInventoryMaster: [
        {
          id: 1,
          masterName: "2021年第一季盤點",
          masterTime: "2021-03-01 至 2021-03-15",
        },
        {
          id: 2,
          masterName: "2020年第四季盤點",
          masterTime: "2020-12-01 至 2020-12-31",
        },
      ],
      msgDialogCtx: {
        visible: false,
        title: "",
        message: "",
        resolve: null,
      },
      masters: [],
    };
  },
  computed: {
    ...mapState(["loggedInUser"]),
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
    clickMasterLink(master) {
      this.$router.push({
        name: "TakeInventoryDetail",
        params: { id: master.id },
      });
    },
    async loadMasters() {
      if (this.loading) return;
      try {
        this.loading = true;
        const masters = await this.$api.inventoryApi.searchMasterByOwner(
          this.loggedInUser.username
        );
        this.masters = !masters ? [] : masters.filter((x) => x.canTakeInventory());
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      } finally {
        this.loading = false;
      }
    },
  },
  mounted() {
    this.loadMasters();
  },
};
</script>