<template>
    <div class="background">
        <div class="registration-confirm-wrapper">
            <div class="content">
                <p>{{ registerConfirmationMessage }}</p>
                <v-btn
                    class="login-btn"
                    color="primary"
                    to="../login"
                >{{ $t("common.login") }}</v-btn>
            </div>
        </div>
    </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';

export default {
    data: () => ({
        token: '',
    }),

    created() {
        this.token = this.$route.params.token;

        this.registerConfirmation(this.token);
    },

    computed: {
        ...mapGetters('authModule', [
            'registerConfirmationMessage',
        ]),
    },

    methods: {
        ...mapActions('authModule', [
            'registerConfirmation',
        ]),
    },
};
</script>

<style lang="scss" scoped>
    .background {
        background: #29D8F3;
        display: flex;
        flex: 1 1 0;
        flex-wrap: nowrap;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .registration-confirm-wrapper {
        margin: 0 auto;
        max-width: 400px;
        width: 90%;
    }

    .content {
        padding: 20px;
        margin: 30px;
        display: flex;
        flex: 1 1 0;
        flex-direction: column;
        flex-wrap: nowrap;
        justify-content: flex-start;
        align-items: center;
        background-color: white;
    }

    .login-btn {
        display: block;
        margin: 0 auto;
    }
</style>
