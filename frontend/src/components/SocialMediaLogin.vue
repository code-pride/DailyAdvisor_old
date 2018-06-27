<template>
    <a v-bind:href="getUrl()" class="login-btn" v-bind:class="getClass()" target="_blank">
        {{getText()}}
    </a>
</template>

<script>
export default {
    name: 'SocialMediaLogin',
    props: {
        media: String,
    },
    methods: {
        getClass() {
            return this.media === 'facebook' ? 'login-btn-facebook' : 'login-btn-google';
        },
        getText() {
            return this.media === 'facebook' ? 'Login with Facebook' : 'Login with Google';
        },
        getUrl() {
            return this.media === 'facebook' ? 'localhost:8091/login/facebook' : 'localhost:8091/login/google';
        },
    },
};
</script>

<style lang="scss" scoped>
    .login-btn {
        box-sizing: border-box;
        position: relative;
        margin: 0.2em;
        padding: 0 15px 0 46px;
        border: none;
        text-align: left;
        line-height: 34px;
        white-space: nowrap;
        border-radius: 0.2em;
        font-size: 16px;
        color: #FFF;

        &:before {
            content: "";
            box-sizing: border-box;
            position: absolute;
            top: 0;
            left: 0;
            width: 34px;
            height: 100%;
        }

        &:focus {
            outline: none;
        }

        &:active {
            box-shadow: inset 0 0 0 32px rgba(0,0,0,0.1);
        }
    }

    .login-btn-facebook {
        background-color: #4C69BA;
        background-image: linear-gradient(#4C69BA, #3B55A0);
        text-shadow: 0 -1px 0 #354C8C;

        &:before {
            border-right: #364e92 1px solid;
            background: url('../assets/facebook.png') 6px 6px no-repeat;
        }

        &:hover, &:focus {
            background-color: #5B7BD5;
            background-image: linear-gradient(#5B7BD5, #4864B1);
        }
    }

    .login-btn-google {
        background: #DD4B39;

        &:before {
            border-right: #BB3F30 1px solid;
            background: url('../assets/google.png') 6px 6px no-repeat;
        }

        &:hover, &:focus {
            background: #E74B37;
        }
    }
</style>
