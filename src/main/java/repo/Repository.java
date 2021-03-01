package repo;

import java.sql.ResultSet;

public interface Repository {
    ResultSet getAll();
    int insert(Object object);
    int update(int id, Object object);
    int delete(int id);
}
