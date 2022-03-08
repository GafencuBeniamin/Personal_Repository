
package isp.lab3.exercise5;

import java.util.Scanner;
/**
 *
 * @author benig
 */
public class VendingMachine {
    
    //instances
    
    private int coins=0;
    private final String[] products={"Bounty", "Snickers", "7Days Cacao", "7Days Vanilla", "Mars", "Twix", 
        "Aqua Carpatica", "Coca Cola", "Pepsi", "Sprite", "Dr. Pepper","Milky Way", "Teasers"};
    private final int[] prices={3, 3, 4, 4, 3, 3, 3, 5, 5, 5, 5, 2, 2};
    
    //methods
    
    private void displayCredit(){
        System.out.println("Credit is: " + this.coins);
    }
    //getter for coins
    public int getCoins(){
        return coins;
    }
    
    public String selectProduct(int id){
        if(id<=0 || id>products.length || this.coins<prices[id-1])
            return "ERROR! Not a valid inserted id or not enough credit.";
        else {
            this.coins-=prices[id-1];
            return "Selected product is: " + products[id-1];
        }
    }
    
    public void displayProducts(){
        for (int i=0;i<products.length;i++){
            int id=i+1;
            System.out.print(products[i] + " ID: " + id + " PRICE: " + prices[i] + "\n");
        }
    }
    
    public void userMenu(){
        System.out.println("Select an option by number: \n 1. Display products \n 2. Display credit \n 3. "
                + "Insert coins \n"
                + " 4. Select product \n 5. Return coins \n 0. Turn off machine");
        Scanner console = new Scanner(System.in);
        int option =console.nextInt();
        while (option != 0){
            switch (option){
                case 1:{
                    displayProducts();
                    break;
                }
                case 2:{
                    displayCredit();
                    break;
                }
                case 3:{
                    System.out.println("How many coins do you insert?");
                    int number=console.nextInt();
                    insertCoin(number);
                    break;
                }
                case 4:{
                    System.out.println("Insert id: ");
                    int number=console.nextInt();
                    System.out.println(selectProduct(number));
                    break;
                }
                case 5:{
                    this.coins=0;
                    break;
                }
                case 0:{
                    System.out.println("Vending Machine will be turned off");
                    break;
                }
                default:{
                    System.out.println("ERROR! Not a valid option.");
                    break;
                }  
            }
            System.out.println("Select an option by number: \n 1. Display products \n 2. Display credit \n 3. Insert coins \n 4. Select product \n 5. Return coins \n 0. Turn off machine");
            option =console.nextInt();
        }
    }
    
    public void insertCoin(int value){
        coins+=value;
    }
}
