<template>
  <b-card :title="$t('createInventoryForm')">
    <b-form class="mt-4" @submit.prevent="savedata">
      <b-form-group :label="$t('title')" label-for="input-title">
        <b-form-input
          id="input-title"
          v-model="title"
          required
          autofocus
        ></b-form-input>
      </b-form-group>
      <b-form-group :label="$t('department')" label-for="select-dept">
        <select id="select-dept" class="form-control" v-model="depselected">
          <option
            v-bind:key="dep.value"
            v-for="dep in deplist"
            :value="dep.value"
          >
            {{ dep.text }}
          </option>
        </select>
      </b-form-group>
      <b-form-group :label="$t('from')" label-for="dtp-from">
        <b-form-datepicker
          id="dtp-from"
          v-model="dtpFrom"
          :min="minDtpFrom"
          @input="onDtpFromUpdated"
        ></b-form-datepicker>
      </b-form-group>
      <b-form-group :label="$t('to')" label-for="dtp-to">
        <b-form-datepicker
          id="dtp-to"
          v-model="dtpTo"
          :min="minDtpTo"
        ></b-form-datepicker>
      </b-form-group>
      <b-form-group :label="$t('remark')" label-for="input-remark">
        <b-form-textarea id="input-remark" v-model="remark"></b-form-textarea>
      </b-form-group>
      <b-button type="submit" variant="primary" class="mr-2">{{
        $t("save")
      }}</b-button>
      <b-button @click.prevent="backToinquiry" variant="secondary">{{
        $t("cancel")
      }}</b-button>
    </b-form>
  </b-card>
</template>

<script>
import { mapState } from "vuex";
import { showErrorAlert } from "../utils/helpers";
import * as dayjs from "dayjs";

export default {
  mounted() {
    this.depselected = this.loggedInUser.dept.id;
  },
  data() {
    return {
      title: "",
      depselected: "",
      dtpFrom: dayjs().format("YYYY-MM-DD"),
      dtpTo: dayjs().format("YYYY-MM-DD"),
      deplist: [
        { value: "1", text: "DOI" },
        { value: "2", text: "DAF" },
        { value: "3", text: "DRC" },
      ],
      remark: "",
    };
  },
  methods: {
    onDtpFromUpdated() {
      if (this.dtpFrom > this.dtpTo) {
        this.dtpTo = this.dtpFrom;
      }
    },
    async savedata() {
      try {
        await this.$api.inventoryApi.createMaster({
          deptId: this.depselected,
          fromTime: dayjs(this.dtpFrom).format("YYYY/MM/DD"),
          endTime: dayjs(this.dtpTo).format("YYYY/MM/DD"),
          title: this.title,
          remark: this.remark,
          userName: "test", // TODO: remove this later
        });
        this.backToinquiry();
      } catch (err) {
        // TODO: show err dialog
        showErrorAlert(err);
      }
    },
    changeEnd() {
      this.dtpicker2 = this.dtpicker1;
    },
    backToinquiry() {
      this.$router.replace({ name: "InventoryFormManagement" });
    },
  },
  computed: {
    ...mapState(["loggedInUser"]),
    minDtpFrom() {
      return dayjs().format("YYYY-MM-DD");
    },
    minDtpTo() {
      return this.dtpFrom;
    },
  },
};
</script>
