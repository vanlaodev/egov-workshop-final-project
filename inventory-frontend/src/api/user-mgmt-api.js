import axios from "axios";
import {
  promiseDelay
} from "../utils/helpers"

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
        id: "999",
        username: "DOI_admin",
        accessToken: accessToken,
        refreshToken: null,
        deptId: 1,
        roles: ["DEPT_ADMIN", "DEPT_USER", "SYS_ADMIN"],
      });
    });
  }

  async signOut() {
    // TODO: revoke access token if need
  }
}

export default UserMgmtApi;