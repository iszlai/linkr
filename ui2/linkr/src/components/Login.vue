<template>
    <div class="login">
        <md-card md-flex="20">
            <md-card-header>
                <div class="md-title">Login</div>
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
                    <md-button v-on:click="submit" class="md-raised md-primary">Login</md-button>
                </form>
            </md-card-content>
        </md-card>
    </div>
</template>

<script>
    import LoginService from '../services/LoginService'
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
                LoginService.submit(this.user, this.password, () => console.log('succes'), () => console.log('fail2'))
            }
        }
    }
</script>

<style scoped>

</style>