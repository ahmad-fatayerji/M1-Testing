package fr.univnantes.basicplanner;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

/**
 * TODO
 */
public interface Meeting {

    /**
     * Sets a reminder to the Meeting.
     * 
     * If null, removes the reminder from the Meeting.
     * 
     * @param reminder The reminder duration.
     * @throws IllegalArgumentException if reminder is _not_ null *and* smaller than
     *                                  5 minutes.
     */
    public void setReminder(Duration reminder);

    /**
     * Move the meeting to a new start time.
     * When moved, the duration of the meeting does not change,
     * nor the other attributes of the meeting (title, etc.).
     * 
     * @param newStartTime The new start time.
     * @throws IllegalArgumentException if newStartTime is null.
     */
    public void move(Instant newStartTime);


    /**
     * TODO
     * @return
     */
    public Instant getStartTime();


    /**
     * TODO
     * @return
     */
    public Instant getEndTime();


    /**
     * TODO
     * @return
     */
    public Duration getReminder();

    /**
     * TODO
     * @return
     */
    public String getTitle();

}