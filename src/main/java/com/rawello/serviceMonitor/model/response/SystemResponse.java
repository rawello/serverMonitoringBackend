package com.rawello.serviceMonitor.model.response;

public class SystemResponse {
    public String uptime;
    public float cpuLoad;
    public float memoryUsage;
    public float diskUsage;

    public SystemResponse(String uptime, float cpuLoad, float memoryUsage, float diskUsage) {
        this.uptime = uptime;
        this.cpuLoad = cpuLoad;
        this.memoryUsage = memoryUsage;
        this.diskUsage = diskUsage;
    }
}