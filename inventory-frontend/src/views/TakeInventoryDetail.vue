<template>
  <div class="view-container">
    <header>
      <h1>{{invertoryFormTitle}}</h1>
      <button v-if="isAdmin" class="add-button" v-on:click="clickAddItem()"><b-icon icon="plus" variant="success"></b-icon></button>
    </header>
    <div class="content-container">
      <div class="inventory-form-time">
        {{invertoryFormTime}}
      </div>
      <div class="inventory-form-des">
        {{invertoryFormDes}}
      </div>
      <div v-if="isAdmin" class="inventory-filter-container">
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
      <div class="inventory-list-container">
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
          <li v-for="inventory in userInventory" :key="inventory.id">
            <b-row class="inventory-row">
              <b-col class="inventory-col-no" cols="2">
              {{ inventory.itemNo }}
              </b-col>
              <b-col>
              {{ inventory.itemName }}
              </b-col>
              <b-col class="action-button-col" md="auto" sm="12">
                <button class="check-button" v-on:click="clickConfirm(inventory.itemNo)" :disabled="inventory.checked">
                  <span v-if="inventory.checked">已確認</span>
                  <span v-else>確認</span>
                </button>
                <button v-if="isAdmin" class="delete-button" v-on:click="clickDelete(inventory.itemNo)">
                  <span>刪除</span>
                </button>
              </b-col>
            </b-row>
          </li>
        </ul>
        <div class="inventory-pagination-container">
            <b-pagination
                v-model="currentPage"
                :total-rows="rows"
                :per-page="perPage"
                class="inventory-pagination"
                @change="pageCallback"
                first-number
                last-number
                >
            </b-pagination>
        </div>
      </div>
    </div>
    <b-modal ref="confirm-modal" id="confirm-modal" hide-footer hide-header>
      <div class="d-block text-center confirm-title">
        <h3>是否確定擁有此物品？</h3>
      </div>
      <b-button class="mt-2" variant="outline-success" block @click="confirm">確定</b-button>
      <b-button class="mt-3 close-button" variant="outline-danger" block @click="hideModal">取消</b-button>
    </b-modal>
    <b-modal ref="delete-modal" id="delete-modal" hide-footer hide-header>
      <div class="d-block text-center delete-title">
        <h3>是否確定刪除此物品？</h3>
      </div>
      <b-button class="mt-2" variant="outline-success" block @click="deleteItem">確定</b-button>
      <b-button class="mt-3 close-button" variant="outline-danger" block @click="hideModal">取消</b-button>
    </b-modal>
    <b-modal ref="search-modal" id="search-modal" size="lg" hide-footer hide-header>
      <div class="d-block text-center search-title">
        <h3>新增物品</h3>
      </div>
      <search-inventory v-on:clickAdd="addItem"></search-inventory>   
      <b-button class="mt-3 search close-button" variant="outline-danger" block @click="hideModal">取消</b-button>
    </b-modal>
  </div>
</template>

<style lang="scss" scoped>
    @import '../assets/scss/take-inventory-detail.scss';
</style>

<script>
import lodash from 'lodash';
import SearchInventory from "../components/SearchInventory";

export default {
  data(){
    return {
        isAdmin: true,
        filterNo: '',
        filterUserNo: '',
        rows: 100,
        perPage: 10,
        currentPage: 1,
        invertoryFormTitle: '2021年第一季盤點',
        invertoryFormTime: '2021-03-01 至 2021-03-15',
        invertoryFormDes: '周一是南韓1919年三一獨立運動102周年，總統文在寅在首爾的紀念活動發表講話，談及韓日及兩韓關係。他表示，今年夏季的日本東京奧運會可為南韓、北韓、美國、日本提供對話契機，又指韓方準備好隨時近年因二戰慰安婦問題而交惡的日本對話。',
        selectedItemNo: null,
        departmentSelected: 0,
        departmentOptions:[
          { value: 0, text: '全部' },
          { value: 1, text: '部門1' },
          { value: 2, text: '部門2' },
          { value: 3, text: '部門3' },
        ],
        userInventory: []
    }
  },
  methods: {
    clickAddItem: function () {
      this.$refs['search-modal'].show()
    },
    clickConfirm: function (itemNo) {
      this.selectedItemNo = itemNo;
      this.$refs['confirm-modal'].show(itemNo)
    },
    clickDelete: function (itemNo) {
      this.selectedItemNo = itemNo;
      this.$refs['delete-modal'].show(itemNo)
    },
    hideModal() {
      this.$refs['confirm-modal'].hide();
      this.$refs['delete-modal'].hide();
      this.$refs['search-modal'].hide();
    },
    confirm() {
      let selectedItem = lodash.find(this.userInventory, {itemNo: this.selectedItemNo});
      selectedItem.checked = true;
      this.$refs['confirm-modal'].hide()
    },
    deleteItem() {
        let selectNo = this.selectedItemNo;
        this.userInventory = lodash.remove(this.userInventory, function(invertory) {
            return invertory.itemNo != selectNo;
        });
        this.$refs['delete-modal'].hide()
    },
    pageCallback() {
        console.log(this.currentPage);
    },
    addItem(itemNo) {
        console.log('addItem', itemNo);
        this.$refs['search-modal'].hide();
    }
  },
  mounted(){
    this.userInventory = [];
    for(let i=1; i<=10; i++){
        this.userInventory.push({
            id: i,
            itemNo: '734123' + i,
            itemName: '物品' + i,
            checked: false,
        })
    }
    this.$root.$on('bv::modal::hide', (bvEvent, modalId) => {
      if(modalId === 'confirm-modal'){
        this.selectedItemNo = null;
      }
    })
  },
  components: {
    SearchInventory
  },
};
</script>