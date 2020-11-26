import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home/Home.vue";
import Register from "./views/Users/Register.vue";
import Login from "./views/Users/Login.vue";
import Profile from "./views/Users/Profile.vue";
import Logout from "./views/Users/Logout.vue";
import Stats from "./views/Stats/Stats.vue";
import Support from "./views/Support/Support.vue";
import Dashboard from "./views/Dashboard/Dashboard.vue";
import Agenda from "./views/Agenda/Agenda.vue";
import BootstrapVue from "bootstrap-vue";

import Caserne from "./views/Barracks/Caserne.vue";
import HomeNotConnected from "./views/Home/HomeNotConnected.vue";
import Admin from "./views/Admin/Admin";

import store from "./store/index";

Vue.use(BootstrapVue);
Vue.use(Router);
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

function isAuthenticated(test) {
  console.log("this:::", test);
  if (localStorage.getItem("login")) {
    console.log(localStorage.getItem("login"));
    return true;
  } else {
    return false;
  }
}
function isAuthenticatedAdmin() {
  if (localStorage.getItem("login") === "Laegan") {
    console.log(localStorage.getItem("login"));
    return true;
  } else {
    return false;
  }
}

let router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/admin",
      name: "admin",
      component: Admin,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login
    },
    {
      path: "/calendar",
      name: "agenda",
      component: Agenda,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/stats",
      name: "stats",
      component: Stats,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
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
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/caserne",
      name: "caserne",
      component: Caserne,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/home",
      name: "home",
      component: HomeNotConnected,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/support",
      name: "support",
      component: Support,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About/About.vue")
    }
  ]
});

router.beforeEach((to, from, next) => {
  console.log(to);
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (store.getters["User/logged"]) {
      next();
      return;
    }
    next("/login");
  } else {
    next();
  }
});

export default router;
