package logistics.diagrams.namedExpressions;

import logistics.diagrams.ParentGraph;

import java.util.Random;

public class TotalClosingCost extends ParentGraph {

    public TotalClosingCost() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        this.backgroundColor = "rgba(" + r + ", " + g + ", " + b + ", " + "0.5)";


        String letters = "0123456789ABCDEF";
        String color = "#";
        StringBuilder newColor = new StringBuilder(color);
        for (int i = 0; i < 6; i++) {
            newColor.append(letters.charAt((int) Math.floor(Math.random() * 16)));
        }

        this.borderColor = newColor.toString();
        this.fill = true;
    }
}
