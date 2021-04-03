/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2structure;

/**
 *
 * @author Paolo
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
