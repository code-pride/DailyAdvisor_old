import {
    FETCH_RANDOM_USER_STARTED,
    FETCH_RANDOM_USER_SUCCEEDED,
    FETCH_RANDOM_USER_FAILED,
    FETCH_RANDOM_USER_FINISHED,
} from '../../actions/randomUserActions';

const initialState = {
    userData: {},
    isLoading: false,
    error: undefined,
};

const randomUserReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_RANDOM_USER_STARTED: {
            return { ...state, isLoading: true };
        }

        case FETCH_RANDOM_USER_SUCCEEDED: {
            return { ...state, userData: action.payload };
        }

        case FETCH_RANDOM_USER_FAILED: {
            return { ...state, isLoading: false, error: action.payload };
        }

        case FETCH_RANDOM_USER_FINISHED: {
            return { ...state, isLoading: false };
        }

        default: {
            return state;
        }
    }
};

export default randomUserReducer;
