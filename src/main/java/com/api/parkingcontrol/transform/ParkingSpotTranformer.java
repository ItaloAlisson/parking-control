package com.api.parkingcontrol.transform;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.beans.BeanUtils;

public class ParkingSpotTranformer {
    public ParkingSpotModel tranformModel(ParkingSpotDto parkingSpotDto) {
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        return parkingSpotModel;
    }
}
