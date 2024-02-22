export class TokensRequest {
    accessToken: string;
    refreshToken: string;
    email: string;

    constructor(accessToken: string | null, refreshToken: string | null, email: string | null) {
        this.accessToken = accessToken ?? '';
        this.refreshToken = refreshToken ?? '';
        this.email = email ?? '';
    }

};
