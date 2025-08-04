package Specification.LCASpecs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TurbineDesign extends Task {
    private String turbineModel;

    @XmlElement
    public String getTurbineModel() {
        return turbineModel;
    }

    public void setTurbineModel(String turbineModel) {
        this.turbineModel = turbineModel;
    }
}