package com.customer.dataProcessing.dto;


import lombok.Data;

@Data
public class Response {
    long totalTime;
    long numberOfThread;

    public Response(long totalTime, long numberOfThread) {
        this.totalTime = totalTime;
        this.numberOfThread = numberOfThread;
    }
}
