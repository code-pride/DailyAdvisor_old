<template>

    <div class="error-test" v-if="inputValidationData.$dirty">
        <div v-for="validation of inputValidationData.$params" :key="validation.type">
            <span v-if="!inputValidationData[validation.type]">{{errorMsgs[validation.type]}}</span>
        </div>
    </div>

</template>

<script>
import i18n from '../translations/locale-configuration';

export default {
    props: ['inputValidationData'],

    data: () => ({
        errorMsgs: {
            maxLength: i18n.t('formErrorMsgs.provideShorterInput'),
            minLength: i18n.t('formErrorMsgs.provideLongerInput'),
            email: i18n.t('formErrorMsgs.wrongEmailAddres'),
            required: i18n.t('formErrorMsgs.requiredField'),
            sameAs: i18n.t('formErrorMsgs.passwordAreDifferent'),
        },
    }),
};

</script>

<style lang="scss" scoped>

    .error-test {
        color: red;
    }

</style>
