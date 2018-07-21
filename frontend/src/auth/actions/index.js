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
    type: REGISTER_USER_FULFILLED,
    payload,
});

export const LOGIN_USER_REJECTED = 'LOGIN_USER_REJECTED';
export const loginUserRejected = payload => ({
    type: REGISTER_USER_REJECTED,
    payload,
});
