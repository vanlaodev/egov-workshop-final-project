package inventory.api.v1.model;

import java.io.Serializable;

public class IBaseClass implements Serializable {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
