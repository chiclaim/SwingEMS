package database.practise.dao;

import java.util.List;

public interface IDao<T> {

    String AND = " AND";

    int add(T data) throws Exception;
    int remove(Object key) throws Exception;
    int update(T data) throws Exception;
    List<T> query(T data) throws Exception;
    Object[] whereParams(StringBuilder builder, T data);

}
