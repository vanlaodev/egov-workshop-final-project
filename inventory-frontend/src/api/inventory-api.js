import axios from "axios";
import store from "../store";
import bus from "../eventbus";
import * as dayjs from 'dayjs';
var isSameOrAfter = require("dayjs/plugin/isSameOrAfter");
dayjs.extend(isSameOrAfter);

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
    const dtos = this._handleResp(resp.data);
    return dtos == null ? [] : dtos.map(this._mapInventoryMasterDto);
  }

  async searchMasterByOwner(owner) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/searchMasterByHolder", {
      holderName: owner
    });
    const dtos = this._handleResp(resp.data);
    return dtos == null ? [] : dtos.map(this._mapInventoryMasterDto);
  }

  async getMasterById(masterId) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/searchMasterById", {
      id: masterId
    });
    const dto = this._handleResp(resp.data);
    return this._mapInventoryMasterDto(dto);
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
    const dtos = this._handleResp(resp.data);
    return !dtos ? [] : dtos.map(this._mapInventoryDetailDto);
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
   * Logs
   */

  async searchLog(searchCriteria) {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/searchLog", searchCriteria);
    const dtos = this._handleResp(resp.data);
    return dtos == null ? [] : dtos.map(this._mapLogDto);
  }

  /*
   * Permissions
   */

  async getPermission() {
    this._assertUserLoggedIn();
    const resp = await this.httpClient.post("v1/getPermission");
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

  _mapInventoryMasterDto(dto) {
    if (!dto) return null;
    dto.fromTime = dayjs(dto.fromTime, "YYYY/MM/DD");
    dto.endTime = dayjs(dto.endTime, "YYYY/MM/DD");
    dto.inPeriod = () => {
      return dayjs().isSameOrAfter(dto.fromTime) &&
        dayjs().isBefore(dto.endTime.add(1, "day"))
    };
    dto.canTakeInventory = () => {
      return dto.inPeriod() && dto.status == 'ACTIVE';
    };
    return dto;
  }

  _mapInventoryDetailDto(dto) {
    if (!dto) return null;
    dto.remarkZh = dto.remarkCn;
    dto.canEdit = () => true;
    dto.checked = dto.actionResult;
    return dto;
  }

  _mapLogDto(dto) {
    if (!dto) return null;
    const inputObj = JSON.parse(dto.input);
    if (inputObj) delete inputObj['accessToken'];
    return {
      id: dto.id,
      masterId: dto.masterId,
      masterTitle: dto.masterTitle,
      createDate: dayjs(dto.createDate, "YYYY/MM/DD HH:mm:ss"),
      functionName: dto.functionName,
      userName: dto.userName,
      input: inputObj,
      output: JSON.parse(dto.output)
    };
  }
}

export default InventoryApi;