import Vue from 'vue';
import Router from 'vue-router';
import store from './store';

import Login from './views/Login.vue';
import Register from './views/Register.vue';
import Home from './views/Home.vue';

Vue.use(Router);

const router = new Router({
    routes: [
        {
            path: '/login',
            name: 'login',
            component: Login,
        },
        {
            path: '/register',
            name: 'register',
            component: Register,
        },
        {
            path: '/restricted',
            name: 'restricted-stuff',
            component: Home,
            meta: { requiresAuthentication: true },
        },
        {
            path: '*',
            name: 'Default',
            component: Login,
        },
    ],
});

router.beforeEach((to, from, next) => {
    const pageRequiresAuthentication = to.meta.requiresAuthentication === true;
    const userIsAuthenticated = store.getters.isAuthenticated;

    if (pageRequiresAuthentication && !userIsAuthenticated) {
        next('/login');
    } else {
        next();
    }
});

export default router;
