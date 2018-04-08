<template>
    <div class="background">
        <img src="../assets/logo.png">
        <div class="login-card-wrapper">
            <v-card class="card-content">
                <v-form v-model="isFormValid" ref="form" lazy-validation class="form-wrapper">
                    <v-text-field
                        label="Name"
                        v-model="userData.name"
                        required
                    ></v-text-field>
                    <v-text-field
                        label="Lastname"
                        v-model="userData.lastname"
                        required
                    ></v-text-field>
                    <v-text-field
                        label="City"
                        v-model="userData.city"
                        required
                    ></v-text-field>
                    <v-text-field
                        label="Email"
                        v-model="userData.email"
                        required
                    ></v-text-field>
                    <v-text-field
                        label="Password"
                        v-model="userData.password"
                        :type="'password'"
                        required
                    ></v-text-field>
                    <v-text-field
                        label="Repeat password"
                        v-model="userData.repeatPassword"
                        :type="'password'"
                        required
                    ></v-text-field>
                    <v-radio-group v-model="userData.userType" row>
                        <v-radio label="Coach" value="coach" ></v-radio>
                        <v-radio label="Normal user" value="normalUser"></v-radio>
                    </v-radio-group>
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
import { mapActions } from 'vuex';

export default {
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

    methods: {
        ...mapActions('authModule', [
            'register',
        ]),
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
