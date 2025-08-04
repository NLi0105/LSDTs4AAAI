package Builder;

import Visitor.Visitor;

public class WindResource {
    private String windResourceID;
    private double Temperature2M;
    private double SpecificHumidity2M;
    private double RelativeHumidity2M;
    private double Precipitation;
    private double SurfacePressure;
    private double WindSpeed10M;
    private double WindSpeed50M;

    // Getters and setters
    public String getWindResourceID() {
        return windResourceID;
    }

    public void setWindResourceID(String windResourceID) {
        this.windResourceID = windResourceID;
    }

    public double getTemperature2M() {
        return Temperature2M;
    }

    public void setTemperature2M(double Temperature2M) {
        this.Temperature2M = Temperature2M;
    }

    public double getSpecificHumidity2M() {
        return SpecificHumidity2M;
    }

    public void setSpecificHumidity2M(double SpecificHumidity2M) {
        this.SpecificHumidity2M = SpecificHumidity2M;
    }

    public double getRelativeHumidity2M() {
        return RelativeHumidity2M;
    }

    public void setRelativeHumidity2M(double RelativeHumidity2M) {
        this.RelativeHumidity2M = RelativeHumidity2M;
    }

    public double getPrecipitation() {
        return Precipitation;
    }

    public void setPrecipitation(double Precipitation) {
        this.Precipitation = Precipitation;
    }

    public double getSurfacePressure() {
        return SurfacePressure;
    }

    public void setSurfacePressure(double SurfacePressure) {
        this.SurfacePressure = SurfacePressure;
    }

    public double getWindSpeed10M() {
        return WindSpeed10M;
    }

    public void setWindSpeed10M(double WindSpeed10M) {
        this.WindSpeed10M = WindSpeed10M;
    }

    public double getWindSpeed50M() {
        return WindSpeed50M;
    }

    public void setWindSpeed50M(double WindSpeed50M) {
        this.WindSpeed50M = WindSpeed50M;
    }

    // Accept method for visitor pattern
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
