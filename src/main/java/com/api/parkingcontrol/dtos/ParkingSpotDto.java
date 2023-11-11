package com.api.parkingcontrol.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParkingSpotDto {

    @NotBlank
    @Schema(description = " Parking spot number", example = "5200")
    private String parkingSpotNumber;
    @NotBlank
    @Size(max = 7)
    @Schema(description = "License plate car", example = "QLH8562")
    private String licensePlateCar;
    @NotBlank
    @Schema(description = "Brand car", example = "volkswagen")
    private String brandCar;
    @NotBlank
    @Schema(description = "Model car", example = "fox")
    private String modelCar;
    @NotBlank
    @Schema(description = "Color car", example = "white")
    private String colorCar;
    @NotBlank
    @Schema(description = "Responsible name", example = "José Freitas")
    private String responsibleName;
    @NotBlank
    @Schema(description = "Apartment number", example = "202")
    private String apartment;
    @NotBlank
    @Schema(description = "Block", example = "A")
    private String block;

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
