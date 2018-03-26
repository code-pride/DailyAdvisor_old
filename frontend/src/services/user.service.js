import * as axios from 'axios';

export class UserService {
    constructor() {
        this.apiUrl = 'localhost:8091';
        this.userLogin = `${this.apiUrl}/login`;
        this.userRegistration = `${this.apiUrl}/registration`;
        this.populate = `${this.apiUrl}/populate`;
    }

    static register(user) {
        return axios.post(this.userRegistration, user);
    }

    static login(body) {
        return axios.post(this.userLogin, body);
    }
}
