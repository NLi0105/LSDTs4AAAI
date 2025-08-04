package Specification.LCASpecs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PowerMonitoring extends Task {
    private double powerProduction;

    @XmlElement
    public double getPowerProduction() {
        return powerProduction;
    }

    public void setPowerProduction(double powerProduction) {
        this.powerProduction = powerProduction;
    }
}