export const messages = {
    en: {
        hello: 'Hello',
    },
    pl: {
        hello: 'Dzień dobry',
    },
};

export const dateTimeFormats = {
    en: {
        short: {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
        },
        long: {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            weekday: 'short',
            hour: 'numeric',
            minute: 'numeric',
            hour12: true,
        },
    },
    pl: {
        short: {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
        },
        long: {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            weekday: 'short',
            hour: 'numeric',
            minute: 'numeric',
        },
    },
};

export const numberFormats = {
    en: {
        currency: {
            style: 'currency', currency: 'USD',
        },
        percent: {
            style: 'percent',
        },
    },

    pl: {
        currency: {
            style: 'currency', currency: 'PLN',
        },
        percent: {
            style: 'percent',
        },
    },
};

export default {
    messages,
    dateTimeFormats,
    numberFormats,
};
