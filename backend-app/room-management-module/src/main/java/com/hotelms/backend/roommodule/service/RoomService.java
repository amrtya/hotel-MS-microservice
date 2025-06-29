package com.hotelms.backend.roommodule.service;

import com.hotelms.backend.roommodule.dto.AddRoomDTO;
import com.hotelms.backend.roommodule.dto.ResponseDTO;
import com.hotelms.backend.roommodule.exception.BusinessException;
import com.hotelms.backend.roommodule.exception.TechnicalException;
import com.hotelms.backend.roommodule.model.RoomModel;
import com.hotelms.backend.roommodule.repository.RoomRepository;
import com.hotelms.backend.roommodule.util.ResponseType;
import com.hotelms.backend.roommodule.util.RoomPriceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomService.class);
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
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
        Optional<RoomModel> roomById = roomRepository.findByRoomId(roomId);
        if(roomById.isEmpty()) {
            throw new BusinessException("No room exists with this ID");
        }

        try {
            roomRepository.deleteById(roomId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TechnicalException("Error while removing room with ID: " + roomId);
        }

        return new ResponseDTO(ResponseType.SUCCESS.name(), "Room removed successfully");
    }
}
