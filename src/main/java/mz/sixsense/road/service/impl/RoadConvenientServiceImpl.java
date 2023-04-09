package mz.sixsense.road.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import mz.sixsense.road.entity.*;
import mz.sixsense.road.repository.*;
import mz.sixsense.road.service.JSONService;
import mz.sixsense.road.service.RoadConvenientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RoadConvenientServiceImpl implements RoadConvenientService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RoadRepository roadRepository;

    @Autowired
    ToiletRepository toiletRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    PhotoZoneRepository photoZoneRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    TouristAttactionRepository touristAttactionRepository;

    @Autowired
    JSONService jsonService;

    @Autowired
    CodeTableRepository codeTableRepository;


    @Override
    public void insertCourse() {
        String[] len = {"33.6", "18.3", "41.0", "36.3", "42.1", "47.5", "22.3", "17.2", "20.5"};
        String[] time = {"10", "6", "14", "13", "13", "15", "9", "5", "6"};

        for (int i = 1; i < 10; i++) {
            Course course = new Course();
            String cr = "0"+String.valueOf(i) + "C";
            course.setCourseID(cr);
            course.setTotal_length(len[i - 1]);
            course.setTotal_time(time[i - 1]);
            String course1 = String.valueOf(i);
            String cg = course1 + "코스";
            course.setImg_path("../images/" + cg + ".png");
            courseRepository.save(course);
            CodeTable codeTable = new CodeTable();
            codeTable.setCodeValueString(cr);
            codeTable.setCodeName(cg);
            codeTableRepository.save(codeTable);
        }
    }

    @Override
    public void getRoadListJson() throws IOException {
        String[][] point = {
                {"35.31601804268958", "129.2621948862194", "35.24446800428602", "129.22107893835832"}, //1코스 1구간
                {"35.24455167204976", "129.22103728366892", "35.15722882704108", "129.1761829138613"}, //1코스 2구간
                {"35.15727787116399", "129.1760613266045", "35.16113847032044", "129.13085344447376"}, //2코스 1구간
                {"35.16146597698672", "129.13067763751818", "35.09964721696999", "129.1234750688315"}, //2코스 2구간
                {"35.09965112722732", "129.1234576261709", "35.13511652851037", "129.05959901803928"}, //3코스 1구간
                {"35.13513454409634", "129.05959947192764", "35.08135759567804", "129.04120146411205"}, //3코스 2구간
                {"35.08138281801318", "129.0412020926293", "35.07558964116543", "129.07561872279874"}, //3코스 3구간
                {"35.08138002670751", "129.0411537867144", "35.08412065286087", "128.9978164790027"}, //4코스 1구간
                {"35.08410314214596", "128.99778535472444", "35.04499132857519", "128.9691522099807"}, //4코스 2구간
                {"35.04460308923821", "128.9690859080399", "35.104989453392974", "128.95643518296967"}, //4코스 3구간
                {"35.1049787140889", "128.95643053978662", "35.06562055728897", "128.8350070327021"}, //5코스 1구간
                {"35.06560961370877", "128.83501555663207", "35.06562055728897", "128.8350070327021"}, //5코스 2구간
                {"35.10480701285013", "128.95679489778672", "35.20616714888395", "128.9961601639987"}, //6코스 1구간
                {"35.20611346264626", "128.99613689029172", "35.18348453337321", "129.04594371940033"}, //6코스 2구간
                {"35.206195974616385", "128.996160869714", "35.24539090573453", "129.06374260331657"},//6코스 3구간
                {"35.183579549446236", "129.04586707065454", "35.24537588739941", "129.06356646546206"}, //7코스 1구간
                {"35.24535051626426", "129.06357461025024", "35.26641021262345", "129.1125420544797"}, //7코스 2구간
                {"35.26641021262345", "129.1125420544797", "35.217271513974865", "129.1188004319354"}, //8코스 1구간
                {"35.21729711884776", "129.11877913581628", "35.16112405857129", "129.13085306844465"}, //8코스 2구간
                {"35.266417341722466", "129.11254663463475", "35.27539355153746", "129.1789228901013"}, //9코스 1구간
                {"35.275357761474346", "129.1789087448025", "35.24455871612959", "129.22104626330918"}  //9코스 2구간
        };
        int p = 0;

        try{
            String url = "http://apis.data.go.kr/6260000/fbusangmgcourseinfo/getgmgcourseinfo";
            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
            String parsekeyword = "getgmgcourseinfo";
            CodeTable codeTable = new CodeTable();

            String realresult = jsonService.JSONRead(url, servicekey, "300", parsekeyword);
            String[] arr = realresult.split("}");
            Road road = null;
            for (String copy : arr) {
                String copy2 = copy.substring(1, copy.length()) + "}";
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                road = mapper.readValue(copy2, Road.class);
                road.setVisits(0L);
                road.setAvgTime("0");

                for (int k = 1; k < 10; k++) {
                    for (int j = 1; j < 4; j++) {
                        String course = String.valueOf(k);
                        course += "코스";
                        String gugan = String.valueOf(j);
                        String cg = course + " " + gugan + "구간";
                        if (road.getGuganNm().equals(cg)) {
                            int endpoint = road.getRange().length();
                            if (road.getRange().substring(endpoint - 2, endpoint).equals("KM")) {
                                String roadLength = road.getRange().substring(0, endpoint - 2) + "km";
                                road.setRange(roadLength);
                            }
                            course = "0"+String.valueOf(k)+"C";
                            gugan = "0"+String.valueOf(j)+"G";
                            Course courseresult = courseRepository.findById(course).get();

                            codeTable.setCodeValueString(course+gugan);
                            codeTable.setCodeName(cg);
                            road.setGuganNm(course+gugan);
                            road.setCourseId(courseresult);
                            road.setImg_path("../images/" + cg + ".png");
                            road.setStart_lat(point[p][0]);
                            road.setStart_long(point[p][1]);
                            road.setEnd_lat(point[p][2]);
                            road.setEnd_long(point[p][3]);
                            System.out.println(road.getStart_lat());
                            if (road.getRange().substring(endpoint - 3, endpoint - 1).equals("KM")) {
                                String roadLength = road.getRange().substring(0, endpoint - 4);
                            }
                            System.out.println(road.getImg_path());
                            System.out.println(road.getCourseId().getCourseID());
                            p++;
                            break;
                        }
                    }
                }
                codeTableRepository.save(codeTable);
                roadRepository.save(road);
            }
        }catch (Exception e) {
            System.out.println("오류");
        }
    }

    @Override
    public void getToiletJSON() throws IOException {
        try {
            String url = "http://apis.data.go.kr/6260000/fbusangmgadvantigeinfo/getgmgrestroominfo";
            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
            String parsekeyword = "getgmgrestroominfo";

            String realresult = jsonService.JSONRead(url, servicekey, "300", parsekeyword);

            String[] arr = realresult.split("}");
            Toilet toilet = null;

            for (String copy : arr) {
                String copy2 = copy.substring(1, copy.length()) + "}";
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                toilet = mapper.readValue(copy2, Toilet.class);
                for (int k = 1; k < 10; k++) {
                    for (int j = 1; j < 4; j++) {
                        String course = String.valueOf(k);
                        String gugan = String.valueOf(j);
                        if (toilet.getRoad_course().equals(course) && toilet.getRoad_course_gugan().equals(gugan)) {
                            if(toilet.getAddress_r().equals("")){
                                toilet.setAddress_r("not found");
                            }
                            if(toilet.getManager_tel_no().equals("")){
                                toilet.setManager_tel_no("not found");
                            }
                            String cg = "0"+course + "C" + "0" + gugan + "G";
                            Road road = roadRepository.findById(cg).get();
                            toilet.setGuganNm(road);
                            break;
                        }
                    }
                }
                toiletRepository.save(toilet);
            }
        }catch (Exception e) {
            System.out.println("오류");
        }
    }

    @Override
    public void getPhotoZoneJSON() throws IOException {
        try {
            String url = "http://apis.data.go.kr/6260000/fbusangmgadvantigeinfo/getgmgphotoinfo";
            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
            String parsekeyword = "getgmgphotoinfo";

            String realresult = jsonService.JSONRead(url, servicekey, "300", parsekeyword);

            String[] arr = realresult.split("}");

            PhotoZone photoZone = null;
            for (String copy : arr) {
                String copy2 = copy.substring(1, copy.length()) + "}";
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                photoZone = mapper.readValue(copy2, PhotoZone.class);
                for (int k = 1; k < 10; k++) {
                    for (int j = 1; j < 4; j++) {
                        String course = String.valueOf(k);
                        String gugan = String.valueOf(j);
                        if (photoZone.getRoad_course().equals(course) && photoZone.getRoad_course_gugan().equals(gugan)) {
                            String cg = "0"+course + "C" + "0" + gugan + "G";
                            Road road = roadRepository.findById(cg).get();
                            photoZone.setGuganNm(road);
                            break;
                        }
                    }
                }
                photoZoneRepository.save(photoZone);
            }
        } catch (Exception e) {
            System.out.println("오류");
        }
    }

    @Override
    public void getTouristAttractionJSON() throws IOException {
        try {
            String url = "http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmgtourinfo";
            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
            String parsekeyword = "getgmgtourinfo";

            String realresult = jsonService.JSONRead(url, servicekey, "85", parsekeyword);

            String[] arr = realresult.split("}");
            TouristAttaction touristAttaction = null;
            for (String copy : arr) {
                String copy2 = copy.substring(1, copy.length()) + "}";
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                touristAttaction = mapper.readValue(copy2, TouristAttaction.class);
                for (int k = 1; k < 10; k++) {
                    for (int j = 1; j < 4; j++) {
                        String course = String.valueOf(k);
                        String gugan = String.valueOf(j);
                        if (touristAttaction.getRoad_course().equals(course) && touristAttaction.getRoad_course_gugan().equals(gugan)) {
                            String cg = "0"+course + "C" + "0" + gugan + "G";
                            Road road = roadRepository.findById(cg).get();
                            touristAttaction.setGuganNm(road);
                            touristAttaction.setMainImage("https://www.visitbusan.net/"+touristAttaction.getMainImage());
                            touristAttaction.setThumbnail("https://www.visitbusan.net/"+touristAttaction.getThumbnail());
                            System.out.println(touristAttaction.getGuganNm());
                            break;
                        }
                    }
                }
                touristAttactionRepository.save(touristAttaction);
            }
        } catch (Exception e) {
            System.out.println("오류");
        }

    }

    @Override
    public void getAroundRestaurantJson() throws IOException {
        try {
            String url = "http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmgfoodinfo";
            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
            String parsekeyword = "getgmgfoodinfo";

            String realresult = jsonService.JSONRead(url, servicekey, "300", parsekeyword);

            String[] arr = realresult.split("}");
            Restaurant restaurant = null;
            for (String copy : arr) {
                String copy2 = copy.substring(1, copy.length()) + "}";
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                restaurant = mapper.readValue(copy2, Restaurant.class);
                for (int k = 1; k < 10; k++) {
                    for (int j = 1; j < 4; j++) {
                        String course = String.valueOf(k);
                        String gugan = String.valueOf(j);
                        if (restaurant.getRoad_course().equals(course) && restaurant.getRoad_course_gugan().equals(gugan)) {
                            String cg = "0"+course + "C" + "0" + gugan + "G";
                            Road road = roadRepository.findById(cg).get();
                            restaurant.setGuganNm(road);
                            restaurant.setMainimage("https://www.visitbusan.net/"+restaurant.getMainimage());
                            restaurant.setThumbnail("https://www.visitbusan.net/"+restaurant.getThumbnail());
                            break;
                        }
                    }
                }
                restaurantRepository.save(restaurant);
            }
        } catch (Exception e) {
            System.out.println("오류");
        }
    }


    @Override
    public void getHotelJSON(String num) throws IOException {
        try {
            String url = "http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmghotelinfo";
            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
            String parsekeyword = "getgmghotelinfo";

            String result = jsonService.JSONRead(url, servicekey, "300", parsekeyword);

            String[] arr = result.split("}");
            Hotel hotel = null;
            for (String copy : arr) {
                String copy2 = copy.substring(1, copy.length()) + "}";
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                hotel = mapper.readValue(copy2, Hotel.class);
                for (int k = 1; k < 10; k++) {
                    for (int j = 1; j < 4; j++) {
                        String course = String.valueOf(k);
                        String gugan = String.valueOf(j);
                        if (hotel.getRoad_course().equals(course) && hotel.getRoad_course_gugan().equals(gugan)) {
                            if(hotel.getHomepageUrl().equals("")){
                                hotel.setHomepageUrl("non url");
                            }
                            if(hotel.getTel().equals("")){
                                hotel.setTel("non tell num");
                            }
                            String cg = "0"+course + "C" + "0" + gugan + "G";
                            Road road = roadRepository.findById(cg).get();
                            hotel.setGuganNm(road);
                            System.out.println(hotel.getGuganNm());
                            break;
                        }
                    }
                }
                hotelRepository.save(hotel);
            }
        } catch (Exception e) {
            System.out.println("아이고");
        }

    }


}
