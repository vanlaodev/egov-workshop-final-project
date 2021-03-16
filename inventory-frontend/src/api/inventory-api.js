import axios from "axios";
import store from "../store";
import bus from "../eventbus";

class InventoryApi {
  constructor(baseUrl) {
    this.baseUrl = baseUrl;
    this.httpClient = axios.create({
      baseURL: baseUrl,
      transformRequest: [
        (data) => {
          if (store.state.loggedInUser) {
            if (!data) data = {}
            data = Object.assign(data, {
              accessToken: store.state.loggedInUser.accessToken,
            });
          }
          return data;
        },
        ...axios.defaults.transformRequest,
      ],
    });
  }

  /*
   * Inventory master
   */

  async createMaster(master) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/createMaster", master);
    return this._handleResp(resp.data);
  }

  async searchMaster(searchCriteria) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/searchMaster", searchCriteria);
    return this._handleResp(resp.data);
  }

  async getMasterById(masterId) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/searchMasterById", {
      id: masterId
    });
    return this._handleResp(resp.data);
  }

  async updateMaster(master) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/editMaster", master);
    return this._handleResp(resp.data);
  }

  async deleteMaster(id) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/deleteMaster", {
      id: id,
    });
    return this._handleResp(resp.data);
  }

  /*
   * Inventory details
   */

  async searchDetail(searchCriteria) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/searchDetail", searchCriteria);
    return this._handleResp(resp.data);
  }

  async createDetail(detail) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/createDetail", detail);
    return this._handleResp(resp.data);
  }

  async updateDetail(detail) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/editDetail", detail);
    return this._handleResp(resp.data);
  }

  async deleteDetail(id) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/deleteDetail", {
      id: id
    });
    return this._handleResp(resp.data);
  }

  /*
   * Dashboard
   */

  async getDashboardProgress() {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/dashboardProgress");
    return this._handleResp(resp.data);
  }

  async getDashboardMasterCount() {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/dashboardMasterCount");
    return this._handleResp(resp.data);
  }

  async getDashBoardDetailCount() {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/dashBoardDetailCount");
    return this._handleResp(resp.data);
  }

  /*
   * Helper functions
   */

  _handleResp(resp) {
    if (resp.code == 0) {
      return resp.data;
    } else if (resp.code == -2) {
      bus.$emit("LOGIN_REQUIRED");
      if (!resp.msg) resp.msg = "Login required.";
    } else if (resp.code == -3) {
      // reserved - not confirmed whether server side will do permission checking
      bus.$emit("API_NO_PERMISSION");
      if (!resp.msg) resp.msg = "Permission denied.";
    }
    throw resp.msg;
  }

  _assertUserLoggedIn() {
    if (!store.state.loggedInUser) {
      bus.$emit("LOGIN_REQUIRED");
      throw "Login required.";
    }
  }
}

export default InventoryApi;