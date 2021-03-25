package inventory.api.v1;

import inventory.api.v1.model.*;
import inventory.service.AccessTokenService;
import inventory.service.InventoryMasterService;
import inventory.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path="/api/v1")
public class MasterController {

	private final static Logger LOGGER = Logger.getLogger(MasterController.class.getName());

	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
	private InventoryMasterService inventoryMasterService;
	@Autowired
	private LogService logService;

	@CrossOrigin
	@PostMapping(path="/createMaster")
	public @ResponseBody OcreateMaster createMaster (@RequestBody IcreateMaster input) {

		if(input == null)
			return OcreateMaster.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OcreateMaster.returnError("AccessToken錯誤!!",-2);

		String userName = accessTokenService.getUserName(input.getAccessToken());

		return inventoryMasterService.createMaster(input,userName);
	}

	@CrossOrigin
	@PostMapping(path="/searchMaster")
	public @ResponseBody
	OsearchMaster searchMaster (@RequestBody IsearchMaster input) {

		if(input == null)
			return OsearchMaster.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OsearchMaster.returnError("AccessToken錯誤!!",-2);

		return inventoryMasterService.searchMaster(input);
	}

	@CrossOrigin
	@PostMapping(path="/searchMasterById")
	public @ResponseBody
	OsearchMasterById searchMasterById (@RequestBody IsearchMasterById input) {

		if(input == null)
			return OsearchMasterById.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OsearchMasterById.returnError("AccessToken錯誤!!",-2);

		return inventoryMasterService.searchMasterId(input);
	}

	@CrossOrigin
	@PostMapping(path="/searchMasterByHolder")
	public @ResponseBody
	OsearchMaster searchMasterByHolder (@RequestBody IsearchMasterByHolder input) {

		if(input == null)
			return OsearchMaster.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OsearchMaster.returnError("AccessToken錯誤!!",-2);

		return inventoryMasterService.searchMasterByHolder(input);
	}

	@CrossOrigin
	@PostMapping(path="/editMaster")
	public @ResponseBody
	OeditMaster editMaster (@RequestBody IeditMaster input) {

		if(input == null)
			return OeditMaster.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OeditMaster.returnError("AccessToken錯誤!!",-2);

		String userName = accessTokenService.getUserName(input.getAccessToken());

		return inventoryMasterService.editMaster(input,userName);
	}

	@CrossOrigin
	@PostMapping(path="/deleteMaster")
	public @ResponseBody
	OdeleteMaster deleteMaster (@RequestBody IdeleteMaster input) {

		if(input == null)
			return OdeleteMaster.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OdeleteMaster.returnError("AccessToken錯誤!!",-2);

		String userName = accessTokenService.getUserName(input.getAccessToken());

		return inventoryMasterService.deleteMaster(input,userName);
	}

	@CrossOrigin
	@GetMapping(path="/testMaster")
	public @ResponseBody String testMaster() {
		// This returns a JSON or XML with the users
		return "Master API OK";
	}
}
