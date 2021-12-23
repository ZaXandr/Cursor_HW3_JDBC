package dao;

import java.sql.SQLException;
import java.util.List;
import entity.Worker;

public interface WorkerDAO {
    void add(Worker worker) throws SQLException;

    List<Worker> getAll() throws SQLException;

    Worker getById(int id) throws SQLException;

    void update(Worker worker) throws SQLException;

    void remove(int id) throws SQLException;
}
