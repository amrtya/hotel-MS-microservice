package com.hotelms.backend.roommodule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateRoomDTO {

    @NotBlank()
    private String roomId;
    private String isDeluxe;
    private int noOfDoubleBed;
    private int noOfSingleBed;
    private String category;
}
