import { http } from '../utils/http';

const registerUser = user => http.post('/registration', user);

export const authApi = {
    registerUser,
};
