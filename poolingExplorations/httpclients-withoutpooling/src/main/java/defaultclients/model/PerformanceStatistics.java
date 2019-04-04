package defaultclients.model;

public class PerformanceStatistics {

    private double processCpuLoad;

    private double totalCpuLoad;

    private long totalPhysicalMemorySize;

    private long freePhysicalMemorySize;

    public double getProcessCpuLoad() {
        return processCpuLoad;
    }

    public void setProcessCpuLoad(double processCpuLoad) {
        this.processCpuLoad = processCpuLoad;
    }

    public double getTotalCpuLoad() {
        return totalCpuLoad;
    }

    public void setTotalCpuLoad(double totalCpuLoad) {
        this.totalCpuLoad = totalCpuLoad;
    }

    public long getTotalPhysicalMemorySize() {
        return totalPhysicalMemorySize;
    }

    public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize) {
        this.totalPhysicalMemorySize = totalPhysicalMemorySize;
    }

    public long getFreePhysicalMemorySize() {
        return freePhysicalMemorySize;
    }

    public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
        this.freePhysicalMemorySize = freePhysicalMemorySize;
    }
}
