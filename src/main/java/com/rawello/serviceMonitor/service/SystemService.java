package com.rawello.serviceMonitor.service;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.util.Util;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SystemService {

    private final SystemInfo systemInfo = new SystemInfo();
    private final CentralProcessor processor = systemInfo.getHardware().getProcessor();
    private final GlobalMemory memory = systemInfo.getHardware().getMemory();

    public long getUptime() {
        return systemInfo.getOperatingSystem().getSystemUptime();
    }

    public float getCpuLoad() {
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(1000);
        return (float) (processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100);
    }

    public double getUsedMemory() {
        return memory.getTotal() - memory.getAvailable();
    }

    public double getTotalMemory() {
        return memory.getTotal();
    }

    public double getFreeSpace() {
        long totalMemory = 0;
        long freeMemory = 0;
        for (var fs : systemInfo.getOperatingSystem().getFileSystem().getFileStores()) {
            totalMemory += fs.getTotalSpace();
            freeMemory += fs.getFreeSpace();

            if (totalMemory <= 0)
                continue;
        }
        return (totalMemory - freeMemory) * 100 / totalMemory;
    }
}