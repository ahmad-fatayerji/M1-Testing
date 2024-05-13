package fr.univnantes.basicplanner.impl;

import java.time.Instant;

import fr.univnantes.basicplanner.Clock;

/**
 * TODO
 */
public class SystemClock implements Clock{

    @Override
    public Instant getCurrentTime() {
        return Instant.now();
    }
    
}
