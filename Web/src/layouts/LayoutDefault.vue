<template>
  <div class="LayoutDefault">
    <nav class="LayoutDefault__nav">
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <router-link class="navbar-brand" to="/">Dashboard</router-link>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarText"
          aria-controls="navbarText"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav ml-auto">

            <li v-if="!user" class="nav-item">
              <router-link class="nav-link" to="/register"
                >Inscription</router-link
              >
            </li>
            <li v-if="!user" class="nav-i tem">
              <router-link class="nav-link" to="/login">Connexion</router-link>
            </li>
            <li v-if="user" class="nav-item">
              
              <router-link class="nav-link" to="/"
                >Accueil</router-link
              >
            </li>
            <li v-if="user" class="nav-item">
              
              <router-link class="nav-link" to="/logout"
                >Deconnexion</router-link
              >
            </li>
            <li>
              <router-link class="nav-link" to="/about"> <img src="/assets/interrogation.png" /> </router-link>
            </li>
          </ul>
          <!----
    <span class="navbar-text">
      <div class="LayoutDefault__user">
        <div v-if="user"> {{user.name}} </div>
        <div v-else>
        No User
      </div>
      </div>

    </span>---->
        </div>
      </nav>
    </nav>
    <main class="LayoutDefault__main">
      <slot />
    </main>
    <footer class="LayoutDefault__footer"></footer>
  </div>
</template>

<style scoped lang="scss"></style>

<script>
export default {
  name: `LayoutDefault`,
  data() {
    return {
      user: null
    };
  },
  watch() {},
  mount() {
    this.user = localStorage.login;
  },
  created() {
    // Simulate fetching user data.
    this.user = localStorage.login;
  }
};
</script>
