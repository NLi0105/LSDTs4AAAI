package Specification;

import org.locationtech.jts.geom.Geometry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import Geometry.GeometryAdapter;

@XmlRootElement(name = "WindResourceSpecs")
public class WindResourceSpecs {
    private String windResourceID;
    private double Temperature2M;
    private double SpecificHumidity2M;
    private double RelativeHumidity2M;
    private double Precipitation;
    private double SurfacePressure;
    private double WindSpeed10M;
    private double WindSpeed50M;

    private Geometry geometry;

    @XmlElement
    public String getWindResourceID() {
        return windResourceID;
    }

    public void setWindResourceID(String windResourceID) {
        this.windResourceID = windResourceID;
    }

    @XmlElement
    public double getTemperature2M() {
        return Temperature2M;
    }

    public void setTemperature2M(double Temperature2M) {
        this.Temperature2M = Temperature2M;
    }

    @XmlElement
    public double getSpecificHumidity2M() {
        return SpecificHumidity2M;
    }

    public void setSpecificHumidity2M(double SpecificHumidity2M) {
        this.SpecificHumidity2M = SpecificHumidity2M;
    }

    @XmlElement
    public double getRelativeHumidity2M() {
        return RelativeHumidity2M;
    }

    public void setRelativeHumidity2M(double RelativeHumidity2M) {
        this.RelativeHumidity2M = RelativeHumidity2M;
    }

    @XmlElement
    public double getPrecipitation() {
        return Precipitation;
    }

    public void setPrecipitation(double Precipitation) {
        this.Precipitation = Precipitation;
    }

    @XmlElement
    public double getSurfacePressure() {
        return SurfacePressure;
    }

    public void setSurfacePressure(double SurfacePressure) {
        this.SurfacePressure = SurfacePressure;
    }

    @XmlElement
    public double getWindSpeed10M() {
        return WindSpeed10M;
    }

    public void setWindSpeed10M(double WindSpeed10M) {
        this.WindSpeed10M = WindSpeed10M;
    }

    @XmlElement
    public double getWindSpeed50M() {
        return WindSpeed50M;
    }

    public void setWindSpeed50M(double WindSpeed50M) {
        this.WindSpeed50M = WindSpeed50M;
    }

    @XmlElement
    @XmlJavaTypeAdapter(GeometryAdapter.class)
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
