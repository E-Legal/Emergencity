<template>
  <div class="barracksmodal">
  {{log(this.idBarracks)}}

  <b-modal centered title="BootstrapVue" hide-header no-close-on-backdrop="true" hide-footer ref="barracks_modal" id="my-modal">  {{this.idBarracks}}
      <b-button class="mt-3" variant="outline-danger" block @click="hideModal">Ok</b-button>
  </b-modal>
  </div>
</template>

<style scoped>

</style>

<script>
import Vuetable from 'vuetable-2';
import axios from 'axios';

export default {
  name: "barracksmodal",
  components: {},
  props: {
    msg: String,
    idBarracks: String,
    reload: Boolean
    },
  watch: { 
      	idBarracks: function(newVal, oldVal) { // watch it
          console.log('Prop changed: ', newVal, ' | was: ', oldVal);
          console.log("barracks")
      }
  },
  data() {
    
    return {
      oui: "oui"
    }
  },

  methods: {
    log(message) {
      console.log(this.idBarracks)
    },
    showModal() {
        this.$refs['barracks_modal'].show()
      },
      hideModal() {
        this.$refs["barracks_modal"].hide()
      },
      toggleModal() {
        // We pass the ID of the button that we want to return focus to
        // when the modal has hidden
        this.$refs['barracks_modal'].toggle('#toggle-btn')
      },
toggleInfoWindow: function(marker, idx) {
    this.infoWindowPos = marker.position;
    this.infoOptions.content = marker.infoText;
    if (this.currentMidx == idx) {
      this.infoWinOpen = !this.infoWinOpen;
    }
    else {
      this.infoWinOpen = true;
      this.currentMidx = idx;
    }
  }
  }
}
</script>