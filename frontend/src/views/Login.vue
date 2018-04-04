<template>
    <div class="background">
        <img src="../assets/logo.png">

        <div v-if="isAuthenticated" class="already-logged-in-message">
            <h2>You're already logged in!</h2>
        </div>

        <div v-else class="login-card-wrapper">
            <v-card class="card-content">
                <v-form v-model="valid" ref="form" lazy-validation class="form-wrapper">
                    <v-text-field
                        label="Email"
                        v-model="email"
                        :rules="emailRules"
                        required
                    ></v-text-field>
                    <v-text-field
                        label="Password"
                        v-model="password"
                        :rules="passwordRules"
                        :type="'password'"
                        required
                    ></v-text-field>
                    <v-btn
                        @click="authenticateAndTryReroute({email, password})"
                        class="sign-in-btn"
                        color="secondary"
                    >Log in</v-btn>
                </v-form>
                <v-btn
                    flat
                    small
                    class="sign-up-btn"
                    color="primary"
                    to="register"
                    >Sign up</v-btn>
            </v-card>
        </div>

        <v-snackbar
            :timeout="0"
            :color="'error'"
            :value="didAuthenticationErrorOccured">
            {{ authenticationErrorMessage }}
            <v-btn dark flat @click="clearAuthenticationErrors">Close</v-btn>
        </v-snackbar>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import router from '../router';

export default {
    data: () => ({
        valid: false,
        email: '',
        emailRules: [
            v => !!v || 'E-mail is required',
            v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid',
        ],
        password: '',
        passwordRules: [
            v => !!v || 'Password is required',
        ],
    }),
    computed: {
        ...mapGetters([
            'didAuthenticationErrorOccured',
            'authenticationErrorMessage',
            'isAuthenticated',
        ]),
    },
    methods: {
        ...mapActions([
            'authenticate',
            'clearAuthenticationErrors',
        ]),
        authenticateAndTryReroute(credentials) {
            this.authenticate(credentials).then(() => {
                if (this.isAuthenticated) {
                    router.push('/restricted');
                }
            });
        },
    },
    watch: {
        didAuthenticationErrorOccured(val) {
            if (val === true) {
                setTimeout(() => {
                    this.clearAuthenticationErrors();
                }, 6000);
            }
        },
    },
};
</script>

<style lang="scss" scoped>
    img {
        width: auto;
        max-height: 100px;
        margin-bottom: 30px;
    }

    .already-logged-in-message {
        color: white;
    }

    .background {
        background: #29D8F3;
        display: flex;
        flex: 1 1 0;
        flex-wrap: nowrap;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .login-card-wrapper {
        height: 300px;
        margin: 0 auto;
        max-width: 400px;
        width: 90%;
    }

    .card-content {
        display: flex;
        flex: 1 1 0;
        flex-direction: column;
        flex-wrap: nowrap;
        justify-content: flex-start;
        align-items: center;
    }

    .form-wrapper {
        padding-top: 10px;
        width: 80%;
    }

    .sign-in-btn {
        display: block;
        margin: 0 auto;
    }

    .sign-up-btn {
        height: 15px;
    }
</style>
