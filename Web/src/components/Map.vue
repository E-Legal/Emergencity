/* eslint-disable */
<template>
  <div name="MyAwesomeMap">
      <div class="container-flex" >
        <GmapMap
          :center="{lat: latitude, lng: longitude}"
          :zoom="17"
          :options="{
            zoomControl: true,
            mapTypeControl: false,
            scaleControl: false,
            streetViewControl: false,
            rotateControl: false,
            fullscreenControl: true,
            disableDefaultUi: false
          }"
          class="gmap"
>
  <GmapInfoWindow :options="infoOptions" :position="infoWindowPos" :opened="infoWinOpen" @closeclick="infoWinOpen=false"></GmapInfoWindow>
  <GmapMarker
        :key="index"
    v-for="(m, index) in markers"
    :position="m.position"
    :clickable="true"
    :draggable="false"
    @click="toggleInfoWindow(m,index)"
  />
  <GmapCircle

    v-for="(m, index) in markers"
              :key="'circle' + index"
    :center="m.position"
    :clickable="true"
    :draggable="false"
    @click="toggleInfoWindow(m,index)"
    :options="{
        strokeColor: '#000',
        strokeOpacity: 0,
        strokeWeight: 0,
        fillColor: m.color,
        fillOpacity: 0.50,
        radius: 7
        }"
    
    ></GmapCircle>
</GmapMap>

      </div>
  </div>
</template>

<style scoped>
.gmap {
width: 1920px; height: calc(100vh - 66px)
}
</style>

<script>

import {gmapApi} from 'vue2-google-maps'
import axios from 'axios';

export default {
  name: "MyAwesomeMap",
  components: {
    google: gmapApi
  },
  data() {
    return {
      zoom: 17,
      infoWindowPos: null,
      infoWinOpen: false,
      currentMidx: null,
      infoOptions: {
          content: '',
          pixelOffset: {
            width: 0,
            height: -35
        }
      },
      markers: [],
      latitude: 43.3102483,
      longitude: 5.3789625
    };
  },
  mounted() {
        var totalMarkers = [];
        axios.get('http://x2021emergencity2490271133000.northeurope.cloudapp.azure.com:9000/feu?token=' + localStorage.getItem('token')).then(response => {
                console.log(response, "response")
                response.data['content'].forEach((element) => {
                    let marker = {position: {lat: parseFloat(element.x), lng:  parseFloat(element.y)}, infoText: '', state: 0, icon: 'mark', color: 'red'};
                    totalMarkers.push(marker);
                })
                this.markers = totalMarkers;
                console.log(this.markers, "marke")
        });

  },
  methods: {
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
};

</script>
