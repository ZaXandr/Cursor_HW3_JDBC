package service;

import connection.Util;
import dao.WorkerDAO;
import entity.Worker;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerService extends Util implements WorkerDAO {

    Connection connection = getConnection();

    @Override
    public void add(Worker worker) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO cursor.worker(idWorker,FirstName,SecondName,Age,Address) VALUES (?,?,?,?,?);";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, worker.getIdWorker());
            preparedStatement.setString(2, worker.getFirstName());
            preparedStatement.setString(3, worker.getSecondName());
            preparedStatement.setInt(4, worker.getAge());
            preparedStatement.setString(5, worker.getAddress());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }


    }

    @Override
    public List<Worker> getAll() throws SQLException {
        Statement statement = null;
        String sql = "SELECT * FROM cursor.worker;";
        List<Worker> workerList = new ArrayList<>();

        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setIdWorker(resultSet.getInt(1));
                worker.setFirstName(resultSet.getString(2));
                worker.setSecondName(resultSet.getString(3));
                worker.setAge(resultSet.getInt(4));
                worker.setAddress(resultSet.getString(5));
                workerList.add(worker);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }

        return workerList;
    }


    @Override
    public Worker getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM cursor.worker WHERE idWorker = ?;";
        Worker worker = new Worker();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            worker.setIdWorker(resultSet.getInt(1));
            worker.setFirstName(resultSet.getString(2));
            worker.setSecondName(resultSet.getString(3));
            worker.setAge(resultSet.getInt(4));
            worker.setAddress(resultSet.getString(5));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }

        return worker;
    }


    @Override
    public void update(Worker worker) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE cursor.worker SET FirstName=?,SecondName=?,Age=?,Address=? WHERE idWorker=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getSecondName());
            preparedStatement.setInt(3, worker.getAge());
            preparedStatement.setString(4, worker.getAddress());
            preparedStatement.setInt(5, worker.getIdWorker());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }


    }

    @Override
    public void remove(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM cursor.worker WHERE idWorker=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
//            if (connection != null) {
//                connection.close();
//            }
        }
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
