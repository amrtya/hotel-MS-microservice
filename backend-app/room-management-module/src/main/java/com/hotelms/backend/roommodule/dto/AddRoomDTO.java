package com.hotelms.backend.roommodule.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddRoomDTO {

    @NotBlank
    private String isDeluxe;
    @NotBlank
    private int noOfDoubleBed;
    @NotBlank
    private int noOfSingleBed;
    @NotBlank
    private String location;
    @NotBlank
    private String category;
}
