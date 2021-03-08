import axios from 'axios';

class AssetMgmtApi {
    constructor(baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = axios.create({
            baseURL: baseUrl,
          });
    }
}

export default AssetMgmtApi;