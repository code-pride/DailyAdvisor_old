import { combineReducers } from 'redux';

import { userReducer } from './user/reducers/';
import { authReducer } from './auth/reducers';

export const rootReducer = combineReducers({
    authReducer,
    userReducer,
});
