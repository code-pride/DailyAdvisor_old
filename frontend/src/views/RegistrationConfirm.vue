<template>
    <div class="sample-div">
        Kliknales w link. Jestes bardziej pojebany niż myslalem! {{ sampleData }}
    </div>
</template>
<script>
export default {
    data: () => ({
        sampleData: 'asdasdas',
    }),

    methods: {
        sampleMethod() {
            console.log('Sample message');
        },
    },
};
</script>

<style lang="scss" scoped>
    .sample-div {
        color: red;
    }
</style>
