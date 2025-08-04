package Specification.RegulationSpecs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "RegulationSpecsList")
public class RegulationSpecsList {
    private List<RegulationSpecs> regulationSpecsList;

    @XmlElement(name = "RegulationSpecs")
    public List<RegulationSpecs> getRegulationSpecsList() {
        return regulationSpecsList;
    }

    public void setRegulationSpecsList(List<RegulationSpecs> regulationSpecsList) {
        this.regulationSpecsList = regulationSpecsList;
    }
}