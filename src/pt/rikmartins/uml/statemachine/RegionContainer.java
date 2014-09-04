package pt.rikmartins.uml.statemachine;

import java.util.Collection;
import java.util.Set;

/**
 * Created by ricardo on 04-09-2014.
 */
public interface RegionContainer {
    /**
     * Returns the contained {@link pt.rikmartins.uml.statemachine.Region}s.
     *
     * @return a set with the contained {@link pt.rikmartins.uml.statemachine.Region}s
     */
    Set<Region> getRegions();

    /**
     * Returns the contained {@link pt.rikmartins.uml.statemachine.Region}s.
     *
     * @return a set with the contained {@link pt.rikmartins.uml.statemachine.Region}s
     */
    void registerRegions(Region region); // TODO: Decide whether this should return some value to indicate success

    void registerRegions(Collection<Region> regions);
}
