package mz.sixsense.road.excel.controller;

import mz.sixsense.board.ScriptUtils;
import mz.sixsense.road.entity.*;
import mz.sixsense.road.repository.*;
import mz.sixsense.road.service.CourseService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelInputController {

    @Autowired
    CourseService courseService;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RoadRepository roadRepository;

    @Autowired
    TouristAttactionRepository touristAttactionRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    PhotoZoneRepository photoZoneRepository;

    @Autowired
    ToiletRepository toiletRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CodeTableRepository codeTableRepository;


    @GetMapping("/excel/insert")
    public String main() { // 1
        return "course/exel";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @PostMapping("/excel/courseExcelCRUD")
    public String courseExcelInput(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse response)
            throws IOException { // 2
        List<Course> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        String deleteList = "";
        String alreadyDeleteList = "";

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            ScriptUtils.alertAndMovePage(response, "파일을 확인 해주세요.", "/excel/insert");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals("코스")) {
                if (row.getCell(5).getStringCellValue().equals("use")) {
                    Course course = null;
                    CodeTable codeTable = null;
                    try {
                        codeTable = courseService.searchCodeValue(row.getCell(6).getStringCellValue());
                        codeTable.setCodeName(row.getCell(1).getStringCellValue());
                        codeTableRepository.save(codeTable);
                        try {
                            course = courseService.findCourse(courseService.searchNameCodeTable(row.getCell(1).getStringCellValue()).getCodeValueString());
                        } catch (Exception e) {
                            course = new Course();
                            course.setCourseID(courseService.searchNameCodeTable(row.getCell(1).getStringCellValue()).getCodeValueString());
                        }
                    } catch (Exception e) {
//                        course = new Course();
                        try {
                            codeTable = new CodeTable();
                            codeTable.setCodeValueString(row.getCell(6).getStringCellValue());
                            codeTable.setCodeName((row.getCell(1).getStringCellValue()));
                            codeTableRepository.save(codeTable);
//                            course = new Course();
                            course.setCourseID(codeTable.getCodeValueString());
                        } catch (Exception ex) {
                            ScriptUtils.alertAndMovePage(response, (i + 1) + "의 id 값이 중복입니다 (고유 번호입니다. 수정하세요)", "/excel/insert");
                        }
                    }
                    try {
                        course.setImg_path(row.getCell(2).getStringCellValue());
                        try {
                            course.setTotal_length(row.getCell(3).getStringCellValue());
                            course.setTotal_time(row.getCell(4).getStringCellValue());
                        } catch (Exception ex) {
                            course.setTotal_length(String.valueOf(row.getCell(3).getNumericCellValue()));
                            course.setTotal_time(String.valueOf(row.getCell(4).getNumericCellValue()));
                        }
                        dataList.add(course);
                    } catch (Exception e) {
                        ScriptUtils.alertAndMovePage(response, (i + 1) + "번째 데이터 중 비어있거나 컬럼에 숫자가 있으니 확인하시고 추가 및 텍스트로 변경해주세요", "/excel/insert");
                    }

                } else if (row.getCell(5).getStringCellValue().equals("delete")) {
                    try {
                        courseRepository.deleteById(courseService.searchNameCodeTable(row.getCell(1).getStringCellValue()).getCodeValueString());
                        deleteList += (String.valueOf(i) + ", ");
                    } catch (EmptyResultDataAccessException e3) {
                        alreadyDeleteList += (String.valueOf(i) + ", ");
                    }
                } else {
                    ScriptUtils.alertAndMovePage(response, "flag내용을 use 또는 delete로 설정해주세요", "/excel/insert");
                }
            } else {
                ScriptUtils.alertAndMovePage(response, "파일명 또는 현재 파일의" + i + 1 + "번의 내용을 확인하세요", "/excel/insert");
            }
        }
        if (deleteList != "") {
            ScriptUtils.alert(response, deleteList.substring(0, deleteList.length() - 2) + "번째 내용을 삭제 하겠습니다");
        }
        if (alreadyDeleteList != "") {
            ScriptUtils.alertAndMovePage(response, alreadyDeleteList.substring(0, alreadyDeleteList.length() - 2) + "번째 내용은 이미 삭제되었습니다.", "/excel/insert");
        }
        courseRepository.saveAll(dataList);

        model.addAttribute("datas", dataList); // 5


        return "course/exelList";

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/excel/roadExcelCRUD")
    public String roadExcelInput(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse response)
            throws IOException { // 2
        List<Road> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        String deleteList = "";
        String alreadyDeleteList = "";

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            ScriptUtils.alertAndMovePage(response, "파일을 확인 해주세요.", "/excel/insert");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals("로드")) {
                try {
                    if (row.getCell(20).getStringCellValue().equals("use")) {
                        Road road = null;
                        Course course = null;
                        CodeTable codeTable = null;
                        try {
                            try {
                                course = courseService.findCourse(courseService.searchNameCodeTable(row.getCell(1).getStringCellValue()).getCodeValueString());
                            } catch (Exception e) {
                                ScriptUtils.alertAndMovePage(response, "코스 및 구간 정보가 없습니다 확인하세요", "/excel/insert");
                            }
                            codeTable = courseService.searchCodeValue(row.getCell(21).getStringCellValue());
                            codeTable.setCodeName(row.getCell(2).getStringCellValue());
                            codeTableRepository.save(codeTable);

                            try {
                                road = courseService.findRoad(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                            } catch (Exception e) {
                                road = new Road();
                                road.setGuganNm(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                            }
                        } catch (Exception e) {
                            codeTable = new CodeTable();
                            codeTable.setCodeValueString(row.getCell(21).getStringCellValue());//코드값(1C 2C)
                            codeTable.setCodeName((row.getCell(2).getStringCellValue()));//코드이름
                            codeTableRepository.save(codeTable);
                            road = new Road();
                            try {
                                road.setGuganNm(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                            } catch (IllegalStateException ex) {
                                ScriptUtils.alertAndMovePage(response, (i + 1) + "의 id 값이 중복입니다 (고유 번호입니다. 수정하세요)", "/excel/insert");
                            }
                        }
                        try {
                            road.setCourseId(course);
                            road.setGmText(row.getCell(3).getStringCellValue());
                            road.setGmDegree(row.getCell(4).getStringCellValue());
                            road.setGmCourse(row.getCell(5).getStringCellValue());
                            road.setStartPls(row.getCell(6).getStringCellValue());
                            road.setStartAddr(row.getCell(7).getStringCellValue());
                            road.setStart_lat(row.getCell(8).getStringCellValue());
                            road.setStart_long(row.getCell(9).getStringCellValue());
                            road.setMiddlePls(row.getCell(10).getStringCellValue());
                            road.setMiddleAdr(row.getCell(11).getStringCellValue());
                            road.setEndPls(row.getCell(12).getStringCellValue());
                            road.setEndAddr(row.getCell(13).getStringCellValue());
                            road.setEnd_lat(row.getCell(14).getStringCellValue());
                            road.setEnd_long(row.getCell(15).getStringCellValue());
                            road.setRange(row.getCell(16).getStringCellValue());
                            road.setAvgTime(row.getCell(17).getStringCellValue());
                            road.setVisits((long) row.getCell(18).getRowIndex());
                            road.setImg_path(row.getCell(19).getStringCellValue());
                            dataList.add(road);
                        } catch (Exception e) {
                            ScriptUtils.alertAndMovePage(response, (i + 1) + "번째 데이터 중 비어있거나 컬럼에 숫자가 있으니 확인하시고 추가 및 텍스트로 변경해주세요", "/excel/insert");
                        }
                    } else if (row.getCell(20).getStringCellValue().equals("delete")) {
                        try {
                            roadRepository.deleteById(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                            deleteList += (String.valueOf(i) + ", ");
                        } catch (EmptyResultDataAccessException e3) {
                            alreadyDeleteList += (String.valueOf(i) + ", ");
                        }
                    } else {
                        ScriptUtils.alertAndMovePage(response, "flag내용을 use 또는 delete로 설정해주세요", "/excel/insert");
                    }
                } catch (NullPointerException e) {
                    ScriptUtils.alertAndMovePage(response, "사용여부(use/delete)컬럼을 확인하세요", "/excel/insert");
                }
            } else {
                ScriptUtils.alertAndMovePage(response, "파일명 또는 현재 파일의" + i + 1 + "번의 내용을 확인하세요", "/excel/insert");
            }
        }
        if (deleteList != "") {
            ScriptUtils.alert(response, deleteList.substring(0, deleteList.length() - 2) + "번째 내용을 삭제 하겠습니다");
        }
        if (alreadyDeleteList != "") {
            ScriptUtils.alertAndMovePage(response, alreadyDeleteList.substring(0, alreadyDeleteList.length() - 2) + "번째 내용은 이미 삭제되었습니다.", "/excel/insert");
        }

        roadRepository.saveAll(dataList);

        model.addAttribute("datas", dataList); // 5
        return "course/exelList";

    }

    @PostMapping("/excel/toiletExcelCRUD")
    public String toiletExcelInput(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse response)
            throws IOException { // 2
        List<Toilet> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        String deleteList = "";
        String alreadyDeleteList = "";

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            ScriptUtils.alertAndMovePage(response, "파일을 확인해주세요", "/excel/insert");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals("화장실")) {
                try {
                    if (row.getCell(13).getStringCellValue().equals("use")) {
                        Toilet toilet = null;
                        Road road = null;
                        try {
                            try {
                                road = courseService.findRoad(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                            } catch (Exception e) {
                                ScriptUtils.alertAndMovePage(response, "코스 및 구간 정보가 없습니다 확인하세요", "/excel/insert");
                            }
                            toilet = courseService.findRoadConvenient(row.getCell(1).getStringCellValue());
                        } catch (Exception e) {
                            toilet = new Toilet();
                            try {
                                toilet.setName(row.getCell(1).getStringCellValue());
                            } catch (IllegalStateException ex) {
                                ScriptUtils.alertAndMovePage(response, (i + 1) + "의 id 값이 중복입니다 (고유 번호입니다. 수정하세요)", "/excel/insert");
                            }
                        }
                        try {
                            toilet.setGuganNm(road);
                            CellType cellType = row.getCell(3).getCellType();

                            toilet.setAddress_j(row.getCell(3).getStringCellValue());
                            toilet.setAddress_r(row.getCell(4).getStringCellValue());
                            toilet.setLatitude(row.getCell(5).getStringCellValue());
                            toilet.setLongitude(row.getCell(6).getStringCellValue());
                            toilet.setRoadConentcatecory(row.getCell(7).getStringCellValue());
                            toilet.setManager(row.getCell(8).getStringCellValue());
                            toilet.setManager_tel_no(row.getCell(9).getStringCellValue());
                            toilet.setOpenTime(row.getCell(10).getStringCellValue());
                            toilet.setRoad_course(row.getCell(11).getStringCellValue());
                            toilet.setRoad_course_gugan(row.getCell(12).getStringCellValue());

                            dataList.add(toilet);
                        } catch (Exception e) {
                            ScriptUtils.alertAndMovePage(response, (i + 1) + "번째 데이터 중 비어있거나 컬럼에 숫자가 있으니 확인하시고 추가 및 텍스트로 변경해주세요", "/excel/insert");
                        }

                    } else if (row.getCell(13).getStringCellValue().equals("delete")) {
                        try {
                            toiletRepository.deleteById(row.getCell(1).getStringCellValue());
                            deleteList += (String.valueOf(i) + ", ");
                        } catch (EmptyResultDataAccessException e3) {
                            alreadyDeleteList += (String.valueOf(i) + ", ");
                        }
                    } else {
                        ScriptUtils.alertAndMovePage(response, "flag내용을 use 또는 delete로 설정해주세요", "/excel/insert");
                    }
                } catch (NullPointerException e) {
                    ScriptUtils.alertAndMovePage(response, "사용여부(use/delete)컬럼을 확인하세요", "/excel/insert");
                }
                toiletRepository.saveAll(dataList);
            } else {
                ScriptUtils.alertAndMovePage(response, "파일명 또는 현재 파일의" + i + 1 + "번의 내용을 확인하세요", "/excel/insert");
            }
        }
        if (deleteList != "") {
            ScriptUtils.alert(response, deleteList.substring(0, deleteList.length() - 2) + "번째 내용을 삭제 하겠습니다");
        }
        if (alreadyDeleteList != "") {
            ScriptUtils.alertAndMovePage(response, alreadyDeleteList.substring(0, alreadyDeleteList.length() - 2) + "번째 내용은 이미 삭제되었습니다.", "/excel/insert");
        }


        model.addAttribute("datas", dataList); // 5


        return "course/exelList";
    }

    @PostMapping("/excel/hotelExcelCRUD")
    public String HotelExcelInput(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse response)
            throws IOException { // 2
        List<Hotel> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        String deleteList = "";
        String alreadyDeleteList = "";

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            ScriptUtils.alertAndMovePage(response, "파일을 확인해주세요", "/excel/insert");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals("숙박시설")) {
                if (row.getCell(14).getStringCellValue().equals("use")) {
                    Hotel hotel = null;
                    Road road = null;
                    try {
                        try {
                            road = courseService.findRoad(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                        } catch (Exception e) {
                            ScriptUtils.alertAndMovePage(response, "코스 및 구간 정보가 없습니다 확인하세요", "/excel/insert");
                        }
                        hotel = courseService.findHotel(row.getCell(1).getStringCellValue());
                    } catch (Exception e) {
                        hotel = new Hotel();
                        try {
                            hotel.setSeq(row.getCell(1).getStringCellValue());
                        } catch (IllegalStateException ex) {
                            ScriptUtils.alertAndMovePage(response, (i + 1) + "의 id 값이 중복입니다 (고유 번호입니다. 수정하세요)", "/excel/insert");
                        }
                    }
                    try {
                        hotel.setGuganNm(road);
                        hotel.setName(row.getCell(3).getStringCellValue());
                        hotel.setTel(row.getCell(4).getStringCellValue());
                        hotel.setPlace(row.getCell(5).getStringCellValue());
                        hotel.setAddress(row.getCell(6).getStringCellValue());
                        hotel.setLongitude(row.getCell(7).getStringCellValue());
                        hotel.setLatitude(row.getCell(8).getStringCellValue());
                        hotel.setHomepageUrl(row.getCell(9).getStringCellValue());
                        hotel.setHotelConentcatecory(row.getCell(10).getStringCellValue());
                        hotel.setRoad_course(row.getCell(11).getStringCellValue());
                        hotel.setRoad_course_gugan(row.getCell(12).getStringCellValue());
                        hotel.setOpenTime(row.getCell(13).getStringCellValue());
                        dataList.add(hotel);
                    } catch (Exception e) {
                        ScriptUtils.alertAndMovePage(response, (i + 1) + "번째 데이터 중 비어있거나 컬럼에 숫자가 있으니 확인하시고 추가 및 텍스트로 변경해주세요", "/excel/insert");
                    }
                } else if (row.getCell(14).getStringCellValue().equals("delete")) {
                    try {
                        hotelRepository.deleteById(row.getCell(1).getStringCellValue());
                        deleteList += (String.valueOf(i) + ", ");
                    } catch (EmptyResultDataAccessException e3) {
                        alreadyDeleteList += (String.valueOf(i) + ", ");
                    }
                } else {
                    ScriptUtils.alertAndMovePage(response, "flag내용을 use 또는 delete로 설정해주세요", "/excel/insert");
                }
                hotelRepository.saveAll(dataList);
            } else {
                ScriptUtils.alertAndMovePage(response, "파일명 또는 현재 파일의" + i + 1 + "번의 내용을 확인하세요", "/excel/insert");
            }
        }
        if (deleteList != "") {
            ScriptUtils.alert(response, deleteList.substring(0, deleteList.length() - 2) + "번째 내용을 삭제 하겠습니다");
        }
        if (alreadyDeleteList != "") {
            ScriptUtils.alertAndMovePage(response, alreadyDeleteList.substring(0, alreadyDeleteList.length() - 2) + "번째 내용은 이미 삭제되었습니다.", "/excel/insert");
        }
        model.addAttribute("datas", dataList); // 5

        return "course/exelList";
    }

    @PostMapping("/excel/restaurantExcelCRUD")
    public String RestaurantExcelInput(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse response)
            throws IOException { // 2

        List<Restaurant> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        String deleteList = "";
        String alreadyDeleteList = "";

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            ScriptUtils.alertAndMovePage(response, "파일을 확인해주세요", "/excel/insert");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals("레스토랑")) {
                if (row.getCell(15).getStringCellValue().equals("use")) {
                    Road road = new Road();
                    Restaurant restaurant = null;
                    try {
                        try {
                            road = courseService.findRoad(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                        } catch (Exception e) {
                            ScriptUtils.alertAndMovePage(response, "코스 및 구간 정보가 없습니다 확인하세요", "/excel/insert");
                        }
                        restaurant = restaurantRepository.findById(row.getCell(1).getStringCellValue()).get();
                    } catch (Exception e) {
                        restaurant = new Restaurant();
                        try {
                            restaurant.setResid(row.getCell(1).getStringCellValue());
                        } catch (IllegalStateException ex) {
                            ScriptUtils.alertAndMovePage(response, (i + 1) + "의 id 값이 중복입니다 (고유 번호입니다. 수정하세요)", "/excel/insert");
                        }
                    }
                    try {
                        restaurant.setGuganNm(road);
                        restaurant.setName(row.getCell(3).getStringCellValue());
                        restaurant.setRestaurantTitle(row.getCell(4).getStringCellValue());
                        restaurant.setIntroduce(row.getCell(5).getStringCellValue());
                        restaurant.setOpentime(row.getCell(6).getStringCellValue());
                        restaurant.setTel(row.getCell(7).getStringCellValue());
                        restaurant.setAddress(row.getCell(8).getStringCellValue());
                        restaurant.setLatitude(row.getCell(9).getStringCellValue());
                        restaurant.setLongitude(row.getCell(10).getStringCellValue());
                        restaurant.setMainimage(row.getCell(11).getStringCellValue());
                        restaurant.setThumbnail(row.getCell(12).getStringCellValue());
                        restaurant.setRoad_course(row.getCell(13).getStringCellValue());
                        restaurant.setRoad_course_gugan(row.getCell(14).getStringCellValue());
                        dataList.add(restaurant);
                    } catch (Exception e) {
                        ScriptUtils.alertAndMovePage(response, (i + 1) + "번째 데이터 중 비어있거나 컬럼에 숫자가 있으니 확인하시고 추가 및 텍스트로 변경해주세요", "/excel/insert");
                    }
                } else if (row.getCell(15).getStringCellValue().equals("delete")) {
                    try {
//                        restaurantRepository.deleteById(row.getCell(1).getStringCellValue());
                        deleteList += (String.valueOf(i) + ", ");
                    } catch (EmptyResultDataAccessException e3) {
                        alreadyDeleteList += (String.valueOf(i) + ", ");
                    }
                } else {
                    ScriptUtils.alertAndMovePage(response, "flag내용을 use 또는 delete로 설정해주세요", "/excel/insert");
                }
                restaurantRepository.deleteAll();
                restaurantRepository.saveAll(dataList);
            } else {
                ScriptUtils.alertAndMovePage(response, "파일명 또는 현재 파일의" + i + 1 + "번의 내용을 확인하세요", "/excel/insert");
            }
        }
        if (deleteList != "") {
            ScriptUtils.alert(response, deleteList.substring(0, deleteList.length() - 2) + "번째 내용을 삭제 하겠습니다");
        }
        if (alreadyDeleteList != "") {
            ScriptUtils.alertAndMovePage(response, alreadyDeleteList.substring(0, alreadyDeleteList.length() - 2) + "번째 내용은 이미 삭제되었습니다.", "/excel/insert");
        }

        model.addAttribute("datas", dataList); // 5
        return "course/exelList";
    }


    @PostMapping("/excel/touristAttactionExcelCRUD")
    public String TouristAttactionExcelInput(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse response)
            throws IOException { // 2

        List<TouristAttaction> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        String deleteList = "";
        String alreadyDeleteList = "";

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            ScriptUtils.alertAndMovePage(response, "파일을 확인해주세요", "/excel/insert");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals("관광지")) {
                if (row.getCell(14).getStringCellValue().equals("use")) {
                    TouristAttaction touristAttaction = null;
                    Road road = null;
                    try {
                        try {
                            road = courseService.findRoad(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                        } catch (Exception e) {
                            ScriptUtils.alertAndMovePage(response, "코스 및 구간 정보가 없습니다 확인하세요", "/excel/insert");
                        }
                        touristAttaction = courseService.findTouristAttaction(row.getCell(1).getStringCellValue());
                    } catch (Exception e) {
                        touristAttaction = new TouristAttaction();
                        try {
                            touristAttaction.setSeq(row.getCell(1).getStringCellValue());
                        } catch (IllegalStateException ex) {
                            ScriptUtils.alertAndMovePage(response, (i + 1) + "의 id 값이 중복입니다 (고유 번호입니다. 수정하세요)", "/excel/insert");
                        }
                    }
                    try {
                        touristAttaction.setGuganNm(road);
                        touristAttaction.setName(row.getCell(3).getStringCellValue());
                        touristAttaction.setPlace(row.getCell(4).getStringCellValue());
                        touristAttaction.setLatitude(row.getCell(5).getStringCellValue());
                        touristAttaction.setLongtitude(row.getCell(6).getStringCellValue());
                        touristAttaction.setCategory(row.getCell(7).getStringCellValue());
                        touristAttaction.setTitle(row.getCell(8).getStringCellValue());
                        touristAttaction.setMainImage(row.getCell(9).getStringCellValue());
                        touristAttaction.setThumbnail(row.getCell(10).getStringCellValue());
                        touristAttaction.setRoad_course(row.getCell(11).getStringCellValue());
                        touristAttaction.setRoad_course_gugan(row.getCell(12).getStringCellValue());
                        touristAttaction.setText(row.getCell(13).getStringCellValue());
                        dataList.add(touristAttaction);
                    } catch (Exception e) {
                        ScriptUtils.alertAndMovePage(response, (i + 1) + "번째 데이터 중 비어있거나 컬럼에 숫자가 있으니 확인하시고 추가 및 텍스트로 변경해주세요", "/excel/insert");
                    }

                } else if (row.getCell(14).getStringCellValue().equals("delete")) {
                    try {
                        touristAttactionRepository.deleteById(row.getCell(1).getStringCellValue());
                        deleteList += (String.valueOf(i) + ", ");
                    } catch (EmptyResultDataAccessException e3) {
                        alreadyDeleteList += (String.valueOf(i) + ", ");
                    }
                } else {
                    ScriptUtils.alertAndMovePage(response, "flag내용을 use 또는 delete로 설정해주세요", "/excel/insert");
                }
                touristAttactionRepository.saveAll(dataList);
            } else {
                ScriptUtils.alertAndMovePage(response, "파일명 또는 현재 파일의" + i + 1 + "번의 내용을 확인하세요", "/excel/insert");
            }
        }
        if (deleteList != "") {
            ScriptUtils.alert(response, deleteList.substring(0, deleteList.length() - 2) + "번째 내용을 삭제 하겠습니다");
        }
        if (alreadyDeleteList != "") {
            ScriptUtils.alertAndMovePage(response, alreadyDeleteList.substring(0, alreadyDeleteList.length() - 2) + "번째 내용은 이미 삭제되었습니다.", "/excel/insert");
        }

        touristAttactionRepository.saveAll(dataList);
        model.addAttribute("datas", dataList); // 5

        return "course/exelList";
    }

    @PostMapping("/excel/PhotoZoneExcelCRUD")
    public String PhotoZoneExcelInput(@RequestParam("file") MultipartFile file, Model model, HttpServletResponse response)
            throws IOException { // 2

        List<PhotoZone> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3
        String deleteList = "";
        String alreadyDeleteList = "";

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            ScriptUtils.alertAndMovePage(response, "파일을 확인해주세요", "/excel/insert");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            if (row.getCell(0).getStringCellValue().equals("포토존")) {
                if (row.getCell(12).getStringCellValue().equals("use")) {
                    PhotoZone photoZone = null;
                    Road road = null;
                    try {
                        try {
                            road = courseService.findRoad(courseService.searchNameCodeTable(row.getCell(2).getStringCellValue()).getCodeValueString());
                        } catch (Exception e) {
                            ScriptUtils.alertAndMovePage(response, "코스 및 구간 정보가 없습니다 확인하세요", "/excel/insert");
                        }
                        photoZone = courseService.findPhotoZone(row.getCell(1).getStringCellValue());
                    } catch (Exception e) {
                        photoZone = new PhotoZone();
                        try {
                            photoZone.setSeq(row.getCell(1).getStringCellValue());
                        } catch (IllegalStateException ex) {
                            ScriptUtils.alertAndMovePage(response, (i + 1) + "의 id 값이 중복입니다 (고유 번호입니다. 수정하세요)", "/excel/insert");
                        }
                    }
                    try {
                        System.out.println(row.getCell(row.getLastCellNum()));
                        photoZone.setGuganNm(road);
                        photoZone.setName(row.getCell(3).getStringCellValue());
                        photoZone.setPos(row.getCell(4).getStringCellValue());
                        photoZone.setAddress(row.getCell(5).getStringCellValue());
                        photoZone.setLatitude(row.getCell(6).getStringCellValue());
                        photoZone.setLongitude(row.getCell(7).getStringCellValue());
                        photoZone.setRoadConentcatecory(row.getCell(8).getStringCellValue());
                        photoZone.setState(row.getCell(9).getStringCellValue());
                        photoZone.setRoad_course(row.getCell(10).getStringCellValue());
                        photoZone.setRoad_course_gugan(row.getCell(11).getStringCellValue());
                        dataList.add(photoZone);
                    } catch (Exception e) {
                        ScriptUtils.alertAndMovePage(response, (i + 1) + "번째 데이터 중 비어있거나 컬럼에 숫자가 있으니 확인하시고 추가 및 텍스트로 변경해주세요", "/excel/insert");
                    }

                } else if (row.getCell(12).getStringCellValue().equals("delete")) {
                    try {
                        photoZoneRepository.deleteById(row.getCell(1).getStringCellValue());
                        deleteList += (String.valueOf(i) + ", ");
                    } catch (EmptyResultDataAccessException e3) {
                        alreadyDeleteList += (String.valueOf(i) + ", ");
                    }
                } else {
                    ScriptUtils.alertAndMovePage(response, "flag내용을 use 또는 delete로 설정해주세요", "/excel/insert");
                }
                photoZoneRepository.saveAll(dataList);
            } else {
                ScriptUtils.alertAndMovePage(response, "파일명 또는 현재 파일의" + i + 1 + "번의 내용을 확인하세요", "/excel/insert");
            }
        }
        if (deleteList != "") {
            ScriptUtils.alert(response, deleteList.substring(0, deleteList.length() - 2) + "번째 내용을 삭제 하겠습니다");
        }
        if (alreadyDeleteList != "") {
            ScriptUtils.alertAndMovePage(response, alreadyDeleteList.substring(0, alreadyDeleteList.length() - 2) + "번째 내용은 이미 삭제되었습니다.", "/excel/insert");
        }
        photoZoneRepository.saveAll(dataList);
        model.addAttribute("datas", dataList); // 5

        return "course/exelList";
    }


}