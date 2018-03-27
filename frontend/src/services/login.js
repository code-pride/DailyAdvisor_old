import qs from 'qs';
import server from './server-config';

export default {
    login(email, password) {
        return server.post(
            '/login',
            qs.stringify({
                Submit: 'Login',
                email,
                password,
            }),
        );
    },
};
