package Builder;
import Visitor.Visitor;
import org.locationtech.jts.geom.Geometry;

public class Turbine {
    private String TurbineID;
    private String TurbineModel;

    // Based on the DTU 3.4-MW Land-Based reference wind turbine.
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

    // Based on the DTU 10-MW OffShore reference wind turbine.
    private double RotorOrientation;
    private String Control;
    private double RatedWindSpeed;
    private double NumberOfBlades;
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

    // Performance/operational parameters
    private double Inclination;
    private String TurbineStatus;
    private String Availability;
    private double PowerOutput;
    private double CapacityFactor;
    private double DownTime;
    private String TurbineType;
    private double TurbinePositionX;
    private double TurbinePositionY;

    // Geometry
    private Geometry geometry;


    public void setTurbineID(String TurbineID) {
        this.TurbineID = TurbineID;
    }

    public String getTurbineID() {
        return TurbineID;
    }

    public void setTurbineModel(String TurbineModel) {
        this.TurbineModel = TurbineModel;
    }

    public String getTurbineModel() {
        return TurbineModel;
    }

    public void setWindClass(String WindClass) {
        this.WindClass = WindClass;
    }

    public String getWindClass() {
        return WindClass;
    }

    public void setRatedAerodynamicPower(double RatedAerodynamicPower) {
        this.RatedAerodynamicPower = RatedAerodynamicPower;
    }

    public double getRatedAerodynamicPower() {
        return RatedAerodynamicPower;
    }

    public void setHubHeight(double HubHeight) {
        this.HubHeight = HubHeight;
    }

    public double getHubHeight() {
        return HubHeight;
    }

    public void setCutInWindSpeed(double CutInWindSpeed) {
        this.CutInWindSpeed = CutInWindSpeed;
    }

    public double getCutInWindSpeed() {
        return CutInWindSpeed;
    }

    public void setRotorConeAngle(double RotorConeAngle) {
        this.RotorConeAngle = RotorConeAngle;
    }

    public double getRotorConeAngle() {
        return RotorConeAngle;
    }

    public void setRotorSolidity(double RotorSolidity) {
        this.RotorSolidity = RotorSolidity;
    }

    public double getRotorSolidity() {
        return RotorSolidity;
    }

    public void setBladeMass(double BladeMass) {
        this.BladeMass = BladeMass;
    }

    public double getBladeMass() {
        return BladeMass;
    }

    public void setBladeCost(double BladeCost) {
        this.BladeCost = BladeCost;
    }

    public double getBladeCost() {
        return BladeCost;
    }

    public void setAerodynamicAEP(double AerodynamicAEP) {
        this.AerodynamicAEP = AerodynamicAEP;
    }

    public double getAerodynamicAEP() {
        return AerodynamicAEP;
    }

    public void setICC(double ICC) {
        this.ICC = ICC;
    }

    public double getICC() {
        return ICC;
    }

    public void setRatedElectricalPower(double RatedElectricalPower) {
        this.RatedElectricalPower = RatedElectricalPower;
    }

    public double getRatedElectricalPower() {
        return RatedElectricalPower;
    }

    public void setGenEfficiency(double GenEfficiency) {
        this.GenEfficiency = GenEfficiency;
    }

    public double getGenEfficiency() {
        return GenEfficiency;
    }

    public void setRotorDiameter(double RotorDiameter) {
        this.RotorDiameter = RotorDiameter;
    }

    public double getRotorDiameter() {
        return RotorDiameter;
    }

    public void setCutOutWindSpeed(double CutOutWindSpeed) {
        this.CutOutWindSpeed = CutOutWindSpeed;
    }

    public double getCutOutWindSpeed() {
        return CutOutWindSpeed;
    }

    public void setNacelleUptiltAngle(double NacelleUptiltAngle) {
        this.NacelleUptiltAngle = NacelleUptiltAngle;
    }

    public double getNacelleUptiltAngle() {
        return NacelleUptiltAngle;
    }

    public void setMaxVtip(double MaxVtip) {
        this.MaxVtip = MaxVtip;
    }

    public double getMaxVtip() {
        return MaxVtip;
    }

    public void setTowerMass(double TowerMass) {
        this.TowerMass = TowerMass;
    }

    public double getTowerMass() {
        return TowerMass;
    }

    public void setTowerCost(double TowerCost) {
        this.TowerCost = TowerCost;
    }

    public double getTowerCost() {
        return TowerCost;
    }

