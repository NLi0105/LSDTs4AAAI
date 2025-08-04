package Builder;

public class WindResourceBuilder {
    private String windResourceID;
    private double Temperature2M;
    private double SpecificHumidity2M;
    private double RelativeHumidity2M;
    private double Precipitation;
    private double SurfacePressure;
    private double WindSpeed10M;
    private double WindSpeed50M;

    // Getters and setters
    public WindResourceBuilder setWindResourceID(String windResourceID) {
        this.windResourceID = windResourceID;
        return this;
    }

    public WindResourceBuilder setTemperature2M(double Temperature2M) {
        this.Temperature2M = Temperature2M;
        return this;
    }

    public WindResourceBuilder setSpecificHumidity2M(double SpecificHumidity2M) {
        this.SpecificHumidity2M = SpecificHumidity2M;
        return this;
    }

    public WindResourceBuilder setRelativeHumidity2M(double RelativeHumidity2M) {
        this.RelativeHumidity2M = RelativeHumidity2M;
        return this;
    }

    public WindResourceBuilder setPrecipitation(double Precipitation) {
        this.Precipitation = Precipitation;
        return this;
    }

    public WindResourceBuilder setSurfacePressure(double SurfacePressure) {
        this.SurfacePressure = SurfacePressure;
        return this;
    }

    public WindResourceBuilder setWindSpeed10M(double WindSpeed10M) {
        this.WindSpeed10M = WindSpeed10M;
        return this;
    }

    public WindResourceBuilder setWindSpeed50M(double WindSpeed50M) {
        this.WindSpeed50M = WindSpeed50M;
        return this;
    }

    public WindResource build() {
        WindResource windResource = new WindResource();
        windResource.setWindResourceID(windResourceID);
        windResource.setTemperature2M(Temperature2M);
        windResource.setSpecificHumidity2M(SpecificHumidity2M);
        windResource.setRelativeHumidity2M(RelativeHumidity2M);
        windResource.setPrecipitation(Precipitation);
        windResource.setSurfacePressure(SurfacePressure);
        windResource.setWindSpeed10M(WindSpeed10M);
        windResource.setWindSpeed50M(WindSpeed50M);

        return windResource;
    }
}
