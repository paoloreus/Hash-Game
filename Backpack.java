package assignment2structure;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
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
    
    //Using an object here makes sense because we're hashing an already existing object
    //The object already exists in the shop so in main, we search for a weapon by name
    //And use the result returned to add the object to our backpack
    private int hashFunction(Weapon weapon){
        int value = 0;
        int weight = 1;
        String name = weapon.getWeaponName();
        int avg = (weapon.getRange() + weapon.getDamage()) / 2;
        
        for(int i = 0; i < name.length(); i++){
            value += (name.toLowerCase().charAt(i) - 'a'+1) * weight;
            weight++;
        }
        
        value += avg;
        return value % maxItems;
    }
    
    //Here I'm getting the object that User has successfully searched for
    //We will make a brand new object that is basically a copy of the original
    //Without referencing that specific object
    //Once again, adding the object here makes sense
    public boolean addItem(Weapon weapon){
        int loc = hashFunction(weapon);
        Node newNode = new Node(weapon);
        
        if(checkNumItems() && checkWeight(weapon)){
            
        if(items[loc] == null){
            items[loc] = newNode;
        }
        else{
            newNode.next = items[loc];
            items[loc] = newNode;
        }
        currWeight += newNode.data.getWeight();
        numItems++;
        return true;
    }
        return false;
    }
    
    //Helper Methods
    public boolean checkWeight(Weapon weapon){
        if(maxWeight - (currWeight + weapon.getWeight()) >= 0){
            return true;
        }
        return false;
    }
    
    public boolean checkNumItems(){
        if(numItems < maxItems){
            return true;
        }
        
        return false;
    }
    
    public int getNumItems(){
        return numItems;
    }
    
    public void backpackItems(){
       Node curr;
       
       for(int i = 0; i < maxItems; i++){
           curr = items[i];
           while(curr != null){
               System.out.println(curr.data.getWeaponName());
               curr = curr.next;
           }
       }
    }
}
