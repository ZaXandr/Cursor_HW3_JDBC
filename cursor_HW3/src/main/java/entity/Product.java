package entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    int idProduct;
    String name;
    double price;
    int count;
    String type;

    public Product(String name, double price, int count, String type) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.type = type;
    }
}
