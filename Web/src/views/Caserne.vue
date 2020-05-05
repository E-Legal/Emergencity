

<template>

  <div class="caserne">
    <div style="background: url('/assets/white3.png') repeat center center fixed;  background-position: center;   background-size: cover;">

    <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" />
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 
 <b-card no-body @caserneget.prevent="caserneget">
    <b-tabs pills card vertical nav-wrapper-class="w-50">
      <b-tab active>{{caserneget.city}}<b-card-text>
        <b-list-group>
 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">{{caserneget.namecaserne }}</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

   <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°1245</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°2110</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>
</b-list-group>
</b-card-text></b-tab>
      <b-tab title="Paris"><b-card-text>
        <b-list-group>
 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°5240</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

   <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°4245</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°3110</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>
</b-list-group>
        </b-card-text></b-tab>
      <b-tab title="Lyon"><b-card-text>
        <b-list-group>
 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°0240</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

   <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°0245</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°0110</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>
</b-list-group>
        </b-card-text></b-tab>
      <b-tab title="Perpignan"><b-card-text>
        <b-list-group>
 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°8240</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

   <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°8245</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>

 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°8110</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1
    </p>

  </b-list-group-item>
</b-list-group>
        </b-card-text></b-tab>
    </b-tabs>
  </b-card>
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
 <br/>  
  <br/>  
 <br/>  
 <br/>  
 <br/>  
  <br/>  
 <br/>  
 <br/>  
    </div>
    
    
  <!--container end.//-->
     </div>
</template>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');
@import url('https://fonts.googleapis.com/css?family=Raleway:300,400');
body {
  background: #2D3142;
  font-family: 'Raleway', sans-serif;
}
.mybutton:link
{
text-decoration:none;
color: black;
}

/* Heading */




/* Layout Styling */

#container {
  width: 100%;
  margin: 0 auto;
  padding: 50px 0 150px 0;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}


#button-2 {
  position: relative;
  overflow: hidden;
  cursor: pointer;
}

#button-2 a {
  position: relative;
  transition: all .35s ease-Out;
}

#slide {
  width: 100%;
  height: 100%;
  left: -200px;
  background: #BFC0C0;
  position: absolute;
  transition: all .35s ease-Out;
  bottom: 0;
}

#button-2:hover #slide {
  left: 0;
}

#button-2:hover a {
  color: #2D3142;
}



/* Button Styles */

.button {
  display: inline-flex;
  height: 40px;
  width: 150px;
  border: 2px solid #BFC0C0;
  margin: 20px 20px 20px 20px;
  color: #BFC0C0;
  text-transform: uppercase;
  text-decoration: none;
  font-size: .8em;
  letter-spacing: 1.5px;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

.mybutton {
  color: #BFC0C0;
  text-decoration: none;
  letter-spacing: 1px;
}


#button-4 {
  position: relative;
  overflow: hidden;
  cursor: pointer;
}

#button-4 a {
  position: relative;
  transition: all .45s ease-Out;
}

#underline {
  width: 100%;
  height: 2.5px;
  margin-top: 15px;
  align-self: flex-end;
  left: -200px;
  background: #BFC0C0;
  position: absolute;
  transition: all .3s ease-Out;
  bottom: 0;
}

#button-4:hover #underline {
  left: 0;
}


/* STRUCTURE */
.emergencity_title {
  padding: 80px;
  font-size: 70px;
  font-weight:bold;
}

.wrapper {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  min-height: 100%;
  padding: 20px;
}

#formContent {
  -webkit-border-radius: 10px 10px 10px 10px;
  border-radius: 10px 10px 10px 10px;
  background: #fff;
  padding: 30px;
  width: 90%;
  max-width: 450px;
  position: relative;
  padding: 0px;
  -webkit-box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
  box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
  text-align: center;
}

#formFooter {
  background-color: #f6f6f6;
  border-top: 1px solid #dce8f1;
  padding: 25px;
  text-align: center;
  -webkit-border-radius: 0 0 10px 10px;
  border-radius: 0 0 10px 10px;
}

/* TABS */

h2.inactive {
  color: #cccccc;
}

h2.active {
  color: #0d0d0d;
  border-bottom: 2px solid #5fbae9;
}

/* FORM TYPOGRAPHY*/



/* ANIMATIONS */

/* Simple CSS3 Fade-in-down Animation */
.fadeInDown {
  -webkit-animation-name: fadeInDown;
  animation-name: fadeInDown;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

@-webkit-keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

@keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}
.box_space {
  margin-top: 30px !important;
  margin-bottom: 20px !important;
  opacity:0.7;
}
.button_space {
  margin-bottom: 50px !important;
  opacity:0.7;
}
.underlineHover:after {
  display: block;
  left: 0;
  bottom: -10px;
  width: 0;
  height: 2px;
  background-color: #56baed;
  content: "";
}

.underlineHover:hover {
  color: #0d0d0d;
}

.underlineHover:hover:after {
  width: 100%;
}

/* OTHERS */

*:focus {
  outline: none;
}

#icon {
  width: 60%;
}

* {
  box-sizing: border-box;
}



</style>
<script>

// @ is an alias to /src
import LayoutDefault from "../layouts/LayoutDefault.vue";
import Display from "../components/MyModal.vue";
import Map from "../components/Map.vue";
import axios from 'axios';

export default {
  name: "profile",
  components: {
    Display,
    Map
  },
  data() {
    return {
      title: "Vue.js Demo Form",
      email: "",
      password: "",
      submitting: false,
      log: "",
      edit: false,
      error: "",
      city: "",
      namecaserne: ""

    };
  },
  methods: {
    save_edit() {
      this.error = "Sauvegarde effectué!"
      this.$bvModal.show('my-modal')
    },
      greet: function () {
      if (this.edit == false) {
        this.edit = true;
      } else {
      this.edit = false;
      }
    },
    caserneget() {
       axios.
        get("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks?token=" + "405472fe")
        .then((response) => {
          console.log(response.status)
          console.log(response.data)
          if (response.status === 200) {
            this.enableSubmitLoader();
          }
          this.city = response.city
          this.namecaserne = response.name
          this.register_success();
      }, (error) => {
        console.log(error)
      });
    
    },
    editProfile() {

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
    this.$emit("update:layout", LayoutDefault);
    this.caserneget();
    console.log("test999")
  },
  created() {
  },
  
};

</script>
