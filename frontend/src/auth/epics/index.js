import { combineEpics, ofType } from 'redux-observable';
import { switchMap, map } from 'rxjs/operators';

import * as actions from '../actions';
import { authApi } from '../api';

export function authEpicFactory() {
    const registerUserEpic = action$ =>
        action$.pipe(
            ofType(actions.REGISTER_USER),
            switchMap(action =>
                authApi.registerUser(action.payload).catch(actions.registerUserRejected),
            ),
            map(res => {
                if (res.status === 226) {
                    return actions.registerUserRejected('Email already in use');
                }
                return actions.registerUserFulfilled();
            }),
        );

    const loginUserEpic = actions$ =>
        actions$.pipe(
            ofType(actions.LOGIN_USER),
            map(action => {
                console.dir(action);
                return actions.getCsrf(action.payload);
            }),
        );

    const csrf = actions$ =>
        actions$.pipe(
            ofType(actions.GET_CSRF),
            switchMap(action => {
                console.log('csrf...');
                console.dir(action);

                return authApi
                    .getCsrf(action.payload)
                    .then(() => actions.getCsrfFulfilled(action.payload))
                    .catch(actions.getCsrfRejected);
            }),
        );

    const csrfFullfilled = actions$ =>
        actions$.pipe(
            ofType(actions.GET_CSRF_FULFILLED),
            switchMap(action => {
                console.log('csrffulfilled...');
                console.dir(action);
                return authApi
                    .loginUser(action.payload)
                    .then(actions.loginUserFulfilled)
                    .catch(actions.loginUserRejected);
            }),
        );

    return combineEpics(csrf, csrfFullfilled, registerUserEpic, loginUserEpic);
}
