package stores;

import products.FoodProduct;

import java.util.HashSet;
import java.util.Set;

public class FoodStore extends Store {
    Set<FoodProduct> productsInStock;

    FoodStore(String name, CashDesk desk) {
        super(name, desk);
        this.productsInStock = new HashSet<>();
    }
}
