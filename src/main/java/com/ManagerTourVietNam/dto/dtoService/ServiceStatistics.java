package com.ManagerTourVietNam.dto.dtoService;

public class ServiceStatistics {
    private long activeServices;
    private long inactiveServices;
    private long totalServices;

    public ServiceStatistics(long activeServices, long inactiveServices, long totalServices) {
        this.totalServices = totalServices;
        this.activeServices = activeServices;
        this.inactiveServices = inactiveServices;
    }

    public long getActiveServices() {
        return activeServices;
    }

    public void setActiveServices(long activeServices) {
        this.activeServices = activeServices;
    }

    public long getInactiveServices() {
        return inactiveServices;
    }

    public void setInactiveServices(long inactiveServices) {
        this.inactiveServices = inactiveServices;
    }

    public long getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(long totalServices) {
        this.totalServices = totalServices;
    }

    public ServiceStatistics(){

    }

    @Override
    public String toString() {
        return "ServiceStatistics{" +
                "activeServices=" + activeServices +
                ", inactiveServices=" + inactiveServices +
                ", totalServices=" + totalServices +
                '}';
    }
}
