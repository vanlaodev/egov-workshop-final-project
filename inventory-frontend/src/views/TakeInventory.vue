<template>
  <div class="view-container">
    <header>
      <h1>正在盤單清單</h1>
    </header>
    <div class="content-container">
      <div class="inventory-master-list-container">
        <ul>
          <li
            v-for="inventoryMaster in userInventoryMaster"
            :key="inventoryMaster.id"
          >
            <button
              class="link-button"
              v-on:click="clickMasterLink(inventoryMaster.id)"
            >
              <div class="inventory-master-content">
                <div class="inventory-master-content-name button-row">
                  {{ inventoryMaster.masterName }}
                </div>
                <div class="inventory-master-content-time button-row">
                  {{ inventoryMaster.masterTime }}
                </div>
              </div>
            </button>
          </li>
        </ul>
      </div>
    </div>
  </div>
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
export default {
  data() {
    return {
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
    };
  },
  methods: {
    clickMasterLink: function (id) {
      console.log(id);
      this.$router.push({ name: "TakeInventoryDetail" });
    },
  },
  mounted() {
    /* this.$api.inventoryApi
      .searchMasterByOwner("John")
      .then((data) => console.log(data))
      .catch((err) => console.err(err)); */
  },
};
</script>