    public void setElectricalAEP(double ElectricalAEP) {
        this.ElectricalAEP = ElectricalAEP;
    }

    public double getElectricalAEP() {
        return ElectricalAEP;
    }

    public void setCOE(double COE) {
        this.COE = COE;
    }

    public double getCOE() {
        return COE;
    }

    public void setRotorOrientation(double RotorOrientation) {
        this.RotorOrientation = RotorOrientation;
    }

    public double getRotorOrientation() {
        return RotorOrientation;
    }

    public void setControl(String Control) {
        this.Control = Control;
    }

    public String getControl() {
        return Control;
    }

    public void setRatedWindSpeed(double RatedWindSpeed) {
        this.RatedWindSpeed = RatedWindSpeed;
    }

    public double getRatedWindSpeed() {
        return RatedWindSpeed;
    }

    public void setNumberOfBlades(double NumberOfBlades) {
        this.NumberOfBlades = NumberOfBlades;
    }

    public double getNumberOfBlades() {
        return NumberOfBlades;
    }

    public void setAirfoilSeries(String AirfoilSeries) {
        this.AirfoilSeries = AirfoilSeries;
    }

    public String getAirfoilSeries() {
        return AirfoilSeries;
    }

    public void setHubDiameter(double HubDiameter) {
        this.HubDiameter = HubDiameter;
    }

    public double getHubDiameter() {
        return HubDiameter;
    }

    public void setDriveTrain(String DriveTrain) {
        this.DriveTrain = DriveTrain;
    }

    public String getDriveTrain() {
        return DriveTrain;
    }

    public void setMinRotorSpeed(double MinRotorSpeed) {
        this.MinRotorSpeed = MinRotorSpeed;
    }

    public double getMinRotorSpeed() {
        return MinRotorSpeed;
    }

    public void setMaxRotorSpeed(double MaxRotorSpeed) {
        this.MaxRotorSpeed = MaxRotorSpeed;
    }

    public double getMaxRotorSpeed() {
        return MaxRotorSpeed;
    }

    public void setGearboxRatio(double GearboxRatio) {
        this.GearboxRatio = GearboxRatio;
    }

    public double getGearboxRatio() {
        return GearboxRatio;
    }

    public void setHubOverhang(double HubOverhang) {
        this.HubOverhang = HubOverhang;
    }

    public double getHubOverhang() {
        return HubOverhang;
    }

    public void setShaftTiltAngle(double ShaftTiltAngle) {
        this.ShaftTiltAngle = ShaftTiltAngle;
    }

    public double getShaftTiltAngle() {
        return ShaftTiltAngle;
    }

    public void setBladePrebend(double BladePrebend) {
        this.BladePrebend = BladePrebend;
    }

    public double getBladePrebend() {
        return BladePrebend;
    }

    public void setNacelleMass(double NacelleMass) {
        this.NacelleMass = NacelleMass;
    }

    public double getNacelleMass() {
        return NacelleMass;
    }

    public void setInclination(double Inclination) {
        this.Inclination = Inclination;
    }

    public double getInclination() {
        return Inclination;
    }

    public void setTurbineStatus(String TurbineStatus) {
        this.TurbineStatus = TurbineStatus;
    }

    public String getTurbineStatus() {
        return TurbineStatus;
    }

    public void setAvailability(String Availability) {
        this.Availability = Availability;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setPowerOutput(double PowerOutput) {
        this.PowerOutput = PowerOutput;
    }

    public double getPowerOutput() {
        return PowerOutput;
    }

    public void setCapacityFactor(double CapacityFactor) {
        this.CapacityFactor = CapacityFactor;
    }

    public double getCapacityFactor() {
        return CapacityFactor;
    }

    public void setDownTime(double DownTime) {
        this.DownTime = DownTime;
    }

    public double getDownTime() {
        return DownTime;
    }

    public void setTurbineType(String TurbineType) {
        this.TurbineType = TurbineType;
    }

    public String getTurbineType() {
        return TurbineType;
    }

    public void setTurbinePositionX(double TurbinePositionX) {
        this.TurbinePositionX = TurbinePositionX;
    }

    public double getTurbinePositionX() {
        return TurbinePositionX;
    }

    public void setTurbinePositionY(double TurbinePositionY) {
        this.TurbinePositionY = TurbinePositionY;
    }

    public double getTurbinePositionY() {
        return TurbinePositionY;
    }

    // Geometry
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}