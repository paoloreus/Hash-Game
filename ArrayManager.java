package assignment2structure;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
 */

//Hash Function needs more than just name
public class ArrayManager {
    
    private int maxItems;
    private int numItems;
    private int numTypes;
    private ShopItem[] table;
    private static final String del = "DELETED";
    private double loadFactor;
    
    public ArrayManager(int size){
        maxItems = size;
        table = new ShopItem[maxItems];
        numItems = 0;
        numTypes = 0;
        loadFactor = 0.75;
    }
    
    //I am only using name to hash because we will be searching by name in
    //other methods and when user searches we wouldn't ask the user to search
    //for anything other than the name (since we won't have duplicate names)
    public int hashFunction(String name){
        int value = 0;
        int weight = 1;
        
        for(int i = 0; i < name.length(); i++){
            value += (name.toLowerCase().charAt(i) - 'a' + 1) * weight;
            weight++;
        }
        
        return value % maxItems;
        
    }
    
    //IMPORTANT: Object has already been created in the main class
    //Based on user inputs, an object would be created, this method's intent
    //is NOT to create a new object but to place the object in the array
    //This technique is compatible with how we have structured this project
    //and in the same time, I'm using less parameters for better readability
    public void put(Weapon item, int quantity){
           
        if(numItems / maxItems < loadFactor && checkNumTypes()){
            int startLoc = hashFunction(item.getWeaponName());
            int loc = startLoc;
            int counter = 1;
            boolean isSameType = false;
            
            while(table[loc] != null && table[loc].item.getWeaponName().compareTo(del) != 0){
                loc = startLoc + counter * counter;
                loc = loc % maxItems;
                counter++;
                isSameType = true;
            }
            if(!isSameType){
                numTypes++;
            }
            table[loc] = new ShopItem(item, quantity);
            numItems += quantity;
        }
    }
    
    //Helper
    public boolean checkNumTypes(){
        if(numTypes / 80 > loadFactor){
            return false;
        }
        
        return true;
    }
    
    //This is an alternative to the main put method, in case user is attempting
    //To store an already existing item
    public void putAdditional(String name, int quantity){
        ShopItem itemToAdd = get(name);
        if(itemToAdd != null){
            itemToAdd.numberInStock += quantity;
        }
    }
    
    //Search by name
    public ShopItem get(String key){
        int startLoc = hashFunction(key);
        int loc = startLoc;
        int counter =1;
        while(table[loc] != null && key.compareTo(table[loc].item.getWeaponName()) != 0){
            loc = startLoc + counter * counter;
            loc = loc % maxItems;
            counter++;
        }
        
        if(table[loc] != null && table[loc].numberInStock > 0){
            return table[loc];
        }
        return null;
    }
    
   
    //Searches by name then deletes the amount, if the whole amount is deleted
    //The object itself will be marked as deleted otherwise we'd just deduct
    //from quantity    
    public boolean deleteAmount(String name, int amount){
        int startLoc = hashFunction(name);
        int loc = startLoc;
        int counter = 1;
        
         while(table[loc] != null && table[loc].item.getWeaponName().compareTo(name) != 0){
            loc = startLoc + counter * counter;
            loc = loc % maxItems;
            counter++;
        }
         
        if(table[loc] != null){ 
        if(amount == table[loc].numberInStock){         
            if(!checkSimilarTypes(table[loc].item.getWeaponName())){
                numTypes--;
            }
            table[loc].item.setWeaponName(del);
            numItems -= amount;
            return true;
        
        }
        else if(amount < table[loc].numberInStock && amount >= 0){
            table[loc].numberInStock -= amount;
            numItems -= amount;
            return true;
        }
        }
        return false;
    }
    
    //Helper Methods below
    public String checkQuantity(String name){
        ShopItem item = get(name);
        if(item != null){
            return "There are " + item.numberInStock + " items of that weapon in stock";
        }
        
        return "Item is not in stock";
    }
    
    public boolean checkSimilarTypes(String name){
        int loc = hashFunction(name);
        
        if(table[loc] != null && table[loc].item.getWeaponName().compareTo(del) != 0){
            return true;
        }
        
        return false;
    }
    
    public void printTable(){
        for(int i = 0; i < maxItems; i++){
            if(table[i] != null && table[i].item.getWeaponName().compareTo(del) != 0 && table[i].numberInStock > 0){
            System.out.println("Name: " +table[i].item.getWeaponName() + "  Range: " + table[i].item.getRange() +
                    "   Damage: " + table[i].item.getDamage() + "   Cost: " +
                    table[i].item.getCost() + "  Quantity in stock: " + table[i].numberInStock);
            }
        }
    }
}
