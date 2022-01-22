package com.zakharov;

import com.zakharov.entity.Product;
import com.zakharov.entity.Shop;
import com.zakharov.entity.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbSelect {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cursor";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public DbSelect() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Worker> getWorkers() {
        List<Worker> lst = new ArrayList<>();
        final String sql = "SELECT worker_id, first_name, second_name, age, address FROM cursor.staff";
        try (
                final Statement statement = getConnection().createStatement();
                final ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setIdWorker(resultSet.getInt("worker_id"));
                worker.setFirstName(resultSet.getString("first_name"));
                worker.setSecondName(resultSet.getString("second_name"));
                worker.setAge(resultSet.getInt("age"));
                worker.setAddress(resultSet.getString("address"));
                lst.add(worker);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    public List<Product> getProductsByPrice(double min, double max) {
        List<Product> lst = new ArrayList<>();
        final String sql = "SELECT product_id, product_name, price, count, type FROM cursor.products WHERE price BETWEEN ? AND ?";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        ) {
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCount(resultSet.getInt("count"));
                product.setType(resultSet.getString("type"));
                lst.add(product);

            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    public List<Product> getProdByPriceAndType(double price, String type) {
        List<Product> lst = new ArrayList<>();
        final String sql = "SELECT product_id, product_name, price, count, type FROM cursor.products WHERE price<? AND type=?";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        ) {
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, type);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCount(resultSet.getInt("count"));
                product.setType(resultSet.getString("type"));
                lst.add(product);


            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lst;
    }

    public List<Shop> getShopByStreet(String name) {
        List<Shop> lst = new ArrayList<>();
        final String sql = "SELECT shop_id, shop_name, city, street FROM cursor.shops WHERE street LIKE ? ";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        ) {
            preparedStatement.setString(1, '%' + name + '%');
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Shop shop = new Shop();

                shop.setIdShop(resultSet.getInt("shop_id"));
                shop.setName(resultSet.getString("shop_name"));
                shop.setCity(resultSet.getString("city"));
                shop.setStreet(resultSet.getString("street"));
                lst.add(shop);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lst;
    }

    public Worker getWorkerById(int id) {
        Worker worker = null;
        final String sql = "SELECT worker_id, first_name, second_name, age, address FROM cursor.staff WHERE worker_id = ?";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            worker = new Worker();
            worker.setIdWorker(resultSet.getInt("worker_id"));
            worker.setFirstName(resultSet.getString("first_name"));
            worker.setSecondName(resultSet.getString("second_name"));
            worker.setAge(resultSet.getInt("age"));
            worker.setAddress(resultSet.getString("address"));

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return worker;
    }
}

