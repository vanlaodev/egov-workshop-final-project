<template>
  <div class="container-fluid">
    <b-card :title="$t('editInventoryForm')" v-if="originalMaster">
      <b-form @submit.prevent="savedata">
        <b-form-group
          :label="`${$t('inventoryid')}:`"
          label-for="input-inventoryid"
        >
          <b-form-input
            id="input-inventoryid"
            :value="originalMaster.id"
            readonly
          ></b-form-input>
        </b-form-group>
        <b-form-group :label="`${$t('item')}:`" label-for="input-item">
          <b-form-input id="input-item" v-model="title" required></b-form-input>
        </b-form-group>
        <b-form-group :label="`${$t('department')}:`" label-for="select-dept">
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
        <b-form-group :label="`${$t('from')}:`" label-for="dtp-from">
          <b-form-datepicker
            id="dtp-from"
            v-model="dtpFrom"
            :min="minDtpFrom"
            @input="onDtpFromUpdated"
          ></b-form-datepicker>
        </b-form-group>
        <b-form-group :label="`${$t('to')}:`" label-for="dtp-to">
          <b-form-datepicker
            id="dtp-to"
            v-model="dtpTo"
            :min="minDtpTo"
          ></b-form-datepicker>
        </b-form-group>
        <b-form-group :label="`${$t('status')}:`" label-for="select-status">
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
        <b-form-group :label="`${$t('remark')}:`" label-for="input-remark">
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
  </div>
</template>

<script>
import { showErrorAlert } from "../utils/helpers";
import * as dayjs from "dayjs";
import { mapState } from "vuex";

export default {
  data() {
    return {
      originalMaster: null,
      title: "",
      inventoryid: "",
      depselected: "",
      dtpFrom: dayjs().format("YYYY-MM-DD"),
      dtpTo: dayjs().format("YYYY-MM-DD"),
      deplist: [
        { value: "1", text: "DOI" },
        { value: "2", text: "DAF" },
        { value: "3", text: "DRC" },
      ],
      selectedStatus: "",
      statusList: [{ value: "ACTIVE", text: this.$t("active") }],
      remark: "",
    };
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
  mounted() {
    this.getInventoryMaster();
  },
  methods: {
    async getInventoryMaster() {
      const masterId = this.$route.params.id;
      if (masterId) {
        try {
          const master = (
            await this.$api.inventoryApi.searchMaster({
              deptId: this.loggedInUser.dept.id,
            })
          ).find((m) => m.id == masterId);
          if (master) {
            this.title = master.title;
            this.remark = master.remark;
            this.depselected = this.deplist.find(
              (d) => d.value == master.deptId
            ).value;
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
            showErrorAlert(this.$t("msg_recordNotFound"));
            this.backToinquiry();
          }
        } catch (err) {
          showErrorAlert(err);
          this.backToinquiry();
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
      try {
        await this.$api.inventoryApi.updateMaster({
          id: this.originalMaster.id,
          deptId: this.depselected,
          status: this.selectedStatus,
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
};
</script>
