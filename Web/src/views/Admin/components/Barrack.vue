/* eslint-disable */
<template>
  <div name="Barrack">
      <div>
      
<b-tabs content-class="mt-3" justified>
    <b-tab  v-if="this.label" title="Gestion Caserne">
    
        <form id="demo" @submit.prevent="submit" novalidate>
            <input
              type="text"
              id="login"
              class="col-lg-3"
              name="city"
              placeholder="Ville"
              v-model="city"
            />
            <input
              type="text"
              id="password"
              class="col-lg-3"
              name="caserne"
              placeholder="Nom de caserne"
              v-model="nom"
              style="margin-left: 1%; margin-right: 1%"
            />
            <input type="submit" class="btn btn-outline-primary" value="Ajouter une caserne" />
      </form>
      <vuetable ref="vuetable"
    :fields="['name', 'city']"
    :api-mode="false"
    :data="this.label"
  >
    </vuetable></b-tab>
    <b-tab v-else title="Gestion Caserne"><div class="loader"></div></b-tab>
</b-tabs>
</div>
  </div>
</template>

<style scoped>
.gmap {
width: 1920px; height: calc(100vh - 66px)
}
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


/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>

<script>
import Vuetable from 'vuetable-2'
import axios from 'axios';

export default {
  name: "Barrack",
  components: {
    Vuetable
  },
  props: {
      total: Array
    },
  data() {
    return {
        city: null,
        nom: null,
        label: null,
    };
  },
  watch: { 
    // Watch Total Props and put it into Label State
      	total: function(newVal, oldVal) { 
          console.log('Prop changed: ', newVal, ' | was: ', oldVal);
          console.log(this.total);
          if (this.total) {
            this.total.forEach((value, index) => {
                  this.label = this.total;
                  console.log('label=')
                  console.log(this.label)
            });
        }
    }
  },
  methods: {
    submit() {
      console.log("tqdkslkdm")
      axios.post('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks/new?city=' + this.city+ '&name=' + this.nom + '&token=' + localStorage.getItem('token'))
        .then((response) => {
           if (response.status === 200) {
             this.$forceUpdate();
           }
        });
    }
  }   
};

</script>
