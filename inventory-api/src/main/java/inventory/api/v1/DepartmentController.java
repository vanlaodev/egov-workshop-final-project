package inventory.api.v1;

import inventory.api.v1.model.*;
import inventory.service.AccessTokenService;
import inventory.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping(path="/api/v1")
public class DepartmentController {

	private final static Logger LOGGER = Logger.getLogger(DepartmentController.class.getName());

	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
	private DepartmentService departmentService;

	@CrossOrigin
	@PostMapping(path="/findAllDept")
	public @ResponseBody OfindAllDept findAllDept (@RequestBody IfindAllDept input) {

		if(!accessTokenService.checkAccessToken(input.getAccessToken()))
			return OfindAllDept.returnError("AccessToken錯誤!!");

		return departmentService.findAllDept(input);
	}
}
