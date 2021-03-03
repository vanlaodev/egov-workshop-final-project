<template>
  <div class="container-fluid">
    <b-card :title="$t('editInventoryForm')">
      <b-form @submit.prevent="savedata">
        <b-form-group :label="`${$t('item')}:`" label-for="input-item">
          <b-form-input id="input-item" v-model="item" required></b-form-input>
        </b-form-group>
        <b-form-group :label="`${$t('department')}:`" label-for="select-dept">
          <select id="select-dept" class="form-control" v-model="depselected">
            <option v-bind:key="dep.value" v-for="dep in deplist" :value="dep.value">{{dep.text}}</option>
          </select>
        </b-form-group>
        <b-form-group :label="`${$t('from')}:`" label-for="dtp-from">
          <b-form-datepicker
            id="dtp-from"
            v-model="dtpFrom"
            :min="minDtpFrom"
            @input="onDtpFromUpdated"
          ></b-form-datepicker>
        </b-form-group>
        <b-form-group :label="`${$t('to')}:`" label-for="dtp-to">
          <b-form-datepicker v-model="dtpTo" :min="minDtpTo"></b-form-datepicker>
        </b-form-group>
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
      item: "",
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
