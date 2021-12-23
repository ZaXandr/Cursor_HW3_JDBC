package service;

import connection.Util;
import dao.ShopDAO;
import entity.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopService extends Util implements ShopDAO {
    Connection connection = getConnection();

    @Override
    public void add(Shop shop) {
        PreparedStatement preparedStatement=null;
        String sql = "INSERT INTO cursor.shop(idShop,Name,City,Street) VALUES (?,?,?,?);";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,shop.getIdShop());
            preparedStatement.setString(2,shop.getName());
            preparedStatement.setString(3,shop.getCity());
            preparedStatement.setString(4,shop.getStreet());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Shop> getAll() {
        Statement statement;
        String sql="SELECT * FROM cursor.shop;";
        List<Shop> shopList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);

            while (resultSet.next()) {
                Shop shop = new Shop();

                shop.setIdShop(resultSet.getInt(1));
                shop.setName(resultSet.getString(2));
                shop.setCity(resultSet.getString(3));
                shop.setStreet(resultSet.getString(4));
                shopList.add(shop);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return shopList;
    }

    @Override
    public Shop getById(int id) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM cursor.shop WHERE idShop = ?;";
        Shop shop = new Shop();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            shop.setIdShop(resultSet.getInt(1));
            shop.setName(resultSet.getString(2));
            shop.setCity(resultSet.getString(3));
            shop.setStreet(resultSet.getString(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shop;
    }

    @Override
    public void update(Shop shop) {
        PreparedStatement preparedStatement;
        String sql = "UPDATE cursor.shop SET NAME=?,City=?,Street=? WHERE idShop=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,shop.getName());
            preparedStatement.setString(2,shop.getCity());
            preparedStatement.setString(3,shop.getStreet());
            preparedStatement.setInt(4,shop.getIdShop());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void remove(int id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM cursor.shop WHERE idShop=2;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

}
