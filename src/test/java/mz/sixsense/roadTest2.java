//package mz.sixsense;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//import mz.sixsense.road.entity.Hotel;
//import mz.sixsense.road.entity.Restaurant;
//import mz.sixsense.road.entity.Road;
//import mz.sixsense.road.entity.TouristAttaction;
//import mz.sixsense.road.repository.RestaurantRepository;
//import mz.sixsense.road.repository.HotelRepository;
//import mz.sixsense.road.repository.RoadRepository;
//import mz.sixsense.road.repository.TouristAttactionRepository;
//import mz.sixsense.road.service.JSONService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class roadTest2 {
//
//    @Autowired
//    private RoadRepository roadRepository;
//
//    @Autowired
//    private TouristAttactionRepository touristAttactionRepository;
//
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    @Autowired
//    private HotelRepository hotelRepository;
//
//    @Autowired
//    private JSONService jsonService;
//
////
////    @Test
////    public void TouristAttactionJson() throws IOException {
////        int i = 0;
////        while (true) {
////            i++;
////            String str = String.valueOf(i);
////            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmgtourinfo"); /*URL*/
////            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D"); /*Service Key*/
////            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*검색건수*/
////            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8")); /*페이지 번호*/
////            urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미 resultType=json 입력*/
////            URL url = new URL(urlBuilder.toString());
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            conn.setRequestProperty("Content-type", "application/json");
////            System.out.println("Response code: " + conn.getResponseCode());
////            BufferedReader rd;
////            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
////                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////            } else {
////                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
////            }
////            StringBuilder sb = new StringBuilder();
////            String line;
////            while ((line = rd.readLine()) != null) {
////                sb.append(line);
////            }
////            rd.close();
////            conn.disconnect();
////            System.out.println(sb.toString());
////
////            String result = sb.toString();
////            System.out.println(result);
////
////            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////            JsonParser parser = new JsonParser();
////            JsonElement element = parser.parse(result);
////
////            String realresult = element.getAsJsonObject().get("getgmgtourinfo").getAsJsonObject().
////                    get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").toString().
////                    replace('[', ' ').replace(']', ' ');
////            System.out.println("필요한 만큼 다 걷어냈다" + realresult);
////            TouristAttaction touristAttaction = null;
////
////            try {
////                touristAttaction = mapper.readValue(realresult, TouristAttaction.class);
////                for (int k = 1; k < 10; k++) {
////                    for (int j = 1; j < 4; j++) {
////                        String course = String.valueOf(k);
////                        String gugan = String.valueOf(j);
////                        if (touristAttaction.getRoad_course().equals(course) && touristAttaction.getRoad_course_gugan().equals(gugan)) {
////                            String cg = course + "코스 " + gugan + "구간";
////                            Road road = roadRepository.findById(cg).get();
////                            touristAttaction.setGuganNm(road);
////                            System.out.println(touristAttaction.getGuganNm());
////                            break;
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                break;
////            }
////            touristAttactionRepository.save(touristAttaction);
////        }
////    }
////
////    @Test
////    public void getAroundRestaurantJson() throws IOException {
////        int i = 0;
////        while (true) {
////            i++;
////            String str = String.valueOf(i);
////            String url = "http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmgfoodinfo";
////            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
////            String parsekeyword = "getgmgfoodinfo";
////
////            String result = jsonService.JSONRead(url, servicekey, str, parsekeyword);
////            Restaurant arroundRestaurant = null;
////
////            try {
////                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////                arroundRestaurant = mapper.readValue(result, Restaurant.class);
////                for (int k = 1; k < 10; k++) {
////                    for (int j = 1; j < 4; j++) {
////                        String course = String.valueOf(k);
////                        String gugan = String.valueOf(j);
////                        if (arroundRestaurant.getRoad_course().equals(course) && arroundRestaurant.getRoad_course_gugan().equals(gugan)) {
////                            String cg = course + "코스 " + gugan + "구간";
////                            Road road = roadRepository.findById(cg).get();
////                            arroundRestaurant.setGuganNm(road);
////                            System.out.println(arroundRestaurant.getGuganNm());
////                            break;
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                break;
////            }
////            restaurantRepository.save(arroundRestaurant);
////        }
////    }
////
////    @Test
////    public void HotelJson() throws IOException {
////        int i = 0;
////        while (true) {
////            i++;
////            String str = String.valueOf(i);
////            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmghotelinfo"); /*URL*/
////            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D"); /*Service Key*/
////            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*검색건수*/
////            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8")); /*페이지 번호*/
////            urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미 resultType=json 입력*/
////            URL url = new URL(urlBuilder.toString());
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            conn.setRequestProperty("Content-type", "application/json");
////            System.out.println("Response code: " + conn.getResponseCode());
////            BufferedReader rd;
////            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
////                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////            } else {
////                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
////            }
////            StringBuilder sb = new StringBuilder();
////            String line;
////            while ((line = rd.readLine()) != null) {
////                sb.append(line);
////            }
////            rd.close();
////            conn.disconnect();
////            System.out.println(sb.toString());
////
////            String result = sb.toString();
////            System.out.println(result);
////
////            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////            JsonParser parser = new JsonParser();
////            JsonElement element = parser.parse(result);
////
////            String realresult = element.getAsJsonObject().get("getgmghotelinfo").getAsJsonObject().
////                    get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").toString().
////                    replace('[', ' ').replace(']', ' ');
////            System.out.println("필요한 만큼 다 걷어냈다" + realresult);
////            Hotel hotel = null;
////
////            try {
////                hotel = mapper.readValue(realresult, Hotel.class);
////                for (int k = 1; k < 10; k++) {
////                    for (int j = 1; j < 4; j++) {
////                        String course = String.valueOf(k);
////                        String gugan = String.valueOf(j);
////                        if (hotel.getRoad_course().equals(course) && hotel.getRoad_course_gugan().equals(gugan)) {
////                            String cg = course + "코스 " + gugan + "구간";
////                            Road road = roadRepository.findById(cg).get();
////                            hotel.setGuganNm(road);
////                            System.out.println(hotel.getGuganNm());
////                            break;
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                break;
////            }
////            hotelRepository.save(hotel);
////        }
////    }
////
////
////    @Test
////    public void TestRestrant(){
////       Restaurant restaurant = restaurantRepository.selectRestrant("973");
////        System.out.println(restaurant);
////
////    }
////    //http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmghotelinfo
////    //=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D
////
//////    @Test
//////    public void HotelJsonTest() throws IOException {
//////        int i = 0;
//////        while (true) {
//////            i++;
//////            String str = String.valueOf(i);
//////            String url = "http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmghotelinfo";
//////            String servicekey = "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D";
//////            String parsekeyword = "getgmghotelinfo";
//////
//////            String realresult = jsonService.JSONRead(url,servicekey,str, parsekeyword);
//////
//////            System.out.println("필요한 만큼 다 걷어냈다" + realresult);
//////            Hotel hotel = null;
//////            try {
//////                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//////                hotel = mapper.readValue(realresult, Hotel.class);
//////                for (int k = 1; k < 10; k++) {
//////                    for (int j = 1; j < 4; j++) {
//////                        String course = String.valueOf(k);
//////                        String gugan = String.valueOf(j);
//////                        if (hotel.getRoad_course().equals(course) && hotel.getRoad_course_gugan().equals(gugan)) {
//////                            String cg = course + "코스 " + gugan + "구간";
//////                            Road road = roadRepository.findById(cg).get();
//////                            hotel.setGuganNm(road);
//////                            System.out.println(hotel.getGuganNm());
//////                            break;
//////                        }
//////                    }
//////                }
//////            } catch (Exception e) {
//////                break;
//////            }
//////            hotelRepository.save(hotel);
//////        }
//////    }
////
////
////    @Test
////    public void HotelJson123() throws IOException {
////
////        try {
////            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/fbusangmgtourinfo/getgmghotelinfo"); /*URL*/
////            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D"); /*Service Key*/
////            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("300", "UTF-8")); /*검색건수*/
////            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
////            urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미 resultType=json 입력*/
////            URL url = new URL(urlBuilder.toString());
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            conn.setRequestProperty("Content-type", "application/json");
////            System.out.println("Response code: " + conn.getResponseCode());
////            BufferedReader rd;
////            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
////                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////            } else {
////                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
////            }
////            StringBuilder sb = new StringBuilder();
////            String line;
////            while ((line = rd.readLine()) != null) {
////                sb.append(line);
////            }
////            String result = sb.toString();
////
////
////            JsonParser parser = new JsonParser();
////            JsonElement element = parser.parse(result);
////
////            List<Hotel> recommenedServices = new ArrayList<>();
////            String realresult = element.getAsJsonObject().get("getgmghotelinfo").getAsJsonObject().
////                    get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").toString().
////                    replace('[', ' ');
////
////            String[] arr = realresult.split("}");
////            Hotel hotel = null;
////            for (String copy : arr) {
////                String copy2 = copy.substring(1, copy.length()) + "}";
////                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////                hotel = mapper.readValue(copy2, Hotel.class);
////                for (int k = 1; k < 10; k++) {
////                    for (int j = 1; j < 4; j++) {
////                        String course = String.valueOf(k);
////                        String gugan = String.valueOf(j);
////                        if (hotel.getRoad_course().equals(course) && hotel.getRoad_course_gugan().equals(gugan)) {
////                            String cg = course + "코스 " + gugan + "구간";
////                            Road road = roadRepository.findById(cg).get();
////                            hotel.setGuganNm(road);
////                            System.out.println(hotel.getGuganNm());
////                            break;
////                        }
////                    }
////                }
////                hotelRepository.save(hotel);
////            }
////        } catch (Exception ex) {
////            System.out.println("오류");
////        }
////
////    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
