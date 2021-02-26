<template>
    <div class="container-fluid" >
        <div >
          <div >
            <h3><B>設備盤點項目</B></h3>
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
                      <select class="form-control" v-model="depselected" required>
                        <option v-bind:key="dep.value" v-for="dep in deplist" >{{dep.text}} </option>
                      </select>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="row">
                    <div class="col-sm-1">
                      <label>From:</label>
                    </div>
                    <div class="col-sm-5 ">
                      <el-date-picker
                        v-model="dtpicker1"
                        type="date"
                        :editable="false"
                        style="width:100%"
                        :clearable="false"
                        @change="changeEnd">
                      </el-date-picker>
                  </div>
                  <div class="col-sm-1">
                    <label>To:</label>
                  </div>
                  <div class="col-sm-5 ">
                    <el-date-picker
                      v-model="dtpicker2"
                      type="date"
                      :editable="false"
                      style="width:100%"
                      :picker-options="pickerOptionsEnd"
                      :clearable="false"  >
                    </el-date-picker>
                  </div>
                  </div>
                </div>
                <div>
                  <button type="submit" class="btn btn-primary" style="margin-top: 20px; width: 100%;" >Save</button>
                </div>
                <div>
                  <button type="button" class="btn btn-primary" style="margin-top: 20px; width: 100%;"  @click.prevent="backToinquiry">Cancel</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
</template>

<script>
export default {
  name: 'ModifyTicket',
  data() {
    return {
        udepselected: 'ALL',
        dtpicker1: new Date(),
        dtpicker2: new Date(),
        deplist: [
            { value: 'ALL', text: 'ALL' },
            { value: 'DOI', text: 'DOI' },
            { value: 'DAF', text: 'DAF' },
            { value: 'DRC', text: 'DRC' }
        ]
    }
  },
  props: {
  }
}
</script>