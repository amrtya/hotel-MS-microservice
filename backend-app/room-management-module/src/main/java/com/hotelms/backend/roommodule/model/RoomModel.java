package com.hotelms.backend.roommodule.model;

import com.hotelms.backend.roommodule.util.RoomStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "room_master")
public class RoomModel {

    @Id
    @Column(name = "roomid", length = 6)
    @NotBlank
    private String roomId;
    @Column(name = "price", length = 10)
    private String price;
    @Column(name = "status", length = 20)
    @NotBlank
    private String status;
    @Column(name = "category", length = 50)
    @NotBlank
    private String category;
    @Column(name = "deluxe", length = 1)
    private String deluxe;
    @Column(name = "double_bed", length = 2)
    private int double_bed;
    @Column(name = "single_bed", length = 2)
    private int single_bed;


    public RoomModel(String price, String category, String deluxe,
                     int double_bed, int single_bed, String location) {
        this.roomId = "R" + location;
        this.price = price;
        this.status = RoomStatus.AVAILABLE.name();
        this.category = category;
        this.deluxe = deluxe;
        this.double_bed = double_bed;
        this.single_bed = single_bed;
    }
}
