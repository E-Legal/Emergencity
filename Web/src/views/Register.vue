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
      title="Inscription"
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
              <div class="row">
                  <div class="col-lg-6">
                  <input
                   class="form-control "
                    name="name"
                    id="name"
                    placeholder="Nom"
                    v-model="nom"
                    required
                  />
                </div>
                <div class="col-lg-6">
                  <input
                   class="form-control "
                    name="prenom"
                    id="prenom"
                    placeholder="Prénom"
                    v-model="prenom"
                    required
                  />
                </div>

                <div class="col-lg-6">
                  <input
                   class="form-control "
                    name="profession"
                    id="profession"
                    placeholder="Profession"
                    v-model="profession"
                    required
                  />
                </div>

                  <div class="col-lg-6">
                  <input
                   class="form-control "
                     name="email"
                    id="email"
                    placeholder="Adresse Email"
                    v-model="email"
                    required
                  />
                </div>

                <div class="col-lg-6">
                  <input
                    class="form-control "
                    name="ville"
                    id="ville"
                    placeholder="Ville"
                    v-model="ville"
                    required
                  />
                </div>

                <div class="col-lg-6">
                  <input
                   class="form-control "
                    name="email-bis"
                    id="email-bis"
                    placeholder="Confirmer Adresse Email"
                    v-model="confirm_mail"
                    required
                  />
                </div>

                <!-- card.// -->
              </div>
              <br>
              <div class="d-flex justify-content-center">
                <input
                  class="btn btn-outline-primary"
                  type="submit"
                  value="Demande envoyé"
                />
              </div>
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

import LayoutDefault from "../layouts/LayoutDefault.vue";
import axios from 'axios'
import Display from "../components/MyModal.vue";

export default {
  name: "register",
  components: {
    Display
  },
  data() {
    return {
      error: "",
      nom: "",
      prenom: "",
      profession: "",
      ville: "",
      email: "",
      confirm_mail: "",
      password: "",
      submitting: false,
      log: ""
    };
  },
  methods: {
    submit() {
      console.log("test")
      if (this.email != this.confirm_mail) {
                this.error = "Les emails ne correspondent pas"
        this.$bvModal.show("my-modal")
        return;
      }
      const options = { crossdomain: true,
        headers: {'Content-Type': 'application/x-www-form-urlencoded', 'crossDomain': true, 'Content-Type': 'text/plain;charset=utf-8',}
      }
      this.password = "123456";
      axios.
        post("http://localhost:9000/register?" + "name=" + this.email + "&password=" + this.password, null, options)
        .then((response) => {
          console.log(response.status)
          console.log(response.data)
          if (response.status === 200) {
            this.enableSubmitLoader();
          }
          this.register_success();
      }, (error) => {
        
        //          this.enableSubmitLoader();
        console.log(error)
        //this.register_success();
      });
    },
    enableSubmitLoader() {
      this.submitting = true;
    },
    register_success() {
      if (this.submitting === true) {
              this.$emit("update:layout", LayoutDefault);
              window.location.href = "/login";
              console.log("send");
            } else {
                      this.error = "Le formulaire n'a pas été envoyé"
        this.$bvModal.show("my-modal")
            }
          }
  },
  mounted() {
    this.$emit("update:layout", LayoutDefault);
    this.log = localStorage.login;
  },
  created() {
    this.$emit("update:layout", LayoutDefault);
  }
};
</script>
