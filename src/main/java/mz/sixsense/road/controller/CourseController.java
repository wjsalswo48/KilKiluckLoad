package mz.sixsense.road.controller;

import mz.sixsense.road.entity.*;
import mz.sixsense.road.repository.CodeTableRepository;
import mz.sixsense.road.repository.ToiletRepository;
import mz.sixsense.road.repository.RoadRepository;
import mz.sixsense.road.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    ToiletRepository toiletRepository;
    @Autowired
    CodeTableRepository codeTableRepository;

    @Autowired
    RoadRepository roadRepository;


    @RequestMapping("/")
    public String getCourseList(Model model){
        List<Course> courseList = courseService.getCourseList();

        System.out.println(courseList.size());
        String [][] codenamelist = new String[courseList.size()][];
        int i = 0;
        for(Course course : courseList){
             CodeTable codeTable = codeTableRepository.findById(course.getCourseID()).get();
             String name = codeTable.getCodeName();
            System.out.println(name);
            codenamelist[i] = new String[course.getRoadList().size()+1];
            codenamelist[i][0]=name;
            int j = 1;
            List<Road> roadList = course.getRoadList();
            for(Road road : roadList){
                codeTable = codeTableRepository.findById(road.getGuganNm()).get();
                name = codeTable.getCodeName();
                codenamelist[i][j]=name;
                j++;
            }
            Arrays.sort(codenamelist[i]);
            i++;
        }
        model.addAttribute("codenamelist",codenamelist);
        model.addAttribute("courseList",courseList);
        return "index";
    }

    @RequestMapping("/road/getRoadList")
    public String getRoadList(Course course, @Param("index") int index, Model model){
        List<Course> courseList = courseService.getCourseList();

        Course roadList = courseService.findCourse(course.getCourseID());
        List<Road>roadsortList = roadList.getRoadList();


        List<String>codenamelist = new ArrayList<>();
        int i = 0;
        for(Road copyroad : roadList.getRoadList()){
            CodeTable codeTable = codeTableRepository.findById(copyroad.getGuganNm()).get();
            String codename = codeTable.getCodeName();
            System.out.println();
            codenamelist.add(codename);
            i++;
        }
        Collections.sort(codenamelist);
        CodeTable codevalue = codeTableRepository.searchNameCodeTable(codenamelist.get(index));
        Road road = roadRepository.findById(codevalue.getCodeValueString()).get();


        model.addAttribute("courseList",courseList);
        model.addAttribute("course",roadList); // 메뉴에서 사용됨
        model.addAttribute("index",index);
        model.addAttribute("road",road);
        model.addAttribute("codenameList",codenamelist);
        return "course/getRoadView";
    }

    @RequestMapping("/road/getTouristList")
    public String getTourustList(Course course, @Param("index1") int index1, @Param("index2") int index2, Model model){
        List<Course> courseList = courseService.getCourseList();
        TouristAttaction touristAttactionList = courseService.findCourse(course.getCourseID()).getRoadList().get(index1).getTouristAttactionList().get(index2);
        model.addAttribute("courseList",courseList); // 메뉴에서 사용됨
        model.addAttribute("touristList",touristAttactionList);

        return "course/getTouristAttaction";
    }

    @RequestMapping("/road/getRestarant")
    public String getRestarant(Course course, @Param("index1") int index1, @Param("index2") int index2, Model model){
        List<Course> courseList = courseService.getCourseList();
        Restaurant arroundRestaurantList = courseService.findCourse(course.getCourseID()).getRoadList().get(index1).getArroundRestaurantList().get(index2);
        model.addAttribute("courseList",courseList); // 메뉴에서 사용됨
        model.addAttribute("restaurantList",arroundRestaurantList);
        return "course/RestarantView";
    }

    @RequestMapping("/road/informationRoad")
    public String informationRoad(){
        return "/road/informationRoad";
    }

}
