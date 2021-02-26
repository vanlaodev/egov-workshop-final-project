<template>
  <div id="app">
    <div v-if="!appInitialized" class="p-2">
      {{ $t("msg_appStartup") }}
    </div>
    <div v-else-if="failedToInitializeAppReason" class="p-2">
      {{ failedToInitializeAppReason }}
    </div>
    <div v-else>
      <b-navbar toggleable="lg" type="dark" variant="primary">
        <b-navbar-brand href="#">{{ $t("assetMgmtSystem") }}</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item-dropdown :text="$t('inventoryOfAssets')">
              <b-dropdown-item @click="$router.push({ path: '/dashboard' })">{{
                $t("dashboard")
              }}</b-dropdown-item>
              <b-dropdown-item
                @click="$router.push({ name: 'CreateInventoryForm' })"
                >{{ $t("createInventoryForm") }}</b-dropdown-item
              >
              <b-dropdown-item
                @click="$router.push({ name: 'InventoryFormManagement' })"
                >{{ $t("inventoryFormManagement") }}</b-dropdown-item
              >
              <b-dropdown-item
                @click="$router.push({ name: 'TakeInventory' })"
                >{{ $t("takeInventory") }}</b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown>
              <template #button-content>
                <b-icon-globe></b-icon-globe>
              </template>
              <b-dropdown-item @click="updateLocale('zh-TW')"
                >中文</b-dropdown-item
              >
              <b-dropdown-item @click="updateLocale('en')"
                >English</b-dropdown-item
              >
            </b-nav-item-dropdown>

            <b-nav-item-dropdown v-if="loggedInUser">
              <template #button-content>
                <b-icon-person-fill></b-icon-person-fill>
                <span>{{ loggedInUser.username }}</span>
              </template>
              <b-dropdown-item href="#">{{
                $t("userProfile")
              }}</b-dropdown-item>
              <b-dropdown-item @click="signOut">{{
                $t("signOut")
              }}</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
      <main role="main" class="container">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  created() {
    this.initializeApp();
  },
  mounted() {
    document.title = `${this.$t("assetMgmtSystem")} - ${this.$t(
      "inventoryOfAssets"
    )}`;
  },
  data() {
    return {
      failedToInitializeAppReason: null,
    };
  },
  computed: {
    ...mapState(["appInitialized", "loggedInUser", "locale"]),
  },
  methods: {
    ...mapActions(["initStore", "updateLocale", "updateLoggedInUser"]),
    ...mapMutations(["setAppInitialized"]),
    async signOut() {
      try {
        await this.$api.userMgmtApi.signOut();
      } catch (err) {
        // ignored
      } finally {
        await this.updateLoggedInUser(null);
        this.redirectToLoginUrl();
      }
    },
    redirectToLoginUrl() {
      window.location = "#"; // TODO: redirect to login url
    },
    async initializeApp() {
      if (this.appInitialized) return;
      await this.initStore();
      if (!this.loggedInUser) {
        const accessToken = this.$route.query.accessToken;
        if (!accessToken) {
          /* this.redirectToLoginUrl();
          return; */
        }
        // TODO: fetch user info
        try {
          const userInfo = await this.$api.userMgmtApi.getUserInfoByAccessToken(
            accessToken
          );
          if (userInfo) {
            await this.updateLoggedInUser(userInfo);
          } else {
            // sign out user if user info not found
            await this.signOut();
            return;
          }
        } catch (err) {
          this.failedToInitializeAppReason = err;
          return;
        }
      }
      this.setAppInitialized(true);
    },
  },
};
</script>