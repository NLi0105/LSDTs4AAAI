package Builder;

import org.locationtech.jts.geom.Geometry;

public class TurbineBuilder {
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


    public TurbineBuilder setTurbineID(String TurbineID) {
        this.TurbineID = TurbineID;
        return this;
    }

    public TurbineBuilder setTurbineModel(String TurbineModel) {
        this.TurbineModel = TurbineModel;
        return this;
    }

    public TurbineBuilder setWindClass(String WindClass) {
        this.WindClass = WindClass;
        return this;
    }

    public TurbineBuilder setRatedAerodynamicPower(double RatedAerodynamicPower) {
        this.RatedAerodynamicPower = RatedAerodynamicPower;
        return this;
    }

    public TurbineBuilder setHubHeight(double HubHeight) {
        this.HubHeight = HubHeight;
        return this;
    }

    public TurbineBuilder setCutInWindSpeed(double CutInWindSpeed) {
        this.CutInWindSpeed = CutInWindSpeed;
        return this;
    }

    public TurbineBuilder setRotorConeAngle(double RotorConeAngle) {
        this.RotorConeAngle = RotorConeAngle;
        return this;
    }

    public TurbineBuilder setRotorSolidity(double RotorSolidity) {
        this.RotorSolidity = RotorSolidity;
        return this;
    }

    public TurbineBuilder setBladeMass(double BladeMass) {
        this.BladeMass = BladeMass;
        return this;
    }

    public TurbineBuilder setBladeCost(double BladeCost) {
        this.BladeCost = BladeCost;
        return this;
    }

    public TurbineBuilder setAerodynamicAEP(double AerodynamicAEP) {
        this.AerodynamicAEP = AerodynamicAEP;
        return this;
    }

    public TurbineBuilder setICC(double ICC) {
        this.ICC = ICC;
        return this;
    }

    public TurbineBuilder setRatedElectricalPower(double RatedElectricalPower) {
        this.RatedElectricalPower = RatedElectricalPower;
        return this;
    }

    public TurbineBuilder setGenEfficiency(double GenEfficiency) {
        this.GenEfficiency = GenEfficiency;
        return this;
    }

    public TurbineBuilder setRotorDiameter(double RotorDiameter) {
        this.RotorDiameter = RotorDiameter;
        return this;
    }

    public TurbineBuilder setCutOutWindSpeed(double CutOutWindSpeed) {
        this.CutOutWindSpeed = CutOutWindSpeed;
        return this;
    }

    public TurbineBuilder setNacelleUptiltAngle(double NacelleUptiltAngle) {
        this.NacelleUptiltAngle = NacelleUptiltAngle;
        return this;
    }

    public TurbineBuilder setMaxVtip(double MaxVtip) {
        this.MaxVtip = MaxVtip;
        return this;
    }

    public TurbineBuilder setTowerMass(double TowerMass) {
        this.TowerMass = TowerMass;
        return this;
    }

    public TurbineBuilder setTowerCost(double TowerCost) {
        this.TowerCost = TowerCost;
        return this;
    }

    public TurbineBuilder setElectricalAEP(double ElectricalAEP) {
        this.ElectricalAEP = ElectricalAEP;
        return this;
    }

    public TurbineBuilder setCOE(double COE) {
        this.COE = COE;
        return this;
    }

    public TurbineBuilder setRotorOrientation(double RotorOrientation) {
        this.RotorOrientation = RotorOrientation;
        return this;
    }

    public TurbineBuilder setControl(String Control) {
        this.Control = Control;
        return this;
    }

    public TurbineBuilder setRatedWindSpeed(double RatedWindSpeed) {
        this.RatedWindSpeed = RatedWindSpeed;
        return this;
    }

    public TurbineBuilder setNumberOfBlades(double NumberOfBlades) {
        this.NumberOfBlades = NumberOfBlades;
        return this;
    }

    public TurbineBuilder setAirfoilSeries(String AirfoilSeries) {
        this.AirfoilSeries = AirfoilSeries;
        return this;
    }

    public TurbineBuilder setHubDiameter(double HubDiameter) {
        this.HubDiameter = HubDiameter;
        return this;
    }

    public TurbineBuilder setDriveTrain(String DriveTrain) {
        this.DriveTrain = DriveTrain;
        return this;
    }

    public TurbineBuilder setMinRotorSpeed(double MinRotorSpeed) {
        this.MinRotorSpeed = MinRotorSpeed;
        return this;
    }

    public TurbineBuilder setMaxRotorSpeed(double MaxRotorSpeed) {
        this.MaxRotorSpeed = MaxRotorSpeed;
        return this;
    }

