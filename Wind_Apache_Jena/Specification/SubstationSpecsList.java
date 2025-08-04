package Specification;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SubstationSpecsList")
public class SubstationSpecsList {
    private List<SubstationSpecs> substationSpecsList;

    @XmlElement(name = "SubstationSpecs")
    public List<SubstationSpecs> getSubstationSpecsList() {
        return substationSpecsList;
    }

    public void setSubstationSpecsList(List<SubstationSpecs> substationSpecsList) {
        this.substationSpecsList = substationSpecsList;
    }
}