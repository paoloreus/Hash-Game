
package assignment2structure;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
 */
public class ShopItem {
    
    Weapon item;
    int numberInStock;
    
    public ShopItem(Weapon item, int numberInStock){
        this.item = item;
        this.numberInStock = numberInStock;
    }
}
