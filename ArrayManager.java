package assignment2structure;

/**
 *
 * @author Paolo
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
    
    public int hashFunction(Weapon weapon){
        int value = 0;
        int weight = 1;
        
        for(int i = 0; i < weapon.weaponName.length(); i++){
            value += (weapon.weaponName.toLowerCase().charAt(i) - 'a' + 1) * weight;
            weight++;
        }
        
        return value % maxItems;
        
    }
    public void put(Weapon item, int quantity){
            
        if(numItems / maxItems < loadFactor && numTypes <= 80){
            int startLoc = hashFunction(item);
            int loc = startLoc;
            int counter = 1;
            while(table[loc] != null && table[loc].item.weaponName.compareTo(del) != 0){
                loc = startLoc + counter * counter;
                loc = loc % maxItems;
                counter++;
                numTypes++;
            }
            table[loc] = new ShopItem(item, quantity);
            numItems++;
        }
    }
    
    public ShopItem get(String key){
        int loc = 0;
        
        while(loc < numItems && key.compareTo(table[loc].item.weaponName) != 0){
            loc++;
        }
        
        if(loc < numItems){
            return table[loc];
        }
        return null;
    }
    
    public void printTable(){
        int count = 0;
        for(int i = 0; i < numItems; i++){
            System.out.println("Name: " +table[i].item.weaponName + "   Damage: " + table[i].item.damage + "   Cost: " +
                    table[i].item.cost + "  Quantity in stock: " + table[i].numberInStock);
        }
    }
}
