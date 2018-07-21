import { combineEpics, ofType } from 'redux-observable';
import { switchMap } from 'rxjs/operators';

import * as actions from '../actions';
import { userApi } from '../api';

export function userDataEpicFactory() {
    const getUserDataEpic = action$ =>
        action$.pipe(
            ofType(actions.GET_USER_DATA),
            switchMap(action =>
                userApi
                    .getUserProfile()
                    .then(data => {
                        console.dir(data);

                        return actions.getUserDataFulfilled(data);
                    })
                    .catch(actions.getUserDataRejected),
            ),
        );
    return combineEpics(getUserDataEpic);
}
