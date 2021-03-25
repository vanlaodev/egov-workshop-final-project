package inventory.dao;

import inventory.entity.Log;

import java.util.Date;
import java.util.List;

public interface LogDao {

    public List<Log> find(Integer masterId, String masterTitle, String functionName, Date startDate, Date endDate);

}
