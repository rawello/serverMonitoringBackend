package com.rawello.serviceMonitor.controller;

import com.aayushatharva.brotli4j.common.annotations.Local;
import com.rawello.serviceMonitor.service.SystemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Locale;

@Path("/system")
@Produces(MediaType.TEXT_PLAIN)
public class SystemController {

    @Inject
    SystemService systemService;

    @GET
    @Path("/uptime")
    public String systemUptime() {
        return String.valueOf(systemService.getUptime());
    }

    @GET
    @Path("/cpu-load")
    public String systemCpuLoad() {
        return String.format(Locale.US, "%.2f", systemService.getCpuLoad());
    }

    @GET
    @Path("/memory")
    public String systemMemory() {
        return String.format(Locale.US, "%.2f / %.2f",
                systemService.getUsedMemory(),
                systemService.getTotalMemory());
    }

    @GET
    @Path("/disk")
    public String systemDisk() {
        return String.format(Locale.US, "%.2f", systemService.getFreeSpace());
    }
}