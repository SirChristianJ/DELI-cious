package com.pluralsight.deli;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private boolean wantsDrink;
    private String drinkSize;
    private boolean wantsChips;

    public Order(List<Sandwich> sandwiches, boolean wantsDrink,String drinkSize, boolean wantsChips) {
        this.sandwiches = sandwiches;
        this.wantsDrink = wantsDrink;
        this.drinkSize = drinkSize;
        this.wantsChips = wantsChips;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public boolean isWantsDrink() {
        return wantsDrink;
    }

    public void setWantsDrink(boolean wantsDrink) {
        this.wantsDrink = wantsDrink;
    }

    public String getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }

    public boolean isWantsChips() {
        return wantsChips;
    }

    public void setWantsChips(boolean wantsChips) {
        this.wantsChips = wantsChips;
    }

    public double calculateLineItemPrice(Sandwich sandwich){
        double totalLineItemPrice = 0;
        if (isWantsChips()) {
            totalLineItemPrice += 1.50;
        }
        if(isWantsDrink()) {
            try {
                switch (getDrinkSize()) {
                    case "small" -> totalLineItemPrice += 2.00;
                    case "medium" -> totalLineItemPrice += 2.50;
                    case "large" -> totalLineItemPrice += 3.00;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        totalLineItemPrice += sandwich.calculatePrice();
        return totalLineItemPrice;
    }

    public double calculateTotalOrderPrice(){
        return getSandwiches().stream().map(this::calculateLineItemPrice)
                                .reduce(0.00, Double::sum);
    }

    public String encodedString(){
        StringBuilder sb = new StringBuilder();
        for(Sandwich sandwich: getSandwiches()){
            sb.append(sandwich.encodedString());
            sb.append(String.format("|$%.2f", calculateLineItemPrice(sandwich)));
        }

        sb.append(String.format("\n|Total|$%.2f\n", calculateTotalOrderPrice()));
        return sb.toString();
    }

    public String displayToConsole(){
        StringBuilder sb = new StringBuilder();
        for(Sandwich sandwich: getSandwiches()){
            sb.append(sandwich).append(String.format("\nMeal Price (drinks & chips included): $%.2f\n", calculateLineItemPrice(sandwich)));
        }

        sb.append(String.format("\n|Total Order Price|: $%.2f", calculateTotalOrderPrice()));
        return sb.toString();
    }


}
