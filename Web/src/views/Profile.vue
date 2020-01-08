<template>

  <div class="profile">
    <link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" />
    <div style="background: url('/assets/white3.png') repeat center center fixed;  background-position: center;">
  <center>
     <br>
      <br>
      <img style="width: 150px; height: 150px;" src="/assets/marie.png" />
      <br>
      <br>
      <Display :msg="this.error" :reload="true"/>
 <h2 style="font-family: 'Raleway', sans-serif;">Bienvenue, <i style="color:gray">Marie</i></h2>
        </center>
        <center>
        <form action="/" class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin">
        <div class="w3-row w3-section">
  <div class="w3-col" style="width:50px">  <i class="fa fa-pen"></i></div>
    <div class="w3-rest">
      <div class="button" id="button-4">
    <div id="underline"></div>
    <a v-on:click="greet" style="text-decoration:none;">Editer informations</a>
  </div>
    </div>
</div>
<br>

 
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input  v-if="this.edit" style="width:350px; height: 50px" class="w3-input w3-border" name="first" type="text" placeholder="" value="Marie">
      <input v-else style="width:350px; height: 50px" class="w3-input w3-border" name="first" type="text" placeholder="" value="Marie" disabled>

    </div>
</div>
<br>
<br>
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input  v-if="this.edit" style="width:350px; height: 50px" class="w3-input w3-border" name="last" type="text" placeholder="" value="Boodart">
      <input  v-else style="width:350px; height: 50px" class="w3-input w3-border" name="last" type="text" placeholder="" value="Boodart" disabled>
    </div>
</div>
<br>
<br>
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope"></i></div>
    <div class="w3-rest">
      <input  v-if="this.edit" style="width:350px; height: 50px" class="w3-input w3-border" name="email" type="text" placeholder="" :value="this.log">
      <input v-else style="width:350px; height: 50px" class="w3-input w3-border" name="email" type="text" placeholder="" :value="this.log" disabled>
    </div>
</div>
<br>
<br>
<div class="w3-row w3-section">
  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input id="oui" v-if="this.edit" style="width:350px; height: 50px" class="w3-input w3-border" name="last" type="text" placeholder="Fonction" value="Agent">
      <input id="lol" v-else style="width:350px; height: 50px" class="w3-input w3-border" name="last" type="text" value="Agent" placeholder="Fonction" disabled>
    </div>
</div>
<br>
<br>
<div class="w3-row w3-section">
   <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
    <div class="w3-rest">
      <input  v-if="this.edit" style="width:350px; height: 50px" class="w3-input w3-border" name="phone" type="text" placeholder="" value="Marseille" >
      <input v-else style="width:350px; height: 50px" class="w3-input w3-border" name="phone" type="text" placeholder=""  value="Marseille" disabled>
    </div>
</div>
<br>
<br>
<div v-on:click="save_edit" class="button" id="button-2">
    <div id="slide"></div>
Sauvegarder
  </div>
  <br>
  <br>
</form>
</center>
      </div>
    </div>
  <!--container end.//-->
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
      error: ""
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
    submit() {
      if (this.password) {
        this.sendFormData();
      } else {
        this.validationError();
      }
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
  },
  created() {
                this.log = localStorage.login;
               const options = { crossdomain: true,
        headers: {'Content-Type': 'application/x-www-form-urlencoded', 'crossDomain': true, 'Content-Type': 'text/plain;charset=utf-8',}
      }
    axios.
        post("http://localhost:9000/login?" + "name=" + this.email + "&password=" + "123456", null, options)
        .then((response) => {
          if (response.status === 200) {
            this.log = response.data["name"]
          }
      }, (error) => {
                    this.log = localStorage.login;
      });

    this.$emit("update:layout", LayoutDefault);
  },
};
</script>
