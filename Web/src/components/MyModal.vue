<template>
  <div class="display">

  <b-modal centered title="BootstrapVue" hide-header no-close-on-backdrop="true" hide-footer ref="my-modal" id="my-modal">{{this.msg}}
      <b-button class="mt-3" variant="outline-danger" block @click="hideModal">Ok</b-button>
  </b-modal>
  </div>
</template>

<style scoped>

</style>

<script>


export default {
  name: "display",
  components: {},
  props: {
    msg: String,
    reload: Boolean
    },
  data() {
    
    return {
      oui: "oui"
    }
  },
  methods: {
    showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
      },
      toggleModal() {
        // We pass the ID of the button that we want to return focus to
        // when the modal has hidden
        this.$refs['my-modal'].toggle('#toggle-btn')
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