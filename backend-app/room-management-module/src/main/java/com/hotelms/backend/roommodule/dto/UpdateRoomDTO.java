package com.hotelms.backend.roommodule.dto;

import lombok.Getter;

@Getter
public class AddRoomDTO {

    private String isDeluxe;
    private int noOfDoubleBed;
    private int noOfSingleBed;
    private String location;
    private String category;
}
