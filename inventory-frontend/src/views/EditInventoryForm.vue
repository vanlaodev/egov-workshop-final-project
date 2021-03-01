<template>
  <div class="container-fluid">
    <b-card :title="$t('editInventoryForm')">
      <b-form @submit.prevent="savedata" class="mt-4">
        <div class="form-group">
          <div class="row">
            <div class="col-sm-12 col-md-1">
              <label>{{ $t('item') }}:</label>
            </div>
            <div class="col-sm-12 col-md-11">
              <input type="text" required="required" class="form-control" id="title" />
            </div>
          </div>
        </div>

        <div class="form-group">
          <div class="row">
            <div class="col-sm-12 col-md-1">
              <label>{{ $t('department') }}:</label>
            </div>
            <div class="col-sm-12 col-md-11">
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
            <div class="col-sm-12 col-md-1">
              <label>{{ $t('from') }}:</label>
            </div>
            <div class="col-sm-12 col-md-5">
              <b-form-datepicker v-model="dtpFrom" :min="minDtpFrom" @input="onDtpFromUpdated"></b-form-datepicker>
            </div>

            <div class="col-sm-12 col-md-1">
              <label>{{ $t('to') }}:</label>
            </div>
            <div class="col-sm-12 col-md-5">
              <b-form-datepicker v-model="dtpTo" :min="minDtpTo"></b-form-datepicker>
            </div>
          </div>
        </div>

        <b-button type="submit" variant="primary" class="mr-2">{{ $t('save') }}</b-button>
        <b-button @click.prevent="backToinquiry" variant="secondary">{{ $t('cancel') }}</b-button>
      </b-form>
    </b-card>
  </div>
</template>

<script>
import * as dayjs from "dayjs";

export default {
  data() {
    return {
      depselected: "ALL",
      dtpFrom: dayjs().format("YYYY-MM-DD"),
      dtpTo: dayjs().format("YYYY-MM-DD"),
      deplist: [
        { value: "ALL", text: "ALL" },
        { value: "DOI", text: "DOI" },
        { value: "DAF", text: "DAF" },
        { value: "DRC", text: "DRC" }
      ]
    };
  },
  methods: {
    onDtpFromUpdated() {
      if (this.dtpFrom > this.dtpTo) {
        this.dtpTo = this.dtpFrom;
      }
    },
    savedata() {
      this.$router.replace({ name: "InventoryFormManagement" });
    },
    changeEnd() {
      this.dtpicker2 = this.dtpicker1;
    },
    backToinquiry() {
      this.$router.replace({ name: "InventoryFormManagement" });
    }
  },
  computed: {
    minDtpFrom() {
      return dayjs().format("YYYY-MM-DD");
    },
    minDtpTo() {
      return this.dtpFrom;
    }
  }
};
</script>
