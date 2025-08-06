package com.hotelms.backend.roommodule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "room_status_logs")
public class RoomStatusModel {

    @Id
    @Column(name = "id", length = 15)
    private String id;
    @Column(name = "roomid", length = 6)
    private String roomId;
    @Column(name = "status_from", length = 10)
    private String statusFrom;
    @Column(name = "status_to", length = 10)
    private String statusTo;
    @Column(name = "changed_by", length = 50)
    private String changedBy;
    @Column(name = "timestamp", length = 10)
    private String timestamp;
    @Column(name = "notes", length = 100)
    private int notes;


}
