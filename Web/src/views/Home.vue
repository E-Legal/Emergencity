<template>
  <div class="home">
    <div style="background: url('/assets/gray.jpg')  no-repeat center center fixed; height: calc(100vh - 56px);  background-position: center;
  background-repeat: no-repeat;
  background-size: cover; " class="container-fluid">
  <br>
          <h1
            class="d-flex justify-content-center bold_title bienvenue "
          >
            Bienvenue
          </h1>
          <h2 class="d-flex justify-content-center emergencity">
            Emergencity
          </h2>
          <div class="row">
            <div class="col-lg-4 d-flex justify-content-center img_padd">
              <router-link class="nav-link" to="/profile">
              <img
                src="/assets/profile_claire.png"
                style=" width: 150px; height: 150px;"
              />  
         
              </router-link>
            </div>
            <div class="col-lg-4 d-flex justify-content-center img_padd">
              <router-link class="nav-link" to="/stats">
              <img
                src="/assets/STAT8BLC.png"
                style=" width: 130px; height: 150px;"
              /></router-link>
            </div>
            <div class="col-lg-4 d-flex justify-content-center img_padd">
               <router-link class="nav-link" to="/dashboard">
              <img
                src="/assets/map_clair.png"
                style=" width: 130px; height: 150px;"
              /></router-link>
            </div>
            <div
              class="col-lg-6 d-flex justify-content-center img_padd"
              style="padding-left: 17%"
            >
              <img
                src="/assets/wifi_blc.png"
                style=" width: 150px; height: 150px;"
              />
            </div>
            <div
              class="col-lg-6 d-flex justify-content-center img_padd"
              style="padding-right: 17%"
            >
              <img
                src="/assets/ambulance_blc.png"
                style=" width: 200px; height: 150px;"
              />
            </div>
          </div>
        </div>
      </div>
</template>

<style scoped>

.bienvenue {
  font-weight: bold;
  font-size: 70px;
  color: white;
  margin-top: 50px;
  margin-bottom: 5px;
}
.emergencity {
  font-size: 40px;
  color: white;
  margin-bottom: 100px;
}
.img_padd {
  padding-bottom: 5%;
}
.bold_title {
  font-weight: bold;
}
#pointer {
  transform: rotate(90deg);
  width: 200px;
  height: 40px;
  position: relative;
  background: blue;
}
#pointer:after {
  content: "";

  border-left: 20px solid transparent;
  border-top: 20px solid transparent;
  border-bottom: 20px solid transparent;
}
#pointer:before {
  content: "";
  position: absolute;
  right: -20px;
  bottom: 0;
  width: 0;
  height: 0;
  border-left: 20px solid blue;
  border-top: 20px solid transparent;
  border-bottom: 20px solid transparent;
}
</style>
<script>
// @ is an alias to /src
import LayoutDefault from "../layouts/LayoutDefault.vue";

export default {
  name: "home",
  components: {},
  data() {
    return {
      email: "",
      password: "",
      submitting: false,
      log: ""
    };
  },
  mounted() {
    this.$emit("update:layout", LayoutDefault);
  },
  created() {
    this.log = localStorage.login;
    this.$emit("update:layout", LayoutDefault);
  }
};
</script>
