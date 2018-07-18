import { http } from '../../http';

const registerUser = user => http.post('/registration', user);

export const authApi = {
    registerUser,
};
