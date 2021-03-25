package inventory.service;

import inventory.api.v1.model.OgetPermission;
import inventory.api.v1.model.Permission;

public interface AccessTokenService {
    public boolean checkAccessToken(String accessToken);

    public Permission getPermission(String accessToken);

    public String getUserName(String accessToken);
}

