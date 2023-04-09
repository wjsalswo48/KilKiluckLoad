package mz.sixsense.road.excel.service;

import mz.sixsense.road.entity.*;
import mz.sixsense.road.service.CourseService;
import mz.sixsense.shop.entity.Delivery;
import mz.sixsense.shop.repository.DeliveryRepository;
import mz.sixsense.shop.service.DeliveryService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class RoadExcelService {
    @Autowired
    CourseService roadService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    DeliveryRepository deliveryRepository;


    public void CourseDownload(HttpServletResponse response) throws IOException {
        String[] roadimpormations = {"파일 식별자","코스 ID", "코스 이미지 경로", "전체 길이", "평균 소요 시간","사용여부(use/delete)","코드값(절대값 건드리지 마시오)"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < roadimpormations.length; i++) {
            cell.setCellValue(roadimpormations[i]);
            cell = row.createCell(cellNum++);
        }


        List<Course> courseList = roadService.getCourseList();
        // Body
        for (Course course : courseList) {
            cellNum = 0;

            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue("코스");

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(course.getCourseID()).getCodeName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(course.getImg_path());

            cell = row.createCell(cellNum++);
            cell.setCellValue(course.getTotal_length());

            cell = row.createCell(cellNum++);
            cell.setCellValue(course.getTotal_time());

            cell = row.createCell(cellNum++);
            cell.setCellValue("use");

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(course.getCourseID()).getCodeValueString());

        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=courseList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    public void RoadDownload(HttpServletResponse response) throws IOException {
        String[] roadimpormations = {"파일 식별자","소속 코스", "구간", "유래", "난이도", "코스", "시작점", "시작점 주소", "시작점 위도", "시작점 경도",
                "중간점", "중간점 주소", "끝점", "끝점 주소", "끝점 위도", "끝점 경도", "평균 길이", "평균 시간", "방문객","사진경로","사용여부(use/delete)","코드값(절대값 건드리지 마시오)"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < roadimpormations.length; i++) {
            cell.setCellValue(roadimpormations[i]);
            cell = row.createCell(cellNum++);
        }


        List<Road> roadList = roadService.getRoadExelList();
        // Body
        for (Road road : roadList) {
            cellNum = 0;

            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue("로드");

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(road.getCourseId().getCourseID()).getCodeName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(road.getGuganNm()).getCodeName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getGmText());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getGmDegree());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getGmCourse());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getStartPls());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getStartAddr());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getStart_lat());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getStart_long());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getMiddlePls());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getMiddleAdr());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getEndPls());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getEndAddr());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getEnd_lat());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getEnd_long());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getRange());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getAvgTime());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getVisits());

            cell = row.createCell(cellNum++);
            cell.setCellValue(road.getImg_path());

            cell = row.createCell(cellNum++);
            cell.setCellValue("use");

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(road.getGuganNm()).getCodeValueString());

        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=roadList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    public void RoadConvenientDownload(HttpServletResponse response) throws IOException {
        String[] RCimpormations = {"파일 식별자", "화장실명", "코스 및 구간","지번", "도로명", "위도", "경도" ,
                "구분", "관리소", "관리소 번호", "운영시간", "코스","구간","사용여부(use/delete)"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < RCimpormations.length; i++) {
            cell.setCellValue(RCimpormations[i]);
            cell = row.createCell(cellNum++);
        }

        List<Toilet> RCList = roadService.getRoadConvenientExelList();
        // Body
        for (Toilet toilet : RCList) {
            cellNum = 0;

            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue("화장실");

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(toilet.getGuganNm().getGuganNm()).getCodeName());


            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getAddress_j());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getAddress_r());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getLatitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getLongitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getRoadConentcatecory());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getManager());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getManager_tel_no());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getOpenTime());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getRoad_course());

            cell = row.createCell(cellNum++);
            cell.setCellValue(toilet.getRoad_course_gugan());

            cell = row.createCell(cellNum++);
            cell.setCellValue("use");

        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=toiletList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    //cell = row.createCell(cellNum++);
//            cell.setCellValue();
    public void TouristAttactionDownload(HttpServletResponse response) throws IOException {
        String[] TAimpormations = {"파일 식별자","seq", "코스 및 구간", "명소이름", "장소", "위도", "경도", "카테고리",
                "타이틀", "메인 이미지 경로", "썸네일 이미지 경로", "코스","구간", "소개글","사용여부(use/delete)"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < TAimpormations.length; i++) {
            cell.setCellValue(TAimpormations[i]);
            cell = row.createCell(cellNum++);
        }

        List<TouristAttaction> TAList = roadService.getTouristAttactionExelList();
        // Body
        for (TouristAttaction touristAttaction : TAList) {
            cellNum = 0;


            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue("관광지");

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getSeq());

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(touristAttaction.getGuganNm().getGuganNm()).getCodeName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getPlace());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getLatitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getLongtitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getCategory());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getTitle());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getMainImage());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getThumbnail());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getRoad_course());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getRoad_course_gugan());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getText());

            cell = row.createCell(cellNum++);
            cell.setCellValue("use");
        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=TouristAttactionList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    //cell = row.createCell(cellNum++);