    public TurbineBuilder setGearboxRatio(double GearboxRatio) {
        this.GearboxRatio = GearboxRatio;
        return this;
    }

    public TurbineBuilder setHubOverhang(double HubOverhang) {
        this.HubOverhang = HubOverhang;
        return this;
    }

    public TurbineBuilder setShaftTiltAngle(double ShaftTiltAngle) {
        this.ShaftTiltAngle = ShaftTiltAngle;
        return this;
    }

    public TurbineBuilder setBladePrebend(double BladePrebend) {
        this.BladePrebend = BladePrebend;
        return this;
    }

    public TurbineBuilder setNacelleMass(double NacelleMass) {
        this.NacelleMass = NacelleMass;
        return this;
    }

    public TurbineBuilder setInclination(double Inclination) {
        this.Inclination = Inclination;
        return this;
    }

    public TurbineBuilder setTurbineStatus(String TurbineStatus) {
        this.TurbineStatus = TurbineStatus;
        return this;
    }

    public TurbineBuilder setAvailability(String Availability) {
        this.Availability = Availability;
        return this;
    }

    public TurbineBuilder setPowerOutput(double PowerOutput) {
        this.PowerOutput = PowerOutput;
        return this;
    }

    public TurbineBuilder setCapacityFactor(double CapacityFactor) {
        this.CapacityFactor = CapacityFactor;
        return this;
    }

    public TurbineBuilder setDownTime(double DownTime) {
        this.DownTime = DownTime;
        return this;
    }

    public TurbineBuilder setTurbineType(String TurbineType) {
        this.TurbineType = TurbineType;
        return this;
    }

    public TurbineBuilder setTurbinePositionX(double TurbinePositionX) {
        this.TurbinePositionX = TurbinePositionX;
        return this;
    }

    public TurbineBuilder setTurbinePositionY(double TurbinePositionY) {
        this.TurbinePositionY = TurbinePositionY;
        return this;
    }

    // Geometry
    public TurbineBuilder setGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }


    public Turbine build() {
        Turbine turbine = new Turbine();
        turbine.setTurbineID(TurbineID);
        turbine.setTurbineModel(TurbineModel);
        turbine.setWindClass(WindClass);
        turbine.setRatedAerodynamicPower(RatedAerodynamicPower);
        turbine.setHubHeight(HubHeight);
        turbine.setCutInWindSpeed(CutInWindSpeed);
        turbine.setRotorConeAngle(RotorConeAngle);
        turbine.setRotorSolidity(RotorSolidity);
        turbine.setBladeMass(BladeMass);
        turbine.setBladeCost(BladeCost);
        turbine.setAerodynamicAEP(AerodynamicAEP);
        turbine.setICC(ICC);
        turbine.setRatedElectricalPower(RatedElectricalPower);
        turbine.setGenEfficiency(GenEfficiency);
        turbine.setRotorDiameter(RotorDiameter);
        turbine.setCutOutWindSpeed(CutOutWindSpeed);
        turbine.setNacelleUptiltAngle(NacelleUptiltAngle);
        turbine.setMaxVtip(MaxVtip);
        turbine.setTowerMass(TowerMass);
        turbine.setTowerCost(TowerCost);
        turbine.setElectricalAEP(ElectricalAEP);
        turbine.setCOE(COE);
        turbine.setRotorOrientation(RotorOrientation);
        turbine.setControl(Control);
        turbine.setRatedWindSpeed(RatedWindSpeed);
        turbine.setNumberOfBlades(NumberOfBlades);
        turbine.setAirfoilSeries(AirfoilSeries);
        turbine.setHubDiameter(HubDiameter);
        turbine.setDriveTrain(DriveTrain);
        turbine.setMinRotorSpeed(MinRotorSpeed);
        turbine.setMaxRotorSpeed(MaxRotorSpeed);
        turbine.setGearboxRatio(GearboxRatio);
        turbine.setHubOverhang(HubOverhang);
        turbine.setShaftTiltAngle(ShaftTiltAngle);
        turbine.setBladePrebend(BladePrebend);
        turbine.setNacelleMass(NacelleMass);
        turbine.setInclination(Inclination);
        turbine.setTurbineStatus(TurbineStatus);
        turbine.setAvailability(Availability);
        turbine.setPowerOutput(PowerOutput);
        turbine.setCapacityFactor(CapacityFactor);
        turbine.setDownTime(DownTime);
        turbine.setTurbineType(TurbineType);
        turbine.setTurbinePositionX(TurbinePositionX);
        turbine.setTurbinePositionY(TurbinePositionY);

        // Geometry
        turbine.setGeometry(geometry);


        return turbine;
    }
}
