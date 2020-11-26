<template>
  <div class="home">
    <link
      href="https://fonts.googleapis.com/css?family=Raleway&display=swap"
      rel="stylesheet"
    />

    <Display :msg="this.error" />
    <div class="background-image"></div>
    <div class="content">
      <br />
      <br />
      <br />
      <div class="container-flex" style="text-align: center; margin: 0 auto">
        <div style="width: 1600px; height: 1000px; margin: 0 auto">
          <b-card
            title=""
            img-src="/assets/landingpage1.png"
            img-alt="Image"
            img-top
            align="center"
            style="-webkit-box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);
-moz-box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);
box-shadow: 10px 10px 5px -5px rgba(0,0,0,0.75);"
          >
            <b-card-text style="font-family: 'Raleway'">
              <br />
              <h2>Qu'est-ce que Emergencity ?</h2>
              <br />
              <p style="font-weight: bold">
                Emergencity est un outil qui permet aux véhicules d’urgences de
                passer plus facilement et de manière sécurisée les intersections
                munis de feux tricolores en fluidifiant le trafic avant leurs
                arrivés.
              </p>
              <br />
              <hr />
              <h2>Multi-plateforme</h2>
              <p style="font-weight: bold">
                Emergencity est également un projet multi-plateforme. Il est
                disponible sur navigateur web et mobile.
              </p>
              <br />
              <br />
              <img src="/assets/computer.png" />
              <br />
              <p style="font-weight: bold">
                Une application mobile indiquant le trajet le plus rapide et les
                feux tricolores équipés de notre boitier.
              </p>

              <br />

              <img src="/assets/mobile.png" />
              <br />
              <p style="font-weight: bold">
                Un écran permettant de donner en temps réel le nombre de
                véhicules en intervention et leurs destinations.
              </p>
              <br />
              <hr />
              <h2>L'équipe</h2>
              <br />
              <br />
              <img src="/assets/monsieur.png" style="width: 400px" />
              <h3 style="font-weight: bold">
                Eliott
                <br />
                Développeur Web
              </h3>
              <br />
              <br />
              <img src="/assets/monsieur.png" style="width: 400px" />
              <h3 style="font-weight: bold">
                Quentin
                <br />
                Développeur Back-end
              </h3>
              <br />
              <br />
              <img src="/assets/monsieur.png" style="width: 400px" />
              <h3 style="font-weight: bold">
                Colin
                <br />
                Développeur Back-end
              </h3>
              <br />
              <br />
              <img src="/assets/monsieur.png" style="width: 400px" />
              <h3 style="font-weight: bold">
                Fabien
                <br />
                Développeur Web
              </h3>
              <br />
              <br />
              <img src="/assets/monsieur.png" style="width: 400px" />
              <h3 style="font-weight: bold">
                Yanis
                <br />
                Développeur Mobile
              </h3>
              <br />
              <br />
              <img src="/assets/madame.png" style="width: 400px" />
              <h3 style="font-weight: bold">
                Sophie
                <br />
                Développeur Web
              </h3>
              <br />
              <br />
              <img src="/assets/madame.png" style="width: 400px" />
              <h3 style="font-weight: bold">
                Eloïse
                <br />
                Développeur Web
              </h3>
              <br />
              <br />
              <hr />
              <h5>
                Contactez-nous pour tout type de demandes ou collaboration. Nous
                sommes ouverts pour partager notre vision et
                <br />développer de nouvelles coopérations avec des entreprises.
                <br />
                <br />
                Email : emergen.city.epitech@gmail.com
                <br />
                <br />
                <a href="/support">Formulaire de contact</a>
              </h5>
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
  background-image: url("https://blogvoyages.fr/wp-content/uploads/2017/04/marseille.jpg");
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
  position: relative;
  margin-left: 20px;
  margin-right: 20px;
  right: 0;
  z-index: 2;
  padding: 0 10px;
}
.hr {
  border: 0;
  height: 1px;
  background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
}
</style>

<script>
import LayoutDefault from "../../layouts/LayoutDefault.vue";
import axios from "axios";
import Display from "../../components/MyModal.vue";

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
      console.log("test");
      if (this.email != this.confirm_mail) {
        this.error = "Les emails ne correspondent pas";
        this.$bvModal.show("my-modal");
        return;
      }
      const options = {
        crossdomain: true,
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
          crossDomain: true,
          "Content-Type": "text/plain;charset=utf-8"
        }
      };
      this.password = "123456";
      axios
        .post(
          "http://localhost:9000/register?" +
            "name=" +
            this.email +
            "&password=" +
            this.password,
          null,
          options
        )
        .then(
          response => {
            console.log(response.status);
            console.log(response.data);
            if (response.status === 200) {
              this.enableSubmitLoader();
            }
            this.register_success();
          },
          error => {
            //          this.enableSubmitLoader();
            console.log(error);
            //this.register_success();
          }
        );
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
        this.error = "Le formulaire n'a pas été envoyé";
        this.$bvModal.show("my-modal");
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
