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
public class Player {
    
    public String name;
    public Weapon[] backpack;
    public int numItems;
    public double money;
    
    public Player(String n, double m){
        name = n;
        money = m;
        numItems = 0;
        backpack = new Weapon[10];
    }
    
    public void buy(Weapon w){
        System.out.println(w.weaponName+" bought...");
        backpack[numItems] = w;
        numItems++;
        System.out.println(numItems);
    }
    
    public void withdraw(double amt){
        money = money - amt;
    }
    
    public boolean inventoryFull(){
        return (numItems == 10);
    }
    
    public void printCharacter(){
        System.out.println("Name: " + name + "\nMoney: " + money);
        printBackpack();
    }
    
    public void printBackpack(){
        System.out.println(name + ", you own " + numItems + " Weapons:");
        for(int i = 0; i < numItems; i++){
            System.out.println(backpack[i].weaponName);
        }
        System.out.println();
    }
}
