package pt.rikmartins.uml.statemachine.behavioralstates;

import pt.rikmartins.uml.statemachine.*;

import java.util.Collection;
import java.util.Set;

/**
 * Created by ricardo on 06-08-2014.
 */
public class SubmachineState extends SpecialBehavioralState implements Stateful, Finalisable, RegionContainer {
    RegionContainer regionContainer;

    public SubmachineState(Region region, String name) throws UMLStateMachineException {
        super(region, name);

        regionContainer = new RegionContainerImplementation() {};
    }

    @Override
    public BehavioralStateSet getCurrentState() {
        return null;
    }

    @Override
    public BehavioralStateSet setCurrentState(BehavioralStateSet behavioralStates) throws UMLStateMachineException {
        return null;
    }

    @Override
    public BehavioralStateSet deactivate() throws UMLStateMachineException {
        return null;
    }

    @Override
    public boolean isFinalised() {
        return false;
    }

    @Override
    public Set<Region> getRegions() {
        return regionContainer.getRegions();
    }

    @Override
    public void registerRegions(Region region) {
        regionContainer.registerRegions(region);
    }

    @Override
    public void registerRegions(Collection<Region> regions) {
        regionContainer.registerRegions(regions);
    }
}
