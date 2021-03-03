<template>
  <div class="inventory-search-container">
      <div class="inventory-search-filter">
          <b-row class="filter-row">
            <b-col class="filter-col" cols="auto">
                <div class="filter-title">搜尋：</div>
            </b-col>
        </b-row>
        <b-row class="filter-row">
            <b-col class="filter-col" cols="3">
                <input v-model="filterNo" placeholder="物品編號">
            </b-col>
            <b-col class="filter-col" cols="3">
                <input v-model="filterUserNo" placeholder="員工編號">
            </b-col>
            <b-col class="filter-col" cols="auto">
                <b-form-select v-model="departmentSelected" :options="departmentOptions" class="department-select"></b-form-select>
            </b-col>
        </b-row>
      </div>
      <div class="inventory-search-list">
        <ul>
          <li>
            <b-row class="inventory-row title">
              <b-col cols="2">
              物品編號
              </b-col>
              <b-col>
              物品名稱
              </b-col>
            </b-row>
          </li>
          <li v-for="inventory in inventoryList" :key="inventory.id">
            <b-row class="inventory-row">
              <b-col class="inventory-col-no" cols="2">
              {{ inventory.itemNo }}
              </b-col>
              <b-col>
              {{ inventory.itemName }}
              </b-col>
              <b-col cols="auto">
                <button class="check-button" v-on:click="clickAdd(inventory.itemNo)" :disabled="inventory.checked">
                  <span>新增</span>
                </button>
              </b-col>
            </b-row>
          </li>
        </ul>
      </div>
  </div>
</template>

<style lang="scss" scoped>
    .inventory-search-container{
        padding: 20px 20px;
        border: 1px solid #D4D4D4;
        margin-top: 30px;
        .filter-title{
            font-size: 16px;
            padding: 0 0 5px 2px;
        }
        input{
            padding: 5px 10px;
        }
        .department-select{
            height: 100%;
        }
    }
</style>

<script>
export default {
  name: "SearchInventory",
  data(){
    return {
        isAdmin: true,
        filterNo: '',
        filterUserNo: '',
        rows: 100,
        perPage: 10,
        currentPage: 1,
        departmentSelected: 0,
        departmentOptions:[
          { value: 0, text: '全部' },
          { value: 1, text: '部門1' },
          { value: 2, text: '部門2' },
          { value: 3, text: '部門3' },
        ],
        inventoryList: []
    }
  },
  methods: {
    clickAdd: function (itemNo) {
        console.log(itemNo);
    },
  },
  mounted(){
    this.inventoryList = [];
    for(let i=1; i<=10; i++){
        this.inventoryList.push({
            id: i,
            itemNo: '734123' + i,
            itemName: '物品' + i
        })
    }
  },
};
</script>