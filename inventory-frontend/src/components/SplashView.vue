<template>
  <div
    class="d-flex flex-column align-items-center justify-content-center"
    style="height: 100vh"
  >
    <div class="p-3">
      <h1>{{ appTitle }}</h1>
      <h4 class="mt-3">{{ $t("msg_appStartup") }}</h4>
      <b-progress
        :value="initAppProgress"
        :max="progressMax"
        height="2rem"
        class="mt-3"
        striped
        animated
      >
<!--         <b-progress-bar
          :value="initAppProgress"
          :label="`${((initAppProgress / progressMax) * 100).toFixed(2)}%`"
        ></b-progress-bar> -->
      </b-progress>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "SplashView",
  data() {
    return {
      progressMax: 100,
    };
  },
  computed: {
    ...mapState(["initAppProgress"]),
    appTitle() {
      return `${this.$t("assetMgmtSystem")} - ${this.$t("inventoryOfAssets")}`;
    },
  },
};
</script>