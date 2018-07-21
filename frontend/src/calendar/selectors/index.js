import { createSelector } from 'reselect';
import moment from 'moment';
import _range from 'lodash/range';

export const yearSelector = createSelector(
    state => state.calendar.month,
    month =>
        moment()
            .month(month)
            .year(),
);

export const daysSelector = createSelector(
    state => state.calendar.month,
    month => {
        const startOfMonth = moment()
            .month(month)
            .startOf('month')
            .dayOfYear();

        const endOfMonth = moment()
            .month(month)
            .endOf('month')
            .dayOfYear();

        return _range(startOfMonth, endOfMonth + 1).map(day =>
            moment()
                .month(month)
                .dayOfYear(day)
                .format('D dddd'),
        );
    },
);

export const monthNameSelector = createSelector(
    state => state.calendar.month,
    month =>
        moment()
            .month(month)
            .format('MMMM'),
);
