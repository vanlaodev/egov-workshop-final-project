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
    const users = [{
        username: 'user',
        type: "User",
        accessToken: "EgovWorkshop2020",
        deptId: 1,
        roles: ["DEPT_USER"]
      },
      {
        username: 'admin',
        type: "Admin",
        accessToken: "EgovWorkshop2020Admin",
        deptId: 1,
        roles: ["DEPT_ADMIN", "DEPT_USER", "SYS_ADMIN"]
      },
    ];
    const user = users.find(u => u.accessToken == accessToken);
    if (!user) return null;
    return {
      username: user.username,
      accessToken: user.accessToken,
      refreshToken: null,
      deptId: user.deptId,
      roles: user.roles,
    };
  }

  async signOut() {
    // TODO: revoke access token if need
  }
}

export default UserMgmtApi;