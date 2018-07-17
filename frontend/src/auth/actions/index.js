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
