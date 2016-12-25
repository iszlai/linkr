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
                console.log('Hi' + this.user + this.password)
            }
        }
    }
</script>

<style scoped>

</style>