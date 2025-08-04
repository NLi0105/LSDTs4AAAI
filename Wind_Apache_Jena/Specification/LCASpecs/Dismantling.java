package Specification.LCASpecs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dismantling extends Task {
    private String turbineDismantling;

    @XmlElement
    public String getTurbineDismantling() {
        return turbineDismantling;
    }

    public void setTurbineDismantling(String turbineDismantling) {
        this.turbineDismantling = turbineDismantling;
    }
}