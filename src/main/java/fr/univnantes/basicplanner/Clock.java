package fr.univnantes.basicplanner;

import java.time.Instant;

/**
 * TODO
 */
public interface Clock {

    /**
     * Retrieve the current time.
     * @return The current time.
     */
    public Instant getCurrentTime();
}
