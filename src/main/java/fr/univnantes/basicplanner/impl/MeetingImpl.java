package fr.univnantes.basicplanner.impl;

import java.time.Duration;
import java.time.Instant;

import fr.univnantes.basicplanner.Meeting;

/**
 * TODO
 */
public class MeetingImpl implements Meeting {

    private Instant startTime;
    private Instant endTime;
    private String title;
    private Duration reminder;

    /**
     * Create an MeetingImpl.
     * 
     * @throws IllegalArgumentException If startTime is null, or if endTime is null,
     *                                  or if startTime is before endTime, or
     *                                  startTime equals endTime, or if title is
     *                                  null or empty.
     */
    public MeetingImpl(Instant startTime, Instant endTime, String title) {

        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }

        if (endTime == null) {
            throw new IllegalArgumentException("End time cannot be null");
        }

        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time.");
        }

        if (startTime.equals(endTime)) {
            throw new IllegalArgumentException("Start time cannot be equal to end time.");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }


        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    @Override
    public void move(Instant newStartTime) {
        if (newStartTime == null) {
            throw new IllegalArgumentException("Reminder duration cannot be smaller than 5 minutes.");
        }

    }

    @Override
    public void setReminder(Duration reminder) {
        if (reminder != null) {
            if (reminder.compareTo(Duration.ofMinutes(5)) < 0) {
                throw new IllegalArgumentException("Reminder duration cannot be smaller than 5 minutes.");
            }
            this.reminder = reminder;
        }
    }

    @Override
    public Instant getStartTime() {
        return this.startTime;
    }

    @Override
    public Instant getEndTime() {
        return this.endTime;
    }

    @Override
    public Duration getReminder() {
        return this.reminder;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

}
