package Farm;

import java.util.ArrayList;

import Foods.Food;


public class Farm {
    private int totalArea;
    private ArrayList<Food> foods;
    private Soil farmSoil;

    public Farm(int totalArea, Soil farmSoil){
        this.totalArea = totalArea;
        this.farmSoil = farmSoil;
    }

    public int getTotalArea(){
        return totalArea;
    }

    public Soil getFarmSoil(){
        return farmSoil;
    }

    public int getFoodQuantity(){
        return foods.size();
    }
    
    public Food getFood(int index){
        if (index >= 0 && index < foods.size()){
            return foods.get(index);
        }

        return null;
    }

    public double getTotalFarmValue(){
        return foods.stream().mapToDouble(Food :: getSellPrice).sum();
    }

    public double getReadyToHarvestValue(){
        return foods.stream().filter(f -> f.getDaysSincePotted() >= f.getDaysToMature())
                .mapToDouble(Food :: getSellPrice).sum();
    }

    public boolean seedFood(Food food){
        int requiredArea = food.getRequiredArea();
        int usedArea = foods.stream().mapToInt(Food :: getRequiredArea).sum();

        if (totalArea - usedArea >= requiredArea){
            foods.add(food);
            return true;
        } else {
            return false;
        }
        
    }

    public Food harvestFood(int index) {
        Food food = foods.get(index);

        if (food != null && food.getDaysSincePotted() >= food.getDaysToMature()) {
            foods.get(index);
            return food;
        }
        return null;
    }

    public void overNightGrow(){
        for (Food food : foods) {
            food.grow(food.getPreferredSoil());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total farm value: ").append(getTotalFarmValue()).append("\n");
        builder.append("Ready to harvest: ").append(getReadyToHarvestValue()).append("\n");
        builder.append("Food available:\n");

        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            builder.append(i).append(" - ")
                .append(food.getName())
                .append(" (").append(food.getPreferredSoil().toString()).append(")")
                .append(" - ")
                .append(food.getDaysSincePotted())
                .append("/")
                .append(food.getDaysToMature()).append(" days\n");
        }

        return builder.toString();
    } 


}