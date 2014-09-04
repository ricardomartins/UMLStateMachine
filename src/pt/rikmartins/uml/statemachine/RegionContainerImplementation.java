package pt.rikmartins.uml.statemachine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class RegionContainerImplementation implements Stateful, Finalisable, Event.Receptor, Activatable, RegionContainer {
    private final Set<Region> regions;

    public RegionContainerImplementation(){
        regions = new HashSet<Region>();
    }

    @Override
    public Set<Region> getRegions() {
        return regions;
    }

    @Override
    public void registerRegions(Region region){
        this.regions.add(region);
    }

    @Override
    public void registerRegions(Collection<Region> regions){
        this.regions.addAll(regions);
    }

    @Override
    public BehavioralStateSet receiveEvent(Event event){
        for (Region region: this.regions)
            region.receiveEvent(event);
        return null;
    }

    @Override
    public BehavioralStateSet activate() throws UMLStateMachineException {
        BehavioralStateSet result = new BehavioralStateSet();
        for (Region region: regions) {
            result.addAll(region.activate());
        }
        return result;
    }

    @Override
    public BehavioralStateSet deactivate() throws UMLStateMachineException { // TODO: Deal with forced deactivations of states, or completely discard it
        BehavioralStateSet result = new BehavioralStateSet();
        for (Region region: regions)
            result.addAll(region.deactivate());
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
        for (Region region: regions){
            if (!region.isFinalised()){
                return false;
            }
        }
        return true;
    }

    @Override
    public BehavioralStateSet setCurrentState(BehavioralStateSet behavioralStates) throws UMLStateMachineException {
        throw new UMLStateMachineException("Unimplemented");
    }

    @Override
    public BehavioralStateSet getCurrentState(){
        if (!isActive()) return null;

        BehavioralStateSet result = new BehavioralStateSet();
        for (Region region: getRegions()) {
            result.addAll(region.getCurrentState());
        }
        return result;
    }


    /**
     * Activates the provided {@link pt.rikmartins.uml.statemachine.Vertex}es and returns the current state
     *
     * @param states list of {@link pt.rikmartins.uml.statemachine.Vertex}es to activate
     * @return a set with the current states or null if no state was activated
     */
    BehavioralStateSet activateAllStates(Set<Vertex> states) throws UMLStateMachineException {
        Vertex vertexToActivate = null;
        Set<Vertex> vertexesToForward = new HashSet<Vertex>(states);

        for (Vertex behavioralState: states){
            if (behavioralState.region.equals(this)) {
                if (vertexToActivate == null) {
                    vertexToActivate = behavioralState;
                    vertexesToForward.remove(behavioralState);
                } else {
                    throw new UMLStateMachineException("Trying to activate more than one BehavioralState for this Region");
                }
            }
        }

        if (vertexToActivate != null)
            return vertexToActivate.activate();

        vertexToActivate.activate();
        throw new UMLStateMachineException("BehavioralState does not belong to this Region"); // TODO: Get a place for this
    }
}
//class RegionContainer(object):
//    @property
//    def current_state(self) -> BehavioralStateSet:
//        return BehavioralStateSet(region.current_state for region in self.regions if region.current_state is not None)
