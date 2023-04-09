package mz.sixsense.road.service;

import java.io.IOException;

public interface RoadConvenientService {

    void insertCourse();

    void getRoadListJson() throws IOException;

    void getToiletJSON() throws IOException;

    void getPhotoZoneJSON() throws IOException;

    void getTouristAttractionJSON() throws IOException;

    void getAroundRestaurantJson() throws IOException;

    void getHotelJSON(String num) throws IOException;

}
