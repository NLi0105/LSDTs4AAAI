package SpecVisitor;

import Specification.*;
import Specification.LCASpecs.LifeCycleSpecs;
import Specification.RegulationSpecs.RegulationSpecs;

public interface SpecVisitor {
    void visit(WindFarmSpecs windFarmSpecs);
    void visit(TurbineSpecs turbineSpecs);
    void visit(WindResourceSpecs windResourceSpecs);
    void visit(SubstationSpecs substationSpecs);
    void visit(PowerLineSpecs powerLineSpecs);
    void visit(LifeCycleSpecs lifeCycleSpecs);
    void visit(RegulationSpecs regulationSpecs);
}