package com.hotelms.backend.roommodule.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;

@Component
public class RoomPriceCalculator {

    @Value("${room.baseprice.non_ac_regular}")
    private BigDecimal non_ac_regular;
    @Value("${room.baseprice.non_ac_deluxe}")
    private BigDecimal non_ac_deluxe;
    @Value("${room.baseprice.ac_regular}")
    private BigDecimal ac_regular;
    @Value("${room.baseprice.ac_deluxe}")
    private BigDecimal ac_deluxe;

    @Value("${room.regular_price.double_bed}")
    private double price_double_bed;
    @Value("${room.regular_price.single_bed}")
    private double price_single_bed;

    private HashMap<String, BigDecimal> basePriceByCategory;

    @PostConstruct
    public void init() {
        basePriceByCategory = new HashMap<>();

        basePriceByCategory.put(RoomCategory.NON_AC_REGULAR.name(), non_ac_regular);
        basePriceByCategory.put(RoomCategory.NON_AC_DELUXE.name(), non_ac_deluxe);
        basePriceByCategory.put(RoomCategory.AC_REGULAR.name(), ac_regular);
        basePriceByCategory.put(RoomCategory.AC_DELUXE.name(), ac_deluxe);
    }

    public String calculateRoomPrice(String category, int countDoubleBed, int countSingleBed) {

        BigDecimal basePrice = basePriceByCategory.get(category);

        basePrice = basePrice.add(new BigDecimal(countDoubleBed*price_double_bed));
        basePrice = basePrice.add(new BigDecimal(countSingleBed*price_single_bed));

        return String.valueOf(basePrice);
    }
}
