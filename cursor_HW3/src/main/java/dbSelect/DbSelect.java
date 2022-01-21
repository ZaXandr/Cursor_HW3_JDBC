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

    public List<Worker> getWorkers() {
        List<Worker> lst = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT worker_id, first_name, second_name, age, address " +
                    "FROM cursor.staff");
            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setIdWorker(resultSet.getInt("worker_id"));
                worker.setFirstName(resultSet.getString("first_name"));
                worker.setSecondName(resultSet.getString("second_name"));
                worker.setAge(resultSet.getInt("age"));
                worker.setAddress(resultSet.getString("address"));
                lst.add(worker);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    public List<Product> getProductsByPrice(double min, double max) {
        List<Product> lst = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT product_id, product_name, price, count, type " +
                    "FROM cursor.products WHERE price BETWEEN ? AND ?");
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCount(resultSet.getInt("count"));
                product.setType(resultSet.getString("type"));
                lst.add(product);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    public List<Product> getProdByPriceAndType(double price, String type) {
        List<Product> lst = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT product_id, product_name, price, count, type "
                    + "FROM cursor.products WHERE price<? AND type=?");
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, type);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCount(resultSet.getInt("count"));
                product.setType(resultSet.getString("type"));
                lst.add(product);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    public List<Shop> getShopByStreet(String name) {
        List<Shop> lst = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT shop_id, shop_name, city, street FROM cursor.shops " +
                    "WHERE street LIKE ? ");
            preparedStatement.setString(1, '%' + name + '%');
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Shop shop = new Shop();

                shop.setIdShop(resultSet.getInt("shop_id"));
                shop.setName(resultSet.getString("shop_name"));
                shop.setCity(resultSet.getString("city"));
                shop.setStreet(resultSet.getString("street"));
                lst.add(shop);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lst;
    }

    public Worker getWorkerById(int id) {
        Worker worker = new Worker();
        try {
            preparedStatement = connection.prepareStatement("SELECT worker_id, first_name, second_name, age, address " +
                    "FROM cursor.staff WHERE worker_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            worker.setIdWorker(resultSet.getInt("worker_id"));
            worker.setFirstName(resultSet.getString("first_name"));
            worker.setSecondName(resultSet.getString("second_name"));
            worker.setAge(resultSet.getInt("age"));
            worker.setAddress(resultSet.getString("address"));

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return worker;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}

