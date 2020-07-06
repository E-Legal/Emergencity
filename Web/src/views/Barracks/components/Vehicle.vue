

<template>
  <div class="vehicle">
    <div v-if="this.all_vehicle">
        <b-list-group>
          <div v-bind:key="index" v-for="(vehic, index) in all_vehicle[0]">
                            <b-list-group-item href="#" class="flex-column align-items-start">

            <div class="d-flex w-100 justify-content-between">
               <img     width="4%" src="https://exoes.com/site_v2/wp-content/uploads/2017/12/icon-lorry.png"/>
              <h5 class="mb-1">Vehicule <b> {{vehic.model}}</b> n°{{vehic.registration}}</h5>
          
            </div>

            </b-list-group-item>
          </div>
</b-list-group>
    </div>
    <div v-else> Loading </div>
  </div>
</template>

<style scoped>
</style>
<script>

// @ is an alias to /src
import axios from 'axios';

export default {
  name: "vehicle",
  components: {
  },
  props: {
      total: Array,
      vehicle: Array,
  },
  data() {
    return {
        label: null,
        all_vehicle: null
    };
  },
  watch: {
      total: function(newVal, oldVal) {
          console.log("old:", oldVal, "newddd: ", newVal)
          axios.
        get("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrackVehicles/" + this.total + "?token=" + localStorage.getItem('token'))
        .then((response) => {
              if (response.status === 200) {
                this.all_vehicle = response.data['content'];
                console.log(this.vehicule)
                console.log("VEHICULE")
              }
          }, (error) => {
            console.log(error)
          });
      },
  },
  mounted() {
    
    if (this.total) {
      this.label = this.total;
    }
    if (this.vehicle) {
      this.all_vehicle = this.vehicle
    }
  },
  methods: {
    log(message) {
      console.log(message)
      console.log("LOG FUNCTION")
    }
  },
};

</script>
