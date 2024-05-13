package fr.univnantes.basicplanner;

import java.time.Duration;
import java.time.Instant;

/**
 * A meeting stored in a {@link Planner}.
 * 
 */
public interface Meeting {

    /**
     * The start time of the meeting.
     * 
     * @return the start time of the meeting.
     */
    public Instant getStartTime();

    /**
     * The end time of the meeting.
     * 
     * @return the end time of the meeting.
     */
    public Instant getEndTime();

    /**
     * How long before the meeting should a reminder be triggered.
     * 
     * For example, if a meeting is at 12:00 and the reminder duration is 15
     * minutes, then the reminder should be triggered at 11:45.
     * 
     * @return the reminder duration.
     */
    public Duration getReminderDuration();

    /**
     * The meeting title.
     * 
     * @return the meeting title?
     */
    public String getTitle();

    /**
     * Sets a reminder to the meeting.
     * 
     * If null, removes the reminder from the Meeting.
     * 
     * @param reminder The reminder duration.
     * @throws IllegalArgumentException if reminderDuration is _not_ null *and*
     *                                  smaller than 5 minutes.
     */
    public void setReminder(Duration reminderDuration);

    /**
     * Move the meeting to a new start time.
     * When moved, the duration of the meeting does not change,
     * nor the other attributes of the meeting (title, etc.).
     * 
     * @param newStartTime The new start time.
     * @throws IllegalArgumentException if newStartTime is null.
     */
    public void move(Instant newStartTime);

}