import {
    promiseDelay
} from '../utils/helpers'

// TODO

class UserMgmtApi {
    constructor(baseUrl) {
        this.baseUrl = baseUrl;
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