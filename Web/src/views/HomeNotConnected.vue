<template>
  <div class="home">
    <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">

      <Display :msg="this.error"/>
<div class="background-image"></div>
<div class="content">
  <br>
  <br>
  <br>
    <div class="container-flex" style="text-align: center; margin: 0 auto">
      <div style="width: 700px; height: 400px; margin: 0 auto">
    <b-card
      title="Qu'est-ce que Emergencity ?"
      img-src="http://www.leparisien.fr/resizer/84WjgayktqIDZjeH4KyitV7snAc=/932x582/arc-anglerfish-eu-central-1-prod-leparisien.s3.amazonaws.com/public/FTSMI3355P2JEHPNA3DPX7D624.jpg"
      img-alt="Image"
      img-top
      align= "center"
      style="-webkit-box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);
-moz-box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);
box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);"
    >
      <b-card-text style="font-family: 'Raleway'">
          <br>
      Emergencity est un outil qui permet aux véhicules d’urgences de passer plus facilement 
      et de manière sécurisé les intersections munis de feux tricolores en fluidifiant le trafic avant leurs arrivés.
      <br>
      </b-card-text>
       <a href="/home"> Formulaire de contact <img width="20px" src="data:image/png;base64,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">
      </a>
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
