package com.rawello.serviceMonitor.controller;

import com.rawello.serviceMonitor.model.response.DockerResponse;
import com.rawello.serviceMonitor.service.DockerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/docker")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DockerController {

    @Inject
    DockerService dockerService;

    @GET
    @Path("/containers")
    public List<String> getContainers() {
        return dockerService.getContainers();
    }

    @POST
    @Path("/start/{id}")
    public DockerResponse startContainer(@PathParam("id") String containerId) {
        dockerService.startContainer(containerId);
        return new DockerResponse(true, "Container " + containerId + " started");
    }

    @POST
    @Path("/stop/{id}")
    public DockerResponse stopContainer(@PathParam("id") String containerId) {
        dockerService.stopContainer(containerId);
        return new DockerResponse(true, "Container " + containerId + " stopped");
    }

    @POST
    @Path("/restart/{id}")
    public DockerResponse restartContainer(@PathParam("id") String containerId) {
        dockerService.restartContainer(containerId);
        return new DockerResponse(true, "Container " + containerId + " restarted");
    }

    @GET
    @Path("/logs/{id}")
    public String getContainerLogs(@PathParam("id") String containerId) {
        return dockerService.getContainerLogs(containerId);
    }
}