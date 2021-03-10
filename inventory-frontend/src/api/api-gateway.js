import Vue from 'vue';
import InventoryApi from './inventory-api';
import AssetMgmtApi from './asset-mgmt-api';
import UserMgmtApi from './user-mgmt-api';

class ApiGateway {

    constructor() {
        this._inventoryApi = new InventoryApi(process.env.VUE_APP_INVENTORY_API_BASE_URL);
        this._assetMgmtApi = new AssetMgmtApi(process.env.VUE_APP_ASSET_MGMT_API_BASE_URL);
        this._userMgmtApi = new UserMgmtApi(process.env.VUE_APP_USER_MGMT_API_BASE_URL);
    }

    get userMgmtApi() {
        return this._userMgmtApi;
    }

    get inventoryApi() {
        return this._inventoryApi;
    }

    get assetMgmtApi() {
        return this._assetMgmtApi;
    }
}

const apiGateway = new ApiGateway();

const Installer = {}
Installer.install = function (Vue) {
    Vue.prototype.$api = apiGateway
};

Vue.use(Installer);

export default apiGateway;