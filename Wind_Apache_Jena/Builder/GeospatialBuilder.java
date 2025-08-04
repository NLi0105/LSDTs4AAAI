package Builder;

public class GeospatialBuilder {
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


    public GeospatialBuilder setGeospatialID(String geospatialID) {
        this.geospatialID = geospatialID;
        return this;
    }

    public GeospatialBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public GeospatialBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public GeospatialBuilder setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public GeospatialBuilder setWindDirection(double windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public GeospatialBuilder setWindPowerDensity(double windPowerDensity) {
        this.windPowerDensity = windPowerDensity;
        return this;
    }

    public GeospatialBuilder setElevation(double elevation) {
        this.elevation = elevation;
        return this;
    }

    public GeospatialBuilder setSlope(double slope) {
        this.slope = slope;
        return this;
    }

    public GeospatialBuilder setAspect(double aspect) {
        this.aspect = aspect;
        return this;
    }

    public GeospatialBuilder setRoughness(double roughness) {
        this.roughness = roughness;
        return this;
    }

    public GeospatialBuilder setLandUse(String landUse) {
        this.landUse = landUse;
        return this;
    }

    public GeospatialBuilder setProtectedAreas(String protectedAreas) {
        this.protectedAreas = protectedAreas;
        return this;
    }

    public GeospatialBuilder setWildlife(String wildlife) {
        this.wildlife = wildlife;
        return this;
    }

    public GeospatialBuilder setWaterBodies(String waterBodies) {
        this.waterBodies = waterBodies;
        return this;
    }

    public GeospatialBuilder setSoilType(String soilType) {
        this.soilType = soilType;
        return this;
    }

    public GeospatialBuilder setDistanceToGrid(double distanceToGrid) {
        this.distanceToGrid = distanceToGrid;
        return this;
    }

    public GeospatialBuilder setDistanceToSubstation(double distanceToSubstation) {
        this.distanceToSubstation = distanceToSubstation;
        return this;
    }

    public GeospatialBuilder setDistanceToRoad(double distanceToRoad) {
        this.distanceToRoad = distanceToRoad;
        return this;
    }

    public GeospatialBuilder setDistanceToRailway(double distanceToRailway) {
        this.distanceToRailway = distanceToRailway;
        return this;
    }

    public GeospatialBuilder setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
        return this;
    }

    public GeospatialBuilder setDistanceToPopulationCenter(double distanceToPopulationCenter) {
        this.distanceToPopulationCenter = distanceToPopulationCenter;
        return this;
    }

    public Geospatial build() {
        Geospatial geospatial = new Geospatial();
        geospatial.setGeospatialID(geospatialID);
        geospatial.setLatitude(latitude);
        geospatial.setLongitude(longitude);
        geospatial.setWindSpeed(windSpeed);
        geospatial.setWindDirection(windDirection);
        geospatial.setWindPowerDensity(windPowerDensity);
        geospatial.setElevation(elevation);
        geospatial.setSlope(slope);
        geospatial.setAspect(aspect);
        geospatial.setRoughness(roughness);
        geospatial.setLandUse(landUse);
        geospatial.setProtectedAreas(protectedAreas);
        geospatial.setWildlife(wildlife);
        geospatial.setWaterBodies(waterBodies);
        geospatial.setSoilType(soilType);
        geospatial.setDistanceToGrid(distanceToGrid);
        geospatial.setDistanceToSubstation(distanceToSubstation);
        geospatial.setDistanceToRoad(distanceToRoad);
        geospatial.setDistanceToRailway(distanceToRailway);
        geospatial.setPopulationDensity(populationDensity);
        geospatial.setDistanceToPopulationCenter(distanceToPopulationCenter);
        return geospatial;
    }
}
