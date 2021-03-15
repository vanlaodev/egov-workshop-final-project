import Vue from "vue";
import Vuex from "vuex";
import localforage from "localforage";
import i18n from "../i18n";
import bus from "../eventbus";

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    appInitialized: false,
    initAppProgress: 0,
    storeInitialized: false,
    loggedInUser: null,
    locale: null,
    deptList: [{
        value: "1",
        text: "DOI"
      },
      {
        value: "2",
        text: "DAF"
      },
      {
        value: "3",
        text: "DRC"
      },
    ],
  },
  mutations: {
    setInitAppProgress(state, progress) {
      if (state.appInitialized) return;
      state.initAppProgress = progress;
    },
    setAppInitialized(state) {
      state.appInitialized = true;
    },
    setStoreInitialized(state) {
      state.storeInitialized = true;
    },
    setLoggedInUser(state, loggedInUser) {
      state.loggedInUser = loggedInUser;
    },
    setLocale(state, locale) {
      i18n.locale = locale;
      state.locale = locale;
    },
  },
  actions: {
    async initStore({
      commit,
      state
    }) {
      if (state.storeInitialized) return;
      commit(
        "setLocale",
        (await localforage.getItem("locale")) ??
        process.env.VUE_APP_I18N_LOCALE ?? "zh-TW"
      );
      commit('setInitAppProgress', 45);
      commit("setLoggedInUser", await localforage.getItem("loggedInUser"));
      commit('setInitAppProgress', 60);
      commit("setStoreInitialized");
    },
    async updateLoggedInUser({
      commit
    }, loggedInUser) {
      commit("setLoggedInUser", loggedInUser);
      await localforage.setItem("loggedInUser", loggedInUser);
    },
    async updateLocale({
      commit
    }, locale) {
      commit("setLocale", locale);
      await localforage.setItem("locale", locale);
      bus.$emit('LOCALE_UPDATED', locale);
    },
  },
});

export default store;