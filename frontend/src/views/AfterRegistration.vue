<template>
    <div class="background">
        <div class="after-registration-wrapper">
            <div class="content">
                <p>{{ $t("common.brawo") }}!</p>
                <p>{{ $t("afterRegistrationInfo") }}</p>
                <v-btn
                    class="login-btn"
                    color="primary"
                    to="../login"
                >{{ $t("common.login") }}</v-btn>
            </div>
        </div>
    </div>
</template>

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

    .after-registration-wrapper {
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
