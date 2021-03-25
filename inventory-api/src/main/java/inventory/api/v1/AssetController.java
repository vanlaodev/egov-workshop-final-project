package inventory.api.v1;

import inventory.api.v1.model.IsearchAsset;
import inventory.api.v1.model.OsearchAsset;
import inventory.service.AccessTokenService;
import inventory.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path="/api/v1")
public class AssetController {

	private final static Logger LOGGER = Logger.getLogger(AssetController.class.getName());

	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
	private AssetService assetService;

	@CrossOrigin
	@PostMapping(path="/findAssetsByDept")
	public @ResponseBody OsearchAsset findAssetsByDept (@RequestBody IsearchAsset input) {

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OsearchAsset.returnError("AccessToken錯誤!!");

		OsearchAsset osearchAsset = new OsearchAsset();
		osearchAsset.setData(assetService.findByDeptId(input.getDeptId()));
		return osearchAsset;
	}
}
