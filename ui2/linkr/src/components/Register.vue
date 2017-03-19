<template>
    <div class="login">
        <md-card md-with-hover>
            <md-card-header>
                <div class="md-title">Create a user</div>
            </md-card-header>
            <md-card-content>
                <form>
                    <md-input-container md-inline>
                        <label>Username</label>
                        <md-input v-model="user"></md-input>
                    </md-input-container>
                    <md-input-container md-has-password>
                        <label>Password</label>
                        <md-input type="password" v-model="password"></md-input>
                    </md-input-container>
                      <md-input-container md-has-password v-bind:class="{ 'md-input-invalid': isMatch() }">
                        <label>Verify Password</label>
                        <md-input type="password" v-model="repeatedPassword"></md-input>
                    </md-input-container>
                    <md-button v-on:click="submit" class="md-raised md-primary">Register</md-button>
                </form>
            </md-card-content>
        </md-card>
    </div>
</template>

<script>
    import LoginService from '../services/LoginService'
   // import router from '../main'
    export default {
        name: 'Register',
        data() {
            return {

                user: '',
                password: '',
                repeatedPassword: '',
                msg: 'Welcome to Your Vue.js App'
            }
        },
        methods: {
            submit: function () {
                if (this.password === this.repeatedPassword) {
                    LoginService.register(this.user, this.password, () => console.log('succes'), () => console.log('fail2'))
                    this.$router.push('login')
                }
        },
            isMatch: function () {
                return !(this.password === this.repeatedPassword)
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