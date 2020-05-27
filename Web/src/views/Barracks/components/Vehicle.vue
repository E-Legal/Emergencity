

<template>

  <div class="vehicle">
    <div v-if="this.all_vehicle">
            {{this.all_vehicle}}
        <b-list-group>
 <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Vehicule n°0240</h5>
   
    </div>

    <p class="mb-1">
      Description numéro 1

      {{this.log(this.all_vehicle)}}
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
    </div>
    <div v-else> Loading </div>
  </div>
</template>

<style lang="scss">

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
          console.log("old:", oldVal, "new: ", newVal)
          if (this.total) {
              console.log("YOOOLOOOO")
              this.label = this.total
          }
      },
      vehicle: function(newVal, oldVal) {
          console.log("old:", oldVal, "new: ", newVal);
          console.log("OLLLLD VEHIICCLLLLE");
          if (this.vehicle) {
            console.log("YOLOOOOOOOO");
              this.all_vehicle = this.vehicle;
          }
      }
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
