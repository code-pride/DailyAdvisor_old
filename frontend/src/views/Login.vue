<template>
    <div class="background">
        <img src="../assets/logo.png">
        <div class="login-card-wrapper">
            <v-card class="card-content">
                <v-form>
                    <v-text-field
                        label="Email"
                        v-model="email"
                        :rules="emailRules"
                    ></v-text-field>
                    <v-text-field
                        label="Password"
                        v-model="password"
                        :rules="passwordRules"
                        :type="'password'"
                    ></v-text-field>
                </v-form>
                <v-btn color="secondary">Log in</v-btn>
                <v-btn flat small color="primary">Sign up</v-btn>
            </v-card>
        </div>
    </div>
</template>

<style lang="scss" scoped>
    img {
        width: auto;
        max-height: 100px;  
        margin-bottom: 30px;
    }

    .background {
        background: #29D8F3;
        // background: red;
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
        width: 90%;
    }

    .card-content {
        display: flex;
        flex: 1 1 0;
        flex-direction: column;
        flex-wrap: nowrap;
        justify-content: center;
        align-items: center;
    }
</style>
