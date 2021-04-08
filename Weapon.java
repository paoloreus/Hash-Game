package assignment2structure;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
 */
public class Weapon {
    
    private String weaponName;
    private int range;
    private int damage;
    private double weight;
    private double cost;
    
    public Weapon(String name, int range, int damage, double weight, double cost){
        this.weaponName = name;
        this.range = range;
        this.damage = damage;
        this.weight = weight;
        this.cost = cost;
    }
    
    //This constructor will allow us to create a copy of a specific object
    //Without referencing that object
    public Weapon(Weapon original){
        weaponName = original.weaponName;
        range = original.range;
        damage = original.damage;
        weight = original.weight;
        cost = original.cost;
    }
    
    //Getters and Setters below
    
    public String getWeaponName(){
        return weaponName;
    }
    
    public void setWeaponName(String name){
        this.weaponName = name;
    }
    
    public int getRange(){
        return range;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public double getCost(){
        return cost;
    }
    
}
