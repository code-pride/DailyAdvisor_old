import moment from 'moment';
import * as actions from '../actions';

const month = moment().month();

const initialState = {
    month,
};

export function calendar(state = initialState, action) {
    switch (action.type) {
        case actions.INCREMENT_MONTH:
            return {
                ...state,
                month: state.month + 1,
            };
        case actions.DECREMENT_MONTH:
            return {
                ...state,
                month: state.month - 1,
            };
        default:
            return state;
    }
}
