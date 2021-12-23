package dao;



import entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void add(Product product) throws SQLException;

    List<Product> getAll() throws SQLException;

    Product getById(int id) throws SQLException;

    void update(Product product) throws SQLException;

    void remove(int id) throws SQLException;
}
