package com.hotelms.backend.roommodule.service;

import com.hotelms.backend.roommodule.dto.AddRoomDTO;
import com.hotelms.backend.roommodule.dto.ResponseDTO;
import com.hotelms.backend.roommodule.dto.UpdateRoomDTO;
import com.hotelms.backend.roommodule.exception.BusinessException;
import com.hotelms.backend.roommodule.exception.TechnicalException;
import com.hotelms.backend.roommodule.model.RoomModel;
import com.hotelms.backend.roommodule.repository.RoomRepository;
import com.hotelms.backend.roommodule.util.ResponseType;
import com.hotelms.backend.roommodule.util.RoomPriceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomService.class);
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    @Lazy
    private RoomPriceCalculator roomPriceCalculator;

    public ResponseDTO addRoom(AddRoomDTO request) {
        Optional<RoomModel> roomById = roomRepository.findByRoomId("R" + request.getLocation());

        if(roomById.isPresent()) {
            throw new BusinessException("Room with same location already exists");
        }

        try {
            roomRepository.save(new RoomModel(
                    roomPriceCalculator.calculateRoomPrice(
                            request.getCategory(), request.getNoOfDoubleBed(), request.getNoOfSingleBed()),
                    request.getCategory(),
                    request.getIsDeluxe(),
                    request.getNoOfDoubleBed(),
                    request.getNoOfSingleBed(),
                    request.getLocation()
            ));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TechnicalException("Error while adding room with location: " + request.getLocation());
        }

        return new ResponseDTO(ResponseType.SUCCESS.name(), "Room added successfully");
    }

    public ResponseDTO removeRoom(String roomId) {
        RoomModel roomById = roomRepository.findByRoomId(roomId).orElseThrow(() ->
                new BusinessException("No room exists with this ID")
        );

        try {
            roomRepository.deleteById(roomId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TechnicalException("Error while removing room with ID: " + roomId);
        }

        return new ResponseDTO(ResponseType.SUCCESS.name(), "Room removed successfully");
    }


    public ResponseDTO updateRoomDetails(UpdateRoomDTO updateObject) {
        String roomId = updateObject.getRoomId();

        RoomModel roomById = roomRepository.findByRoomId(roomId).orElseThrow(() ->
            new BusinessException("No room exists with this ID")
        );

        try {

            if(!Objects.equals(roomById.getDeluxe(), updateObject.getIsDeluxe()))
                roomById.setDeluxe(updateObject.getIsDeluxe());
            if(!Objects.equals(roomById.getDouble_bed(), updateObject.getNoOfDoubleBed()))
                roomById.setDouble_bed(updateObject.getNoOfDoubleBed());
            if(!Objects.equals(roomById.getSingle_bed(), updateObject.getNoOfSingleBed()))
                roomById.setSingle_bed(updateObject.getNoOfSingleBed());
            if(!Objects.equals(roomById.getCategory(), updateObject.getCategory()))
                roomById.setCategory(updateObject.getCategory());

            roomById.setPrice(roomPriceCalculator.calculateRoomPrice(
                    roomById.getCategory(), roomById.getDouble_bed(), roomById.getSingle_bed()));

            roomRepository.save(roomById);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TechnicalException("Error while removing room with ID: " + roomId);
        }

        return new ResponseDTO(ResponseType.SUCCESS.name(), "Room details updated successfully");
    }
}
