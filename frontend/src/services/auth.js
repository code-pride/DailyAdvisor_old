import axios from 'axios';
import qs from 'qs';

const authService = {
    login(email, password) {
        return axios.post(
            '/login',
            qs.stringify({
                Submit: 'Login',
                email,
                password,
            }),
        );
    },
};

export default authService;
