<template>
  <div
    class="d-flex flex-column align-items-center justify-content-center"
    style="height: 100vh"
  >
    <div class="p-3">
      <h1>{{ appTitle }}</h1>
      <h4 class="mt-3">{{ $t("msg_appStartup") }}</h4>
      <b-progress
        :max="progressMax"
        height="2rem"
        class="mt-3"
        striped
        animated
      >
        <b-progress-bar
          :value="progressVal"
          :label="`${((progressVal / progressMax) * 100).toFixed(2)}%`"
        ></b-progress-bar>
      </b-progress>
    </div>
  </div>
</template>

<script>
import bus from "../eventbus";

export default {
  name: "SplashView",
  data() {
    return {
      progressVal: 0,
      progressMax: 100,
    };
  },
  computed: {
    appTitle() {
      return `${this.$t("assetMgmtSystem")} - ${this.$t("inventoryOfAssets")}`;
    },
  },
  mounted() {
    bus.$on("APP_STARTUP_PROGRESS_UPDATED", (p) => {
      this.progressVal = p;
    });
  },
  beforeDestroy() {
    bus.$off("APP_STARTUP_PROGRESS_UPDATED");
  },
};
</script>