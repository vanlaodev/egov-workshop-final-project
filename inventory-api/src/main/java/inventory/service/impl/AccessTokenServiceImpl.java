package inventory.service.impl;

import inventory.api.v1.model.OgetPermission;
import inventory.api.v1.model.Permission;
import inventory.service.AccessTokenService;
import inventory.utils.StringUtils;
import org.springframework.stereotype.Service;

@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {
    @Override
    public boolean checkAccessToken(String accessToken) {
        if(StringUtils.isEmpty(accessToken)) return false;

        if("EgovWorkshop2020".compareTo(accessToken) == 0 ||"EgovWorkshop2020Admin".compareTo(accessToken) == 0)
            return true;
        else
            return false;
    }

    @Override
    public Permission getPermission(String accessToken) {
        if(accessToken == null)
            return null;

        Permission permission = new Permission();
        if("EgovWorkshop2020".compareTo(accessToken) == 0){
            permission.setGroup("USER");
        }else if("EgovWorkshop2020Admin".compareTo(accessToken) == 0){
            permission.setGroup("ADMIN");
        }else{
            permission.setGroup(null);
        }

        return permission;

    }

    @Override
    public String getUserName(String accessToken) {
        if(accessToken == null)
            return null;


        if("EgovWorkshop2020".compareTo(accessToken) == 0){
            return "JOHN";
        }else if("EgovWorkshop2020Admin".compareTo(accessToken) == 0){
            return "ADMIN";
        }else{
            return null;
        }
    }


}
