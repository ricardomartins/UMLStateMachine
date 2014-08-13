package pt.rikmartins.uml.statemachine.vertexes.transitionwise;

import pt.rikmartins.uml.statemachine.transitions.BehavioralTransition;

/**
 * Created by ricardo on 06-08-2014.
 */
public interface ElseTransitionSource extends MultipleTransitionSource {
    public BehavioralTransition getElseTransition();
    public void setElseTransition(BehavioralTransition transition);
}
