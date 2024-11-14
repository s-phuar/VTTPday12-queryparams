package VTTPssfday12.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weather")

public class WeatherControllers {
    @GetMapping
    public String getWeather(
      @RequestParam(defaultValue = "metrics") String unit,
      Model model) {
       model.addAttribute("city", "singapore");
       model.addAttribute("unit", unit);
       return "weather";
    }
    
 
    // GET /weather/singapore
    //when we manually input /weather/city in the url, we are sending a GET request from the browsser to the server(localhost)
    @GetMapping("{city}")
    public String getWeather( 
          @PathVariable String city, //the pathvariable annotation binds the value of the city key from the url, to the city param
          //we know the city name when we manually input /"city" in the url after /weather
          @RequestParam(defaultValue = "metrics") String unit,
          Model model) {
 
       model.addAttribute("city", city);
       model.addAttribute("unit", unit);
       return "weather";
    }
}
