package com.pluralsight.deli;
import com.pluralsight.Account.Account;
import com.pluralsight.Account.AccountFileManager;
import com.pluralsight.Utility.ColorCodes;
import com.pluralsight.Utility.Console;
import com.pluralsight.customer.Customer;
import com.pluralsight.customer.CustomerFileManger;

import java.util.*;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Methods responsible for prompting if the user has an account, if not then they'll create one
     * else if they do, the user will enter the required account information for verification
     * **/
    /**
     * Prompt if the user has an account*/
    public void promptUserForAccount(){
        do{
            String welcomePrompt =
                    """
                    ______________________________________
                   ||                                    ||
                   ||  Welcome to DELI-cious Sandwiches! ||
                   ||____________________________________||
                            
                    """;
            System.out.println(welcomePrompt);
            boolean isCreatingAccount = Console.PromptForYesNo("Do you have an account?");
            if (!isCreatingAccount){
                userSignupScreen();
            }
            else{
                verifyUserScreen();
            }

            return;

        }while(true);
    }
    /**
     * Create an account */
    public void userSignupScreen(){
        try{
            String signupMenu =
                    """
                     ______________________________________
                    ||                                    ||
                    ||        Lets create an account!     ||
                    ||____________________________________||
                    
                    """;
            System.out.println(signupMenu + "\n");
            String userFirstName = Console.PromptForString("Enter first name: ");
            String userLastName = Console.PromptForString("Enter last name: ");
            String userEmail = Console.PromptForString("Enter your email: ");
            String userPhoneNumber = Console.PromptForString("Enter phone number: ");

            Customer customer = new Customer(userFirstName, userLastName, userEmail, userPhoneNumber);
            Account account = new Account(customer);
            AccountFileManager.addAccount(account);
            AccountFileManager.writeToCSV();
            startMenu(account);
        }
        catch (InputMismatchException e){
            System.out.println(e.getMessage() + "<--- Error creating account");
        }

    }
    /**
     * Verify account information */
    public void verifyUserScreen(){
        String userFirstName = Console.PromptForString("Enter first name: ");
        String userLastName = Console.PromptForString("Enter last name: ");
        String userEmail = Console.PromptForString("Enter your email: ");
        String userPhoneNumber = Console.PromptForString("Enter phone number: ");

        Customer verifyCustomer = new Customer(userFirstName,userLastName,userEmail,userPhoneNumber);
        Account verifyAccount = new Account(verifyCustomer);

        String verifyMenu =
                """
                ______________________________________
               ||                                    ||
               ||  Account Successfully Verified!    ||
               ||____________________________________||
                        
                """;

        for(Account a: AccountFileManager.readFromCSV()){
            if(verifyAccount.getCustomer().encodedString().equalsIgnoreCase(a.getCustomer().encodedString())){
                System.out.println(verifyMenu);
                startMenu(a);
            }
        }
    }

    /**
     * Once account information is provided, the ordering process begins with this first level menu
     * */
    public void startMenu(Account a){
        do{
            try {
                String startMenu = """
                         ______________________________________
                        ||                                    ||
                        ||  Welcome to DELI-cious Sandwiches! ||
                        ||____________________________________||
                        
                            1) New Order
                            0) Exit Application
                        
                        
                        """;
                System.out.println(ColorCodes.YELLOW);
                System.out.println(startMenu);
                int menuChoice = Console.PromptForInt("Select an option: ");
                if (menuChoice == 1) {
                    processNewOrderRequest(a);
                } else {
                    System.out.println("Exiting application...");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "<-- Enter a numeric value.");
            }
        }while (true);
    }
    /**
     *  Sandwich, drinks and chips menu display
     * */
    public void sandwichOptionsMenu(){
        String deliMenu = """
                 ______________________________________
                ||                                    ||
                ||  Welcome to DELI-cious Sandwiches! ||
                ||____________________________________||
                                                        
                
                                            4in        8in      12in
                Bread                       $5.50      $7.00    $8.50
                - white
                - wheat
                - rye
                - wrap
                
                Cheese                      $.75       $1.50    $2.25                             
                - american
                - provolone
                - cheddar
                - swiss         
                                     
                Meats                       $1.00      $2.00    $3.00
                - steak                 
                - ham
                - salami
                - roast beef
                - chicken
                - bacon
                
                    
                
                """;
        System.out.println(ColorCodes.BLUE);
        System.out.println(deliMenu);
    }
    public void drinksAndChipsOptionsMenu(){
        String drinksAndChipsMenu = """
                 ______________________________________
                ||                                    ||
                ||  Welcome to DELI-cious Sandwiches! ||
                ||____________________________________||
                                                        
                
                
                Drinks            Small     Medium     Large
                                  $2.00     $2.50      $3.00
                                  
                Chips (Any)       $1.50
                    
                
                """;
        System.out.println(ColorCodes.PURPLE);
        System.out.println(drinksAndChipsMenu);
    }
    /**
     *   Order processed
     * */
    public void processNewOrderRequest(Account a){
        List<Sandwich> sandwiches = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        do{
            try{
                /**
                 * Creation of a sandwich
                 * */
                sandwichOptionsMenu();
                int sandwichLength = 0;
                try{
                    sandwichLength = Console.PromptForInt("What length sandwich would you like?");
                }
                catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                String sandwichBread = Console.PromptForString("Which type of bread would you like?");

                boolean wantsToAddMeat = Console.PromptForYesNo("Would you like to add meat?");
                String sandwichMeatType = "";
                if (wantsToAddMeat) {
                    sandwichMeatType = Console.PromptForString("Enter the type of meat: ");
                }
                boolean wantsToAddCheese = Console.PromptForYesNo("Would you like to add cheese?");
                String sandwichCheeseType = "";
                if (wantsToAddCheese) {
                    sandwichCheeseType = Console.PromptForString("Enter the type of cheese: ");
                }
                boolean wantsToAddSauce = Console.PromptForYesNo("Would you like to add sauce?");
                String sandwichSauce = "";
                if (wantsToAddSauce) {
                    sandwichSauce = Console.PromptForString("What sauce would you like to add?");
                }
                boolean wantsAdditionalToppings = Console.PromptForYesNo("Would you like to add additional toppings?");
                if (wantsAdditionalToppings) {
                    String additionalTopping = Console.PromptForString("What type of additional topping would you like?");
                    sandwiches.add(new Sandwich(sandwichLength, sandwichBread, sandwichMeatType, sandwichCheeseType, sandwichSauce, wantsAdditionalToppings, additionalTopping));
                } else {
                    sandwiches.add(new Sandwich(sandwichLength, sandwichBread, sandwichMeatType, sandwichCheeseType, sandwichSauce, wantsAdditionalToppings));
                }

                /**
                 *  Creation of an order
                 * **/
                drinksAndChipsOptionsMenu();
                boolean wantsDrinks = Console.PromptForYesNo("Would you like to add a drink to the order? ");
                String drinkSize = "";
                if (wantsDrinks) {
                    drinkSize = Console.PromptForString("Enter size for drink: ");
                }
                boolean wantsChips = Console.PromptForYesNo("Would you like to add chips to your order? ");

                Order order = new Order(sandwiches, wantsDrinks, drinkSize, wantsChips);

                /**
                 *  Add order to the current logged in Customer
                 * */
                orders.add(order);
                a.getCustomer().setOrders(orders);
                CustomerFileManger.writeToCSV(a);

                /**
                 * Option to add another sandwich to order
                 * **/
                boolean isAdditionalSandwich = Console.PromptForYesNo("Would you like to add another sandwich? ");
                if (!isAdditionalSandwich) {
                    System.out.println(order.displayToConsole() + "\n");

                    return;
                }
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "<-- Enter numeric value!.");
            }

        }while(true);
    }

}
