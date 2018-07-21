import axios from 'axios';

import { getCookie } from './cookie';

const apiUrl = process.env.REACT_APP_API_LOCAL_URL;

export const http = axios.create({
    baseURL: apiUrl,
    withCredentials: true,
    headers: {
        'X-XSRF-TOKEN': getCookie('XSRF-TOKEN'),
    },
});
