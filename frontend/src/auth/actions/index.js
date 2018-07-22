export const REGISTER_USER = 'REGISTER_USER';
export const registerUser = payload => ({
    type: REGISTER_USER,
    payload,
});

export const REGISTER_USER_FULFILLED = 'REGISTER_USER_FULFILLED';
export const registerUserFulfilled = payload => ({
    type: REGISTER_USER_FULFILLED,
    payload,
});

export const REGISTER_USER_REJECTED = 'REGISTER_USER_REJECTED';
export const registerUserRejected = payload => ({
    type: REGISTER_USER_REJECTED,
    payload,
});

export const LOGIN_USER = 'LOGIN_USER';
export const loginUser = payload => ({
    type: LOGIN_USER,
    payload,
});

export const LOGIN_USER_FULFILLED = 'LOGIN_USER_FULFILLED';
export const loginUserFulfilled = payload => ({
    type: LOGIN_USER_FULFILLED,
    payload,
});

export const LOGIN_USER_REJECTED = 'LOGIN_USER_REJECTED';
export const loginUserRejected = payload => ({
    type: LOGIN_USER_REJECTED,
    payload,
});

export const GET_CSRF = 'GET_CSRF';
export const getCsrf = payload => ({
    type: GET_CSRF,
    payload,
});

export const GET_CSRF_FULFILLED = 'GET_CSRF_FULFILLED';
export const getCsrfFulfilled = payload => ({
    type: GET_CSRF_FULFILLED,
    payload,
});

export const GET_CSRF_REJECTED = 'GET_CSRF_REJECTED';
export const getCsrfRejected = payload => ({
    type: GET_CSRF_REJECTED,
    payload,
});
