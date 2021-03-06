package assignment2structure;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
 */
public class Node {
    
    //Typical Node class that would help us implement separate chaining
    
    public Weapon data;
    public Node next;
    
    public Node(Weapon data){
        this.data = new Weapon(data);
        next = null;
    }
}
