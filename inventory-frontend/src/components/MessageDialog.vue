<template>
  <b-modal
    v-if="ctx != null"
    :title="modalTitle"
    v-model="ctx.visible"
    ok-only
    :ok-title="$t('ok')"
    @change="onChange"
  >
    <p class="my-1">{{ ctx.message }}</p>
  </b-modal>
</template>

<script>
export default {
  name: "MessageDialog",
  props: ["ctx"],
  computed: {
    modalTitle() {
      return this.ctx != null &&
        this.ctx.title != null &&
        this.ctx.title.length > 0
        ? this.ctx.title
        : this.$t("message");
    },
  },
  methods: {
    onChange(isVisible) {
      if (this.ctx != null && this.ctx.resolve && !isVisible) {
        this.ctx.resolve();
      }
    },
  },
};
</script>