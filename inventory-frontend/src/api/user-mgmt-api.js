import axios from 'axios';
import {
    promiseDelay
} from '../utils/helpers'

class UserMgmtApi {
    constructor(baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = axios.create({
            baseURL: baseUrl,
          });
    }

    async getUserInfoByAccessToken(accessToken) {
        // TODO
        await promiseDelay(1000);
        return await new Promise((resolve) => {
            resolve({
                id: 'adminid',
                username: 'admin',
                accessToken: accessToken,
                refreshToken: null,
                department: {
                    id: 'dummydept',
                },
                roles: []
            });
        });
    }

    async signOut() {
        // TODO: revoke access token if need
        await promiseDelay(1000);
    }
}

export default UserMgmtApi;