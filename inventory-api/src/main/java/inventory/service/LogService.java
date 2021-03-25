package inventory.service;

import inventory.api.v1.model.Permission;
import inventory.entity.Log;

import java.util.Date;
import java.util.List;

public interface LogService {

    public Log save(Integer masterId, String title, String functionName, Object input, Object output, String userName);

    public List<Log> find(Integer masterId, String masterTitle, String functionName, Date startDate, Date endDate) ;
}

