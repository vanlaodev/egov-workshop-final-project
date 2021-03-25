package inventory.api.v1;

import inventory.api.v1.model.*;
import inventory.service.AccessTokenService;
import inventory.service.InventoryMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path="/api/v1")
public class AuthController {

	private final static Logger LOGGER = Logger.getLogger(AuthController.class.getName());

	@Autowired
	private AccessTokenService accessTokenService;

	@CrossOrigin
	@PostMapping(path="/getPermission")
	public @ResponseBody OgetPermission getPermission (@RequestBody IBaseClass input) {

		if(input == null)
			return OgetPermission.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OgetPermission.returnError("AccessToken錯誤!!",-2);

		Permission permission = accessTokenService.getPermission(input.getAccessToken());
		return OgetPermission.returnSuccess(permission,null);
	}

	@CrossOrigin
	@PostMapping(path="/getUserName")
	public @ResponseBody String getUserName (@RequestBody IBaseClass input) {

		if(input == null)
			return "";

		return accessTokenService.getUserName(input.getAccessToken());
	}
}
