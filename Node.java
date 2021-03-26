package assignment2structure;

/**
 *
 * @author Paolo
 */
public class Node {
    
    public Weapon data;
    public Node next;
    
    public Node(Weapon data){
        this.data = data;
        next = null;
    }
}
