package org.example.dmo;

import javax.persistence.*;


@Entity
public class CreditCard {
    @Id
    private long number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="owner", referencedColumnName = "id",nullable = false)
    private User owner;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public CreditCard(long number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    public CreditCard() {
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number=" + number +
                //", owner=" + owner +
                '}';
    }
}
