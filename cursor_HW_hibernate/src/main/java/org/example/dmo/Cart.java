package org.example.dmo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name="owner_id",referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name="purchases",
            joinColumns = @JoinColumn(name="cart_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="product_id",referencedColumnName = "product_id")
    )
    private List<Product> products;
}
