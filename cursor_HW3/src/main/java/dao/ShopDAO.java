package dao;

import entity.Shop;
import java.util.List;

public interface ShopDAO {
    void add(Shop shop);

    List<Shop> getAll();

    Shop getById(int id);

    void update(Shop shop);

    void remove(int id);
}
