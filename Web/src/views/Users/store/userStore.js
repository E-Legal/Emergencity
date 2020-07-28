import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)


const store = new Vuex.Store({
  state: {
    user: null,
    loading: false
  },
  mutations: {
    addUser(state, user) {
      state.user = user;
    },
    startLoading() {
      state.loading = true;
    },
    endLoading() {
      state.loading = false;
    }
  },
  getters: {
    user: state => state.user
  },
  actions: {  
    addUser ({ commit }, user) {
      commit('addUser', user)
    },
    handleLogin({commit}, {email, password}) {
      axios.
        post("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/login?" + "name=" + email + "&password=" + password, null)
        .then((response) => {
          if (response.status === 200) {
            commit('addUser', response.data);
          }
      }, (error) => {
        console.log(error)
      });
    }
  }
});

export default store;
