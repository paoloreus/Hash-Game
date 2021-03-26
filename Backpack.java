package assignment2structure;

/**
 *
 * @author Paolo
 */
public class Backpack {
    
    private int numItems;
    private int maxItems;
    private double currWeight;
    private double maxWeight;
    private Node[] items;
    private static final String del = "DELETED";
    
    public Backpack(int maxItems, double maxWeight){
        this.maxItems = maxItems;
        this.maxWeight = maxWeight;
        numItems = 0;
        items = new Node[maxItems];
        currWeight = 0.0;
    }
    
    private int hashFunction(Weapon weapon){
        int value = 0;
        int weight = 1;
        String name = weapon.weaponName;
        int avg = (weapon.range + weapon.damage) / 2;
        
        for(int i = 0; i < name.length(); i++){
            value += (name.toLowerCase().charAt(i) - 'a'+1) * weight;
            weight++;
        }
        
        value += avg;
        return value % maxItems;
    }
    
    public boolean addItem(Weapon weapon){
        int loc = hashFunction(weapon);
        Node newNode = new Node(weapon);
        
        if(numItems < maxItems && maxWeight - (currWeight + newNode.data.weight) >= 0){
            
        if(items[loc] == null){
            items[loc] = newNode;
        }
        else{
            newNode.next = items[loc];
            items[loc] = newNode;
        }
        currWeight += newNode.data.weight;
        numItems++;
        return true;
    }
        return false;
    }
}
