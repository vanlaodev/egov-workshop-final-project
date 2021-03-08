<template>
  <div class="inventory-search-container">
      <div class="inventory-search-filter">
          <b-row class="filter-row">
            <b-col class="filter-col" cols="auto">
                <div class="filter-title">搜尋：</div>
            </b-col>
        </b-row>
        <b-row class="filter-row">
            <b-col class="filter-col" cols="4">
                <input v-model="filterNo" placeholder="物品編號">
            </b-col>
            <b-col class="filter-col" cols="4">
                <input v-model="filterUserNo" placeholder="員工編號">
            </b-col>
            <b-col class="filter-col" cols="auto">
                <b-form-select v-model="departmentSelected" :options="departmentOptions" class="department-select"></b-form-select>
            </b-col>
        </b-row>
      </div>
      <div class="inventory-search-list">
        <div class="no-find-item" v-if="inventoryList.length == 0">
            沒有找到物品
        </div>
        <ul v-if="inventoryList.length > 0">
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
                <button class="choose-button" v-on:click="clickAdd(inventory.itemNo)" :disabled="inventory.checked">
                  <span>新增</span>
                </button>
              </b-col>
            </b-row>
          </li>
        </ul>
        <div v-if="inventoryList.length > 0" class="inventory-pagination-container">
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
      <b-modal ref="search-confirm-modal" id="search-confirm-modal" hide-footer hide-header>
        <div class="d-block text-center search-confirm-title">
            <h3>是否確定新增?</h3>
        </div>
        <b-button class="mt-2" variant="outline-success" block @click="confirm">確定</b-button>
        <b-button class="mt-3 close-button" variant="outline-danger" block @click="hideModal">取消</b-button>
      </b-modal>
  </div>
</template>

<style lang="scss" scoped>
    .inventory-search-container{
        padding: 20px 20px;
        border: 1px solid #D4D4D4;
        margin-top: 20px;
        .filter-title{
            font-size: 16px;
            padding: 0 0 5px 2px;
        }
        .filter-col{
            padding-right: 5px;
            &:nth-child(n+2){
                padding-left: 5px;
            }
        }
        input{
            padding: 5px 10px;
            width: 100%;
        }
        .department-select{
            height: 100%;
        }
        .no-find-item{
            width: 100%;
            text-align: center;
            padding: 20px 0;
        }
        .inventory-search-list{
            border: 1px solid #D4D4D4;
            margin-top: 20px;
            ul{
            list-style-type: none;
            margin: 0;
            padding: 0;
                li{
                    display: block;
                    .row{
                        margin: 0;
                    }
                    &:last-child{
                        .inventory-row{
                            border-bottom: 0;
                        }
                    }
                }
            }
            .inventory-row{
                padding: 10px 10px;
                align-items: center;
                justify-content: center;
                border-bottom: 1px solid #D4D4D4;
                text-align: left;
                .col-2:first-child{
                    min-width: 100px;
                }
                .inventory-col-no{
                    font-weight: 600;
                }
                &.title{
                    background-color: #EDEDED;
                }
            }
            .choose-button{
                background: #EEEEEE;
                border: 1px solid #C5C5C5;
                padding: 5px 10px;
                box-shadow: 2px 2px 3px 1px rgba(0,0,0,0.1);
                margin-left: 10px;
                &:disabled{
                    background-color: #087E15;
                    color: white;
                }
            }
            .inventory-pagination-container{
                margin-top: 20px;
                width: 100%;
                text-align: center;
                padding: 10px;
            }
        }
    }
    #search-confirm-modal{
        .search-confirm-title{
            margin-bottom: 25px;
        }
        .close-button{
            margin-top: 5px !important;
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
        currentItem: null,
        rows: 100,
        perPage: 5,
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
    pageCallback() {
        console.log(this.currentPage);
    },
    clickAdd: function (itemNo) {
        this.$refs['search-confirm-modal'].show()
        this.currentItem = itemNo;
    },
    confirm() {
        this.$emit('clickAdd', this.currentItem);
    },
    hideModal() {
      this.$refs['search-confirm-modal'].hide();
    },
  },
  mounted(){
    this.inventoryList = [];
    for(let i=1; i<=this.perPage; i++){
        this.inventoryList.push({
            id: i,
            itemNo: '734123' + i,
            itemName: '物品' + i
        })
    }
    this.$refs['search-confirm-modal'].$root.$off('bv::modal::hide');
    this.$refs['search-confirm-modal'].$root.$on('bv::modal::hide', (bvEvent, modalId) => {
      if(modalId === 'search-confirm-modal'){
        this.currentItem  = null;
      }
    })
  }
};
</script>