package VTTPssfday12.exercise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/generate")

public class GenerateController {

    private int toInt(String str, int defValue){
        try{
            return Integer.parseInt(str);
        }catch(Exception ex){
            return defValue;
        }
    }


    @GetMapping
    public String getGenerate(       
        @RequestParam MultiValueMap<String, String> form,
        Model model){

        //trying with multi param, not neccessary if there is no checkbox
        String name = form.getFirst("name"); //grab the data from the index.html
        int count = toInt(form.getFirst("count"), 4);



        List<String> imagePaths = new ArrayList<>();  // List to hold all image paths
        ArrayList<Integer> oldIDX = new ArrayList<>(); //track images already chosen
        

        Random rnd = new SecureRandom();
        for(int i = 0; i < count; i++){
            int idx = rnd.nextInt(Constants.PICTURES.length); //idx of chosen image
            while(oldIDX.contains(idx)){    //reroll if image already chosen
                idx = rnd.nextInt(Constants.PICTURES.length);
            }
            oldIDX.add(idx);
            String imagePath = "/images/%s".formatted(Constants.PICTURES[idx]);
            imagePaths.add(imagePath);
            // model.addAttribute("image" + idx, "/images/%s".formatted(Constants.PICTURES[idx]));//returns the image path of chosen image
        }

        //check whether name has been input in index.html landing page
        //if not, our template defaults to list.html
        if(form.containsKey("list")){  
            List<String> newImagePaths = new ArrayList<>();  // List to hold all image paths, refreshed
            //create array based on query parameter list
            String imgList [] = form.getFirst("list").split(",");
            for(int i = 0; i < imgList.length; i++){
                int temp = Integer.parseInt(imgList[i]);
                String imagePath = "/images/%s".formatted(Constants.PICTURES[temp]);
                newImagePaths.add(imagePath);
            }
            model.addAttribute("images", newImagePaths);
            return "list";
        }

        model.addAttribute("images", imagePaths);
        model.addAttribute("name", name.toUpperCase());
        model.addAttribute("count", count);


        return "generate";
        // return "list";
        }



    
}
