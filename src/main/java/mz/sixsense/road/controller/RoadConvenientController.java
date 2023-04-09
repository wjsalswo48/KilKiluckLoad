package mz.sixsense.road.controller;

import mz.sixsense.road.service.RoadConvenientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/json/")
public class RoadConvenientController {

    @Autowired
    RoadConvenientService roadConvenientService;



    @RequestMapping("/insertCourse")
    public String insertCourse() {
        roadConvenientService.insertCourse();
        return "redirect:/";
    }

    @RequestMapping("/roadJson")
    public String roadJson() throws IOException {
        roadConvenientService.getRoadListJson();
        return "redirect:/";
    }

    @RequestMapping("/touristJson")
    public String touristJson() throws IOException {
        roadConvenientService.getTouristAttractionJSON();
        return "redirect:/";
    }

    @RequestMapping("/toiletJson")
    public String toiletJSOn() throws IOException {
        roadConvenientService.getToiletJSON();
        return "redirect:/";
    }

    @RequestMapping("/restaurantJson")
    public String restaurantJson() throws IOException {
        roadConvenientService.getAroundRestaurantJson();
        return "redirect:/";
    }

    @RequestMapping("/hotelJson")
    public String hotelJson(String num) throws IOException {
        roadConvenientService.getHotelJSON(num);
        return "redirect:/";
    }
    @RequestMapping("/photoZoneJson")
    public String photoZoneJson() throws IOException {
        roadConvenientService.getPhotoZoneJSON();
        return "redirect:/";
    }

    @RequestMapping("/allJSON")
    public String allJSON(String num)throws IOException {
        roadConvenientService.insertCourse();
        roadConvenientService.getRoadListJson();
        roadConvenientService.getTouristAttractionJSON();
        roadConvenientService.getToiletJSON();
        roadConvenientService.getAroundRestaurantJson();
        roadConvenientService.getHotelJSON(num);
        roadConvenientService.getPhotoZoneJSON();
        return "redirect:/";
    }

}
