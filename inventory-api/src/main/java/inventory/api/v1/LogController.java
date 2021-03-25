package inventory.api.v1;

import inventory.api.v1.model.*;
import inventory.entity.Log;
import inventory.service.AccessTokenService;
import inventory.service.InventoryMasterService;
import inventory.service.LogService;
import inventory.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(path="/api/v1")
public class LogController {

	private final static Logger LOGGER = Logger.getLogger(LogController.class.getName());

	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
	private LogService logService;

	@CrossOrigin
	@PostMapping(path="/searchLog")
	public @ResponseBody
	OsearchLog searchLog (@RequestBody IsearchLog input) {

		if(input == null)
			return OsearchLog.returnError("Input is Null!!");

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OsearchLog.returnError("AccessToken錯誤!!",-2);

		Date statDate = DateUtils.toDate(input.getStartDate(),"yyyy/MM/dd");
		Date endDate = DateUtils.toDate(input.getEndDate(),"yyyy/MM/dd");;

		List<Log> logs = logService.find(input.getMasterId(), input.getMasterTitle(), null, statDate, endDate);

		return OsearchLog.returnSuccess(logs,"");
	}


}
