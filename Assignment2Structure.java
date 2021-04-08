package assignment2structure;

import java.util.Scanner;

/**
 *
 * @authors:
 * Paolo Tous            - 101325245
 * Yukina Ishiguro       - 101274311
 * Robertha Alvarez Diaz - 101236645
 */
public class Assignment2Structure {

   
    public static int getInteger(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextInt()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextInt();
        }
    
    //Helper method to make sure input is alphabetical
    //I'm using that mainly with weapons
    public static String getAlpha(Scanner sc, String message){
        System.out.println(message);
        String word = sc.next();
        while(!word.matches("[a-zA-Z]+")){
            System.out.println("Input cannot have numbers.");
            System.out.println(message);
            word = sc.next();
        }
        
        return word;
    }
        
        public static double getDouble(Scanner sc,String message){
            System.out.print(message);
            while (!sc.hasNextDouble()) 
            {
                sc.nextLine(); //clear the invalid input ...
                System.out.print(message);
            }
            return sc.nextDouble();
        }
        
        
        //This will validate input first check if item already exists and
        //Apply either adding additional number of items to stock or adding
        //A brand new item to the shop, in that case we'd make a new object
        //And use the methods from the ArrayManager class to store the object
        public static void addWeapons(ArrayManager h,Scanner sc)
        {
            System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
            String weaponName; int weaponRange; int weaponDamage; double weaponWeight; double weaponCost;
            int quantity;
            String prompt = "Please enter the NAME of the Weapon ('end' to quit):";
            weaponName = getAlpha(sc, prompt);
            while (weaponName.compareTo("end") != 0)
            {
                if(h.get(weaponName) != null){
                    quantity = getInteger(sc, "Item already exists. Please enter the quantity you'd like to add in stock: ");
                    h.putAdditional(weaponName, quantity);
                }
                else{
                if(h.checkNumTypes()){
                weaponRange= getInteger(sc,"Please enter the Range of the Weapon (0-10):"); 
                weaponDamage=getInteger(sc,"Please enter the Damage of the Weapon:"); 
                weaponWeight= getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
                weaponCost=getDouble(sc,"Please enter the Cost of the Weapon:");
                Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
                quantity=getInteger(sc,"Please enter the quantity in stock:"); 
                h.put(w,quantity);                
                }
                else{
                    System.out.println("Cannot add any new types of weapons");
                }
                }
                System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
                weaponName = sc.next();
            }
        }
        
        //This will validate input, search for the item by name and if a valid
        //result is returned, we will display the number of quantity that item
        //has in stock and user selects the number of items they'd like to delete
        //Please refer to the delete method in ArrayManager for more info
        public static void deleteWeapons(ArrayManager h, Scanner sc){
            System.out.println("***********WELCOME TO THE WEAPON DELETE MENU*********");
            String msg = "Please enter the NAME of the Weapon ('end' to quit):";
            String msgQuantity = "Please enter the number of items you want to remove from stock:";
            String weaponName = getAlpha(sc, msg);
            int num;
            
            while(weaponName.compareTo("end") != 0){
            System.out.println(h.checkQuantity(weaponName));    
            if(h.get(weaponName) != null){                
            num = getInteger(sc, msgQuantity);
            if(h.deleteAmount(weaponName, num))
            System.out.println(num + " items have been deleted");   
            else{
                System.out.println("Invalid choice");
            }
            }
            
            System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
            weaponName = getAlpha(sc, msg);
            
            }                       
        }


        
        //This will run upon visiting the shop
        public static void showRoomMenu(ArrayManager ht,Player p){
            System.out.println("WELCOME TO THE SHOWROOM!!!!");
            ht.printTable();
            System.out.println("You have "+p.getMoney()+" money.");
        }
        
        //Shop menu, Player may attempt to buy an item, we will validate
        //input and check if user has enough money, if all is clear then
        //we proceed with the purchase and apply changes accordingly
        public static void showRoom(ArrayManager ht, Player p,Scanner sc)
        {
            String choice;
            showRoomMenu(ht,p);
            String msg = "Please select a weapon to buy('end' to quit):";
            choice = getAlpha(sc, msg);
            while (choice.compareTo("end") != 0 && p.inventoryCheck())
            {
                ShopItem si = ht.get(choice);
                if (si != null)
                {
                        if(p.weightCheck(si.item)){
                        boolean transaction = p.buy(si.item);
                        if(transaction){
                        p.withdraw(si.item.getCost());
                        ht.deleteAmount(choice, 1);
                        }
                        else{
                            System.out.println("Transaction Failed");
                        }
                        }
                }
                else
                {
                    System.out.println(" ** "+choice+" not found!! **" );
                }
                showRoomMenu(ht,p);
                choice = getAlpha(sc,msg);
            }
            System.out.println("");
        }
        
        //This will run the menu
        public static int runMenu(Scanner sc){
           System.out.println("***********WELCOME TO WORLD OF WARCRAFT*********");
            System.out.println("1) Add Items to the shop");
            System.out.println("2) Delete Items from the shop");
            System.out.println("3) Buy from the shop");
            System.out.println("4) View backpack");
            System.out.println("5) View Player");
            System.out.println("6) Exit");
            
            
            if (!sc.hasNextInt()) 
            {
                sc.nextLine(); //clear the invalid input ...
                return runMenu(sc); //Recursively call the function until a valid input has been entered
            }
            return sc.nextInt();
                       
        }
        
        //This is to give the User a chance to process the information before moving on
        static void checkToProceed(Scanner sc){
            System.out.println("Press any key to continue");
            sc.nextLine();
            sc.nextLine();
        }
        
        //Menu functions
        public static void menuFunctions(Scanner sc, ArrayManager h, Player p){
            
            
            int choice = runMenu(sc);
            if(choice == 6){
                return;
            }
            
            if(choice == 1){
                addWeapons(h, sc);
                checkToProceed(sc);
            }
            else if(choice == 2){
                deleteWeapons(h, sc);
                checkToProceed(sc);
            }
            
            else if(choice == 3){
                showRoom(h, p, sc);
                checkToProceed(sc);
            }
            
            else if(choice == 4){
                p.printBackpack();
                checkToProceed(sc);
                
            }
            
            else if(choice == 5){
                p.printCharacter();
                checkToProceed(sc);
            }
            
            menuFunctions(sc, h, p);  //Using recursion instead of a while loop
            return;
        }
        
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            String pname;
            System.out.println("Please enter Player name:");
            pname=sc.next();
            Player pl= new Player(pname,45);
            ArrayManager ht= new ArrayManager(101);
            menuFunctions(sc, ht, pl);

        }
    

    
}
