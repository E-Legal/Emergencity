<template>
  <div class="user_modal">
  <b-modal centered title="BootstrapVue" hide-header no-close-on-backdrop="true" hide-footer ref="user_modal" id="user_modal">
    <form id="demo" @submit.prevent="submit" novalidate>
      <h3>Modifier l'Utilisateur</h3>
      <b-container fluid>
  <b-row class="my-1">
    <b-col sm="2">
      <label for="input-small">Identifiant:</label>
    </b-col>
    <br/>
    <b-col sm="10">
      <b-form-input id="input-small" size="sm" placeholder="test"></b-form-input>
    </b-col>
        <b-col sm="2">
      <label for="input-small">Adresse mail :</label>
    </b-col>
    <b-col sm="10">
      <b-form-input id="input-small" size="sm" placeholder="test@live.fr"></b-form-input>
    </b-col>
    
  </b-row>
      </b-container>
      </form>
    <div> 
      <div v-if="this.barracks">
               <vuetable ref="vuetable"
    :fields="fields"
    :api-mode="false"
    :data="this.barracks"
  >
        <template slot="actions" slot-scope=""> 
          <div>
            <h1>Utilisateur</h1>
          </div>
        </template>
    </vuetable>
      </div>
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

console.log("coucou")
export default {
  name: "user_modal",
  components: {
    Vuetable
  },
  props: {
    msg: String,
    idBarracks: String,
    reload: Boolean
    },
  watch: { 
      	idUser: function(newVal, oldVal) { // watch it
          console.log('Prop changed: ', newVal, ' | was: ', oldVal);
          console.log("U S E R ")
          //this.getUserInfo();
        }
  },
  data() {
    
    return {
      all_barracks: null,
      selected: null,
      options: [],
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
    //this.getUserInfo();
  },
  methods: {
    getUserInfo(idUser) {
      axios.get('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks?token=' + localStorage.getItem('token')).then(response => {
              console.log("USER=")
              console.log(response.data, "USER")
              // this.all_barracks = response.data['content']
              // console.log(this.barracks);
              /*this.all_barracks.forEach((value, index) => {
                  var obj = {};
                  obj["value"] = value.id;
                  obj["text"] = value.name + ' | ' + value.city;
                  this.options.push(obj);
              })*/
          });
    },
    listBarracks() {

      axios.get('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barracks?token=' + localStorage.getItem('token')).then(response => {
              console.log("Barracks=")
              console.log(response.data['content'])
              this.all_barracks = response.data['content']
              console.log(this.barracks);
              this.all_barracks.forEach((value, index) => {
                  var obj = {};
                  obj["value"] = value.id;
                  obj["text"] = value.name + ' | ' + value.city;
                  this.options.push(obj);
              })
          });
    },
    submit() {
       axios.
        post("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrack/" + this.selected + "/user/new?token=" + localStorage.getItem("token") + "&user_id=" + this.idBarracks)
        .then((response) => {
            this.linkedBarracks()
      }, (error) => {
        console.log(error)
      });
    },
    eraseLink(props, val ) {
      console.log(this.idBarracks)
      axios.
        delete("http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/barrack/" + val + "/user/delete?token=" + localStorage.getItem('token') + "&user_id=" + this.idBarracks)
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
        this.$refs['user_modal'].show()
      },
      hideModal() {
        this.$refs["user_modal"].hide()
      },
      toggleModal() {
        // We pass the ID of the button that we want to return focus to
        // when the modal has hidden
        this.$refs['user_modal'].toggle('#toggle-btn')
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