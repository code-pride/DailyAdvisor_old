<template>
    <div class="background">
        <img src="../assets/logo.png">
        <div class="login-card-wrapper">
            <v-card class="card-content">
                <v-form class="form-wrapper">
                    <v-text-field
                        label="Name"
                        v-model.trim="userData.name"
                        @input="$v.userData.name.$touch()"
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.name"></error>

                    <v-text-field
                        label="Lastname"
                        v-model.trim="userData.lastname"
                        @input="$v.userData.lastname.$touch()"
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.lastname"></error>

                    <v-text-field
                        label="City"
                        v-model.trim="userData.city"
                        @input="$v.userData.city.$touch()"
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.city"></error>

                    <v-text-field
                        label="Email"
                        v-model.trim="userData.email"
                        @input="$v.userData.email.$touch()"
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.email"></error>

                    <v-text-field
                        label="Password"
                        v-model="userData.password"
                        :type="'password'"
                        @input="$v.userData.password.$touch()"
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.password"></error>

                    <v-text-field
                        label="Repeat password"
                        v-model="userData.repeatPassword"
                        :type="'password'"
                        @input="$v.userData.repeatPassword.$touch()"
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.repeatPassword"></error>

                    <v-radio-group
                        v-model="userData.userType"
                        row
                        @input="$v.userData.userType.$touch()">
                        <v-radio label="Coach" value="coach" ></v-radio>
                        <v-radio label="Normal user" value="client"></v-radio>
                    </v-radio-group>
                    <error :input-validation-data="$v.userData.userType"></error>

                    <v-btn
                        @click="validate(userData)"
                        class="sign-in-btn"
                        color="primary"
                    >sign up</v-btn>
                </v-form>
                <v-btn
                    flat
                    small
                    class="sign-up-btn"
                    color="primary"
                    to="login"
                >Login</v-btn>
            </v-card>
        </div>

        <v-snackbar
            :timeout="0"
            :color="registerSnackBarColor"
            :value="didRegisterSuccess">
            {{ registerErrorMessage }} {{ registerSuccessMessage }}
            <v-btn dark flat @click="clearRegisterMessages">Close</v-btn>
        </v-snackbar>
    </div>
</template>

<script>
import {
    required,
    sameAs,
    email,
    minLength,
    maxLength,
} from 'vuelidate/lib/validators';
import { mapActions, mapGetters } from 'vuex';
import Error from '../components/Error.vue';

export default {
    components: {
        error: Error,
    },
    data: () => ({
        userData: {
            name: '',
            lastname: '',
            city: '',
            email: '',
            password: '',
            repeatPassword: '',
            userType: '',
        },
        snackbarType: 'error',
    }),

    validations: {
        userData: {
            name: {
                required,
                minLength: minLength(2),
                maxLength: maxLength(100),
            },
            lastname: {
                required,
                minLength: minLength(2),
                maxLength: maxLength(100),
            },
            city: {
                required,
                minLength: minLength(4),
                maxLength: maxLength(100),
            },
            email: {
                required,
                email,
                minLength: minLength(4),
                maxLength: maxLength(100),
            },
            password: {
                required,
                minLength: minLength(8),
                maxLength: maxLength(255),
            },
            repeatPassword: {
                sameAs: sameAs('password'),
            },
            userType: {
                required,
            },
        },
    },

    computed: {
        ...mapGetters('authModule', [
            'didRegisterErrorOccured',
            'didRegisterSuccess',
            'registerSuccessMessage',
            'registerErrorMessage',
            'registerSnackBarColor',
        ]),
    },

    methods: {
        ...mapActions('authModule', [
            'register',
            'clearRegisterMessages',
        ]),

        validate(userData) {
            this.$v.userData.$touch();
            if (!this.$v.userData.$invalid) {
                this.register({ ...userData });
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
