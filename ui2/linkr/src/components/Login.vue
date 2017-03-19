<template>
    <div class="login">
        <md-card md-with-hover>
            <md-card-header>
                <div class="md-title">Login</div>
            </md-card-header>
            <md-card-content>
                <form>
                    <md-input-container md-inline>
                        <label>Username</label>
                        <md-input v-model="user"></md-input>
                    </md-input-container>
                    <md-input-container md-has-password v-on:keyup.enter="submit">
                        <label>Password</label>
                        <md-input type="password" v-model="password"></md-input>
                    </md-input-container>
                    <md-button v-on:click="submit" class="md-raised md-primary">Login</md-button>
                </form>
                <router-link to="/register">Want to join? Sign up</router-link>
            </md-card-content>
        </md-card>
    </div>
</template>

<script>
    import LoginService from '../services/LoginService'
   // import router from '../main'
    export default {
        name: 'Login',
        data() {
            return {

                user: '',
                password: '',
                msg: 'Welcome to Your Vue.js App'
            }
        },
        methods: {
            submit: function () {
                LoginService.submit(this.user, this.password, () => {
                    window.user = this.user
                    this.$router.push('content')
                    },
                     () => console.log('fail2'))
        }
        }
    }
</script>

<style scoped>
.login{
    margin: 40px auto;
    max-width: 650px;
}
</style>