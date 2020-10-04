package database.practise.dao;

import database.practise.bean.StaffLevel;
import database.practise.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffLevelDaoImpl implements IDao<StaffLevel> {
    @Override
    public int add(StaffLevel data) throws Exception {
        return 0;
    }

    @Override
    public int remove(Object key) throws Exception {
        return 0;
    }

    @Override
    public int update(StaffLevel data) throws Exception {
        return 0;
    }

    @Override
    public List<StaffLevel> query(StaffLevel data) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        final StringBuilder sql = new StringBuilder("SELECT id, s_name FROM staff_level ");

        List<StaffLevel> list = new ArrayList<>();
        Object[] parameters = convertToParams(sql, data);

        try {
            conn = DBManager.get().getConnection();
            ps = DBManager.getPS(conn, sql.toString(), parameters);
            rs = ps.executeQuery();
            while (rs.next()) {
                StaffLevel staffLevel = new StaffLevel(rs.getInt("id"), rs.getString("s_name"));
                list.add(staffLevel);
            }
        } finally {
            DBManager.get().close(rs, ps, conn);
        }
        return list;
    }

    @Override
    public Object[] convertToParams(StringBuilder builder, StaffLevel staffLevel) {
        if (staffLevel == null) return null;
        List<Object> params = new ArrayList<>();
        if (!StringUtils.isEmpty(staffLevel.getName())) {
            params.add(staffLevel.getName());
            builder.append(" WHERE s_name = ? ");
        }
        return params.toArray();
    }


    @Override
    public StaffLevel findById(Object key) throws Exception {
        return null;
    }
}
