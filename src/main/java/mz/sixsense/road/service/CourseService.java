package mz.sixsense.road.service;

import mz.sixsense.road.entity.*;

import java.util.List;

public interface CourseService {
    public Course findCourse(String courseId);

    public List<Course> getCourseList();

    public Road findRoad(String road);

    public Toilet findRoadConvenient(String roadConvenient);

    public Hotel findHotel(String hotel);

    public PhotoZone findPhotoZone(String photoZoneSeq);

    public Restaurant findAroundRestaurant(String restaurantSeq);

    public TouristAttaction findTouristAttaction(String touristAtSeq);

    public List<Road> getRoadExelList();
    public List<Toilet> getRoadConvenientExelList();

    public List<PhotoZone> getPhotoZoneExelList();

    public List<Hotel> getHotelExelList();

    public List<TouristAttaction> getTouristAttactionExelList();

    public List<Restaurant> getArroundRestaurantExelList();

    public CodeTable searchCodeValue(String value);

    public CodeTable searchNameCodeTable(String name);

    public CodeTable changeCodeName(String value, String rename);
}
