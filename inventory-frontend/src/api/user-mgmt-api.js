import axios from "axios";

class UserMgmtApi {
  constructor(baseUrl) {
    this.baseUrl = baseUrl;
    this.httpClient = axios.create({
      baseURL: baseUrl,
    });
  }

  async getUserInfoByAccessToken(accessToken) {
    // TODO
    return await new Promise((resolve) => {
      resolve({
        id: "999",
        username: "DOI_admin",
        accessToken: accessToken,
        refreshToken: null,
        deptId: 1,
        roles: ["DEPT_ADMIN"],
      });
    });
  }

  async signOut() {
    // TODO: revoke access token if need
  }
}

export default UserMgmtApi;
