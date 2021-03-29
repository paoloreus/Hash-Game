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
    public Backpack backpack;
    public int numItems;
    public double money;
    
    public Player(String n, double m){
        name = n;
        money = m;
        numItems = 0;
        backpack = new Backpack(30, 90);
    }
    
    public boolean buy(Weapon w){
        if(money >= w.cost){
        if(backpack.addItem(w)){
          System.out.println(w.weaponName+" bought...");
        //numItems++;
        System.out.println(backpack.getNumItems());
        return true;
        }       
    }
        return false;
    }
    
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
