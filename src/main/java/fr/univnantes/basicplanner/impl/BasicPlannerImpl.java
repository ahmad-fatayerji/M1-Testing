package fr.univnantes.basicplanner.impl;

import java.util.ArrayList;
import java.util.List;

import fr.univnantes.basicplanner.BasicPlanner;
import fr.univnantes.basicplanner.Clock;
import fr.univnantes.basicplanner.Meeting;

/**
 * An implementation of {@link BasicPlanner}.
 */
public class BasicPlannerImpl implements BasicPlanner {

    private List<Meeting> meetings;
    private Clock clock;

    /**
     * Default constructor.
     * 
     */
    public BasicPlannerImpl() {
        this.meetings = new ArrayList<>();
        this.clock = new SystemClock();
    }

    @Override
    public void addMeeting(Meeting newMeeting) throws PlannerException {
        for (Meeting existingMeeting : this.meetings) {
            if ((newMeeting.getStartTime().isAfter(existingMeeting.getStartTime()) &&
                    newMeeting.getStartTime().isBefore(existingMeeting.getEndTime()))
                    ||
                    (newMeeting.getEndTime().isAfter(existingMeeting.getStartTime()) &&
                            newMeeting.getEndTime().isBefore(existingMeeting.getEndTime()))) {
                throw new PlannerException();
            }
        }
        this.meetings.add(newMeeting);
    }

    @Override
    public void removeMeeting(Meeting meeting) {
        this.meetings.remove(meeting);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return List.copyOf(this.meetings);
    }

    @Override
    public Meeting seeCurrentMeeting() {
        for (Meeting existingMeeting : this.meetings) {
            if (clock.getCurrentTime().isAfter(existingMeeting.getStartTime())
                    && clock.getCurrentTime().isBefore(existingMeeting.getEndTime())) {
                return existingMeeting;
            }
        }
        return null;
    }

    @Override
    public List<Meeting> seeActiveReminders() {

        List<Meeting> result = new ArrayList<>();
        for (Meeting existingMeeting : this.meetings) {
            if (clock.getCurrentTime().isBefore(existingMeeting.getStartTime())
                    && clock.getCurrentTime().isAfter(
                            existingMeeting.getStartTime().minus(existingMeeting.getReminderDuration()))) {
                result.add(existingMeeting);
            }
        }
        return result;
    }

}
