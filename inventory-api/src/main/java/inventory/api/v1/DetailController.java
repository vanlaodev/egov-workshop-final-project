package inventory.api.v1;

import inventory.api.v1.model.*;
import inventory.entity.Asset;
import inventory.service.AccessTokenService;
import inventory.service.InventoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path="/api/v1")
public class DetailController {

	private final static Logger LOGGER = Logger.getLogger(DetailController.class.getName());

	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
	private InventoryDetailService inventoryDetailService;

	@CrossOrigin
	@PostMapping(path="/createDetail")
	public @ResponseBody
	OcreateDetail createDetail (@RequestBody IcreateDetail input) {
		if(input == null)
			return OcreateDetail.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OcreateDetail.returnError("AccessToken錯誤!!");

		String userName = accessTokenService.getUserName(input.getAccessToken());

		Asset asset = new Asset();
		asset.setAssetNum(input.getAssetNum());
		asset.setAssetType(input.getAssetType());
		asset.setBrand(input.getBrand());
		asset.setDeptId(input.getDept());
		asset.setDescription(input.getDescription());
		asset.setBudgetYear(input.getBudgetYear());
		asset.setFeature(input.getFeature());
		asset.setEconomicClass(input.getEconomicClass());
		asset.setId(input.getAssetId());
		asset.setLocation(input.getLocation());
		asset.setModelNum(input.getModelNum());
		asset.setRemarkCn(input.getRemarkCn());
		asset.setRemarkPt(input.getRemarkPt());
		asset.setRetrievalMethod(input.getRetrievalMethod());
		asset.setSerialNum(input.getSerialNum());
		asset.setSupplier(input.getSupplier());
		asset.setUserName(input.getUserName());
		asset.setValue(input.getValue());
		asset.setYear(input.getYear());
		asset.setDimension(input.getDimension());

		OcreateDetail ocreateDetail = new OcreateDetail();
		ocreateDetail.setData(inventoryDetailService.save(input.getInventoryMasterId(), asset, userName));

		return ocreateDetail;
	}

	@CrossOrigin
	@PostMapping(path="/editDetail")
	public @ResponseBody
	OeditDetail editDetail (@RequestBody IeditDetail input) {

		if(input == null)
			return OeditDetail.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OeditDetail.returnError("AccessToken錯誤!!");

		String userName = accessTokenService.getUserName(input.getAccessToken());

		return inventoryDetailService.editDetail(input,userName);
	}

	@CrossOrigin
	@PostMapping(path="/deleteDetail")
	public @ResponseBody
	OdeleteDetail deleteDetail (@RequestBody IdeleteDetail input) {

		if(input == null)
			return OdeleteDetail.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OdeleteDetail.returnError("AccessToken錯誤!!");

		String userName = accessTokenService.getUserName(input.getAccessToken());

		return inventoryDetailService.deleteDetail(input,userName);
	}

	@CrossOrigin
	@PostMapping(path="/searchDetail")
	public @ResponseBody
	OsearchDetail searchDetail (@RequestBody IsearchDetail input) {

		if(input == null)
			return OsearchDetail.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OsearchDetail.returnError("AccessToken錯誤!!");

		return inventoryDetailService.searchDetail(input);
	}

	@GetMapping(path="/testDetail")
	public @ResponseBody String testDetail() {
		// This returns a JSON or XML with the users
		return "Detail API OK";
	}
}
