/* Module1.store.js */
import axios from "axios";
import qs from "qs";
// State object
const initialState = () => ({
  user: null,
  loading: false,
  logged: false,
  token: null,
  listUsers: null
});

const state = initialState();
// Getter functions
const getters = {
  user: state => {
    return state.user;
  },
  logged: state => {
    return state.logged;
  },
  token: state => {
    return state.token;
  },
  listUsers: state => {
    return state.listUser;
  }
};

// Actions
const actions = {
  handleListUsers({commit}, {token}) {
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'GET',
        url: `http://localhost:9000/users`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
      });
      result.then((response) => {
        commit("listUsers", response.data);
        resolve(true);
      }).catch((error) => {
        //commit("addUser", response.data, response.data.token);
        console.log("error", error);
        reject(false);
      });
    })
  },
  deleteUser({commit}) {

  },
  handleLogout({ commit }) {
    commit("logout");
  },
  createUser({commit}, {token, user, password})
  {
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      const result = axios({
        method: 'POST',
        url: `http://localhost:9000/users/register`,
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/x-www-form-urlencoded"
        },
        data: qs.stringify({
          username: user,
          password: password
        }),
      });
      result.then((response) => {
        resolve(true);
      }).catch((error) => {
        reject(false);
      });
    })
  },

  handleLogin({ commit }, { email, password }) {
    const options = {
      crossdomain: true,
      headers: { "Content-Type": "application/x-www-form-urlencoded" }
    };

    return new Promise((resolve, reject) => {
      axios
        .post(
          "http://localhost:9000/users/login",
          qs.stringify({
            username: email,
            password: password
          }),
          options
        )
        .then(
          response => {
            if (response.status === 200) {
              localStorage.login = response.data["username"];
              localStorage.id_user = response.data["user_id"];
              localStorage.token = response.data["token"];
              console.log(response.data.token, "RESPONSE")
              commit("addUser", response.data, response.data.token);
              resolve("/");
            }
          },
          error => {
            console.log(error);
            reject("User wasn't able to connect");
          }
        );
    });
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
  listUsers(state, payload)
  {
    state.listUser = payload
  },
  addUser(state, payload) {
    state.user = payload;
    state.logged = true;
    state.token = payload.token
  },
  logout(state) {
    state.logged = false;
    state.user = null;
    state.token = null;
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
