<template>
  <div class="admin">
    <div class="container">
      <div class="Chart">
        <h2>Menu d'Administration</h2>
      </div>
    </div>
    <div class="container">
      <div class="Chart">
        <b-card no-body>
          <b-tabs pills card vertical v-model="selected">
            <!--- 0 -->
            <b-tab title="Utilisateurs" lazy>
              <User :total="this.users" />
            </b-tab>
            <!--- 1 -->
            <b-tab title="Casernes" lazy>
              <Barrack :total="this.barracks" />
            </b-tab>
            <!--- 2 -->
            <b-tab title="Feux de signalisation" lazy>
              <Light :total="this.light" />
            </b-tab>
          </b-tabs>
        </b-card>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.loader {
  border: 10px solid #f3f3f3;
  border-radius: 50%;
  border-top: 10px solid #3498db;
  width: 30px;
  margin: 0 auto;
  height: 30px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

.Chart {
  background: #212733;
  border-radius: 15px;
  box-shadow: 0px 2px 15px rgba(25, 25, 25, 0.27);
  margin: 25px 0;
}

.Chart h2 {
  margin-top: 0;
  padding: 15px 0;
  color: #ababab;
  text-align: center;
  border-bottom: 1px solid #323d54;
}
.container {
  max-width: 1500px;
}
/* Safari */
@-webkit-keyframes spin {
  0% {
    -webkit-transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
  }
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
<script>
import LayoutDefault from "../../layouts/LayoutDefault.vue";
import Barrack from "./components/Barrack.vue";
import Light from "./components/Light.vue";
import User from "./components/User.vue";
import axios from "axios";

export default {
  name: "admin",
  components: {
    Barrack,
    Light,
    User
  },
  data() {
    return {
      users: null,
      barracks: null,
      light: null,
      selected: 0
    };
  },
  watch: {
    selected(value) {
      console.log("test");
      if (value === 0) {
        // Mettre requête Utilisateur
        this.$store.dispatch("User/handleListUsers", {token: this.$store.getters["User/token"]}).then((response) => {
           this.users = this.$store.getters['User/listUsers']
        })
      }
      if (value === 1) {
        // Mettre requête Caserne
        this.$store.dispatch("Barrack/getBarracks", this.$store.getters["User/token"]).then((response) => {
           this.barracks = this.$store.getters['Barrack/barracks']
          console.log(this.caserne, "yoloswag")
        })
        // axios
        //   .get(
        //     "http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks?token=" +
        //       localStorage.getItem("token")
        //   )
        //   .then(response => {
        //     console.log("Barracks=");

        //     this.barracks = response.data["content"];
        //     console.log(this.barracks);
        //   });
      }
      if (value === 2) {
        // Mettre requête Feux de signalisation
        this.$store.dispatch("Light/getTrafficLights", {token: this.$store.getters["User/token"]}).then((response) => {
           this.light = this.$store.getters["Light/listTraffic"]
           console.log(this.$store.getters, "GETTER");
          console.log(this.light, "yoloswag")
        })
        // axios
        //   .get(
        //     "http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/feu?token=" +
        //       localStorage.getItem("token")
        //   )
        //   .then(response => {
        //     this.light = response.data["content"];
        //   });
      }
    }
  },
  mounted() {
    this.$store.dispatch("User/handleListUsers", {token: this.$store.getters["User/token"]}).then((response) => {
      this.users = this.$store.getters['User/listUsers']
    })
    // axios
    //   .get(
    //     "http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/SU/user/list?token=" +
    //       localStorage.getItem("token")
    //   )
    //   .then(response => {
    //     console.log(response.data);
    //     this.users = response.data["content"];
    //   });
    this.$emit("update:layout", LayoutDefault);
  },
  created() {
    this.log = localStorage.login;
    this.$emit("update:layout", LayoutDefault);
  }
};
</script>
