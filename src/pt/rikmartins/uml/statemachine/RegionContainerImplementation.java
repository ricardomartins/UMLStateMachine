package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.tools.BigStructure;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class RegionContainerImplementation implements Stateful, Finalisable, Event.EventReceptor, RegionContainer {
    private final Set<Region> regions;

    public RegionContainerImplementation(){
        regions = new HashSet<Region>();
    }

    @Override
    public Set<Region> getRegions() {
        Set<Region> result = new HashSet<Region>(regions);
        return result;
    }

    @Override
    public final void registerRegion(Region region) throws UMLStateMachineException {
        if (regions.contains(region))
            throw new UMLStateMachineException("The region is already registered, nothing new was registered.");
        if (!region.isNameless())
            for (Region regionInRegions: regions)
                if (!regionInRegions.isNameless() && regionInRegions.getName().equals(region.getName()))
                    throw new UMLStateMachineException("There's already one region with that name, nothing new was registered.");

        this.regions.add(region);
    }

    @Override
    public BigStructure receiveEvent(Event event){
        BigStructure result = new BigStructure();
        for (Region region: this.regions)
            result.addAll(region.receiveEvent(event));
        return result;
    }

    @Override
    public BigStructure activate() throws UMLStateMachineException {
        BigStructure result = new BigStructure();
        for (Region region: regions) {
            result.addAll(region.activate());
        }
        return result;
    }

    @Override
    public Set<BehavioralState> deactivate() throws UMLStateMachineException {
        if (!isActive())
            throw new UMLStateMachineException("Trying to deactivate an already inactive " + this.getClass().getName() + ". nothing was changed.");

        Set<BehavioralState> result = new HashSet<BehavioralState>();
        for (Region region: regions)
            try {
                result.addAll(region.deactivate());
            } catch (UMLStateMachineException usmex) {
                // That's ok, the region was already deactivated
            }
        return result;
    }

    @Override
    public boolean isActive(){
        for (Region region: regions){
            if (region.isActive()){
                return true;
            }
        }
        return false;
    }

    public boolean isFinalised(){
        boolean oneRegionFinalised = false;

        for (Region region: regions)
            if (region.isFinalised())
                oneRegionFinalised = true;
            else if (region.isActive())
                return false;

        return oneRegionFinalised;
    }

    @Override
    public Set<BehavioralState> setCurrentState(Set<BehavioralState> behavioralStates) throws UMLStateMachineException {
        assert false : "Unimplemented"; // TODO: Implement or something
        return null;
    }

    @Override
    public Set<BehavioralState> getCurrentState(){
        Set<BehavioralState> result = new HashSet<BehavioralState>();

        for (Region region: getRegions())
            result.addAll(region.getCurrentState());

        return result;
    }


//    /**
//     * Activates the provided {@link pt.rikmartins.uml.statemachine.Vertex}es and returns the current state
//     *
//     * @param states list of {@link pt.rikmartins.uml.statemachine.Vertex}es to activate
//     * @return a set with the current states or null if no state was activated
//     */
//    Set<BehavioralState> activateAllStates(Set<Vertex> states) throws UMLStateMachineException { // TODO: Get rid of this, looks unnecessarily complicated and redundant
//        Vertex vertexToActivate = null;
//        Set<Vertex> vertexesToForward = new HashSet<Vertex>(states);
//
//        for (Vertex behavioralState: states){
//            if (behavioralState.getRegion().equals(this)) {
//                if (vertexToActivate == null) {
//                    vertexToActivate = behavioralState;
//                    vertexesToForward.remove(behavioralState);
//                } else {
//                    throw new UMLStateMachineException("Trying to activate more than one BehavioralState for this Region");
//                }
//            }
//        }
//
//        if (vertexToActivate != null)
//            return vertexToActivate.activate();
//
//        vertexToActivate.activate();
//        throw new UMLStateMachineException("BehavioralState does not belong to this Region"); // TODO: Get a place for this
//    }
}
