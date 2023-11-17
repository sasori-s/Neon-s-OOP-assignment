package Foods;

import Farm.Soil;

public class Fruit extends Food{
    // private static  Soil preferredSoil = Soil.Silt;

    public Fruit(String name, double sellPrice, int daysToMature, int requiredArea){
        super(name, sellPrice, daysToMature, requiredArea, Soil.Silt);
    }

    @Override
    public double grow(Soil soil){
        if (soil != getPreferredSoil()){
            setDaysSincePotted(getDaysSincePotted() + 2);
            return super.grow();
        } else {
            return super.grow();
        }
        
    }
}
