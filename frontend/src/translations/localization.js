export const messages = {
    en: {
        hello: 'Hello',
        userExist: 'User exist',
        signUp: 'Sign Up',
        login: 'Login',
        formErrorMsgs: {
            provideShorterInput: 'Please provide shorter input',
            provideLongerInput: 'Please provide longer input',
            wrongEmailAddres: 'This is wrong addres',
            requiredField: 'This field is required',
            passwordAreDifferent: 'Password are different',
        },
    },
    pl: {
        hello: 'Dzień dobry',
        userExist: 'Użytkownik z takim adresem email już istnieje.',
        signUp: 'Zarejestruj',
        login: 'Zaloguj',
        formErrorMsgs: {
            provideShorterInput: 'Wprowadzony tekst jest za długi.',
            provideLongerInput: 'Wprowadzony tekst jest za krótki.',
            wrongEmailAddres: 'Zły adres e-mail.',
            requiredField: 'To pole jest obowiązkowe.',
            passwordAreDifferent: 'Hasła się od siebie różnią.',
        },
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
