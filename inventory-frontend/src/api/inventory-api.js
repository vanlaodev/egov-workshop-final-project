import axios from 'axios';

class InventoryApi {
    constructor(baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = axios.create({
            baseURL: baseUrl,
          });
    }
}

export default InventoryApi;