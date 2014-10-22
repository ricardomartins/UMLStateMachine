package pt.rikmartins.uml.statemachine.tools;

import pt.rikmartins.uml.statemachine.BehavioralState;
import pt.rikmartins.uml.statemachine.BehavioralTransition;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by ricardo on 08-10-2014.
 */
public final class BigStructure {
    private final HashSet<BehavioralState> theSetRENAME;
    private final LinkedHashSet<BehavioralTransition> transitionRENAME;

    public BigStructure(){
        theSetRENAME = new HashSet<BehavioralState>();
        transitionRENAME = new LinkedHashSet<BehavioralTransition>();
    }

    public BigStructure(Set<BehavioralState> theSetRENAME, Set<BehavioralTransition> transitionRENAME){
        this();
        this.theSetRENAME.addAll(theSetRENAME);
        this.transitionRENAME.addAll(transitionRENAME);
    }

    public HashSet<BehavioralState> getTheSetRENAME() {
        return new HashSet<BehavioralState>(theSetRENAME);
    }

    public LinkedHashSet<BehavioralTransition> getTransitionRENAME() {
        return new LinkedHashSet<BehavioralTransition>(transitionRENAME);
    }

    public boolean addToTheSetRENAME(BehavioralState behavioralState){
        return theSetRENAME.add(behavioralState);
    }

    public boolean addAllToTheSetRENAME(Set<BehavioralState> behavioralStateSet){
        return theSetRENAME.addAll(behavioralStateSet);
    }

    public boolean addToTransitionRENAME(BehavioralTransition behavioralTransition){
        return transitionRENAME.add(behavioralTransition);
    }

    public boolean addAllToTransitionRENAME(Set<BehavioralTransition> behavioralTransitionSet){
        return transitionRENAME.addAll(behavioralTransitionSet);
    }

    public boolean addAll(BigStructure bigStructure){
        return this.addAllToTheSetRENAME(bigStructure.getTheSetRENAME()) && this.addAllToTransitionRENAME(bigStructure.getTransitionRENAME());
    }
}
