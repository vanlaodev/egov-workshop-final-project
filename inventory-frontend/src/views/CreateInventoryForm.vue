<template>
  <b-card :title="$t('createInventoryForm')" class="shadow-sm">
    <b-form @submit.prevent="savedata" class="mt-3">
      <b-form-group
        :label="$t('department')"
        label-for="input-dept"
        v-if="selectedDept"
        class="required"
      >
        <b-form-input
          id="input-dept"
          :value="selectedDept.text"
          readonly
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group
        :label="$t('title')"
        label-for="input-title"
        class="required"
      >
        <b-form-input
          id="input-title"
          v-model="title"
          required
          autofocus
        ></b-form-input>
      </b-form-group>
      <div class="row">
        <b-form-group :label="$t('from')" label-for="dtp-from" class="col-md-6 required">
          <b-form-datepicker
            id="dtp-from"
            v-model="dtpFrom"
            :min="minDtpFrom"
            @input="onDtpFromUpdated"
          ></b-form-datepicker>
        </b-form-group>
        <b-form-group :label="$t('to')" label-for="dtp-to" class="col-md-6 required">
          <b-form-datepicker
            id="dtp-to"
            v-model="dtpTo"
            :min="minDtpTo"
          ></b-form-datepicker>
        </b-form-group>
      </div>
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
    this.depselected = this.loggedInUser.deptId;
  },
  data() {
    return {
      title: "",
      dtpFrom: dayjs().format("YYYY-MM-DD"),
      dtpTo: dayjs().format("YYYY-MM-DD"),
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
          deptId: this.loggedInUser.deptId,
          fromTime: dayjs(this.dtpFrom).format("YYYY/MM/DD"),
          endTime: dayjs(this.dtpTo).format("YYYY/MM/DD"),
          title: this.title,
          remark: this.remark,
        });
        this.$root.$bvToast.toast(this.$t("msg_operationSuccess"), {
          title: this.$t("message"),
          variant: "success",
          autoHideDelay: 2000,
          solid: true,
        });
        this.backToinquiry();
      } catch (err) {
        await this.showMsgDialog(err, this.$t("error"));
      } finally {
        this.saving = false;
      }
    },
    backToinquiry() {
      this.$router.replace({ name: "InventoryFormManagement" });
    },
  },
  computed: {
    ...mapState(["loggedInUser", "deptList"]),
    minDtpFrom() {
      return dayjs().format("YYYY-MM-DD");
    },
    minDtpTo() {
      return this.dtpFrom;
    },
    selectedDept() {
      return this.deptList.find((d) => d.value == this.loggedInUser.deptId);
    },
  },
};
</script>
