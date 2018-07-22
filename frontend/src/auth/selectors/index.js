import { createSelector } from 'reselect';

export const isLoggedInSelector = createSelector(
    state => state.authReducer.isLoggedIn,
    isLoggedIn => isLoggedIn,
);
