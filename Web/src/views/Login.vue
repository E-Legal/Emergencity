<template>
  <div class="login" style="overflow: hidden">
    <div style="background: url('/assets/traffics.jpg') no-repeat center center fixed; height: calc(100vh - 64px);  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;">
      <h1 style="color:white" class="d-flex justify-content-center emergencity_title">EMERGENCITY</h1>
      <div
        style="background-color:rgba(158,156,156, 0.3); z-index: 1000"
      >
        <center>
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
              type="text"
              id="password"
              class="col-lg-6 box_space"
              name="login"
              placeholder="Mot de passe"
              v-model="password"
            />
            <br />
            <input type="submit" class="fadeIn fourth button_space" value="Connexion" />
          </form>
        </center>
      </div>
      <div class="container centered" styl e>
        <aside class="col-sm-4" style="margin: 0 auto;">
          <!-- card.// -->
        </aside>
      </div>
      <!-- row.// -->
      {{ email }} {{ password }} {{ log }} {{ info }}
    </div>
  </div>
  <!--container end.//-->
</template>

<style lang="scss">

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

input[type="button"],
input[type="submit"],
input[type="reset"] {
  background-color: rgba(255, 255, 255, 0.1);
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

  padding: 0.6em 2em;
  border-radius: 8px;
  color: #fff;
  background-color: none;
  font-size: 1.1em;
  border: 1px solid white;
  cursor: pointer;
  margin: 1em;
}

input[type="button"]:hover,
input[type="submit"]:hover,
input[type="reset"]:hover {
  background-color: #dadadb;
}

input[type="button"]:active,
input[type="submit"]:active,
input[type="reset"]:active {
  -moz-transform: scale(0.95);
  -webkit-transform: scale(0.95);
  -o-transform: scale(0.95);
  -ms-transform: scale(0.95);
  transform: scale(0.95);
}

input[type="text"] {
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

  -webkit-border-radius: 45px 45px 45px 45px;
  border-radius: 45px 45px 45px 45px;
}

input[type="text"]:focus {
  background-color: #fff;
  border-bottom: 2px solid #5fbae9;
}

input[type="text"]:placeholder {
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
import axios from 'axios'

export default {
  name: "login",
  data() {
    return {
      title: "Vue.js Demo Form",
      email: "",
      password: "",
      submitting: false,
      log: "",
      info: ""
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
            const options = {
        headers: {'accept': 'application/json'}
      }
      axios.
        post('http://localhost:8081/login', { email: this.email, password: this.password}, options)
        .then((response) => {
          console.log(response.status)
          if (response.status === 200) {
            this.enableSubmitLoader();
          }
          this.login_success();
      }, (error) => {
        this.enableSubmitLoader();
        alert("Login without Network")
        console.log(error)
        this.login_success();
      });
    },
    login_success() {
      if (this.submitting === true) {
        localStorage.login = this.email;
        this.log = localStorage.login;
        this.$emit("update:layout", LayoutDefault);
        window.location.href = "/";
        console.log("send");
      } else {
        alert("Mauvais Email ou/et mot de passe");
      }
    },
    validationError() {
      alert("Pas de mot de passe");
    }
  },
  mounted() {
    this.$emit("update:layout", LayoutDefault);
  },
  created() {
    this.log = localStorage.login;
    this.$emit("update:layout", LayoutDefault);
  },
  components: {}
};
</script>
