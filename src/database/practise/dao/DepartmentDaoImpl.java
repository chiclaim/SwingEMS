package database.practise.dao;

import database.practise.bean.Department;
import database.practise.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements IDao<Department> {
    @Override
    public int add(Department data) throws Exception {
        Object[] parameters = new Object[]{data.getName()};
        return DBManager.get().executeUpdate("INSERT INTO department (d_name) " +
                "VALUES (?)", parameters);
    }

    @Override
    public int remove(Object key) throws Exception {
        Object[] parameters = new Object[]{key};
        return DBManager.get().executeUpdate("DELETE FROM department WHERE id = ?", parameters);
    }

    @Override
    public int update(Department data) throws Exception {
        Object[] parameters = new Object[]{data.getName(), data.getId()};
        return DBManager.get().executeUpdate("UPDATE department SET d_name = ? WHERE id = ?", parameters);
    }

    @Override
    public List<Department> query(Department data) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        final StringBuilder sql = new StringBuilder("SELECT id, d_name FROM department");

        List<Department> list = new ArrayList<>();
        Object[] parameters = whereParams(sql, data);

        try {
            conn = DBManager.get().getConnection();
            ps = DBManager.getPS(conn, sql.toString(), parameters);
            rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department(rs.getInt("id"), rs.getString("d_name"));
                list.add(department);
            }
        } finally {
            DBManager.get().close(rs, ps, conn);
        }
        return list;
    }

    @Override
    public Object[] whereParams(StringBuilder builder, Department department) {
        if (department == null) return null;
        List<Object> params = new ArrayList<>();
        if (!StringUtils.isEmpty(department.getName())) {
            params.add(department.getName());
            builder.append(" WHERE d_name = ? ");
        }
        return params.toArray();
    }

}
