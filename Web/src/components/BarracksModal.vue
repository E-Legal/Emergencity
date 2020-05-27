<template>
  <div class="barracksmodal">
  <b-modal centered title="BootstrapVue" hide-header no-close-on-backdrop="true" hide-footer ref="barracks_modal" id="barracks_modal">  
    <div v-if="this.idBarracks"> 
      <div v-if="this.barracks">
               <vuetable ref="vuetable"
    :fields="fields"
    :api-mode="false"
    :data="this.barracks"
  >
        <template slot="actions" slot-scope="props"> 
          <div>

            <b-button :id="props['rowData'].id" v-on:click="showMsgBoxOne(props, props['rowData'].id)" variant="danger">Supprimer</b-button>
          </div>
        </template>
    </vuetable>
      </div>
      <div v-else>
        
      </div>
    </div>
    <div v-else> 
      Loading
    </div>
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
  components: {
    Vuetable
  },
  props: {
    msg: String,
    idBarracks: String,
    reload: Boolean
    },
  watch: { 
      	idBarracks: function(newVal, oldVal) { // watch it
          console.log('Prop changed: ', newVal, ' | was: ', oldVal);
          console.log("barracks")
          this.linkedBarracks()
        }
  },
  data() {
    
    return {
      fields: [
        {
          name: 'city',
          title: 'Ville'
        },
        {
          name: 'name',
          title: 'Caserne'
        },
      {
          name: 'rank_actions',
          title: 'Rang',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        },
      {
          name: 'caserne_actions',
          title: 'Caserne',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        },
        {
          name: 'actions',
          title: 'Actions',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        }
      ],
      barracks: null,
    }
  },

  methods: {
    log(message) {
      console.log(this.idBarracks)
    },
    linkedBarracks() {
      axios.
        get("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrack/user/" + this.idBarracks + "?token=" + localStorage.getItem('token'))
        .then((response) => {
          if (response.status === 200) {
            console.log(response.data['content']);
            this.barracks = response.data['content'][0]
          }
      }, (error) => {
        console.log(error)
      });
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