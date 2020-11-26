/* Module1.store.js */
import axios from "axios";
import qs from "qs";

// State object
const initialState = () => ({
  barracksList: null,
  barrackVehicle: null,
  userBarracks : null,
  allVehicles: null
});

const state = initialState();
// Getter functions
const getters = {
  barracks: state => {
    return state.barracksList;
  },
  barrackVehicle: state => {
    return state.barrackVehicle;
  },
  userBarracks: state => {
    return state.userBarracks;
  },
  allVehicles: state => {
    return state.allVehicles
  }
};

// Actions
const actions = {
  deleteUserBarracks({commit}, {token, userId, barrackId})
  {
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'DELETE',
        url: `http://localhost:9000/barrackUsers/${barrackId}/${userId}`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
      });
      result.then((response) => {
        resolve(true);
      }).catch((error) => {
        reject(false);
      });
    })
  },
  deleteBarrack({commit}, {token, barrackId})
  {
    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'DELETE',
        url: `http://localhost:9000/barracks/${barrackId}`,
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
  addBarrack({commit}, {token, name, city, address})
  {
    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'POST',
        url: `http://localhost:9000/barracks/`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
        data: qs.stringify({
          address: address,
          name: name,
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
  addUserBarracks({commit}, {token, userId, barrackId})
  {
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'POST',
        url: `http://localhost:9000/barrackUsers`,
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
  },
  getUserBaracks({commit}, { token, userId})
  {
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'GET',
        url: `http://localhost:9000/barrackUsers/user/${userId}`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
      });
      result.then((response) => {
        commit("addUserBarracks", response.data);
        resolve(true);
      }).catch((error) => {
        console.log("error", error);
        reject(false);
      });
    })
  },
  handleLogout({ commit }) {
    commit("logout");
  },
  getBarracks({ commit }, token ) {
    console.log("isToken==", token);
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'GET',
        url: `http://localhost:9000/barracks`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
      });
      result.then((response) => {
        commit("addListBarracks", response.data);
        console.log("response", response.data)
        resolve(true);
      }).catch((error) => {
        //commit("addUser", response.data, response.data.token);
        console.log("error", error);
        reject(false);
      });
    })
  },

  ///////////////////////// VEHICULE /////////////////////////////
  deleteUserVehicle({commit}, {token, idBarrack, idVehicle}) 
  {
    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'DELETE',
        url: `http://localhost:9000/barrackVehicles/${idBarrack}/${idVehicle}`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
      });
      result.then((response) => {
        resolve(true);
      }).catch((error) => {
        reject(false);
      });
    })
  },
  createUserVehicle({commit}, {token, idBarrack, idVehicle})
  {
    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'POST',
        url: `http://localhost:9000/barrackVehicles`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
        data: qs.stringify({
          barrack_id: idBarrack,
          vehicle_id: idVehicle
        }),
      });
      result.then((response) => {
        resolve(true);
      }).catch((error) => {
        reject(false);
      });
    })
  },
  createVehicle({commit}, {token, model, registration})
  {
    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'POST',
        url: `http://localhost:9000/vehicles`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
        data: qs.stringify({
          model: model,
          registration: registration
        }),
      });
      result.then((response) => {
        resolve(true);
      }).catch((error) => {
        reject(false);
      });
    })
  },
  getAllVehicle({commit}, {token}) {
    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'GET',
        url: `http://localhost:9000/vehicles`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
      });
      result.then((response) => {
        commit("addAllVehicles", response.data);
        resolve(true);
      }).catch((error) => {
        reject(false);
      });
    })
  },
  getBarrackVehicle({ commit }, {token, idBarracks} ) {
    console.log("isToken==", token);
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'GET',
        url: `http://localhost:9000/barrackVehicles/barrack/${idBarracks}`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
      });
      result.then((response) => {
        commit("addBarrackVehicle", response.data);
        console.log("response", response.data)
        resolve(true);
      }).catch((error) => {
        //commit("addUser", response.data, response.data.token);
        console.log("error", error);
        reject(false);
      });
    })
  }

}

// Mutations
const mutations = {
  RESET(state) {
    const newState = initialState();
    Object.keys(newState).forEach(key => {
      state[key] = newState[key];
    });
  },
  addAllVehicles(state, payload)
  {
    state.allVehicles = payload;
  },
  addUserBarracks(state, payload)
  {
    state.userBarracks = payload;
  },
  addBarrackVehicle(state, payload)
  {
    state.barrackVehicle = payload;
  },
  addListBarracks(state, payload) {
    state.barracksList = payload
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
