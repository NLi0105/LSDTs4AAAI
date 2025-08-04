// Java
package Specification;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "PowerLineSpecsList")
public class PowerLineSpecsList {
    private List<PowerLineSpecs> powerLineSpecs;

    @XmlElement(name = "PowerLineSpecs")
    public List<PowerLineSpecs> getPowerLineSpecs() {
        return powerLineSpecs;
    }

    public void setPowerLineSpecs(List<PowerLineSpecs> powerLineSpecs) {
        this.powerLineSpecs = powerLineSpecs;
    }
}