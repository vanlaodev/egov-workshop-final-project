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

    createMaster(master) {
        this._assetLoggedIn();
        return this.httpClient.post('v1/createMaster', master);
    }

    searchMaster(searchCriteria) {
        this._assetLoggedIn();
        return this.httpClient.post('v1/searchMaster', searchCriteria);
    }

    editMaster(master) {
        this._assetLoggedIn();
        return this.httpClient.post('v1/editMaster', master);
    }

    deleteMaster(id) {
        this._assetLoggedIn();
        return this.httpClient.post('v1/deleteMaster', {
            id: id
        });
    }

    _assetLoggedIn() {
        if (!store.state.loggedInUser) throw 'User not logged in.';
    }
}

export default InventoryApi;