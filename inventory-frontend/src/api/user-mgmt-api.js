import axios from 'axios';

class UserMgmtApi {
    constructor(baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = axios.create({
            baseURL: baseUrl,
          });
    }

    async getUserInfoByAccessToken(accessToken) {
        // TODO
        return await new Promise((resolve) => {
            resolve({
                id: 'adminid',
                username: 'admin',
                accessToken: accessToken,
                refreshToken: null,
                dept: {
                    id: 1,
                },
                roles: []
            });
        });
    }

    async signOut() {
        // TODO: revoke access token if need
    }
}

export default UserMgmtApi;