<template>
  <div id="app">
    <splash-view v-if="!appInitialized"></splash-view>
    <div v-else>
      <b-navbar toggleable="lg" type="dark" variant="primary">
        <b-navbar-brand href="#">{{ $t("assetMgmtSystem") }}</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item-dropdown :text="$t('inventoryOfAssets')">
              <b-dropdown-item
                v-for="(item, index) in mainMenuItemsAllowToNav"
                :key="index"
                @click="navToMainMenuItem(item)"
                >{{ $t(item.titleI18nKey) }}</b-dropdown-item
              >
            </b-nav-item-dropdown>
            <b-nav-item href="#">{{ $t("assetsManagement") }}</b-nav-item>
            <b-nav-item>{{ $t("transferOfAssets") }}</b-nav-item>
            <b-nav-item href="#">{{ $t("reports") }}</b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown>
              <template #button-content>
                <b-icon-globe class="mr-1" aria-hidden="true"></b-icon-globe>
                <span>{{ $t("language") }}</span>
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
                <b-icon-person-fill
                  class="mr-1"
                  aria-hidden="true"
                ></b-icon-person-fill>
                <span v-if="loggedInUser">{{ loggedInUser.username }}</span>
              </template>
              <b-dropdown-item href="#">
                {{ $t("userProfile") }}
              </b-dropdown-item>
              <b-dropdown-item @click="signOut">
                {{ $t("signOut") }}
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
      <main role="main" class="container py-4">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<style>
body {
  background-color: #eff4f7;
}
</style>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import SplashView from "./components/SplashView";
import mainMenu from "./domain/main-menu";
import { promiseDelay } from "./utils/helpers";

export default {
  created() {
    this.initializeApp().then(() => {
      this.$bus.$on("LOGIN_REQUIRED", this.signOut);
      this.$bus.$on(
        "LOCALE_UPDATED",
        () => (document.title = this.documentTitle)
      );
    });
  },
  beforeDestroy() {
    this.$bus.$off("LOGIN_REQUIRED");
    this.$bus.$off("LOCALE_UPDATED");
  },
  components: {
    SplashView,
  },
  computed: {
    ...mapState(["appInitialized", "loggedInUser", "locale"]),
    documentTitle() {
      return `${this.$t("assetMgmtSystem")} - ${this.$t("inventoryOfAssets")}`;
    },
    mainMenuItemsAllowToNav() {
      return mainMenu.items.filter((mi) =>
        mi.allowNavigate(this.loggedInUser?.roles)
      );
    },
  },
  methods: {
    ...mapActions(["initStore", "updateLocale", "updateLoggedInUser"]),
    ...mapMutations(["setAppInitialized", "setInitAppProgress"]),
    navToMainMenuItem(item) {
      this.$router.push({ name: item.routeName }).catch(() => {});
    },
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
      window.location = `${process.env.VUE_APP_LOGIN_URL}?redirectUrl=${window.location}&locale=${this.locale}`;
    },
    async initializeApp() {
      if (this.appInitialized) return;
      this.setInitAppProgress(30);
      await this.initStore();
      document.title = this.documentTitle;
      const accessToken = this.$route.query.accessToken ?? "EgovWorkshop2020"; // TODO: for dev only, remove the fake token later
      if (accessToken) {
        try {
          const userInfo = await this.$api.userMgmtApi.getUserInfoByAccessToken(
            accessToken
          );
          this.setInitAppProgress(80);
          if (userInfo) {
            // user info found
            await this.updateLoggedInUser(userInfo);
            this.setInitAppProgress(90);
          } else {
            // redirect to login url if user info not found
            this.redirectToLoginUrl();
            return;
          }
        } catch (err) {
          // redirect to login url if failed to get user info
          this.redirectToLoginUrl();
          return;
        }
      }
      if (!this.loggedInUser) {
        // redirect to login url if user not logged in
        this.redirectToLoginUrl();
        return;
      }
      this.setInitAppProgress(100);

      await promiseDelay(600);

      this.setAppInitialized(true);

      const mainMenuItem = mainMenu.items.filter(
        (i) => this.$route.name === i.routeName
      );
      if (
        mainMenuItem.length > 0 &&
        !mainMenuItem[0].allowNavigate(
          this.loggedInUser == null ? [] : this.loggedInUser.roles
        )
      ) {
        this.$router.replace({
          name: "PermissionDenied",
          query: { originalPath: this.$route.path },
        });
        return;
      }
    },
  },
};
</script>