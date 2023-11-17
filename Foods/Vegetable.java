package Foods;

import Farm.Soil;

public class Vegetable extends Food {
    public Vegetable(String name, double sellPrice, int daysToMature, int requiredArea){
        super(name, sellPrice, daysToMature, requiredArea, Soil.Loam);
        // this.preferredSoil = Soil.Slit;
    }

    @Override
    public double grow(Soil soil){
        if (soil != getPreferredSoil()){;
            setDaysSincePotted(getDaysSincePotted() + 1);
            return super.grow();
        } else {
            return super.grow();
        }
    }
    
}