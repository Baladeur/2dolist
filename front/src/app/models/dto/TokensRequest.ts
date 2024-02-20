export class TokensRequest {
    accessToken: string;
    refreshToken: string;
    email: string;

    constructor(accessToken: string, refreshToken: string, email: string) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
    }

};
