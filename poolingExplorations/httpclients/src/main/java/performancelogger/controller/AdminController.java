package performancelogger.controller;

import performancelogger.model.PerformaceStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import performancelogger.service.AdminService;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/performanceStatsGet", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PerformaceStatistics shortcutStore() {
        return adminService.getPerformanceStatistics();
    }
}
