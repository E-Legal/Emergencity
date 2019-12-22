<template>
  <div class="LayoutDefault">
      <nav class="navbar navbar-expand-lg shadow" style="    padding-right: 14%;">
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarText"
          aria-controls="navbarText"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav ml-auto">

            <li v-if="!user" class="nav-item">
              <router-link class="nav-link" to="/register"
                >Inscription</router-link
              >
            </li>
            <li v-if="!user" class="nav-i tem">
              <router-link class="nav-link" to="/login">Connexion</router-link>
            </li>

              
  
               <li v-if="user" class="nav-item">
                <router-link class="navbar-brand" to="/"><span class="power_up"><img class="pict" src="/img/icons/map.svg"/></span><span class="link"> Map </span></router-link>
            </li>
            <li v-if="user" class="nav-item">
                <router-link class="navbar-brand" to="/"><span class="power_up"><img class="pict" src="/img/icons/bar.svg"/></span><span class="link"> Statistique </span></router-link>
            </li>
             <li v-if="user" class="nav-item">
                <router-link class="navbar-brand" to="/"><span class="power_up"><img class="pict" src="/img/icons/car.svg"/></span><span class="link"> Véhicules </span></router-link>
            </li>
            <li v-if="user" class="nav-item">
              <b-dropdown id="dropdown-dropleft" variant="link"  toggle-class="text-decoration-none" no-caret>
                <template v-slot:button-content>
                  <span class="sr-only">Search</span>
                  <img class="pict" style="height: 30px; width: 30px" src="/img/icons/user.svg"/>
                </template>
                <b-dropdown-item disabled><center><img style="width: 30px; height: 30px;" src="/assets/marie.png"/></center></b-dropdown-item>
                <b-dropdown-item disabled><center>Marie Boodart</center></b-dropdown-item>
                <b-dropdown-item disabled><center><u>marie.boodart@email.com</u></center></b-dropdown-item>
                <b-dropdown-item><center><router-link class="nav-link" to="/logout">Deconnexion</router-link></center></b-dropdown-item>
              </b-dropdown>
            </li>

            <!-- <li>
              <router-link class="nav-link" to="/about"> <img src="/assets/interrogation.png" /> </router-link>
            </li> -->
          </ul>
          <!----
    <span class="navbar-text">
      <div class="LayoutDefault__user">
        <div v-if="user"> {{user.name}} </div>
        <div v-else>
        No User
      </div>
      </div>

    </span>---->
        </div>
      </nav>

    <main class="LayoutDefault__main">
      <slot />
    </main>
    <footer class="LayoutDefault__footer"></footer>
    
  </div>
</template>

<style scoped lang="scss">
.shadow {
box-shadow: 3px 7px 23px 1px rgba(0,0,0,0.75) !important;
background: linear-gradient(90deg, rgba(81,84,87,1) 0%, rgba(52,58,64,1) 59%);
};
.power_up img{
            transition: 0.2s ease-in-out;
          position: relative;
          height: 30px;
          width: 30px;
}
.navbar {
    border: 0;
    border-radius: 0;
    .nav li > router-link, .navbar-brand {

      background: transparent !important;

      
      &:hover{
        .power_up img{
          height: 25px;
          width: 25px;
          transition: 0.2s ease-in-out;
          position: relative;
        }
        background-color: rgba(255,255,255,0.2);

        .link {

          //padding: 0 5px 0 5px;
          visibility: visible;
          font-size: 14px;
        }
      }
    }
    .link {
      width: 0;
      font-family: 'Roboto', sans-serif;
      transition: 0.2s ease-in-out;
      visibility: hidden;
      font-size: 0px;
    }
    span {
    
      color: white;
    }
    .navbar-toggle {
      padding-right: 0;
      .icon-bar {
        background: white;
      }
    }
    .navbar-collapse {
      display: none;
    }
    button {
      background: transparent;
    }
    button[type=submit] {
      padding-right: 0;
      span {
        transition: 0.3s ease-in-out;
      }
      &:hover {
        span {
          transform: scale(1.3) rotate(90deg);
        }
      }
    }
    form {
      padding: 0;
      .form-control {
        border: 0;
        border-radius: 0;
        color: black;
        font-weight: bold;
        background: rgba(255,255,255,0.7);
        transition: 0.2s ease-in-out;
        &:hover {
          background: white;
        }
      }
    }
}
</style>

<script>

export default {
  name: `LayoutDefault`,
  data() {
    return {
      user: null
    };
  },
  watch() {},
  mount() {
    this.user = localStorage.login;
  },
  created() {
    // Simulate fetching user data.
    this.user = localStorage.login;
  },
  methods: {
    
  }
};
</script>
