package assignment2structure;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
 */
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
    
    public int hashFunction(String name){
        int value = 0;
        int weight = 1;
        
        for(int i = 0; i < name.length(); i++){
            value += (name.toLowerCase().charAt(i) - 'a' + 1) * weight;
            weight++;
        }
        
        return value % maxItems;
        
    }
    public void put(Weapon item, int quantity){
           
        if(numItems / maxItems < loadFactor && checkTypes()){
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
            numItems++;
        }
    }
    
    public boolean checkTypes(){
        if(numTypes > 80){
            return false;
        }
        
        return true;
    }
    
    
    public void putAdditional(Weapon item, int quantity){
        ShopItem itemToAdd = get(item.getWeaponName());
        if(itemToAdd != null){
            itemToAdd.numberInStock += quantity;
        }
    }
    
    
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
    
   
    
    public boolean delete(String name){
        int startLoc = hashFunction(name);
        int loc = startLoc;
        int counter = 1;
        
        while(table[loc] != null && table[loc].item.getWeaponName().compareTo(name) != 0){
            loc = startLoc + counter * counter;
            loc = loc % maxItems;
            counter++;
        }
        
        if(table[loc] != null){
            table[loc].item.setWeaponName(del);
            numItems--;
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
