<template>
  <div class="register">
    <Display :msg="this.error"/>
<div class="background-image"></div>
<div class="content">
  <br>
  <br>
  <br>
    <div class="container-flex" style="text-align: center; margin: 0 auto">
      <div style="width: 400px; heidht: 400px; margin: 0 auto">
    <b-card
      title="Connexion"
      img-src="https://ipravda.sk/res/2014/12/10/thumbs/marseille_01-clanokW.jpg  "
      img-alt="Image"
      img-top
      align= "center"
      style="-webkit-box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);
-moz-box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);
box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);"
    >
      <b-card-text>
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
              type="password"
              id="password"
              class="col-lg-6 box_space"
              name="login"
              placeholder="Mot de passe"
              v-model="password"
              style="margin-left: 1%;"
            />
            <br />
            <input type="submit" class="btn btn-outline-primary" value="Connexion" />
          </form>
      </b-card-text>
    </b-card>
    </div>
    </div>
      </div>
  </div>
</template>

<style scoped>
* {
  box-sizing: border-box;
}
.form-control {
  margin: 2%;
}
.background-image {
  background-image: url('https://blogvoyages.fr/wp-content/uploads/2017/04/marseille.jpg');
  background-size: cover;
  display: block;
  filter: blur(5px);
  -webkit-filter: blur(5px);
  height: 1000px;
  left: 0;
  position: fixed;
  right: 0;
  z-index: 1;
}

.content {
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  top: 10px;
  left: 0;
  position: fixed;
  margin-left: 20px;
  margin-right: 20px;
  right: 0;
  z-index: 2;
  padding: 0 10px;
}
</style>

<script>
// @ is an alias to /src
import LayoutDefault from "../layouts/LayoutDefault.vue";
import axios from 'axios'
import Display from "../components/MyModal.vue";

export default {
  name: "login",
  data() {
    return {
      title: "Vue.js Demo Form",
      email: "",
      password: "",
      submitting: false,
      log: "",
      info: "",
      error: ""
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
        this.error = "Connexion sans APi"
        this.$bvModal.show("my-modal")
        this.enableSubmitLoader();
        this.login_success();
        console.log(error)
      });
    },
    login_success() {
      if (this.submitting === true) {
        localStorage.login = this.email;
        this.log = localStorage.login;
        this.$emit("update:layout", LayoutDefault);
        this.error = "Connexion réussie"
        this.$bvModal.show("my-modal")
        window.location.href = "/";
        console.log("send");
      } else {
        this.error = "Mauvais Email ou/et Mot de passe"
                this.$bvModal.show("my-modal")
      }
    },
    validationError() {
              this.error = "Aucun mot de passe"
                this.$bvModal.show("my-modal")
    }
  },
  mounted() {
    this.$emit("update:layout", LayoutDefault);
  },
  created() {
    this.log = localStorage.login;
    this.$emit("update:layout", LayoutDefault);
  },
  components: {
    Display
  }
};
</script>
