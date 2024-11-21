package VTTPssfday12.application.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import VTTPssfday12.application.models.LineItem;
import VTTPssfday12.application.models.Constants;

@Controller
@RequestMapping("/cart")
//requestmapping maps to the /cart page, but how do we get here?

//in this example we have 3 possible urls
   // index.html
   // index.html/cart
   // index.html/cart/map


public class CartController {

   @GetMapping
   public String getCart(
      @RequestParam(required = false) String name, 
      //modified from @RequestParam(defaultValue = "3") int count,
      @RequestParam(required = false) String count,
      Model model) {

      //name and count captured by requestparam, count is optional. Delivery is not captured

      int itemCount = 3;
      try {
         itemCount = Integer.parseInt(count);
      } catch (Exception ex) { }

      List<LineItem> items = Constants.generateLineItems(itemCount); //create and store fruit list with count of 3

      if (name == null){ //cannot use .equals because it doesnt work if name is actually null
         name = "";
      }

      model.addAttribute("name", name.toUpperCase()); //fruit names
      model.addAttribute("items", items); //sends over List
      model.addAttribute("count", itemCount); //sends over no. of fruit types

      return "cart"; //forwards the data to the dynamic cart view
   }

   @GetMapping("/map")
   public String getCart(
      @RequestParam MultiValueMap<String, String> form ,
      Model model) {

      //name, count and delivery captured by form variable, any of them is optional

      String name = form.getFirst("name");      //retrieving information from index.html
      String count = form.getFirst("count");    //retrieving information from index.html

      System.out.printf(">>>>>   get first: %s\n", form.getFirst("delivery"));
      System.out.printf(">>>>>   get: %s\n", form.get("delivery"));

      int itemCount = 3;
      try {
         itemCount = Integer.parseInt(count);
      } catch (Exception ex) { }

      List<LineItem> items = Constants.generateLineItems(itemCount);

      model.addAttribute("name", name.toUpperCase()); //name of fruits
      model.addAttribute("items", items); //sends the List
      model.addAttribute("count", itemCount); //no. of fruit types

      return "cart"; //forwards the data to the dynamic cart view
   }

}
