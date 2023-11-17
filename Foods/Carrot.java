package Foods;

import Farm.Soil;

public class Carrot extends Vegetable {
    public Carrot(){
        super("Carrots", 750.32 , 15 , 100);
        setPreferredSoil(Soil.Sand);
    }
}