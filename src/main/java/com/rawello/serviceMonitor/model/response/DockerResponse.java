package com.rawello.serviceMonitor.model.response;

public class DockerResponse {
    public boolean success;
    public String message;

    public DockerResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}