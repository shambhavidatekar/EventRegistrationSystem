
import java.time.LocalDate;
import java.util.ArrayList;

public class Event {

    private String eventId;
    private String eventName;
    private LocalDate eventDate;
    private String venue;
    private int capacity;

    // Stores participants registered for this event
    private ArrayList<Participant> participants;

    // Constructor
    public Event(String eventId, String eventName,
                 LocalDate eventDate, String venue, int capacity) {

        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.capacity = capacity;

        participants = new ArrayList<>();
    }

    // Getter methods
    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    // Add participant to event
    public boolean addParticipant(Participant participant) {

        if (participants.size() >= capacity) {
            return false;
        }

        for (Participant p : participants) {
            if (p.getParticipantId().equalsIgnoreCase(
                    participant.getParticipantId())) {
                return false;
            }
        }

        participants.add(participant);
        return true;
    }

    // Cancel participant registration
    public boolean removeParticipant(String participantId) {

        for (Participant p : participants) {

            if (p.getParticipantId().equalsIgnoreCase(participantId)) {
                participants.remove(p);
                return true;
            }
        }

        return false;
    }

    // Display event details
    public void displayEvent() {

        System.out.println("--------------------------------");
        System.out.println("Event ID: " + eventId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Venue: " + venue);
        System.out.println("Capacity: " + capacity);
        System.out.println("Registered Participants: " + participants.size());
        System.out.println("Available Seats: " + (capacity - participants.size()));
        System.out.println("--------------------------------");
    }
}
