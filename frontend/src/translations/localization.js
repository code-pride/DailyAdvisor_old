export const messages = {
    en: {
        common: {
            name: 'Name',
            lastname: 'Lastname',
            city: 'City',
            email: 'Email',
            password: 'Password',
            repeatPassword: 'Repeat password',
            user: 'User',
            coach: 'Coach',
            casualUser: 'Casual user',
            signUp: 'Sign Up',
            login: 'Login',
            close: 'Close',
        },
        formErrorMsgs: {
            provideShorterInput: 'Please provide shorter input',
            provideLongerInput: 'Please provide longer input',
            wrongEmailAddres: 'This is wrong addres',
            requiredField: 'This field is required',
            passwordAreDifferent: 'Password are different',
        },
        userExist: 'User exist',
        yourAccountIsConfirmed: 'Your account is confirmed. You can login now.',
        yourAccountHasBenActivatedEarlier: 'Your account has been activated earlier.',
    },
    pl: {
        common: {
            name: 'Imię',
            lastname: 'Nazwisko',
            city: 'Miasto',
            email: 'Email',
            password: 'Hasło',
            repeatPassword: 'Powtórz hasło',
            user: 'Użytkownik',
            coach: 'Trener',
            casualUser: 'Zwykły użytkownik',
            signUp: 'Zarejestruj',
            login: 'Zaloguj',
            close: 'Anuluj',
        },
        formErrorMsgs: {
            provideShorterInput: 'Wprowadzony tekst jest za długi.',
            provideLongerInput: 'Wprowadzony tekst jest za krótki.',
            wrongEmailAddres: 'Zły adres e-mail.',
            requiredField: 'To pole jest obowiązkowe.',
            passwordAreDifferent: 'Hasła się od siebie różnią.',
        },
        userExist: 'Użytkownik z takim adresem email już istnieje.',
        yourAccountIsConfirmed: 'Twoje konto zostało potwierdzone. Możesz się teraz bezpiecznie zalogować.',
        yourAccountHasBenActivatedEarlier: 'Konto zostało już wcześniej aktywowane.',
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
