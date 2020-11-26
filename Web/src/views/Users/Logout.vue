<template>
  <div class="logout"></div>
</template>

<style lang="scss"></style>

<script>
// @ is an alias to /src
import LayoutDefault from "../../layouts/LayoutDefault.vue";
//import store from "./store/userStore";

export default {
  name: "logout",
  data() {
    return {};
  },
  mounted() {
    this.$store.dispatch("User/handleLogout");
    this.$router.push("/login");
  }
};
</script>
