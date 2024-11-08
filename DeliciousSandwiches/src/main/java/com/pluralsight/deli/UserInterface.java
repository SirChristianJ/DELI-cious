package com.pluralsight.deli;
import com.pluralsight.Utility.ColorCodes;
import com.pluralsight.Utility.Console;

import java.util.*;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);

    public void startMenu(){
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
        if(menuChoice == 1) {
            processNewOrderRequest();
        }
        else
            System.out.println("Exiting application...");
    }
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

    public void processNewOrderRequest(){
            List<Sandwich> sandwiches = new ArrayList<>();
            do{
            /**
             * Creation of a sandwich
             * */
                sandwichOptionsMenu();
                int sandwichLength = Console.PromptForInt("What length sandwich would you like?");
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
                if(wantsToAddSauce) {
                    sandwichSauce = Console.PromptForString("What sauce would you like to add?");
                }
                boolean wantsAdditionalToppings = Console.PromptForYesNo("Would you like to add additional toppings?");
                if(wantsAdditionalToppings) {
                    String additionalTopping = Console.PromptForString("What type of additional topping would you like?");
                    sandwiches.add(new Sandwich(sandwichLength, sandwichBread, sandwichMeatType, sandwichCheeseType, sandwichSauce, wantsAdditionalToppings, additionalTopping));
                }
                else {
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

                Order order = new Order(sandwiches,wantsDrinks,drinkSize,wantsChips);

            /**
             * Option to add another sandwich to order
             * **/
                boolean isAdditionalSandwich = Console.PromptForYesNo("Would you like to add another sandwich? ");
                if (!isAdditionalSandwich) {
                System.out.println(order.displayToConsole() + "\n");
                return;
            }


        }while(true);
    }

}
