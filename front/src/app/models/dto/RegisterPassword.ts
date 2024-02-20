export class RegisterPassword {
    email: string;
    password: string;
    repeatPassword: string;
    registrationToken: string;

    constructor(email: string, password: string, repeatPassword: string, registrationToken: string) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.registrationToken = registrationToken;
    }

};
