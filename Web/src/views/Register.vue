<template>
  <div class="register">
    <div class="container-flex centered">
   <div
      style="background: url('/assets/gray.jpg') no-repeat center center fixed; height: calc(100vh - 56px);  background-position: center;
  background-repeat: no-repeat;
  background-size: cover; "
    >
        <h1 class="d-flex justify-content-center bold_title inscription">INSCRIPTION</h1>
        <h1 class="d-flex justify-content-center emergencity">Emergencity</h1>
          <div style="background-color:rgba(158,156,156, 0.5); z-index: 1000; height: calc(50vh - 56px)">
                      <div class="vertical-center">
            <form id="demo" @submit.prevent="submit" novalidate>
              <div class="row">
                  <div class="col-lg-6">
                  <input
                   class="form-control form-rounded"
                    name="name"
                    id="name"
                    placeholder="Nom"
                    v-model="nom"
                    required
                  />
                </div>
                <div class="col-lg-6">
                  <input
                   class="form-control form-rounded"
                    name="prenom"
                    id="prenom"
                    placeholder="Prénom"
                    v-model="prenom"
                    required
                  />
                </div>

                <div class="col-lg-6">
                  <input
                   class="form-control form-rounded"
                    name="profession"
                    id="profession"
                    placeholder="Profession"
                    v-model="profession"
                    required
                  />
                </div>

                  <div class="col-lg-6">
                  <input
                   class="form-control form-rounded"
                     name="email"
                    id="email"
                    placeholder="Adresse Email"
                    v-model="email"
                    required
                  />
                </div>

                <div class="col-lg-6">
                  <input
                    class="form-control form-rounded"
                    name="ville"
                    id="ville"
                    placeholder="Ville"
                    v-model="ville"
                    required
                  />
                </div>

                <div class="col-lg-6">
                  <input
                   class="form-control form-rounded"
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
                  class="btn btn-primary"
                  type="submit"
                  value="Demande envoyé"
                />
              </div>
            </form>
          </div></div>
        </div>
        <!-- col.//-->
        <!-- row.//-->
      </div>
    </div>

</template>

<style scoped>
.padd-input {
  background: transparent;
  background: transparent;
  border-top: transparent !important;
  border-left: transparent !important;
  border-right: transparent !important;
  border-bottom: 4px solid !important;
  font-weight: 200;
  color: white;
  padding-left: 0;
  margin-top: 20px;
  margin-left: 20%;
  margin-right: 20%;
  margin-bottom: 6%;
  width: 50%;
}

.inscription {
  padding-top: 20px;
  font-weight: bold;
  font-size: 70px;
  color: white;
}
.emergencity {
    color: white;
      font-size: 40px;
      margin-bottom: 40px;
}
.dot {
  height: 25px;
  width: 25px;
  background-color: white;
  border-radius: 50%;
  display: inline-block;
}
.padd-input,
.padd_input::-webkit-input-placeholder {
  font-size: 18px;
  line-height: 2;
  padding-left: 20px;
}

.bold_title {
  font-weight: bold;
}
.vertical-center {
  margin: 0 auto;
  position: relative;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}
/* 
 */
 @import url('https://fonts.googleapis.com/css?family=Poppins');


/* STRUCTURE */

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
  -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
  box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
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


input[type=button], input[type=submit], input[type=reset]  {
  background-color: rgba(255,255,255,0.1);
  border: none;
  color: white;
  padding: 15px 80px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  text-transform: uppercase;
  font-size: 13px;
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
  margin: 5px 20px 40px 20px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
  padding:0.6em 2em;
  border-radius: 8px;
  color:#fff;
  background-color: none;
  font-size:1.1em;
  border: 1px solid white;
  cursor:pointer;
  margin:1em;
}

input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover  {
  background-color: #DADADB;
}

input[type=button]:active, input[type=submit]:active, input[type=reset]:active  {
  -moz-transform: scale(0.95);
  -webkit-transform: scale(0.95);
  -o-transform: scale(0.95);
  -ms-transform: scale(0.95);
  transform: scale(0.95);
}


input[type=text] {
  background-color: #f6f6f6;
  border: none;
  color: #0d0d0d;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 5px;
  width: 85%;
  border: 2px solid #f6f6f6;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
  transition: all 0.5s ease-in-out;
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
}

input[type=text]:focus {
  background-color: #fff;
  border-bottom: 2px solid #5fbae9;
}

input[type=text]:placeholder {
  color: #cccccc;
}



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

/* Simple CSS3 Fade-in Animation */
@-webkit-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@-moz-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@keyframes fadeIn { from { opacity:0; } to { opacity:1; } }

.fadeIn {
  opacity:0;
  -webkit-animation:fadeIn ease-in 1;
  -moz-animation:fadeIn ease-in 1;
  animation:fadeIn ease-in 1;

  -webkit-animation-fill-mode:forwards;
  -moz-animation-fill-mode:forwards;
  animation-fill-mode:forwards;

  -webkit-animation-duration:1s;
  -moz-animation-duration:1s;
  animation-duration:1s;
}

.fadeIn.first {
  -webkit-animation-delay: 0.4s;
  -moz-animation-delay: 0.4s;
  animation-delay: 0.4s;
}

.fadeIn.second {
  -webkit-animation-delay: 0.6s;
  -moz-animation-delay: 0.6s;
  animation-delay: 0.6s;
}

.fadeIn.third {
  -webkit-animation-delay: 0.8s;
  -moz-animation-delay: 0.8s;
  animation-delay: 0.8s;
}

.fadeIn.fourth {
  -webkit-animation-delay: 1s;
  -moz-animation-delay: 1s;
  animation-delay: 1s;
}

/* Simple CSS3 Fade-in Animation */
.underlineHover:after {
  display: block;
  left: 0;
  bottom: -10px;
  width: 0;
  height: 2px;
  background-color: #56baed;
  content: "";
  transition: width 0.2s;
}

.underlineHover:hover {
  color: #0d0d0d;
}

.underlineHover:hover:after{
  width: 100%;
}



/* OTHERS */

*:focus {
    outline: none;
} 

#icon {
  width:60%;
}

* {
  box-sizing: border-box;
}
.form-rounded {
  opacity: 0.6 !important;
border-radius: 1rem;
width: 50%;
  margin: 0 auto;
  position: relative;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  
  margin-bottom: 3%;
}
</style>

<script>

import LayoutDefault from "../layouts/LayoutDefault.vue";
import axios from 'axios'

export default {
  name: "register",
  components: {},
  data() {
    return {
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
      const options = {
        headers: {'accept': 'application/json'}
      }
      alert("yolo")
      alert("swag")
      console.log("test")
      if (this.email != this.confirm_mail) {
        alert("Email different");
        return;
      }
      axios.
        post('http://localhost:8081/', { nom: this.prenom, prenom: this.prenom, profession: this.profession, ville: this.ville, email: this.email}, options)
        .then((response) => {
          console.log(response.status)
          if (response.status === 200) {
            this.enableSubmitLoader();
          }
          this.register_success();
      }, (error) => {
        alert("Register without Network");
                    this.enableSubmitLoader();
        console.log(error)
        this.register_success();
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
              alert("Le Formulaire n'a pas été envoyé")
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
