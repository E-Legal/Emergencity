<template>
  <div class="login" style="overflow: hidden">
    <div style="background: url('/assets/traffics.jpg') no-repeat center center fixed; height: calc(100vh - 64px);  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;">
      <h1 style="color:white" class="d-flex justify-content-center emergencity_title">EMERGENCITY</h1>
      <div
        style="background-color:rgba(158,156,156, 0.3); z-index: 1000"
      >
        <center>
          <form id="demo" @submit.prevent="submit" novalidate>
            <input
              type="text"
              id="login"
              class="col-lg-6 box_space"
              name="login"
              placeholder="Identifiant"
              v-model="email"
            />
            <input
              type="text"
              id="password"
              class="col-lg-6 box_space"
              name="login"
              placeholder="Mot de passe"
              v-model="password"
            />
            <br />
            <input type="submit" class="fadeIn fourth button_space" value="Connexion" />
          </form>
        </center>
      </div>
      <div class="container centered" styl e>
        <aside class="col-sm-4" style="margin: 0 auto;">
          <!-- card.// -->
        </aside>
      </div>
      <!-- row.// -->
      {{ email }} {{ password }} {{ log }} {{ info }}
    </div>
  </div>

  <!--container end.//-->
</template>

<style lang="scss">


</style>

<script>
// @ is an alias to /src
import LayoutDefault from "../layouts/LayoutDefault.vue";
import axios from 'axios'

export default {
  name: "login",
  data() {
    return {
      title: "Vue.js Demo Form",
      email: "",
      password: "",
      submitting: false,
      log: "",
      info: ""
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
      console.log(this.email) 
      console.log(this.password)
      const params = {
        name: this.email, 
        password: this.password
      };
            const options = { crossdomain: true,
        headers: {'Content-Type': 'application/x-www-form-urlencoded', 'crossDomain': true, 'Content-Type': 'text/plain;charset=utf-8',}
      }
      axios.
        post("http://localhost:9000/login?" + "name=" + this.email + "&password=" + this.password, null, options)
        .then((response) => {
          console.log("oui")
          console.log(response.status)
          if (response.status === 200) {
            this.enableSubmitLoader();
            this.login_success();
          }
      }, (error) => {
        console.log(error)
        //this.enableSubmitLoader();
        alert("Login without Network")
                    this.enableSubmitLoader();
            this.login_success();
        console.log(error)
        this.login_success();
      });
    },
    login_success() {
      if (this.submitting === true) {
        localStorage.login = this.email;
        this.log = localStorage.login;
        this.$emit("update:layout", LayoutDefault);
        window.location.href = "/";
        console.log("send");
      } else {
        alert("Mauvais Email ou/et mot de passe");
      }
    },
    validationError() {
      alert("Pas de mot de passe");
    }
  },
  mounted() {
    this.$emit("update:layout", LayoutDefault);
  },
  created() {
    this.log = localStorage.login;
    this.$emit("update:layout", LayoutDefault);
  },
  components: {}
};
</script>
