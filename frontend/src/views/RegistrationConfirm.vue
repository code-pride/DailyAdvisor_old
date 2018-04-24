<template>
    <div class="sample-div">
        Kliknales w link. Jestes bardziej pojebany niż myslalem!
    </div>
</template>
<script>
import { mapActions } from 'vuex';

export default {
    data: () => ({
        token: '',
    }),

    created() {
        this.token = this.$route.params.token;

        this.registerConfirmation(this.token);
    },

    methods: {
        ...mapActions('authModule', [
            'registerConfirmation',
        ]),
    },
};
</script>

<style lang="scss" scoped>
    .sample-div {
        color: red;
    }
</style>
