package Specification;

import org.locationtech.jts.geom.Geometry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import Geometry.GeometryAdapter;

@XmlRootElement(name = "TurbineSpecs")
public class TurbineSpecs {
    private String TurbineID;
    private String TurbineModel;
    private String WindClass;
    private double RatedAerodynamicPower;
    private double HubHeight;
    private double CutInWindSpeed;
    private double RotorConeAngle;
    private double RotorSolidity;
    private double BladeMass;
    private double BladeCost;
    private double AerodynamicAEP;
    private double ICC;
    private double RatedElectricalPower;
    private double GenEfficiency;
    private double RotorDiameter;
    private double CutOutWindSpeed;
    private double NacelleUptiltAngle;
    private double MaxVtip;
    private double TowerMass;
    private double TowerCost;
    private double ElectricalAEP;
    private double COE;
    private double RotorOrientation;
    private String Control;
    private double RatedWindSpeed;
    private int NumberOfBlades;
    private String AirfoilSeries;
    private double HubDiameter;
    private String DriveTrain;
    private double MinRotorSpeed;
    private double MaxRotorSpeed;
    private double GearboxRatio;
    private double HubOverhang;
    private double ShaftTiltAngle;
    private double BladePrebend;
    private double NacelleMass;
    private double Inclination;
    private String TurbineStatus;
    private String Availability;
    private double PowerOutput;
    private double CapacityFactor;
    private double DownTime;
    private String TurbineType;
    private double TurbinePositionX;
    private double TurbinePositionY;
    private Geometry geometry;

    // For hurricane response
    private double PitchAngle;
    private double YawAngle;

    @XmlElement
    public String getTurbineID() {
        return TurbineID;
    }

    public void setTurbineID(String turbineID) {
        TurbineID = turbineID;
    }

    @XmlElement
    public String getTurbineModel() {
        return TurbineModel;
    }

    public void setTurbineModel(String turbineModel) {
        TurbineModel = turbineModel;
    }

    @XmlElement
    public String getWindClass() {
        return WindClass;
    }

    public void setWindClass(String windClass) {
        WindClass = windClass;
    }

    @XmlElement
    public double getRatedAerodynamicPower() {
        return RatedAerodynamicPower;
    }

    public void setRatedAerodynamicPower(double ratedAerodynamicPower) {
        RatedAerodynamicPower = ratedAerodynamicPower;
    }

    @XmlElement
    public double getHubHeight() {
        return HubHeight;
    }

    public void setHubHeight(double hubHeight) {
        HubHeight = hubHeight;
    }

    @XmlElement
    public double getCutInWindSpeed() {
        return CutInWindSpeed;
    }

    public void setCutInWindSpeed(double cutInWindSpeed) {
        CutInWindSpeed = cutInWindSpeed;
    }

    @XmlElement
    public double getRotorConeAngle() {
        return RotorConeAngle;
    }

    public void setRotorConeAngle(double rotorConeAngle) {
        RotorConeAngle = rotorConeAngle;
    }

    @XmlElement
    public double getRotorSolidity() {
        return RotorSolidity;
    }

    public void setRotorSolidity(double rotorSolidity) {
        RotorSolidity = rotorSolidity;
    }

    @XmlElement
    public double getBladeMass() {
        return BladeMass;
    }

    public void setBladeMass(double bladeMass) {
        BladeMass = bladeMass;
    }

    @XmlElement
    public double getBladeCost() {
        return BladeCost;
    }

    public void setBladeCost(double bladeCost) {
        BladeCost = bladeCost;
    }

    @XmlElement
    public double getAerodynamicAEP() {
        return AerodynamicAEP;
    }

    public void setAerodynamicAEP(double aerodynamicAEP) {
        AerodynamicAEP = aerodynamicAEP;
    }

    @XmlElement
    public double getICC() {
        return ICC;
    }

    public void setICC(double ICC) {
        this.ICC = ICC;
    }

    @XmlElement
    public double getRatedElectricalPower() {
        return RatedElectricalPower;
    }

    public void setRatedElectricalPower(double ratedElectricalPower) {
        RatedElectricalPower = ratedElectricalPower;
    }

    @XmlElement
    public double getGenEfficiency() {
        return GenEfficiency;
    }

    public void setGenEfficiency(double genEfficiency) {
        GenEfficiency = genEfficiency;
    }

    @XmlElement
    public double getRotorDiameter() {
        return RotorDiameter;
    }

    public void setRotorDiameter(double rotorDiameter) {
        RotorDiameter = rotorDiameter;
    }

    @XmlElement
    public double getCutOutWindSpeed() {
        return CutOutWindSpeed;
    }

    public void setCutOutWindSpeed(double cutOutWindSpeed) {
        CutOutWindSpeed = cutOutWindSpeed;
    }

    @XmlElement
    public double getNacelleUptiltAngle() {
        return NacelleUptiltAngle;
    }

    public void setNacelleUptiltAngle(double nacelleUptiltAngle) {
        NacelleUptiltAngle = nacelleUptiltAngle;
    }

    @XmlElement
    public double getMaxVtip() {
        return MaxVtip;
    }

    public void setMaxVtip(double maxVtip) {
        MaxVtip = maxVtip;
    }

    @XmlElement
    public double getTowerMass() {
        return TowerMass;
    }

