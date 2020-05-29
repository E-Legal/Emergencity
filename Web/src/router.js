import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Register from "./views/Register.vue";
import Login from "./views/Login.vue";
import Profile from "./views/Profile.vue";
import Logout from "./views/Logout.vue";
import Stats from "./views/Stats.vue";
import Support from "./views/Support.vue";
import Dashboard from "./views/Dashboard.vue";
import BootstrapVue from "bootstrap-vue";

import Caserne from "./views/Barracks/Caserne.vue"
import HomeNotConnected from './views/HomeNotConnected.vue'
import Admin from './views/Admin/Admin';


Vue.use(BootstrapVue);
Vue.use(Router);
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

function isAuthenticated() {
  if (localStorage.getItem("login")) {
    console.log(localStorage.getItem("login"));
    return true;
  } else {
    return false;
  }
}
function isAuthenticatedAdmin() {
  if (localStorage.getItem("login") === "test") {
    console.log(localStorage.getItem("login"));
    return true;
  } else {
    return false;
  }
}

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      beforeEnter(to, from, next) {
        if (isAuthenticated()) {
          next();
        } else {
          next("/home");
        }
      }
    },
    {
      path: "/admin",
      name: "admin",
      component: Admin,
      beforeEnter(to, from, next) {
        if (isAuthenticatedAdmin()) {
          next();
        } else {
          next("/home");
        }
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/stats",
      name: "stats",
      component: Stats,
      beforeEnter(to, from, next) {
        if (isAuthenticated()) {
          next();
        } else {
          next("/login");
        }
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      beforeEnter(to, from, next) {
        if (isAuthenticated()) {
          next();
        } else {
          next("/home");
        }
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: Dashboard,
      beforeEnter(to, from, next) {
        if (isAuthenticated()) {
          next();
        } else {
          next("/home");
        }
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      beforeEnter(to, from, next) {
        if (isAuthenticated()) {
          next();
        } else {
          next("/login");
        }
      }
    },
    {
      path: "/caserne",
      name: "caserne",
      component: Caserne
    },
    {
      path: "/home",
      name: "home",
      component: HomeNotConnected
    },
    {
      path: "/support",
      name: "support",
      component: Support
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    }
  ]
});
