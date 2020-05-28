

<template>
  <div class="caserne">
    <div style="background: url('/assets/white3.png') repeat center center fixed;  background-position: center;   background-size: cover;">

 <br>

 <template v-if="this.total_vehicle">
  </template>
 <b-card>
    <b-tabs pills card vertical nav-wrapper-class="w-50" v-model="selected">
      <template v-for="cas in caserne">
        <b-tab item :title="cas.name" :key="cas.id" lazy>
          <Vehicle :key="cas.id + 'vehi'" :total="getTabs()" :vehicle="getVehicule()"/>
        </b-tab>
      </template>
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
import LayoutDefault from "../../layouts/LayoutDefault.vue";
import Display from "../../components/MyModal.vue";
import axios from 'axios';
import Vehicle from './components/Vehicle.vue'

export default {
  name: "caserne",
  data() {
    return {
      total_vehicle: null,
      caserne: null,
      selected: 0,
      all_vehicle: null
    };
  },
  components: {
    Display,
    Map,
    Vehicle
  },
  watch: {
    selected(value) {
      if (this.caserne) {
        console.log(this.caserne[value]["id"]);
        this.total_vehicle = [this.caserne[value]]
        console.log(this.total_vehicle)
        this.vehiculeget(this.caserne[value]["id"])
      }
      console.log(value);
      console.log("SELECTED")
    }
  },
  methods: {
    getTabs() {
      return(this.total_vehicle);
    },
    getVehicule() {
      console.log(this.vehicule, "VEHI")
      return(this.vehicule)
    },
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
        get("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrack/user/" + localStorage.getItem('id_user') + "?token=" + localStorage.getItem('token'))
        .then((response) => {
          if (response.status === 200) {
            this.caserne = response.data['content'][0];
            console.log(this.caserne);
            console.log("caserne");
            return(this.caserne)
          }
      }, (error) => {
        console.log(error)
      });
    
    },
     vehiculeget(idBarracks) {
       axios.
        get("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrackVehicles/" + idBarracks + "?token=" + localStorage.getItem('token'))
        .then((response) => {
          if (response.status === 200) {
            this.vehicule = response.data['content'];
            console.log(this.vehicule)
            console.log("VEHICULE")
          }
      }, (error) => {
        console.log(error)
      });
    
    },
  },
  beforeMount() {
    this.$emit("update:layout", LayoutDefault);
    this.caserneget();
  },
  created() {
    console.log("created");
  },
  
};

</script>
