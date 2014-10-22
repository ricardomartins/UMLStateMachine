package pt.rikmartins.uml.statemachine.behavioralstates;

import pt.rikmartins.uml.statemachine.*;

import java.util.Set;

/**
 * Created by ricardo on 06-08-2014.
 */
public class SubmachineState extends SpecialBehavioralState implements Stateful, RegionContainer, Vertex.MultipleTransitionSource, Vertex.MultipleTransitionTarget {
    RegionContainerImplementation regionContainer;

    public SubmachineState(Region region, String name) throws UMLStateMachineException {
        super(region, name);

        regionContainer = new RegionContainerImplementation() {
        };
    }

    @Override
    public Set<BehavioralState> getCurrentState() {
        return null;
    }

    @Override
    public Set<BehavioralState> setCurrentState(Set<BehavioralState> behavioralStates) throws UMLStateMachineException {
        return null;
    }

    @Override
    public Set<BehavioralState> deactivate() throws UMLStateMachineException {
        return null;
    }

    @Override
    public boolean isFinalised() {
        return regionContainer.isFinalised();
    }

    @Override
    public Set<Region> getRegions() {
        return regionContainer.getRegions();
    }

    @Override
    public void registerRegion(Region region) throws UMLStateMachineException {
        regionContainer.registerRegion(region);
    }

}
