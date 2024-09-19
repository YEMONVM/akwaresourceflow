package com.akwaresourceflow.dto;

import lombok.Value;

@Value
public class StationDTO {
    Long id;
    String name;
    String address;
    String city;
    String phonenumber;
}