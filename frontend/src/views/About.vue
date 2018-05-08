<template>
    <div id="app">
        <p>normal strings</p>
        <p>{{ $t('hello') }}</p>


        <br>
        <p>data formats</p>
        <p>{{ $d(new Date(), 'short') }}</p>
        <p>{{ $d(new Date(), 'long') }}</p>
        <p>{{ $d(new Date(), 'long', 'pl') }}</p>

        <br>
        <p>numbers</p>
        <p>{{ $n(100, 'currency') }}</p>
        <p>{{ $n(100, 'currency', 'pl') }}</p>
        <p>{{ $n(0.53, 'percent') }}</p>
        <router-view/>
    </div>
</template>
