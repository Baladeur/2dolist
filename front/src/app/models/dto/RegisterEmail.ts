import { environment } from "../../../environments/environment";

export class RegisterEmail {
    email: string;
    emailRepeat: string;
    frontUrl!: string;

    constructor(email: string, emailRepeat: string, frontUrl: string | null) {
        this.email = email;
        this.emailRepeat = emailRepeat;
        if (!frontUrl) {
            this.setDefaultFrontUrl();
        } else {
            this.frontUrl = frontUrl;
        }
    }

    setDefaultFrontUrl() {
        this.frontUrl = environment.frontUrl + environment.frontRegistrationComleteUrl;
    }

};
