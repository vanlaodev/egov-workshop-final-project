import axios from 'axios';
import store from '../store'

class InventoryApi {
    constructor(baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = axios.create({
            baseURL: baseUrl,
            transformRequest: [(data) => {
                if (store.state.loggedInUser) {
                    data = Object.assign(data, { accessToken: store.state.loggedInUser.accessToken });
                }
                return data;
            }, ...axios.defaults.transformRequest]
        });
    }

    async createMaster(master) {
        this._assertUserLoggedIn();
        const resp = await this.httpClient.post('v1/createMaster', master);
        return this._handleResp(resp.data);
    }

    async searchMaster(searchCriteria) {
        this._assertUserLoggedIn();
        const resp = await this.httpClient.post('v1/searchMaster', searchCriteria);
        return this._handleResp(resp.data);
    }

    async updateMaster(master) {
        this._assertUserLoggedIn();
        const resp = await this.httpClient.post('v1/editMaster', master);
        return this._handleResp(resp.data);
    }

    async deleteMaster(id) {
        this._assertUserLoggedIn();
        const resp = await this.httpClient.post('v1/deleteMaster', {
            id: id
        });
        return this._handleResp(resp.data);
    }

    _handleResp(resp) {
        if (resp.code == 0) {
            return resp.data;
        } else if (resp.code == -2) {
            // TODO: access token invalid - redirect to login page
        }
        throw resp.msg;
    }

    _assertUserLoggedIn() {
        if (!store.state.loggedInUser) throw 'User not logged in.';
    }
}

export default InventoryApi;