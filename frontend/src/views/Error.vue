<template>

    <div class="error-test">
        <div v-for="validation of inputValidationData.$params" :key="validation.type">
            <span v-if="!inputValidationData[validation.type]">{{errorMsgs[validation.type]}}</span>
        </div>
    </div>

</template>

<script>

export default {

    props: ['inputValidationData'],

    data: () => ({
        errorMsgs: {
            maxLength: 'Please provide shorter input',
            minLength: 'Please provide longer input',
            email: 'This is wrong addres',
            required: 'This field is required',
            sameAs: 'Password are different',
        },
    }),

    mounted() {
        console.log(this.inputValidationData);
    },
}

</script>

<style lang="scss" scoped>

    .error-test {
        color: red;
    }

</style>