//            cell.setCellValue();
    public void PhotoZoneDownload(HttpServletResponse response) throws IOException {
        String[] PZimpormations = {"파일 식별자", "seq", "코스 및 구간", "포토존 이름", "위치", "주소", "위도", "경도", "카테고리", "상태", "코스","구간","사용여부(use/delete)"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < PZimpormations.length; i++) {
            cell.setCellValue(PZimpormations[i]);
            cell = row.createCell(cellNum++);
        }

        List<PhotoZone> PZList = roadService.getPhotoZoneExelList();
        // Body
        for (PhotoZone touristAttaction : PZList) {
            cellNum = 0;

            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue("포토존");

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getSeq());

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(touristAttaction.getGuganNm().getGuganNm()).getCodeName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getPos());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getAddress());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getLatitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getLongitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getRoadConentcatecory());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getState());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getRoad_course());

            cell = row.createCell(cellNum++);
            cell.setCellValue(touristAttaction.getRoad_course_gugan());

            cell = row.createCell(cellNum++);
            cell.setCellValue("use");

        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=PhotoZoneList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    //cell = row.createCell(cellNum++);
//            cell.setCellValue();
    public void HotelDownload(HttpServletResponse response) throws IOException {
        String[] HDimpormations = {"파일 식별자", "seq", "코스 및 구간", "호텔명", "전화번호", "위치", "주소",
                "위도", "경도", "url주소", "카테고리","코스","구간","오픈시간","사용여부(use/delete)"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < HDimpormations.length; i++) {
            cell.setCellValue(HDimpormations[i]);
            cell = row.createCell(cellNum++);
        }

        List<Hotel> htlist = roadService.getHotelExelList();
        // Body
        for (Hotel hotel : htlist) {
            cellNum = 0;

            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue("숙박시설");

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getSeq());

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(hotel.getGuganNm().getGuganNm()).getCodeName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getTel());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getPlace());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getAddress());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getLatitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getLongitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getHomepageUrl());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getHotelConentcatecory());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getRoad_course());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getRoad_course_gugan());

            cell = row.createCell(cellNum++);
            cell.setCellValue(hotel.getOpenTime());

            cell = row.createCell(cellNum++);
            cell.setCellValue("use");

        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=HotelList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    public void RestaurantDownload(HttpServletResponse response) throws IOException {
        String[] RDimpormations = {"파일 식별자", "ID" ,"코스 및 구간", "레스토랑명", "타이틀", "소개글", "오픈시간", "전화번호",
                "주소1", "위도", "경도", "메인이미지 주소", "썸네일 이미지 주소","코스","구간", "사용여부(use/delete)","파일 식별자"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < RDimpormations.length; i++) {
            cell.setCellValue(RDimpormations[i]);
            cell = row.createCell(cellNum++);
        }

        List<Restaurant> PZList = roadService.getArroundRestaurantExelList();
        // Body
        for (Restaurant arroundRestaurant : PZList) {
            cellNum = 0;

            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue("레스토랑");

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getResid());

            cell = row.createCell(cellNum++);
            cell.setCellValue(roadService.searchCodeValue(arroundRestaurant.getGuganNm().getGuganNm()).getCodeName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getRestaurantTitle());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getIntroduce());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getOpentime());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getTel());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getAddress());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getLatitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getLongitude());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getMainimage());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getThumbnail());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getRoad_course());

            cell = row.createCell(cellNum++);
            cell.setCellValue(arroundRestaurant.getRoad_course_gugan());

            cell = row.createCell(cellNum++);
            cell.setCellValue("use");

        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=RestaurantList.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    public void deliveryListDownload(HttpServletResponse response) throws IOException {
        String[] RDimpormations = {"번호" ,"주소", "상세주소", "배송상태","주문수량","주문자"};

        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("첫번째 시트");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;

        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        for (int i = 0; i < RDimpormations.length; i++) {
            cell.setCellValue(RDimpormations[i]);
            cell = row.createCell(cellNum++);
        }

        List<Delivery> deliveriesList = deliveryRepository.findAll();
        // Body
        for (Delivery delivery : deliveriesList) {
            cellNum = 0;
            row = sheet.createRow(rowNum++);

            cell = row.createCell(cellNum++);
            cell.setCellValue(delivery.getDeliveryID());

            cell = row.createCell(cellNum++);
            cell.setCellValue(delivery.getAddress());

            cell = row.createCell(cellNum++);
            cell.setCellValue(delivery.getAddressDetail());

            cell = row.createCell(cellNum++);
            cell.setCellValue(delivery.getDeliveryStatus().toString());

            cell = row.createCell(cellNum++);
            cell.setCellValue(delivery.getOrderAmount());

            cell = row.createCell(cellNum++);
            cell.setCellValue(delivery.getMemberID().getMemberID());
        }

        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=deliveryOrder.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }
}
