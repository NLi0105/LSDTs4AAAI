package Specification.LCASpecs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TurbineInstallation extends Task {
    private String installationMethod;

    @XmlElement
    public String getInstallationMethod() {
        return installationMethod;
    }

    public void setInstallationMethod(String installationMethod) {
        this.installationMethod = installationMethod;
    }
}