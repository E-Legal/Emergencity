<template>
  <div class="vehiculemodal">
  <b-modal centered title="BootstrapVue" hide-header no-close-on-backdrop="true" hide-footer ref="vehicule_modal" id="vehicule_modal">
    <form id="demo" @submit.prevent="submit" novalidate>
          <div>
            <input
              type="text"
              id="login"
              class="col-lg-3"
              name="login"
              placeholder="Modèle"
              v-model="modele"
            />
            <input
              type="text"
              id="password"
              class="col-lg-3"
              name="login"
              placeholder="Immatriculation"
              v-model="immatriculation"
              style="margin-left: 1%; margin-right: 1%"
            />
            <input type="submit" class="btn btn-outline-primary" value="Creer un Vehicule" />
          </div>
        </form>
        <form id="demo" @submit.prevent="submit2" novalidate>
          <div>
            <b-form-select style="width: 200px" v-model="selected" :options="options"></b-form-select>

            <input type="submit" class="btn btn-outline-primary" value="Lier le Vehicule" />
          </div>
      </form>
    <div v-if="this.idBarracks"> 
      <div v-if="this.barracks">
               <vuetable ref="vuetable"
    :fields="fields"
    :api-mode="false"
    :data="this.barracks"
  >
        <template slot="actions" slot-scope="props"> 
          <div>

            <b-button :id="props['rowData'].id" v-on:click="eraseLink(props, props['rowData'].id)" variant="danger">Supprimer</b-button>
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
  name: "vehiculemodal",
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
          this.listVehicles()
        }
  },
  data() {
    
    return {
      modele: null,
      immatriculation: null,
      all_barracks: null,
      selected: null,
      options: [],
      fields: [
        {
          name: 'model',
          title: 'Modele du Vehicule'
        },
        {
          name: 'registration',
          title: 'Plaque d\immatriculation'
        },
        {
          name: 'actions',
          title: '',
          titleClass: 'center aligned',
          dataClass: 'center aligned'
        }
      ],
      barracks: null,
    }
  },
  mounted() {
      this.listAllVehicles()
  },
  methods: {
    listAllVehicles() {
        axios.get('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/vehicles/?token=' + localStorage.getItem('token')).then(response => {
               this.all_barracks = response.data['content']
              console.log(this.barracks);
              this.all_barracks.forEach((value, index) => {
                  var obj = {};
                  obj["value"] = value.id;
                  obj["text"] = value.model + ' | ' + value.registration;
                  this.options.push(obj);
              })
              
          });
    },
    listVehicles() {

      axios.get('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrackVehicles/' + this.idBarracks + '?token=' + localStorage.getItem('token')).then(response => {
              console.log("Barracks=")
              console.log(response.data['content'])
              this.barracks = response.data['content'][0]
              console.log(this.barracks);
          });
    },
    submit() {
       axios.
        post("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/vehicles/new?token=" + localStorage.getItem("token") + "&model=" + this.modele + "&registration=" + this.immatriculation)
        .then((response) => {
            this.listVehicles()
      }, (error) => {
        console.log(error)
      });
    },
    submit2() {
        axios.
        post("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrackVehicles/new?vehicle_id=" + this.selected + "&token=" + localStorage.getItem("token") + "&barrack_id=" + this.idBarracks)
        .then((response) => {
            this.listVehicles()
      }, (error) => {
        console.log(error)
      });
    },
    eraseLink(props, val ) {
      console.log(this.idBarracks)
      axios.
        delete("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrackVehicles/delete?token=" + localStorage.getItem('token') + "&barrack_id=" + this.idBarracks + "&vehicle_id=" + val)
        .then((response) => {
          if (response.status === 200) {
            this.barracks.splice(props['rowIndex'], 1);
          }
      }, (error) => {
        console.log(error)
      });
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
        this.$refs['vehicule_modal'].show()
      },
      hideModal() {
        this.$refs["vehicule_modal"].hide()
      },
      toggleModal() {
        // We pass the ID of the button that we want to return focus to
        // when the modal has hidden
        this.$refs['vehicule_modal'].toggle('#toggle-btn')
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