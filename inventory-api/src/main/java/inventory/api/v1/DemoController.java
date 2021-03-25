package inventory.api.v1;

import inventory.api.v1.model.IcreateMaster;
import inventory.dao.DashBoardDao;
import inventory.repository.InventoryMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/demo")
public class DemoController {

	private final static Logger LOGGER = Logger.getLogger(DemoController.class.getName());

	@CrossOrigin
	@GetMapping(path="/test")
	public @ResponseBody String test() {
		return "test";
	}

	@CrossOrigin
	@PostMapping(path="/test2")
	public @ResponseBody String test2(@RequestBody IcreateMaster master) {
		return "test";
	}

	@CrossOrigin
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		return "Saved";
	}

}
