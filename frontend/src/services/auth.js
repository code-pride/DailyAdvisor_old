import * as axios from 'axios';
import qs from 'qs';

// const INCORRECT_CREDENTIALS_ERROR = 'Incorrect email or password.';

const authService = {
    login(credentials) {
        console.log(credentials);
        return axios.post(
            'http://localhost:8091/oauth/authorize',
            qs.stringify({
                grant_type: 'password',
                username: 'm@m.mm',
                password: '111111',
                redirect_uri: 'http://localhost:8080/swagger-ui.html',
                client_id: 'frontendClientId',
            }),
        );
    },
};

export default authService;
