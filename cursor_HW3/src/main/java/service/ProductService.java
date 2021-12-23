package service;

import connection.Util;
import dao.ProductDAO;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends Util implements ProductDAO {

    Connection connection = getConnection();

    @Override
    public void add(Product product) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO cursor.product(idProduct,Name,Price,Count,Type) VALUES (?,?,?,?,?);";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, product.getIdProduct());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getCount());
            preparedStatement.setString(5, product.getType());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Product> getAll() throws SQLException {
        Statement statement = null;
        String sql = "SELECT * FROM cursor.product";
        List<Product> productList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setCount(resultSet.getInt(4));
                product.setType(resultSet.getString(5));
                productList.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return productList;
    }

    @Override
    public Product getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM cursor.product WHERE idProduct = ?;";

        Product product = new Product();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            product.setIdProduct(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getDouble(3));
            product.setCount(resultSet.getInt(4));
            product.setType(resultSet.getString(5));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return product;
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE cursor.product SET  Name=?,Price = ?, Count = ? , Type = ? WHERE idProduct = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getCount());
            preparedStatement.setString(4, product.getType());
            preparedStatement.setInt(5, product.getIdProduct());

            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


    }

    @Override
    public void remove(int id) throws SQLException {
    PreparedStatement preparedStatement = null;
    String sql = "DELETE FROM cursor.product WHERE idProduct=?;";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
