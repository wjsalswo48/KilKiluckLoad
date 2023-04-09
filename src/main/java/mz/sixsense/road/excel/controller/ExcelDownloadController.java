package mz.sixsense.road.excel.controller;

import mz.sixsense.road.excel.service.RoadExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/")
public class ExcelDownloadController {
 
	@Autowired
    private RoadExcelService roadExcelService;

    @GetMapping("excel/courseDownload")
    public void courseDownload(HttpServletResponse response) throws IOException {
        roadExcelService.CourseDownload(response);
    }
    @GetMapping("excel/roadDownload")
    public void roadDownload(HttpServletResponse response) throws IOException {
        roadExcelService.RoadDownload(response);
    }
    @GetMapping("excel/toiletDownload")
    public void toiletDownload(HttpServletResponse response) throws IOException {
        roadExcelService.RoadConvenientDownload(response);
    }
    @GetMapping("excel/ptozoneDownload")
    public void photoZoneDownload(HttpServletResponse response) throws IOException {
        roadExcelService.PhotoZoneDownload(response);
    }
    @GetMapping("excel/TouristDownload")
    public void TouristDownload(HttpServletResponse response) throws IOException {
        roadExcelService.TouristAttactionDownload(response);
    }
    @GetMapping("excel/HotelDownload")
    public void HotelDownload(HttpServletResponse response) throws IOException {
        roadExcelService.HotelDownload(response);
    }
    @GetMapping("excel/restaurantDownload")
    public void restaurantDownload(HttpServletResponse response) throws IOException {
        roadExcelService.RestaurantDownload(response);
    }
    @GetMapping("excel/downloadDelivery")
    public void downloadDelivery(HttpServletResponse response)throws IOException {
        roadExcelService.deliveryListDownload(response);
    }
}