<template>
  <b-modal
    :title="modalTitle"
    v-model="ctx.visible"
    :ok-title="$t('confirm')"
    :cancel-title="$t('cancel')"
    @ok="onConfirm"
    @change="onChange"
  >
    <p class="my-1">{{ ctx.message }}</p>
  </b-modal>
</template>

<script>
export default {
  name: "ConfirmDialog",
  props: ["ctx"],
  computed: {
    modalTitle() {
      return this.ctx.title != null && this.ctx.title.length > 0
        ? this.ctx.title
        : this.$t("confirmation");
    },
  },
  methods: {
    onChange(isVisible) {
      if (this.ctx.resolve && !isVisible) {
        this.ctx.resolve(false);
      }
    },
    onConfirm() {
      this.ctx.resolve(true);
    },
  },
};
</script>