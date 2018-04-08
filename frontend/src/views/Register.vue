<template>
    <div class="background">
        <img src="../assets/logo.png">
        <div class="login-card-wrapper">
            <v-card class="card-content">
                <v-form v-model="isFormValid" ref="form" lazy-validation class="form-wrapper">
                    <v-text-field
                        label="Name"
                        v-model.trim="userData.name"
                        @input="$v.userData.name.$touch()"
                        required
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.name"></error>

                    <v-text-field
                        label="Lastname"
                        v-model.trim="userData.lastname"
                        @input="$v.userData.lastname.$touch()"
                        required
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.lastname"></error>

                    <v-text-field
                        label="City"
                        v-model.trim="userData.city"
                        @input="$v.userData.city.$touch()"
                        required
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.city"></error>

                    <v-text-field
                        label="Email"
                        v-model.trim="userData.email"
                        @input="$v.userData.email.$touch()"
                        required
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.email"></error>

                    <v-text-field
                        label="Password"
                        v-model.trim="userData.password"
                        @input="$v.userData.password.$touch()"
                        :type="'text'"
                        required
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.password"></error>

                    <v-text-field
                        label="Repeat password"
                        v-model.trim="userData.repeatPassword"
                        @input="$v.userData.repeatPassword.$touch()"
                        :type="'text'"
                        required
                    ></v-text-field>
                    <error :input-validation-data="$v.userData.repeatPassword"></error>

                    <v-radio-group v-model="userData.userType" row @input="$v.userData.userType.$touch()">
                        <v-radio label="Coach" value="coach" ></v-radio>
                        <v-radio label="Normal user" value="normalUser"></v-radio>
                    </v-radio-group>
                    <error :input-validation-data="$v.userData.userType"></error>

                    <v-btn
                        v-on:click="register(userData)"
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
    </div>
</template>

<script>
import {
    required,
    alpha,
    alphaNum,
    sameAs,
    email,
    minLength,
    maxLength
} from 'vuelidate/lib/validators';
import { mapActions } from 'vuex';
import Error from './Error';

export default {
    components: {
        "error": Error,
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
        isFormValid: false,
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
                minLength: minLength(7),
                maxLength: maxLength(255),
            },
            repeatPassword: {
                sameAsPassword: sameAs('userData.password'),
            },
            userType: {
                required,
            }
        },
    },

    methods: {
        // ...mapActions('authModule', [
        //     'register',
        // ]),
        register(userData) {
            // console.log(userData);
            console.log(this.$v);
        }
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
