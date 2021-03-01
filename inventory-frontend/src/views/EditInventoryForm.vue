<template>
  <div class="container-fluid">
    <div>
      <div>
        <h3>
          <B>修改盤點單</B>
        </h3>
        <div style="padding: 20px;">
          <form @submit.prevent="savedata">
            <div class="form-group">
              <div class="row">
                <div class="col-sm-12 col-md-2">
                  <label>項目:</label>
                </div>
                <div class="col-sm-12 col-md">
                  <input type="text" required="required" class="form-control" id="title" />
                </div>
              </div>
            </div>

            <div class="form-group">
              <div class="row">
                <div class="col-sm-12 col-md-2">
                  <label>部門:</label>
                </div>
                <div class="col-sm-12 col-md">
                  <select class="form-control" v-model="depselected">
                    <option
                      v-bind:key="dep.value"
                      v-for="dep in deplist"
                      :value="dep.value"
                    >{{dep.text}}</option>
                  </select>
                </div>
              </div>
            </div>

            <div class="form-group">
              <div class="row">
                <div class="col-sm-1">
                  <label>From:</label>
                </div>
                <div class="col-sm-5">
                  <b-form-datepicker v-model="dtpicker1"></b-form-datepicker>
                </div>

                <div class="col-sm-1">
                  <label>To:</label>
                </div>
                <div class="col-sm-5">
                <b-form-datepicker v-model="dtpicker2"></b-form-datepicker>
                  <!-- <el-date-picker
                      v-model="dtpicker2"
                      type="date"
                      :editable="false"
                      style="width:100%"
                      :picker-options="pickerOptionsEnd"
                      :clearable="false"  >
                  </el-date-picker>-->
                </div>
              </div>
            </div>

            <div>
              <button
                type="submit"
                class="btn btn-primary"
                style="margin-top: 20px; width: 100%;"
              >Save</button>
            </div>
            <div>
              <button
                type="button"
                class="btn btn-primary"
                style="margin-top: 20px; width: 100%;"
                @click.prevent="backToinquiry"
              >Cancel</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>




<script>
export default {
  name: "EditInventoryForm",
  components: {
    // BIconXCircle,
  },
  data() {
    return {
      depselected: "ALL",
      dtpicker1: new Date(),
      dtpicker2: new Date(),
      deplist: [
        { value: "ALL", text: "ALL" },
        { value: "DOI", text: "DOI" },
        { value: "DAF", text: "DAF" },
        { value: "DRC", text: "DRC" }
      ]
    };
  },

  methods: {
    savedata: function() {
      //console.log($("#dtpicker1").datepicker('getDate'));
      //localStorage.setItem('keytitle','');
      this.$router.replace("InventoryFormManagement");
    },

    changeEnd() {
      this.dtpicker2 = this.dtpicker1;
    },

    backToinquiry: function() {
      //localStorage.setItem('keytitle','');
      this.$router.replace("InventoryFormManagement");
    }
  },
  created: function() {
    // if (localStorage.getItem('keytitle')!=null && localStorage.getItem('keytitle')!='') {
    // } else { this.$router.replace('/');}
  },
  mounted() {
    //document.getElementById("title").value = localStorage.getItem('keytitle');
    //$("#dtpicker1").datepicker('update', localStorage.getItem('keyfrom'));
  },
  computed: {
    pickerOptionsEnd() {
      var _this = this;
      return {
        disabledDate(time) {
          let time1 = new Date(_this.dtpicker1);
          time1.setDate(time1.getDate());
          return time.getTime() < time1.getTime();
        }
      };
    }
  }
};
</script>
