

<template>

  <div class="vehicle">
      {{this.label}}
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
  data() {
    return {
        label: null
    };
  },
      props: {
      total: Array
  },
  watch: {
      total: {
          handler: function(newVal, oldVal) {
          console.log("old:", oldVal, "new: ", newVal)
          if (this.total) {
              this.label = this.total
          }
      }
    }
  },
  methods: {
  },
  mounted() {
    if (this.total) {
        this.label = this.total
    }
  },
  created() {
  },
  
};

</script>