    public void setTowerMass(double towerMass) {
        TowerMass = towerMass;
    }

    @XmlElement
    public double getTowerCost() {
        return TowerCost;
    }

    public void setTowerCost(double towerCost) {
        TowerCost = towerCost;
    }

    @XmlElement
    public double getElectricalAEP() {
        return ElectricalAEP;
    }

    public void setElectricalAEP(double electricalAEP) {
        ElectricalAEP = electricalAEP;
    }

    @XmlElement
    public double getCOE() {
        return COE;
    }

    public void setCOE(double COE) {
        this.COE = COE;
    }

    @XmlElement
    public double getRotorOrientation() {
        return RotorOrientation;
    }

    public void setRotorOrientation(double rotorOrientation) {
        RotorOrientation = rotorOrientation;
    }

    @XmlElement
    public String getControl() {
        return Control;
    }

    public void setControl(String control) {
        Control = control;
    }

    @XmlElement
    public double getRatedWindSpeed() {
        return RatedWindSpeed;
    }

    public void setRatedWindSpeed(double ratedWindSpeed) {
        RatedWindSpeed = ratedWindSpeed;
    }

    @XmlElement
    public int getNumberOfBlades() {
        return NumberOfBlades;
    }

    public void setNumberOfBlades(int numberOfBlades) {
        NumberOfBlades = numberOfBlades;
    }

    @XmlElement
    public String getAirfoilSeries() {
        return AirfoilSeries;
    }

    public void setAirfoilSeries(String airfoilSeries) {
        AirfoilSeries = airfoilSeries;
    }

    @XmlElement
    public double getHubDiameter() {
        return HubDiameter;
    }

    public void setHubDiameter(double hubDiameter) {
        HubDiameter = hubDiameter;
    }

    @XmlElement
    public String getDriveTrain() {
        return DriveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        DriveTrain = driveTrain;
    }

    @XmlElement
    public double getMinRotorSpeed() {
        return MinRotorSpeed;
    }

    public void setMinRotorSpeed(double minRotorSpeed) {
        MinRotorSpeed = minRotorSpeed;
    }

    @XmlElement
    public double getMaxRotorSpeed() {
        return MaxRotorSpeed;
    }

    public void setMaxRotorSpeed(double maxRotorSpeed) {
        MaxRotorSpeed = maxRotorSpeed;
    }

    @XmlElement
    public double getGearboxRatio() {
        return GearboxRatio;
    }

    public void setGearboxRatio(double gearboxRatio) {
        GearboxRatio = gearboxRatio;
    }

    @XmlElement
    public double getHubOverhang() {
        return HubOverhang;
    }

    public void setHubOverhang(double hubOverhang) {
        HubOverhang = hubOverhang;
    }

    @XmlElement
    public double getShaftTiltAngle() {
        return ShaftTiltAngle;
    }

    public void setShaftTiltAngle(double shaftTiltAngle) {
        ShaftTiltAngle = shaftTiltAngle;
    }

    @XmlElement
    public double getBladePrebend() {
        return BladePrebend;
    }

    public void setBladePrebend(double bladePrebend) {
        BladePrebend = bladePrebend;
    }

    @XmlElement
    public double getNacelleMass() {
        return NacelleMass;
    }

    public void setNacelleMass(double nacelleMass) {
        NacelleMass = nacelleMass;
    }

    @XmlElement
    public double getInclination() {
        return Inclination;
    }

    public void setInclination(double inclination) {
        Inclination = inclination;
    }

    @XmlElement
    public String getTurbineStatus() {
        return TurbineStatus;
    }

    public void setTurbineStatus(String turbineStatus) {
        TurbineStatus = turbineStatus;
    }

    @XmlElement
    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    @XmlElement
    public double getPowerOutput() {
        return PowerOutput;
    }

    public void setPowerOutput(double powerOutput) {
        PowerOutput = powerOutput;
    }

    @XmlElement
    public double getCapacityFactor() {
        return CapacityFactor;
    }

    public void setCapacityFactor(double capacityFactor) {
        CapacityFactor = capacityFactor;
    }

    @XmlElement
    public double getDownTime() {
        return DownTime;
    }

    public void setDownTime(double downTime) {
        DownTime = downTime;
    }

    @XmlElement
    public String getTurbineType() {
        return TurbineType;
    }

    public void setTurbineType(String turbineType) {
        TurbineType = turbineType;
    }

    @XmlElement
    public double getTurbinePositionX() {
        return TurbinePositionX;
    }

    public void setTurbinePositionX(double turbinePositionX) {
        TurbinePositionX = turbinePositionX;
    }

    @XmlElement
    public double getTurbinePositionY() {
        return TurbinePositionY;
    }

    public void setTurbinePositionY(double turbinePositionY) {
        TurbinePositionY = turbinePositionY;
    }

    @XmlElement
    @XmlJavaTypeAdapter(GeometryAdapter.class)
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    // For hurricane response
    @XmlElement
    public double getPitchAngle() {
        return PitchAngle;
    }

    public void setPitchAngle(double pitchAngle) {
        PitchAngle = pitchAngle;
    }

    @XmlElement
    public double getYawAngle() {
        return YawAngle;
    }

    public void setYawAngle(double yawAngle) {
        YawAngle = yawAngle;
    }
}