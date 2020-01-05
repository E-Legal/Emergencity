<template>
  <div class="home">
    <div style="background: url('/assets/black.jpg')   no-repeat center center fixed; height: calc(100vh - 56px);  background-position: center;
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
                src="/img/profile/avatar.png"
                style=""
              />  
         
              </router-link>
            </div>
            <div class="col-lg-4 d-flex justify-content-center img_padd">
              <router-link class="nav-link" to="/stats">
              <img 
                src="/img/profile/profits.png"
                style=""
              /></router-link>
            </div>
            <div class="col-lg-4 d-flex justify-content-center img_padd">
               <router-link class="nav-link" to="/dashboard">
              <img
                src="/img/profile/map.png"
                style=""
              /></router-link>
            </div>
            <div
              class="col-lg-4 offset-lg-4 d-flex justify-content-center img_padd"
             
            >
              <img 
                src="/img/profile/car.png"
             
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
.img_border {
    border-style: solid;
  border-width: 2px;
  border-color: white;
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
