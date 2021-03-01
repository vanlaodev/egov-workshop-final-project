<template>
  <div class="view-container">
    <header>
      <h1>{{invertoryFormTitle}}</h1>
    </header>
    <div class="content-container">
      <div class="inventory-form-time">
        {{invertoryFormTime}}
      </div>
      <div class="inventory-form-des">
        {{invertoryFormDes}}
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
              <b-col cols="auto">
                <button class="check-button" v-on:click="clickConfirm(inventory.itemNo)" :disabled="inventory.checked">
                  <span v-if="inventory.checked">已確認</span>
                  <span v-else>確認</span>
                </button>
              </b-col>
            </b-row>
          </li>
        </ul>
      </div>
    </div>
    <b-modal ref="confirm-modal" id="confirm-modal" hide-footer hide-header>
      <div class="d-block text-center confirm-title">
        <h3>是否確定擁有此物品？</h3>
      </div>
      <b-button class="mt-2" variant="outline-success" block @click="confirm">確定</b-button>
      <b-button class="mt-3 close-button" variant="outline-danger" block @click="hideModal">取消</b-button>
    </b-modal>
  </div>
</template>

<style lang="scss" scoped>
.view-container{
  padding: 30px 10px;
  h1{
    font-size: 2rem;
  }
  .content-container{
    padding: 10px 5px;
    .inventory-form-des{
      font-size: 1.2rem;
    }
    .inventory-form-time{
      color: #E83981;
      font-weight: 600;
    }
    .inventory-list-container{
      padding: 20px 20px;
      border: 1px solid #D4D4D4;
      margin-top: 30px;
      ul{
        list-style-type: none;
        margin: 0;
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
        .inventory-col-no{
          font-weight: 600;
        }
        &.title{
          background-color: #EDEDED;
        }
      }
      .check-button{
        background: #EEEEEE;
        border: 1px solid #C5C5C5;
        padding: 5px 10px;
        box-shadow: 2px 2px 3px 1px rgba(0,0,0,0.1);
        &:disabled{
          background-color: #087E15;
          color: white;
        }
      }
    }
  }
}
#confirm-modal{
  .confirm-title{
    margin-bottom: 25px;
  }
  .close-button{
    margin-top: 5px !important;
  }
}
</style>

<script>
import lodash from 'lodash'

export default {
  data(){
    return {
      invertoryFormTitle: '2021年第一季盤點',
      invertoryFormTime: '2021-03-01 至 2021-03-15',
      invertoryFormDes: '周一是南韓1919年三一獨立運動102周年，總統文在寅在首爾的紀念活動發表講話，談及韓日及兩韓關係。他表示，今年夏季的日本東京奧運會可為南韓、北韓、美國、日本提供對話契機，又指韓方準備好隨時近年因二戰慰安婦問題而交惡的日本對話。',
      selectedItemNo: null,
      userInventory: [
        {
          id: 1,
          itemNo: '734123',
          itemName: '物品1',
          checked: false,
        },
        {
          id: 2,
          itemNo: '264642',
          itemName: '物品2',
          checked: false,
        },
        {
          id: 3,
          itemNo: '643634',
          itemName: '物品3',
          checked: false,
        }
      ]
    }
  },
  methods: {
    clickConfirm: function (itemNo) {
      this.selectedItemNo = itemNo;
      this.$refs['confirm-modal'].show(itemNo)
    },
    hideModal() {
      this.$refs['confirm-modal'].hide()
    },
    confirm() {
      let selectedItem = lodash.find(this.userInventory, {itemNo: this.selectedItemNo});
      selectedItem.checked = true;
      this.$refs['confirm-modal'].hide()
    }
  },
  mounted(){
    this.$root.$on('bv::modal::hide', (bvEvent, modalId) => {
      if(modalId === 'confirm-modal'){
        this.selectedItemNo = null;
      }
    })
  }
};
</script>