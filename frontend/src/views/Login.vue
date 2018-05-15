<template>
    <div class="background">
        <img src="../assets/logo.png">
        <div class="login-card-wrapper">
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
                        @click="authenticate({email, password})"
                        :disabled="!valid"
                        class="sign-in-btn"
                        color="secondary"
                    >{{ $t("common.login") }}</v-btn>
                </v-form>
                <v-btn
                    flat
                    small
                    class="sign-up-btn"
                    color="primary"
                    to="register"
                    >{{ $t("common.signUp") }}</v-btn>
            </v-card>
        </div>
    </div>
</template>

<script>
import { mapActions } from 'vuex';

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

    methods: {
        ...mapActions('authModule', ['authenticate']),
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
        padding: 20px 0;
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
