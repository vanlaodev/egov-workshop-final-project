<template>
  <b-card :title="$t('editInventoryForm')" class="shadow-sm">
    <b-form
      @submit.prevent="savedata"
      v-if="originalMaster && !loading"
      class="mt-3"
    >
      <b-form-group :label="$t('id')" label-for="input-id">
        <b-form-input
          id="input-id"
          :value="originalMaster.id"
          readonly
        ></b-form-input>
      </b-form-group>
      <b-form-group
        :label="$t('department')"
        label-for="input-dept"
        v-if="selectedDept"
      >
        <b-form-input
          id="input-dept"
          :value="selectedDept.text"
          readonly
        ></b-form-input>
      </b-form-group>
      <b-form-group :label="$t('title')" label-for="input-title">
        <b-form-input
          id="input-title"
          v-model="title"
          required
          autofocus
        ></b-form-input>
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
      <b-form-group :label="$t('status')" label-for="select-status">
        <select
          id="select-status"
          class="form-control"
          v-model="selectedStatus"
        >
          <option
            v-bind:key="status.value"
            v-for="status in statusList"
            :value="status.value"
          >
            {{ status.text }}
          </option>
        </select>
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
    <div v-else-if="loading" class="mt-4">
      <strong>{{ $t("loading") }}...</strong>
    </div>
    <message-dialog :ctx="msgDialogCtx"></message-dialog>
  </b-card>
</template>

<script>
import * as dayjs from "dayjs";
import { mapState } from "vuex";
import MessageDialog from "../components/MessageDialog";

export default {
  components: {
    MessageDialog,
  },
  data() {
    return {
      originalMaster: null,
      title: "",
      inventoryid: "",
      dtpFrom: dayjs().format("YYYY-MM-DD"),
      dtpTo: dayjs().format("YYYY-MM-DD"),
      selectedStatus: "",
      remark: "",
      saving: false,
      loading: false,
      msgDialogCtx: {
        visible: false,
        title: "",
        message: "",
        resolve: null,
      },
    };
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
      if (!this.originalMaster) return null;
      return this.deptList.find((d) => d.value == this.originalMaster.deptId);
    },
    statusList() {
      return [
        { value: "ACTIVE", text: this.$t("active") },
        { value: "INVALID", text: this.$t("invalid") },
      ];
    },
  },
  mounted() {
    this.getInventoryMaster();
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
    async getInventoryMaster() {
      const masterId = this.$route.params.id;
      if (masterId) {
        if (this.loading) return;
        try {
          this.loading = true;
          const master = (
            await this.$api.inventoryApi.searchMaster({
              deptId: this.loggedInUser.dept.id,
            })
          ).find((m) => m.id == masterId);
          if (master) {
            this.title = master.title;
            this.remark = master.remark;
            this.selectedStatus = this.statusList.find(
              (d) => d.value == master.status
            ).value;
            this.dtpFrom = dayjs(master.fromTime, "YYYY/MM/DD").format(
              "YYYY-MM-DD"
            );
            this.dtpTo = dayjs(master.endTime, "YYYY/MM/DD").format(
              "YYYY-MM-DD"
            );
            this.originalMaster = master;
          } else {
            await this.showMsgDialog(this.$t("msg_recordNotFound"));
            this.backToinquiry();
          }
        } catch (err) {
          await this.showMsgDialog(err, this.$t("error"));
          this.backToinquiry();
        } finally {
          this.loading = false;
        }
      } else {
        this.backToinquiry();
      }
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
        await this.$api.inventoryApi.updateMaster({
          id: this.originalMaster.id,
          deptId: this.originalMaster.deptId,
          status: this.selectedStatus,
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
};
</script>
