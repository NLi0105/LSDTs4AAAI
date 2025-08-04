package Builder;

import Visitor.Visitor;

public class Geospatial {
    private String geospatialID;
    private double latitude;
    private double longitude;
    private double windSpeed;
    private double windDirection;
    private double windPowerDensity;
    private double elevation;
    private double slope;
    private double aspect;
    private double roughness;
    private String landUse;
    private String protectedAreas;
    private String wildlife;
    private String waterBodies;
    private String soilType;
    private double distanceToGrid;
    private double distanceToSubstation;
    private double distanceToRoad;
    private double distanceToRailway;
    private double populationDensity;
    private double distanceToPopulationCenter;

    // Getters and setters

    public String getGeospatialID() {
        return geospatialID;
    }

    public void setGeospatialID(String geospatialID) {
        this.geospatialID = geospatialID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindPowerDensity() {
        return windPowerDensity;
    }

    public void setWindPowerDensity(double windPowerDensity) {
        this.windPowerDensity = windPowerDensity;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getAspect() {
        return aspect;
    }

    public void setAspect(double aspect) {
        this.aspect = aspect;
    }

    public double getRoughness() {
        return roughness;
    }

    public void setRoughness(double roughness) {
        this.roughness = roughness;
    }

    public String getLandUse() {
        return landUse;
    }

    public void setLandUse(String landUse) {
        this.landUse = landUse;
    }

    public String getProtectedAreas() {
        return protectedAreas;
    }

    public void setProtectedAreas(String protectedAreas) {
        this.protectedAreas = protectedAreas;
    }

    public String getWildlife() {
        return wildlife;
    }

    public void setWildlife(String wildlife) {
        this.wildlife = wildlife;
    }

    public String getWaterBodies() {
        return waterBodies;
    }

    public void setWaterBodies(String waterBodies) {
        this.waterBodies = waterBodies;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public double getDistanceToGrid() {
        return distanceToGrid;
    }

    public void setDistanceToGrid(double distanceToGrid) {
        this.distanceToGrid = distanceToGrid;
    }

    public double getDistanceToSubstation() {
        return distanceToSubstation;
    }

    public void setDistanceToSubstation(double distanceToSubstation) {
        this.distanceToSubstation = distanceToSubstation;
    }

    public double getDistanceToRoad() {
        return distanceToRoad;
    }

    public void setDistanceToRoad(double distanceToRoad) {
        this.distanceToRoad = distanceToRoad;
    }

    public double getDistanceToRailway() {
        return distanceToRailway;
    }

    public void setDistanceToRailway(double distanceToRailway) {
        this.distanceToRailway = distanceToRailway;
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
    }

    public double getDistanceToPopulationCenter() {
        return distanceToPopulationCenter;
    }

    public void setDistanceToPopulationCenter(double distanceToPopulationCenter) {
        this.distanceToPopulationCenter = distanceToPopulationCenter;
    }

    // Accept method for visitor pattern
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}