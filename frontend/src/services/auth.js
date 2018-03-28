const authService = {
    login(credentials) {
        // temporar solution, as API doesn't work yet

        return new Promise((resolve, reject) => {
            if (credentials.email === 'm@m.mm' && credentials.password === '111111') {
                resolve();
            } else {
                reject();
            }
        });
    },
};

export default authService;
