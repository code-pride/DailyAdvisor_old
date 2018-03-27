<template>
    <div class="test-div">
        <v-form v-model="valid" ref="form" lazy-validation>
            <v-text-field
                label="E-mail"
                v-model="email"
                :rules="emailRules"
                :counter="10"
                required
            ></v-text-field>
            <v-text-field
                label="Password"
                v-model="password"
                :rules="passwordRules"
                required
            ></v-text-field>
        </v-form>
    </div>
</template>

<script>

import * as axios from 'axios';

export default {
    data: () => ({
        email: '',
        emailRules: [
            v => !!v || 'E-mail is required',
            v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid',
        ],
        password: '',
        passwordRules: [
            v => !!v || 'Password is required',
        ],
        valid: true,
    }),

    methods: {
        submit() {
            if (this.$refs.form.validate()) {
                console.log('this is valid form');
            }
        }
    }
};

</script>

<style lang="scss" scoped>
@import '../variables';

    .test-div {
        border: 1px solid black;

        h1 {
            color: green;
        }
    }

</style>
