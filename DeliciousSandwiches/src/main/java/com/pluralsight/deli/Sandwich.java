package com.pluralsight.deli;

import java.util.List;

public class Sandwich {
    private int length;
    private String breadType;
    private String meatType;
    private String cheeseType;
    private String sauceType;
    private boolean hasExtraToppings;
    private String extraToppingType;

    public Sandwich(int length, String breadType, String meatType, String cheeseType, String sauceType, boolean hasExtraToppings) {
        this.length = length;
        this.breadType = breadType;
        this.meatType = meatType;
        this.cheeseType = cheeseType;
        this.sauceType = sauceType;
        this.hasExtraToppings = hasExtraToppings;
    }

    public Sandwich(int length, String breadType, String meatType, String cheeseType, String sauceType, boolean hasExtraToppings, String extraToppingType) {
        this.length = length;
        this.breadType = breadType;
        this.meatType = meatType;
        this.cheeseType = cheeseType;
        this.sauceType = sauceType;
        this.hasExtraToppings = hasExtraToppings;
        this.extraToppingType = extraToppingType;
    }

    public int getLength() {
        return length;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getMeatType() {
        return meatType;
    }

    public String getCheeseType() {
        return cheeseType;
    }

    public String getSauceType() {
        return sauceType;
    }

    public boolean isHasExtraToppings() {
        return hasExtraToppings;
    }

    public void setHasExtraToppings(boolean hasExtraToppings) {
        this.hasExtraToppings = hasExtraToppings;
    }

    public String getExtraToppingType() {
        return extraToppingType;
    }

    public double calculatePrice() throws RuntimeException{
        double priceBasedOnBread = 0.00;
        double priceBasedOnMeat = 0.00;
        double  priceBasedOnCheese = 0.00;

        double addedCharge = 0;

        switch (getLength()){
                    case 4 -> {
                        priceBasedOnBread = 5.50;
                        if(!getMeatType().isEmpty())
                            priceBasedOnMeat = 1.00;
                        if(!getCheeseType().isEmpty())
                            priceBasedOnCheese = .75;

                    }
                    case 8 -> {
                        priceBasedOnBread = 7.00;
                        if (!getMeatType().isEmpty())
                            priceBasedOnMeat = 2.00;
                        if (!getCheeseType().isEmpty())
                            priceBasedOnCheese = 1.50;
                    }
                    case 12 -> {
                        priceBasedOnBread = 8.50;
                        if (!getMeatType().isEmpty())
                            priceBasedOnMeat = 3.00;
                        if (!getCheeseType().isEmpty())
                            priceBasedOnCheese = 2.25;
                    }
                    default -> throw new RuntimeException("This size is not an option.");
                };
        if (isHasExtraToppings()){
            if ((getExtraToppingType().contains("Meat") || getExtraToppingType().contains("meat")) && !getMeatType().isEmpty()){
                addedCharge = priceBasedOnMeat/2;
                priceBasedOnMeat += addedCharge;
            }
            else if ((getExtraToppingType().contains("Cheese") || getExtraToppingType().contains("cheese")) && !getCheeseType().isEmpty()){
                addedCharge = priceBasedOnCheese/2.5;
                priceBasedOnCheese += addedCharge;
            }
        }

        return priceBasedOnBread + priceBasedOnMeat + priceBasedOnCheese;

    }

    public String encodedString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getLength()+"|").append(getBreadType()+"|").append((!getMeatType().isEmpty()?getMeatType():"null")+"|").append((!getCheeseType().isEmpty()?getCheeseType():"null")+"|").append((!getSauceType().isEmpty()?getSauceType():"null")+"|").append((isHasExtraToppings()?getExtraToppingType():"null")+"|").append(String.format("$%.2f",calculatePrice()));

        return sb.toString();
    }

    @Override
    public String toString(){
        return String.format("\n|Order| \nSandwich length:%d inches \nBread Type:%s \nMeat:%s \nCheese:%s \nSauce:%s \nSandwich Price: $%.2f",
                getLength(), getBreadType(), getMeatType(), getCheeseType(), getSauceType(), calculatePrice());
    }
}
