package UnitTests;

// FREEZE CODE BEGIN

import Farm.Soil;
import Foods.Food;

public class ConcreteFoodTestClass extends Food {


    public ConcreteFoodTestClass(String name, double sellPrice, int daysToMature, int requiredArea) {
        super(name, sellPrice, daysToMature, requiredArea);
    }

    public double grow(Soil farmSoil) {
        return 0;
    }
}

// FREEZE CODE END
