package mz.sixsense.road.service.impl;

import mz.sixsense.road.entity.*;
import mz.sixsense.road.repository.*;
import mz.sixsense.road.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    RoadRepository roadRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    TouristAttactionRepository touristAttactionRepository;
    @Autowired
    PhotoZoneRepository photoZoneRepository;
    @Autowired
    ToiletRepository toiletRepository;

    @Autowired
    CodeTableRepository codeTableRepository;

    @Override
    public Course findCourse(String courseId){
        Course course = courseRepository.findById(courseId).get();
        return course;
    }

    @Override
    public Road findRoad(String roadID){
        return roadRepository.findById(roadID).get();
    }

    @Override
    public Toilet findRoadConvenient(String roadConvenientID){
        return toiletRepository.findById(roadConvenientID).get();
    }

    @Override
    public PhotoZone findPhotoZone(String photoZoneSeq){
        return photoZoneRepository.findById(photoZoneSeq).get();
    }

    @Override
    public Hotel findHotel(String hotel){
        return hotelRepository.findById(hotel).get();
    }

    @Override
    public Restaurant findAroundRestaurant(String restaurantSeq){
        return restaurantRepository.findById(restaurantSeq).get();
    }

    @Override
    public TouristAttaction findTouristAttaction(String touristAtSeq){
        return touristAttactionRepository.findById(touristAtSeq).get();
    }

    @Override
    public List<Course> getCourseList(){
        return (List<Course>) courseRepository.findAllByOrderByCourseIDAsc();
    }

    @Override
    public List<Road> getRoadExelList() {
        return (List<Road>) roadRepository.findAll();
    }

    @Override
    public List<Toilet> getRoadConvenientExelList() {
        return (List<Toilet>) toiletRepository.findAll();
    }

    @Override
    public List<PhotoZone> getPhotoZoneExelList() {
        return (List<PhotoZone>) photoZoneRepository.findAll();
    }

    @Override
    public List<Hotel> getHotelExelList() {
        return (List<Hotel>) hotelRepository.findAll();
    }

    @Override
    public List<TouristAttaction> getTouristAttactionExelList() {
        return (List<TouristAttaction>) touristAttactionRepository.findAll();
    }

    @Override
    public List<Restaurant> getArroundRestaurantExelList() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public CodeTable searchCodeValue(String value){
        return codeTableRepository.findById(value).get();
    }

    @Override
    public CodeTable searchNameCodeTable(String name) {
        return codeTableRepository.searchNameCodeTable(name);
    }

    @Override
    public CodeTable changeCodeName(String value, String rename) {
        CodeTable codeTable = codeTableRepository.findById(value).get();
        codeTable.setCodeName(rename);
        codeTableRepository.save(codeTable);
        return codeTable;
    }
}
