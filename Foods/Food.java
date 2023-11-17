package Foods;

import Farm.Soil;


public abstract class Food implements Comparable<Food>{
    private String name;
    private double sellPrice;
    private int daysToMature;
    private int daysSincePotted;
    private int requiredArea;
    private Soil preferredSoil;

    public Food(String name, double sellPrice, int daysToMature, int requiredArea, Soil preferredSoil){
        this.name = name;
        this.sellPrice = sellPrice;
        this.daysToMature = daysToMature;
        this.setDaysSincePotted(0);
        this.requiredArea = requiredArea;
        this.preferredSoil = preferredSoil;
    }

    

    public void setDaysSincePotted(int daysSincePotted) {
        this.daysSincePotted = daysSincePotted;
    }

    public void setPreferredSoil(Soil preferredSoil){
        this.preferredSoil = preferredSoil;
    }


    public String getName(){
        return name;
    }

    public double getSellPrice(){
        return sellPrice;
    }

    public Soil getPreferredSoil(){
        return preferredSoil;
    }

    public int getDaysToMature(){
        return daysToMature;    
    }

    public int getDaysSincePotted(){
        return daysSincePotted;
    }

    public int getRequiredArea(){
        return requiredArea;
    }

    public abstract double grow(Soil soil);

    public double grow(){
        setDaysSincePotted(getDaysSincePotted() + 1);
        if (getDaysSincePotted() > daysToMature){
            return 1.0;
        } else {
            return (double) getDaysSincePotted() / daysToMature;
        }
    }

    @Override
    public String toString(){
        return name + "(" + preferredSoil + " )-" + getDaysSincePotted() + "/" + daysToMature + "days";
    }

    @Override
    public int compareTo(Food otherFood){
        return Double.compare(otherFood.sellPrice, sellPrice);
    }

}