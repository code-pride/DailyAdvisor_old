import { combineEpics, ofType } from 'redux-observable';
import { switchMap, map, mapTo } from 'rxjs/operators';

import * as actions from '../actions';
import { authApi } from '../api';
import { getCookie } from '../../utils/cookie';

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
            mapTo(actions.getCsrf()),
        );

    const csrf = actions$ =>
        actions$.pipe(
            ofType(actions.GET_CSRF),
            switchMap(action =>
                authApi
                    .getCsrf()
                    .then(actions.getCsrfFulfilled)
                    .catch(actions.getCsrfRejected),
            ),
        );

    const csrfFullfilled = actions$ =>
        actions$.pipe(
            ofType(actions.GET_CSRF_FULFILLED),
            switchMap(action =>
                authApi
                    .loginUser({ username: 'm@m.mm', password: '111111' })
                    .then(actions.loginUserFulfilled)
                    .catch(response => {
                        console.dir(response);
                        return actions.loginUserRejected(response);
                    }),
            ),
        );

    return combineEpics(csrf, csrfFullfilled, registerUserEpic, loginUserEpic);
}
