package Farm;

import Foods.Apple;
import Foods.Food;

public class AppleFarm extends Farm {
    public AppleFarm(int totalArea){
        super(totalArea, Soil.Loam);
    }
    
    @Override
    public boolean seedFood(Food food){
        if (food instanceof Apple) {
            return super.seedFood(food);
        }
        return false;
    }
}