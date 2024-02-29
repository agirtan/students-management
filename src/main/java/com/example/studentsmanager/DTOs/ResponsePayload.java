package com.example.studentsmanager.DTOs;

import lombok.Builder;

@Builder
public record ResponsePayload(Object response, String statusCode) { }