package fr.univnantes.basicplanner.impl;

import fr.univnantes.basicplanner.PlannerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.configuration.IMockitoConfiguration;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Exercice2Test {

    //Setting up instants
    Instant instantEleven;
    Instant instantElevenThirty;
    Instant instantTwelve;
    Instant instantThirtheen;


    //Setting up titles
    String goodTitle;

    //Setting up meetings
    MeetingImpl meetingElevenToTwelve;


    //Setting up planners
    BasicPlannerImpl planner;

    @BeforeEach
    public void Setup(){

        //Instantiating instants
        instantEleven = LocalDateTime.of(2025,1,1,11,0).toInstant(ZoneOffset.UTC);
        instantElevenThirty = LocalDateTime.of(2025,1,1,11,30).toInstant(ZoneOffset.UTC);
        instantTwelve = LocalDateTime.of(2025,1,1,12,0).toInstant(ZoneOffset.UTC);
        instantThirtheen = LocalDateTime.of(2025,1,1,13,0).toInstant(ZoneOffset.UTC);

        //Instantiating titles
        goodTitle = "workingTitle";

        //Instantiating meetings
        meetingElevenToTwelve = new MeetingImpl(instantEleven,instantTwelve,goodTitle);

    }

    @Test
    @DisplayName("Test seeCurrentMeeting BasicPlannerImpl returns null when no upcoming meetings")
    void TestBasicPlannerImplSeeCurrentMeetingNull() throws PlannerException {
        //it's 13h, the only meeting we had was from 11h till 12h
        SystemClock mockedClock = mock(SystemClock.class);
        when(mockedClock.getCurrentTime()).thenReturn(instantThirtheen);

        planner = new BasicPlannerImpl(mockedClock);
        planner.addMeeting(meetingElevenToTwelve);

        assertNull(planner.seeCurrentMeeting());
    }

    @Test
    @DisplayName("Test seeCurrentMeeting BasicPlannerImpl is good when meeting now")
    void TestBasicPlannerImplSeeCurrentMeetingGood() throws PlannerException {
        SystemClock mockedClock = mock(SystemClock.class);
        when(mockedClock.getCurrentTime()).thenReturn(instantElevenThirty);

        planner = new BasicPlannerImpl(mockedClock);
        planner.addMeeting(meetingElevenToTwelve);

        assertEquals(meetingElevenToTwelve,planner.seeCurrentMeeting());
    }
}