package dbSelect;

import entity.Product;
import entity.Shop;
import entity.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbSelect {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cursor";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;


    public DbSelect() {
        try {
            Class.forName(DB_DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }
    }

    //1
    public List<Worker> getWorkers() {
        List<Worker> lst = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM cursor.worker");
            while (resultSet.next()) {
                lst.add(buildWorker(resultSet));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    //2
    public List<Product> getProductsByPrice(double min, double max) {
        List<Product> lst = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select *from cursor.product WHERE Price BETWEEN ? AND ?");
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lst.add(buildProduct(resultSet));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    //3
    public List<Product> getProdByPriceAndType(double price, String type) {
        List<Product> lst = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cursor.product WHERE Price<? AND Type=?");
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, type);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lst.add(buildProduct(resultSet));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    //4
    public List<Shop> getShopByStreet(String name) {
        List<Shop> lst = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from cursor.shop WHERE Street LIKE ? ");
            preparedStatement.setString(1, '%' + name + '%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lst.add(buildShop(resultSet));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lst;
    }

    //5
    public Worker getWorkerById(int id) {
        Worker worker = new Worker();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cursor.product WHERE idProduct = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            worker = buildWorker(resultSet);
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return worker;
    }

    private Shop buildShop(ResultSet resultSet) throws SQLException {
        Shop shop = new Shop();

        shop.setIdShop(resultSet.getInt(1));
        shop.setName(resultSet.getString(2));
        shop.setCity(resultSet.getString(3));
        shop.setStreet(resultSet.getString(4));
        return shop;

    }

    private Product buildProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setIdProduct(resultSet.getInt(1));
        product.setName(resultSet.getString(2));
        product.setPrice(resultSet.getDouble(3));
        product.setCount(resultSet.getInt(4));
        product.setType(resultSet.getString(5));
        return product;
    }

    private Worker buildWorker(ResultSet resultSet) throws SQLException {
        Worker worker = new Worker();
        worker.setIdWorker(resultSet.getInt(1));
        worker.setFirstName(resultSet.getString(2));
        worker.setSecondName(resultSet.getString(3));
        worker.setAge(resultSet.getInt(4));
        worker.setAddress(resultSet.getString(5));
        return worker;
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection close");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}

