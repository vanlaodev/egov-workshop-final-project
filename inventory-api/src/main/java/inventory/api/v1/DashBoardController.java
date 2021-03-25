package inventory.api.v1;

import inventory.api.v1.dashboardModel.*;
import inventory.api.v1.model.IBaseClass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Controller	// This means that this class is a Controller
@RequestMapping(path="/api/v1")
public class DashBoardController {

	private final static Logger LOGGER = Logger.getLogger(DashBoardController.class.getName());


	@CrossOrigin
	@PostMapping(path="/dashboardProgress")
	public @ResponseBody
	ODashBoardProgress dashboardProgress(@RequestBody IBaseClass master) {


		ODashBoardProgress ret = new ODashBoardProgress();
		ret.setCode(0);
		ret.setMsg(null);

		ODashBoardProgress.Data data = new ODashBoardProgress.Data();
		data.setYear(2021);
		data.setDeptName("資訊系統部");
		List<Progress> list = new ArrayList<Progress>();
		list.add(new Progress("電腦資產", 50, 20));
		list.add(new Progress("文具資產", 200, 60));
		list.add(new Progress("二維碼讀取器", 20, 0));
		list.add(new Progress("投影機", 10, 2));
		list.add(new Progress("手提電腦資產", 22, 4));
		list.add(new Progress("枱椅資產", 50, 8));
		list.add(new Progress("書本", 138, 50));
		data.setProgress(list);
		ret.setData(data);

		return ret;
	}


	@CrossOrigin
	@PostMapping(path="/dashboardMasterCount")
	public @ResponseBody
	ODashBoardMasterCount dashboardMasterCount(@RequestBody IBaseClass master) {

		ODashBoardMasterCount ret = new ODashBoardMasterCount();
		ret.setCode(0);
		ret.setMsg(null);

		ODashBoardMasterCount.Data data = new ODashBoardMasterCount.Data();
		data.setYear(2021);
		List<Counter> list = new ArrayList<Counter>();
		list.add(new Counter("資訊部", 45));
		list.add(new Counter("文書部", 36));
		list.add(new Counter("財務部", 20));
		list.add(new Counter("管理委員會", 17));
		list.add(new Counter("文康部", 28));
		list.add(new Counter("出納部", 53));
		list.add(new Counter("會計部", 18));
		data.setCounter(list);
		ret.setData(data);

		return ret;
	}


	@CrossOrigin
	@PostMapping(path="/dashBoardDetailCount")
	public @ResponseBody
	ODashBoardDetailCount dashBoardDetailCount(@RequestBody IBaseClass master) {

		ODashBoardDetailCount ret = new ODashBoardDetailCount();
		ret.setCode(0);
		ret.setMsg(null);

		ODashBoardDetailCount.Data data = new ODashBoardDetailCount.Data();
		data.setYear(2021);
		List<DetailCount> list = new ArrayList<DetailCount>();
		list.add(new DetailCount("電腦資產", 60));
		list.add(new DetailCount("枱椅資產", 70));
		list.add(new DetailCount("文具資產", 87));
		list.add(new DetailCount("投影機", 45));
		list.add(new DetailCount("手提電腦資產", 34));
		list.add(new DetailCount("二維碼讀取器", 20));
		list.add(new DetailCount("書本", 38));
		data.setDetailCount(list);
		ret.setData(data);

		return ret;
	}

}
