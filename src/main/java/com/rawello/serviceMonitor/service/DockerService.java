package com.rawello.serviceMonitor.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DockerService {

    @Inject
    DockerClient dockerClient;

    public List<String> getContainers() {
        return dockerClient.listContainersCmd()
                .withShowAll(true)
                .exec()
                .stream()
                .map(container -> String.format(
                        "ID: %s, Names: [%s], Status: %s, Image: %s, State: %s",
                        container.getId(),
                        container.getNames() != null ? String.join(",", container.getNames()) : "Unknown",
                        container.getStatus() != null ? container.getStatus() : "Unknown",
                        container.getImage() != null ? container.getImage() : "Unknown",
                        container.getState() != null ? container.getState() : "Unknown"))
                .collect(Collectors.toList());
    }

    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    public void restartContainer(String containerId) {
        dockerClient.restartContainerCmd(containerId).exec();
    }

    public String getContainerLogs(String containerId) {
        return dockerClient.logContainerCmd(containerId)
                .withStdOut(true)
                .withStdErr(true)
                .withFollowStream(false).toString();
    }
}