package performancelogger.service;
import com.sun.management.OperatingSystemMXBean;
import performancelogger.model.PerformaceStatistics;
import org.springframework.stereotype.Component;
import java.lang.management.ManagementFactory;


@Component
public class AdminService {

    public PerformaceStatistics getPerformanceStatistics(){

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);
        PerformaceStatistics performaceStatistics = new PerformaceStatistics();
        performaceStatistics.setProcessCpuLoad(osBean.getProcessCpuLoad());
        performaceStatistics.setTotalCpuLoad(osBean.getSystemCpuLoad());
        performaceStatistics.setTotalPhysicalMemorySize(osBean.getTotalPhysicalMemorySize());
        performaceStatistics.setFreePhysicalMemorySize(osBean.getFreePhysicalMemorySize());
        return performaceStatistics;
    }
}
