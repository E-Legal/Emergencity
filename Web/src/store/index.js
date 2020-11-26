/** store/index.js **/

import Vue from "vue";
import Vuex from "vuex";
import createLogger from "vuex/dist/logger";
import modules from "./modules";
import VuexPersist from "vuex-persist";

import localForage from "localforage";

Vue.use(Vuex);
const debug = process.env.NODE_ENV !== "production";

const vuexStorage = new VuexPersist({
  key: "sourcelink",
  storage: window.localStorage,
  reducer: state => ({
    User: state.User,
    Admin: state.Admin
    // getRidOfThisModule: state.getRidOfThisModule (No one likes it.)
  })
});

export default new Vuex.Store({
  plugins: debug ? [vuexStorage.plugin] : [vuexStorage.plugin],
  modules,
  actions: {
    reset({ commit }) {
      Object.keys(modules).forEach(moduleName => {
        commit(`${moduleName}/RESET`);
      });
    }
  },
  strict: debug
});
