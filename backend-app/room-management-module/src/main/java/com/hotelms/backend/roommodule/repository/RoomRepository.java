package com.hotelms.backend.roommodule.repository;

import com.hotelms.backend.roommodule.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, String> {

    Optional<RoomModel> findByRoomId(String s);
}
