package inventory.service.impl;

import inventory.dao.LogDao;
import inventory.entity.Log;
import inventory.repository.LogRepository;
import inventory.service.AccessTokenService;
import inventory.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private LogDao logDao;

    @Override
    public Log save(Integer masterId, String title, String functionName, Object input, Object output, String userName) {
        Log log = new Log(masterId, title, functionName, input, output, userName);
        return logRepository.save(log);
    }

    @Override
    public List<Log> find(Integer masterId, String masterTitle, String functionName, Date startDate, Date endDate) {
        return logDao.find(masterId, masterTitle, functionName, startDate, endDate);
    }
}
