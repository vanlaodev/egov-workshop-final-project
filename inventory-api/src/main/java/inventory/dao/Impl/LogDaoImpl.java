package inventory.dao.Impl;

import inventory.dao.LogDao;
import inventory.entity.Log;
import inventory.utils.DateUtils;
import inventory.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository("logDao")
public class LogDaoImpl implements LogDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Log> find(Integer masterId, String masterTitle, String functionName, Date startDate, Date endDate) {
        String hql = "FROM Log where 1=1 ";

        if( masterId!=null && masterId>0 ) hql += " and masterId = :masterId ";
        if( !StringUtils.isEmpty(masterTitle) ) hql += " and masterTitle like :masterTitle ";
        if( !StringUtils.isEmpty(functionName) ) hql += " and functionName like :functionName ";
        if( startDate!=null ) hql += " and createDate >= STR_TO_DATE('" + DateUtils.toyyyyMMdd(startDate) +  "', '%Y/%m/%d')";
        if( endDate!=null ) hql += " and createDate <= STR_TO_DATE('" + DateUtils.toyyyyMMdd(endDate) +  "', '%Y/%m/%d')";

        Query query = entityManager.createQuery(hql);
        if( masterId!=null && masterId>0 ) query.setParameter("masterId", masterId);
        if( !StringUtils.isEmpty(masterTitle) ) query.setParameter("masterTitle", "%"+masterTitle.trim()+"%");
        if( !StringUtils.isEmpty(functionName) ) query.setParameter("type", "%"+functionName.trim().toUpperCase()+"%");

        return query.getResultList();
    }
}
