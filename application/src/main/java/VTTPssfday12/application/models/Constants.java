package VTTPssfday12.application.models;

import java.util.LinkedList;
import java.util.List;

public class Constants {
    
    public static final String[] ITEMS = {
        "apple", "orange", "pear", "grapes", "plum"
     };
  
     public static final int[] QUANTITY = {
        10, 5, 8, 3, 7
     };
  
     //method to store list of fruit object
     //each fruit is its own class with a name and quantity
     public static List<LineItem> generateLineItems(int count) {
  
        List<LineItem> items = new LinkedList<>();
  
        if (count > ITEMS.length) //max possible count is length of fruit items
           count = ITEMS.length;
  
        for (int i = 0; i < count; i++) {
           LineItem li = new LineItem(); //create a fruit object iterativey
           li.setName(ITEMS[i]);
           li.setQuantity(QUANTITY[i]);
           items.add(li);  //add fruit to fruit list
        }
  
        return items;
     }
  
     public static List<LineItem> generateLineItems() { //method overloading to return length of max possible number of fruits
        return generateLineItems(ITEMS.length);
     }


}
