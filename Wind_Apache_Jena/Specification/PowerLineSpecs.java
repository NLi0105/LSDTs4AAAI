package Specification;

import org.locationtech.jts.geom.Geometry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import Geometry.GeometryAdapter;

import java.util.List;

@XmlRootElement(name = "PowerLineSpecs")
public class PowerLineSpecs {
    private String PowerLineID;
    private double Voltage;
    private double Capacity;
    private String Material;
    private String Status;

    private Geometry geometry;

    @XmlElement
    public String getPowerLineID() {
        return PowerLineID;
    }

    public void setPowerLineID(String powerLineID) {
        PowerLineID = powerLineID;
    }

    @XmlElement
    public double getVoltage() {
        return Voltage;
    }

    public void setVoltage(double voltage) {
        Voltage = voltage;
    }

    @XmlElement
    public double getCapacity() {
        return Capacity;
    }

    public void setCapacity(double capacity) {
        Capacity = capacity;
    }

    @XmlElement
    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    @XmlElement
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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
