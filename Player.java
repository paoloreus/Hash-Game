
package assignment2structure;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
 */
public class Player {
    
    private String name;
    private Backpack backpack;
    private int numItems;
    private double money;
    
    public Player(String n, double m){
        name = n;
        money = m;
        numItems = 0;
        backpack = new Backpack(30, 90);
    }
    
    public double getMoney(){
        return money;
    }
    
    //This is a helper method that checks if player has enough money
    //If so then an attempt to store the weapon is made
    public boolean buy(Weapon w){
        if(money >= w.getCost()){
        if(backpack.addItem(w)){
        System.out.println(w.getWeaponName()+" bought...");
        System.out.println(backpack.getNumItems());
        return true;
        }       
    }
        return false;
    }
    
    //Helpers below
    
    public void withdraw(double amt){
        money = money - amt;
    }
    
    public boolean inventoryCheck(){
        if(backpack.checkNumItems()){
            return true;
        }
        
        System.out.println("Bag is full");
        return false;
    }
    
    public boolean weightCheck(Weapon weapon){
        if(backpack.checkWeight(weapon)){
            return true;
        }
        System.out.println("Weight is not enough to carry this weapon");
        return false;
    }
    
    public void printCharacter(){
        System.out.println("Name: " + name + "\nMoney: " + money);
        printBackpack();
    }
    
    public void printBackpack(){
        System.out.println(name + ", you own " + backpack.getNumItems() + " Weapons:");
        backpack.backpackItems(); //printing each weapon's name from the backpack object
        System.out.println();
    }
}
