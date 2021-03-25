package inventory.dao.Impl;

import inventory.dao.DashBoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository("dashBoardDao")
public class DashBoardDaoImpl implements DashBoardDao, Serializable {

    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional
    public String findTitleById(Integer id) {
        String sql = "SELECT title FROM inventory_master where id = "+id;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        List<Map<String, Object>> rsList = jdbcTemplate.queryForList(sql);
        if (rsList == null || rsList.size() <= 0)
            return null;

        try {
            for (Map<String, Object> rs : rsList) {
                String title = (String) rs.get("title");
                return title;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
