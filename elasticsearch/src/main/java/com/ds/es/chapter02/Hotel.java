package com.ds.es.chapter02;

import lombok.Data;

/**
 * @author ds
 * @date 2026/9/17
 * @description
 */
@Data
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    private String starName;
    private String business;
    private String longitude;
    private String latitude;
    private String pic;
}
