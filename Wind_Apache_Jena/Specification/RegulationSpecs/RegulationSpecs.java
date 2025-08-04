package Specification.RegulationSpecs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RegulationSpecs")
public class RegulationSpecs {
    private String regulationName;
    private String regulationDescription;
    private String regulationType;
    private String severity;
    private String impactAreaURI;
    private String remedy;

    @XmlElement
    public String getRegulationName() {
        return regulationName;
    }

    public void setRegulationName(String regulationName) {
        this.regulationName = regulationName;
    }

    @XmlElement
    public String getRegulationDescription() {
        return regulationDescription;
    }

    public void setRegulationDescription(String regulationDescription) {
        this.regulationDescription = regulationDescription;
    }

    @XmlElement
    public String getRegulationType() {
        return regulationType;
    }

    public void setRegulationType(String regulationType) {
        this.regulationType = regulationType;
    }

    @XmlElement
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @XmlElement
    public String getImpactAreaURI() {
        return impactAreaURI;
    }

    public void setImpactAreaURI(String impactAreaURI) {
        this.impactAreaURI = impactAreaURI;
    }

    @XmlElement
    public String getRemedy() {
        return remedy;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }
}