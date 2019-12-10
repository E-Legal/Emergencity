<template>
  <div class="logout"></div>
</template>

<style lang="scss"></style>

<script>
// @ is an alias to /src
import LayoutDefault from "../layouts/LayoutDefault.vue";

export default {
  name: "logout",
  data() {
    return {
      title: "Vue.js Demo Form",
      email: "",
      password: "",
      submitting: false,
      log: ""
    };
  },
  methods: {
    submit() {
      if (this.password) {
        this.sendFormData();
      } else {
        this.validationError();
      }
    },
    enableSubmitLoader() {
      this.submitting = true;
    },
    sendFormData() {
      this.enableSubmitLoader();
      localStorage.login = this.email;
      this.log = localStorage.login;
      this.$emit("update:layout", LayoutDefault);
      window.location.href = "/";
      console.log("send");
    },
    validationError() {
      console.log("nouh");
    }
  },
  mounted() {
    localStorage.removeItem("login");
    this.$emit("update:layout", LayoutDefault);
    window.location.href = "/login";
  },
  created() {
    localStorage.removeItem("login");
    this.log = localStorage.login;
    this.$emit("update:layout", LayoutDefault);
  },
  components: {}
};
</script>
