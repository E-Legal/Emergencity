<template>
  <component :is="layout">
    <router-view :layout.sync="layout" />
  </component>
</template>

<script>
import LayoutDefault from "./layouts/LayoutDefault.vue";
import store from "./store/index";

export default {
  name: `App`,
  store: store,
  data() {
    return {
      layout: `div`
    };
  },
  mounted() {
    this.$emit("update:layout", LayoutDefault);
  }
};
</script>

<style lang="scss">
html {
  height: 100vh;
}
body {
  height: 100%;
  max-width: 100%;
  overflow-x: hidden;
  background: url("/assets/white3.png") repeat center center fixed !important;
  background-position: center;
  overflow: auto;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
