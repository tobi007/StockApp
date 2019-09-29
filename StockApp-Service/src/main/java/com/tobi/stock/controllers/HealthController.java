package com.tobi.stock.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/health")
public class HealthController
{
    @GetMapping
    public String healthCheck(){
        return "Okay Boss";
    }
}
