<template>
  <component :is="layout">
    
    <router-view :layout.sync="layout" />
  </component>
</template>

<script>
import LayoutDefault from "./layouts/LayoutDefault.vue";
export default {
  name: `App`,
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
  height: 100%;
}
body {
  height: 100%;
  max-width: 100%;
  overflow-x: hidden;
  background-image: linear-gradient(
    to bottom,
    #ababab,
    #9b9b9b,
    #8c8b8c,
    #7e7b7c,
    #706c6c
  );
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
