package fr.univnantes.basicplanner.impl;

import fr.univnantes.basicplanner.PlannerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class Exercice1Test {

    //Setting up instants
    Instant instantNow;
    Instant instantPlusOne;
    Instant instantPlusTwo;
    Instant instantPlusThree;
    Instant instantPlusFour;

    //Setting up titles
    String goodTitle;
    String emptyStringTitle;

    //Setting up meetings
    MeetingImpl goodMeeting;
    MeetingImpl meetingOneToFour;
    MeetingImpl meetingOneToTwo;
    MeetingImpl meetingTwoToThree;
    MeetingImpl meetingTwoToFour;
    MeetingImpl meetingOneToThree;
    MeetingImpl meetingThreeToFour;

    //Setting up planners
    BasicPlannerImpl planner;

    @BeforeEach
    public void Setup(){

        //Instantiating instants
        instantNow = Instant.now();
        instantPlusOne = Instant.now().plus(Duration.ofHours(1));
        instantPlusTwo = Instant.now().plus(Duration.ofHours(2));
        instantPlusThree = Instant.now().plus(Duration.ofHours(3));
        instantPlusFour = Instant.now().plus(Duration.ofHours(4));

        //Instantiating titles
        goodTitle = "workingTitle";
        emptyStringTitle = "";

        //Instantiating meetings
        goodMeeting = new MeetingImpl(instantPlusOne,instantPlusTwo,goodTitle);
        meetingOneToThree = new MeetingImpl(instantPlusOne,instantPlusThree,goodTitle);
        meetingTwoToFour = new MeetingImpl(instantPlusTwo,instantPlusFour,goodTitle);
        meetingOneToFour = new MeetingImpl(instantPlusOne,instantPlusFour,goodTitle);
        meetingTwoToThree = new MeetingImpl(instantPlusTwo,instantPlusThree,goodTitle);
        meetingOneToTwo = new MeetingImpl(instantPlusOne,instantPlusTwo,goodTitle);
        meetingThreeToFour = new MeetingImpl(instantPlusThree,instantPlusFour,goodTitle);
        //Instantiating planners
        planner = new BasicPlannerImpl();
    }


    @Nested
    @DisplayName("Test for the MeetingImplConstructor class constructor")
    class MeetingImplConstructorTest {

        @Test
        @DisplayName("La valeur de startTime est null.")
        void startTimeNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new MeetingImpl(null, instantNow,goodTitle);
            });
        }

        @Test
        @DisplayName("La valeur de startTime est supérieure à la valeur de endTime.")
        void startTimeSuperiorToEndTime() {
            assertThrows(IllegalArgumentException.class, () -> {
                new MeetingImpl(instantPlusTwo,instantPlusOne, goodTitle);
            });
        }

        @Test
        @DisplayName("Le titre est une chaîne de caractères vide.")
        void titleIsEmptyString() {
            assertThrows(IllegalArgumentException.class, () -> {
                new MeetingImpl(instantPlusOne,instantPlusTwo, emptyStringTitle);
            });
        }

        @Test
        @DisplayName("Un meeting est créé avec succès.")
        void meetingSucessfullyCreated() {
            assertEquals(goodMeeting.getStartTime(), instantPlusOne);
            assertEquals(goodMeeting.getEndTime(), instantPlusTwo);
            assertEquals(goodMeeting.getTitle(), goodTitle);
        }
    }

    @Nested
    @DisplayName("Test for the BasicPlannelImpl")
    class BasicPlannerImplTest{

        @Test
        @DisplayName("Le nouveau meeting se termine pendant un meeting déjà existant dans l’agenda")
        void newMeetingEndWhileOtherMeetingExists() throws PlannerException {
            planner.addMeeting(meetingOneToFour); // 1 --> 4

            assertThrows(PlannerException.class, () -> {
                planner.addMeeting(meetingTwoToThree); // 2 --> 3
            });
        }

        @Test
        @DisplayName("Le nouveau meeting débute pendant un meeting déjà existant dans l’agenda")
        void newMeetingStartsWhileOtherMeetingExists() throws PlannerException {
            planner.addMeeting(meetingOneToThree); // 1 --> 3

            assertThrows(PlannerException.class, () -> {
                planner.addMeeting(meetingTwoToFour); //2 --> 4
            });
        }

        @Test
        @DisplayName("Le nouveau meeting est ajouté avec succès.")
        void newMeetingSucessfullyAdded() throws PlannerException {

            //Adding two good meetings that don't clash with each other
            planner.addMeeting(meetingOneToTwo);
            planner.addMeeting(meetingThreeToFour);

            assertEquals(2,planner.getAllMeetings().size());
            assertTrue(planner.getAllMeetings().contains(meetingOneToTwo));
            assertTrue(planner.getAllMeetings().contains(meetingThreeToFour));
        }

        @Test
        @DisplayName("On déplace un meeting null")
        void moveNullMeeting() {
            assertThrows(IllegalArgumentException.class, () -> {
                planner.moveMeeting(null,instantNow);
            });
        }

        @Test
        @DisplayName("On déplace un meeting qui n’est pas déjà dans l’agenda")
        void moveMeetingNotInAgenda() {
            assertEquals(0,planner.getAllMeetings().size()); //On check que la liste de meetings est vide
            assertThrows(PlannerException.class, () -> {
                planner.moveMeeting(meetingOneToTwo,instantPlusFour); //On ne peut pas move un meeting qui n'est pas dans le planner
            });
        }

        @Test
        @DisplayName("On déplace un meeting en choisissant une nouvelle heure de début qui tombe pendant un meeting existant")
        void moveMeetingWithNewStartHourThatMatchesExistingMeeting() throws PlannerException {
            planner.addMeeting(meetingOneToTwo);
            planner.addMeeting(meetingTwoToFour);

            assertThrows(PlannerException.class, () -> {
                planner.moveMeeting(meetingOneToTwo,instantPlusTwo); //Becomes two to three
            });
        }

        @Test
        @DisplayName("On déplace un meeting en choisissant une nouvelle heure de fin qui tombe pendant un meeting existant")
        void moveMeetingWithNewEndHourThatMatchesExistingMeeting() throws PlannerException {
            planner.addMeeting(meetingOneToTwo);
            planner.addMeeting(meetingTwoToFour);

            assertThrows(PlannerException.class, () -> {
                planner.moveMeeting(meetingOneToTwo,instantPlusThree); //becomes three to four
            });
        }
    }
}