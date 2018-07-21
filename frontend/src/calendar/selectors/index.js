import { createSelector } from 'reselect';
import moment from 'moment';
import _range from 'lodash/range';

import { data } from '../data';

export const yearSelector = createSelector(
    state => state.calendar.month,
    month =>
        moment()
            .month(month)
            .year(),
);

export const daysSelector = createSelector(
    yearSelector,
    state => state.calendar.month,
    (year, month) => {
        const startOfMonth = moment()
            .month(month)
            .startOf('month')
            .dayOfYear();

        const endOfMonth = moment()
            .month(month)
            .endOf('month')
            .dayOfYear();

        return _range(startOfMonth, endOfMonth + 1).map(day => ({
            name: moment()
                .month(month)
                .dayOfYear(day)
                .format('D dddd'),
            unique: `${year}${day}`,
            day,
        }));
    },
);

export const monthNameSelector = createSelector(
    state => state.calendar.month,
    month =>
        moment()
            .month(month)
            .format('MMMM'),
);

export const trainingDaysSelector = data.trainings.map(training => {
    const year = moment(training.event.startDate).year();
    const day = moment(training.event.startDate).dayOfYear();
    return `${year}${day}`;
});
