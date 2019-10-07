<template>
  <div class="login">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" />

    <div class="container centered">
        <aside class="col-sm-4" style="margin: 0 auto;">
          <div class="card">
            <article class="card-body">
              <router-link to="/register" class="float-right btn btn-outline-primary">Sign up</router-link>
              <h4 class="card-title mb-4 mt-1">Sign in</h4>
              <form id="demo" @submit.prevent="submit" novalidate>
                <div class="form-group">
                  <label>Your email</label>
                  <input name class="form-control" placeholder="Email" type="email" v-model="email">
                </div>
                <!-- form-group// -->
                <div class="form-group">
                  <a class="float-right" href="#">Forgot?</a>
                  <label>Your password</label>
                  <input class="form-control" placeholder="******" type="password" v-model="password"/>
                </div>
                <!-- form-group// -->
                <div class="form-group">
                  <div class="checkbox">
                    <label>
                      <input type="checkbox" /> Save password
                    </label>
                  </div>
                  <!-- checkbox .// -->
                </div>
                <!-- form-group// -->
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-block">Login</button>
                </div>
                <!-- form-group// -->
              </form>
            </article>
          </div>
          <!-- card.// -->
        </aside>
        <!-- col.// -->
      </div>
      <!-- row.// --> {{email}} {{password}}
    </div>

    <!--container end.//-->
</template>

<style lang="scss">
</style>

<script>
// @ is an alias to /src
import LayoutDefault from "../layouts/LayoutDefault.vue";

export default {
  name: "login",
  data () {
    return {
      title: 'Vue.js Demo Form',
      email: "",
      password: "",
      submitting: false
    }
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
      console.log("send");
    },
        validationError() {
          console.log("nouh")
    },
  },
  created() {
    this.$emit("update:layout", LayoutDefault);
  },
  components: {}
};

</script>
