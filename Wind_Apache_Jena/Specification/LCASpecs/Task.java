package Specification.LCASpecs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({WindAssessment.class,
            ImpactAssessment.class,
            TurbineDesign.class,
            TurbineInstallation.class,
            PowerMonitoring.class,
            Dismantling.class}) // This tells JAXB to consider the WindAssessment class

public class Task {
    private String name;
    private String input;
    private String output;
    private String metric;
    private String startDate;
    private String endDate;
    private double cost;

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @XmlElement
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @XmlElement
    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    @XmlElement
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @XmlElement
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @XmlElement
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}