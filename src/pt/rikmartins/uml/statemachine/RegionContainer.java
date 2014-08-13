package pt.rikmartins.uml.statemachine;

import pt.rikmartins.uml.statemachine.events.Event;
import pt.rikmartins.uml.statemachine.vertexes.behavioralstates.abstractions.BehavioralState;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ricardo on 04-08-2014.
 */
public abstract class RegionContainer implements Deactivatable, Finalisable, Event.EventReceptor, Stateful {
    private final Set<Region> regions;

    public RegionContainer(){
        regions = new HashSet<Region>();
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void registerRegions(Region region){
        this.regions.add(region);
    }

    public void registerRegions(Collection<Region> regions){
        this.regions.addAll(regions);
    }

    @Override
    public Set<BehavioralState> receiveEvent(Event event){
        for (Region region: this.regions)
            region.receiveEvent(event);
        return null;
    }

    @Override
    public Set<BehavioralState> activate(){ // TODO: Deal with forced activations of states, or completely discard it
        Set<BehavioralState> result = new HashSet<BehavioralState>();
        for (Region region: regions) {
            result.addAll(region.activate());
        }
        return result;
    }

    @Override
    public Set<BehavioralState> deactivate(){ // TODO: Deal with forced deactivations of states, or completely discard it
        Set<BehavioralState> result = new HashSet<BehavioralState>();
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

    public Coiso getCurrentState(){ // TODO: Implement

        return null;
    }

}
//class RegionContainer(object):
//    @property
//    def current_state(self) -> BehavioralStateSet:
//        return BehavioralStateSet(region.current_state for region in self.regions if region.current_state is not None)
