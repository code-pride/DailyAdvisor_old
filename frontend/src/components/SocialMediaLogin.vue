<template>
    <button class="login-btn" v-bind:class="getClass()">
        {{getText()}}
    </button>
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
    },
};
</script>

<style>
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
    }

    .login-btn:before {
        content: "";
        box-sizing: border-box;
        position: absolute;
        top: 0;
        left: 0;
        width: 34px;
        height: 100%;
    }

    .login-btn:focus {
        outline: none;
    }

    .login-btn:active {
        box-shadow: inset 0 0 0 32px rgba(0,0,0,0.1);
    }

    .login-btn-facebook {
        background-color: #4C69BA;
        background-image: linear-gradient(#4C69BA, #3B55A0);
        text-shadow: 0 -1px 0 #354C8C;
    }

    .login-btn-facebook:before {
        border-right: #364e92 1px solid;
        background: url('../assets/facebook.png') 6px 6px no-repeat;
    }

    .login-btn-facebook:hover,
    .login-btn-facebook:focus {
        background-color: #5B7BD5;
        background-image: linear-gradient(#5B7BD5, #4864B1);
    }

    .login-btn-google {
        background: #DD4B39;
    }

    .login-btn-google:before {
        border-right: #BB3F30 1px solid;
        background: url('../assets/google.png') 6px 6px no-repeat;
    }

    .login-btn-google:hover,
    .login-btn-google:focus {
        background: #E74B37;
    }
</style>
