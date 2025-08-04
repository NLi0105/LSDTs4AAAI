package Specification;

import org.locationtech.jts.geom.Geometry;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import Geometry.GeometryAdapter;

@XmlRootElement(name = "SubstationSpecs")
public class SubstationSpecs {
    private String substationID;
    private String type;  // "Offshore" or "Onshore"
    private double capacity;
    private double voltage;
    private double powerFactor;
    private double loadFactor;
    private double lossFactor;
    private double load;
    private double loss;
    private double power;
    private int numOfTransformers;
    private String operationalStatus;
    private String coolingSystem;
    private String protectionSystem;
    private String foundation;
    private Geometry geometry;

    @XmlElement
    public String getSubstationID() { return substationID; }
    public void setSubstationID(String substationID) { this.substationID = substationID; }

    @XmlElement
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @XmlElement
    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    @XmlElement
    public double getVoltage() { return voltage; }
    public void setVoltage(double voltage) { this.voltage = voltage; }

    @XmlElement
    public double getPowerFactor() { return powerFactor; }
    public void setPowerFactor(double powerFactor) { this.powerFactor = powerFactor; }

    @XmlElement
    public double getLoadFactor() { return loadFactor; }
    public void setLoadFactor(double loadFactor) { this.loadFactor = loadFactor; }

    @XmlElement
    public double getLossFactor() { return lossFactor; }
    public void setLossFactor(double lossFactor) { this.lossFactor = lossFactor; }

    @XmlElement
    public double getLoad() { return load; }
    public void setLoad(double load) { this.load = load; }

    @XmlElement
    public double getLoss() { return loss; }
    public void setLoss(double loss) { this.loss = loss; }

    @XmlElement
    public double getPower() { return power; }
    public void setPower(double power) { this.power = power; }

    @XmlElement
    public int getNumOfTransformers() { return numOfTransformers; }
    public void setNumOfTransformers(int numOfTransformers) { this.numOfTransformers = numOfTransformers; }

    @XmlElement
    public String getOperationalStatus() { return operationalStatus; }
    public void setOperationalStatus(String operationalStatus) { this.operationalStatus = operationalStatus; }

    @XmlElement
    public String getCoolingSystem() { return coolingSystem; }
    public void setCoolingSystem(String coolingSystem) { this.coolingSystem = coolingSystem; }

    @XmlElement
    public String getProtectionSystem() { return protectionSystem; }
    public void setProtectionSystem(String protectionSystem) { this.protectionSystem = protectionSystem; }

    @XmlElement
    public String getFoundation() { return foundation; }
    public void setFoundation(String foundation) { this.foundation = foundation; }

    @XmlElement
    @XmlJavaTypeAdapter(GeometryAdapter.class)
    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry geometry) { this.geometry = geometry; }
}