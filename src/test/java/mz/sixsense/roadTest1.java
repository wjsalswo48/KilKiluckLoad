//package mz.sixsense;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//import mz.sixsense.road.entity.*;
//import mz.sixsense.road.repository.*;
//
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
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class roadTest1 {
//
//    @Autowired
//    private RoadRepository roadRepository;
//
//    @Autowired
//    private ToiletRepository roadConvenientRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private PhotoZoneRepository photoZoneRepository;
//
//
////    @Test
////    public void course_insert() {
////        String[] len = {"33.6", "18.3", "41.0", "36.3", "42.1", "47.5", "22.3", "17.2", "20.5"};
////        String[] time = {"10", "6", "14", "13", "13", "15", "9", "5", "6"};
////
////        for (int i = 1; i < 10; i++) {
////            Course course = new Course();
////            String cr = String.valueOf(i) + "코스";
////            course.setCourse_ID(cr);
////            course.setTotal_length(len[i - 1]);
////            course.setTotal_time(time[i - 1]);
////            String course1 = String.valueOf(i);
////            String cg = course1 + "코스";
////            course.setImg_path("../images/" + cg + ".png");
////            courseRepository.save(course);
////        }
////    }
////
////    @Test
////    public void roadjson() throws IOException {
////        String[][] point = {
////                {"35.31601804268958", "129.2621948862194", "35.24446800428602", "129.22107893835832"}, //1코스 1구간
////                {"35.24455167204976", "129.22103728366892", "35.15722882704108", "129.1761829138613"}, //1코스 2구간
////                {"35.15727787116399", "129.1760613266045", "35.16113847032044", "129.13085344447376"}, //2코스 1구간
////                {"35.16146597698672", "129.13067763751818", "35.09964721696999", "129.1234750688315"}, //2코스 2구간
////                {"35.09965112722732", "129.1234576261709", "35.13511652851037", "129.05959901803928"}, //3코스 1구간
////                {"35.13513454409634", "129.05959947192764", "35.08135759567804", "129.04120146411205"}, //3코스 2구간
////                {"35.08138281801318", "129.0412020926293", "35.07558964116543", "129.07561872279874"}, //3코스 3구간
////                {"35.08138002670751", "129.0411537867144", "35.08412065286087", "128.9978164790027"}, //4코스 1구간
////                {"35.08410314214596", "128.99778535472444", "35.04499132857519", "128.9691522099807"}, //4코스 2구간
////                {"35.04460308923821", "128.9690859080399", "35.104989453392974", "128.95643518296967"}, //4코스 3구간
////                {"35.1049787140889", "128.95643053978662", "35.06562055728897", "128.8350070327021"}, //5코스 1구간
////                {"35.06560961370877", "128.83501555663207", "35.06562055728897", "128.8350070327021"}, //5코스 2구간
////                {"35.10480701285013", "128.95679489778672", "35.20616714888395", "128.9961601639987"}, //6코스 1구간
////                {"35.20611346264626", "128.99613689029172", "35.18348453337321", "129.04594371940033"}, //6코스 2구간
////                {"35.206195974616385", "128.996160869714", "35.24539090573453","129.06374260331657"},//6코스 3구간
////                {"35.183579549446236", "129.04586707065454", "35.24537588739941", "129.06356646546206"}, //7코스 1구간
////                {"35.24535051626426", "129.06357461025024", "35.26641021262345", "129.1125420544797"}, //7코스 2구간
////                {"35.26641021262345", "129.1125420544797", "35.217271513974865", "129.1188004319354"}, //8코스 1구간
////                {"35.21729711884776", "129.11877913581628", "35.16112405857129", "129.13085306844465"}, //8코스 2구간
////                {"35.266417341722466", "129.11254663463475", "35.27539355153746", "129.1789228901013"}, //9코스 1구간
////                {"35.275357761474346", "129.1789087448025", "35.24455871612959", "129.22104626330918"}  //9코스 2구간
////        };
////        int i = 0;
////        int p = 0;
////        while (true) {
////            i++;
////            String str = String.valueOf(i);
////            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/fbusangmgcourseinfo/getgmgcourseinfo"); /*URL*/
////            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D"); /*Service Key*/
////            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*검색건수*/
////            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8")); /*페이지 번호*/
////            urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미 resultType=json 입력*/
////            URL url = new URL(urlBuilder.toString());
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            conn.setRequestProperty("Content-type", "application/json");
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
////
////            rd.close();
////            conn.disconnect();
////
////            String result = sb.toString();
////
////            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////            JsonParser parser = new JsonParser();
////            JsonElement element = parser.parse(result);
////
////            String realresult = element.getAsJsonObject().get("getgmgcourseinfo").getAsJsonObject().
////                    get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").toString().
////                    replace('[', ' ').replace(']', ' ');
////            Road road = null;
////            try {
////                road = mapper.readValue(realresult, Road.class);
////                road.setVisits(0L);
////                road.setAvgTime("2022-12-16 00:00:00");
////
////                for (int k = 1; k < 10; k++) {
////                    for (int j = 1; j < 4; j++) {
////                        String course = String.valueOf(k);
////                        course += "코스";
////                        String gugan = String.valueOf(j);
////                        String cg = course + " " + gugan + "구간";
////
////                        if (road.getGuganNm().equals(cg)) {
////                            int endpoint=road.getRange().length();
////                            System.out.println(endpoint);
////                            if(road.getRange().substring(endpoint-2,endpoint).equals("KM")){
////                                String roadLength = road.getRange().substring(0,endpoint-2)+"km";
////                                System.out.println(roadLength);
////                            }
////                            Course courseresult = courseRepository.findById(course).get();
////                            road.setCourse_id(courseresult);
////                            road.setImg_path("../images/" + cg + ".png");
////                            road.setStart_lat(point[p][0]);
////                            road.setStart_long(point[p][1]);
////                            road.setEnd_lat(point[p][2]);
////                            road.setEnd_long(point[p][3]);
////
////                            p++;
////                            break;
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                break;
////            }
////           // roadRepository.save(road);
////
////        }
////    }
////
////    @Test
////    public void RoadConvenientjson() throws IOException {
////        int i = 0;
////        while (true) {
////            i++;
////            String str = String.valueOf(i);
////            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/fbusangmgadvantigeinfo/getgmgrestroominfo"); /*URL*/
////            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=1XPUVK59VJEXiY9535RdWzxtcYbxlrW3aqRCICHCEZky4Ss9400vCKSYe6OvxwJ%2FnsiucWf8wqEzIChHq9SFgw%3D%3D"); /*Service Key*/
////            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*검색건수*/
////            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8")); /*페이지 번호*/
////            urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미 resultType=json 입력*/
////            URL url = new URL(urlBuilder.toString());
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            conn.setRequestProperty("Content-type", "application/json");
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
////
////            rd.close();
////            conn.disconnect();
////
////            String result = sb.toString();
////            System.out.println(result);
////
////            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////            JsonParser parser = new JsonParser();
////            JsonElement element = parser.parse(result);
////
////            String realresult = element.getAsJsonObject().get("getgmgrestroominfo").getAsJsonObject().
////                    get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").toString().
////                    replace('[', ' ').replace(']', ' ');
////            System.out.println("필요한 만큼 다 걷어냈다" + realresult);
////            Toilet roadConvenient = null;
////
////            try {
////                roadConvenient = mapper.readValue(realresult, Toilet.class);
////                for (int k = 1; k < 10; k++) {
////                    for (int j = 1; j < 4; j++) {
////                        String course = String.valueOf(k);
////                        String gugan = String.valueOf(j);
////                        if (roadConvenient.getRoad_course().equals(course) && roadConvenient.getRoad_course_gugan().equals(gugan)) {
////                            String cg = course + "코스 " + gugan + "구간";
////                            Road road = roadRepository.findById(cg).get();
////                            roadConvenient.setGuganNm(road);
////                            break;
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                break;
////            }
////            roadConvenientRepository.save(roadConvenient);
////        }
////    }
////
////
////    @Test
////    public void Photozonejson() throws IOException {
////        int i = 0;
////        while (true) {
////            i++;
////            String str = String.valueOf(i);
////            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6260000/fbusangmgadvantigeinfo/getgmgphotoinfo"); /*URL*/
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
////            String realresult = element.getAsJsonObject().get("getgmgphotoinfo").getAsJsonObject().
////                    get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").toString().
////                    replace('[', ' ').replace(']', ' ');
////            System.out.println("필요한 만큼 다 걷어냈다" + realresult);
////            PhotoZone photoZone = null;
////
////            try {
////                photoZone = mapper.readValue(realresult, PhotoZone.class);
////                for (int k = 1; k < 10; k++) {
////                    for (int j = 1; j < 4; j++) {
////                        String course = String.valueOf(k);
////                        String gugan = String.valueOf(j);
////                        if (photoZone.getRoad_course().equals(course) && photoZone.getRoad_course_gugan().equals(gugan)) {
////                            String cg = course + "코스 " + gugan + "구간";
////                            Road road = roadRepository.findById(cg).get();
////                            photoZone.setGuganNm(road);
////                            break;
////                        }
////                    }
////                }
////            } catch (Exception e) {
////                break;
////            }
////                photoZoneRepository.save(photoZone);
////        }
////    }
//}
