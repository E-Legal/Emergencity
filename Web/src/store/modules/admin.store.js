/* Module1.store.js */
import axios from "axios";
// State object
const initialState = () => ({
  listTraffic: null
});

const state = initialState();
// Getter functions
const getters = {
  listTraffic: state => {
    return state.listTraffic;
  },
};

// Actions
const actions = {
  handleLogout({ commit }) {
    commit("logout");
  },
  handleLogin({ commit }, { token }) {
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
        const result = axios({
          method: 'GET',
          url: `http://localhost:9000/trafficlights`,
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/x-www-form-urlencoded"
          },
          data: qs.stringify({
            barrack_id: barrackId,
            user_id: userId
          }),
        });
        result.then((response) => {
          resolve(true);
        }).catch((error) => {
          reject(false);
        });
    })
  }
};

// Mutations
const mutations = {
  RESET(state) {
    const newState = initialState();
    Object.keys(newState).forEach(key => {
      state[key] = newState[key];
    });
  },
  addUser(state, user) {
    state.user = user;
    state.logged = true;
  },
  logout(state) {
    state.logged = false;
    state.user = null;
  },
  startLoading() {
    state.loading = true;
  },
  endLoading() {
    state.loading = false;
  }
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
