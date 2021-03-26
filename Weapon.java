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
    
    public String weaponName;
    public int range;
    public int damage;
    public double weight;
    public double cost;
    
    public Weapon(String name, int range, int damage, double weight, double cost){
        this.weaponName = name;
        this.range = range;
        this.damage = damage;
        this.weight = weight;
        this.cost = cost;
    }
    
    
}
