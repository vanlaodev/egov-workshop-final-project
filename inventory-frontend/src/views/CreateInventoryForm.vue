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
      <b-button
        :disabled="saving"
        type="submit"
        variant="primary"
        class="mr-2"
        >{{ $t("save") }}</b-button
      >
      <b-button
        :disabled="saving"
        @click.prevent="backToinquiry"
        variant="secondary"
        >{{ $t("cancel") }}</b-button
      >
    </b-form>
    <message-dialog :ctx="msgDialogCtx"></message-dialog>
  </b-card>
</template>

<script>
import { mapState } from "vuex";
import * as dayjs from "dayjs";
import MessageDialog from "../components/MessageDialog";

export default {
  components: {
    MessageDialog,
  },
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
      saving: false,
      msgDialogCtx: {
        visible: false,
        title: "",
        message: "",
        resolve: null,
      },
    };
  },
  methods: {
    showMsgDialog(message, title) {
      return new Promise((resolve) => {
        this.msgDialogCtx.title = title;
        this.msgDialogCtx.message = message;
        this.msgDialogCtx.resolve = resolve;
        this.msgDialogCtx.visible = true;
      });
    },
    onDtpFromUpdated() {
      if (this.dtpFrom > this.dtpTo) {
        this.dtpTo = this.dtpFrom;
      }
    },
    async savedata() {
      if (this.saving) return;
      try {
        this.saving = true;
        await this.$api.inventoryApi.createMaster({
          deptId: this.depselected,
          fromTime: dayjs(this.dtpFrom).format("YYYY/MM/DD"),
          endTime: dayjs(this.dtpTo).format("YYYY/MM/DD"),
          title: this.title,
          remark: this.remark,
          userName: "test", // TODO: remove this later
        });
        await this.showMsgDialog(this.$t("msg_operationSuccess"));
        this.backToinquiry();
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      } finally {
        this.saving = false;
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
