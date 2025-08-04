package Specification;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "TurbineSpecsList")
public class TurbineSpecsList {
    private List<TurbineSpecs> turbineSpecsList;

    @XmlElement(name = "TurbineSpecs")
    public List<TurbineSpecs> getTurbineSpecsList() {
        return turbineSpecsList;
    }

    public void setTurbineSpecsList(List<TurbineSpecs> turbineSpecsList) {
        this.turbineSpecsList = turbineSpecsList;
    }
}