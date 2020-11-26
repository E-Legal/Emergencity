/* Module1.store.js */
import axios from "axios";
import qs from "qs";
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
  deleteTrafficLight({commit}, {token, idLight}) 
  {
    return new Promise((resolve, reject) => {
        const result = axios({
          method: 'DELETE',
          url: `http://localhost:9000/trafficlights/${idLight}`,
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/x-www-form-urlencoded"
          }
        });
        result.then((response) => {
          resolve(true);
        }).catch((error) => {
          reject(false);
        });
      })
  },
  addTrafficLight({commit}, {token, x, y, z, city}) 
  {
    return new Promise((resolve, reject) => {
        const result = axios({
          method: 'POST',
          url: `http://localhost:9000/trafficlights`,
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/x-www-form-urlencoded"
          },
          data: qs.stringify({
            x: x,
            y: y,
            z: z,
            city: city
          }),
        });
        result.then((response) => {
          resolve(true);
        }).catch((error) => {
          reject(false);
        });
      })
  },
  getTrafficLights({ commit }, { token }) {
    return new Promise((resolve, reject) => {
        const result = axios({
          method: 'GET',
          url: `http://localhost:9000/trafficlights`,
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/x-www-form-urlencoded"
          },
        });
        result.then((response) => {
        commit("addTrafficList", response.data)
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
  addTrafficList(state, payload)
  {
    state.listTraffic = payload;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
