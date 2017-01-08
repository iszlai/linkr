<template>
  <div id="app">
    <md-toolbar>
      <h1 class="md-title">Linkr</h1>
      <md-button>
        <router-link to="/content">content</router-link>
      </md-button>
      <div style="flex: 1" />
      <md-button id="loginButton" class="md-right">
        <router-link to="/login">Login</router-link>
      </md-button>
    </md-toolbar>

    <div class="main-content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import Login from './components/Login'
  import VueMaterial from 'vue-material'
  import Vue from 'vue'

  export default {
    name: 'app',
    components: {
      Login
    }
  }

  Vue.use(VueMaterial)

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}


</style>