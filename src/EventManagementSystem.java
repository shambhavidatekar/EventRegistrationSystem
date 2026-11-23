
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class EventManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Event> events = new ArrayList<>();

    public static void main(String[] args) {

        int choice = 0;

        do {
            System.out.println("\n=================================");
            System.out.println(" EVENT REGISTRATION AND MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Create Event");
            System.out.println("2. Display Upcoming Events");
            System.out.println("3. Register Participant");
            System.out.println("4. Cancel Registration");
            System.out.println("5. Search Participant by ID");
            System.out.println("6. Search Participant by Name");
            System.out.println("7. Display Event-wise Participant List");
            System.out.println("8. Registration Summary");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    createEvent();
                    break;

                case 2:
                    displayUpcomingEvents();
                    break;

                case 3:
                    registerParticipant();
                    break;

                case 4:
                    cancelRegistration();
                    break;

                case 5:
                    searchParticipantById();
                    break;

                case 6:
                    searchParticipantByName();
                    break;

                case 7:
                    displayParticipantList();
                    break;

                case 8:
                    registrationSummary();
                    break;

                case 9:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 9);

        sc.close();
    }

    // 1. Create Event
    public static void createEvent() {

        System.out.print("Enter Event ID: ");
        String id = sc.nextLine();

        if (findEvent(id) != null) {
            System.out.println("Event ID already exists!");
            return;
        }

        System.out.print("Enter Event Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Event Date (YYYY-MM-DD): ");
        LocalDate date;

        try {
            date = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format!");
            return;
        }

        System.out.print("Enter Venue: ");
        String venue = sc.nextLine();

        System.out.print("Enter Event Capacity: ");

        int capacity;

        try {
            capacity = Integer.parseInt(sc.nextLine());

            if (capacity <= 0) {
                System.out.println("Capacity must be greater than zero.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid capacity!");
            return;
        }

        Event event = new Event(id, name, date, venue, capacity);

        events.add(event);

        System.out.println("Event created successfully!");
    }

    // 2. Display Upcoming Events
    public static void displayUpcomingEvents() {

        boolean found = false;

        System.out.println("\n--- UPCOMING EVENTS ---");

        for (Event e : events) {

            if (!e.getEventDate().isBefore(LocalDate.now())) {

                e.displayEvent();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No upcoming events available.");
        }
    }

    // 3. Register Participant
    public static void registerParticipant() {

        System.out.print("Enter Event ID: ");
        String eventId = sc.nextLine();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        if (event.getEventDate().isBefore(LocalDate.now())) {
            System.out.println("Cannot register for a past event.");
            return;
        }

        if (event.getParticipants().size() >= event.getCapacity()) {
            System.out.println("Event is full! Registration not possible.");
            return;
        }

        System.out.print("Enter Participant ID: ");
        String participantId = sc.nextLine();

        for (Participant p : event.getParticipants()) {

            if (p.getParticipantId().equalsIgnoreCase(participantId)) {
                System.out.println("Duplicate registration not allowed!");
                return;
            }
        }

        System.out.print("Enter Participant Name: ");
        String participantName = sc.nextLine();

        Participant participant =
                new Participant(participantId, participantName);

        if (event.addParticipant(participant)) {
            System.out.println("Participant registered successfully!");
        } else {
            System.out.println("Registration failed!");
        }
    }

    // 4. Cancel Registration
    public static void cancelRegistration() {

        System.out.print("Enter Event ID: ");
        String eventId = sc.nextLine();

        Event event = findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        System.out.print("Enter Participant ID: ");
        String participantId = sc.nextLine();

        if (event.removeParticipant(participantId)) {
            System.out.println("Registration cancelled successfully!");
        } else {
            System.out.println("Participant registration not found!");
        }
    }

    // 5. Search Participant by ID
    public static void searchParticipantById() {

        System.out.print("Enter Participant ID: ");
        String id = sc.nextLine();

        boolean found = false;

        for (Event e : events) {

            for (Participant p : e.getParticipants()) {

                if (p.getParticipantId().equalsIgnoreCase(id)) {

                    System.out.println("\nParticipant Found:");
                    p.displayParticipant();
                    System.out.println("Registered Event: " + e.getEventName());

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Participant not found!");
        }
    }

    // 6. Search Participant by Name
    public static void searchParticipantByName() {

        System.out.print("Enter Participant Name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Event e : events) {

            for (Participant p : e.getParticipants()) {

                if (p.getParticipantName().equalsIgnoreCase(name)) {

                    System.out.println("\nParticipant Found:");
                    p.displayParticipant();
                    System.out.println("Registered Event: " + e.getEventName());

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Participant not found!");
        }
    }

    // 7. Display Event-wise Participant List
    public static void displayParticipantList() {

        System.out.print("Enter Event ID: ");
        String id = sc.nextLine();

        Event event = findEvent(id);

        if (event == null) {
            System.out.println("Event not found!");
            return;
        }

        System.out.println("\nParticipants of " + event.getEventName());

        if (event.getParticipants().isEmpty()) {
            System.out.println("No participants registered yet.");
            return;
        }

        for (Participant p : event.getParticipants()) {

            p.displayParticipant();
            System.out.println("--------------------");
        }
    }

    // 8. Registration Summary
    public static void registrationSummary() {

        System.out.println("\n--- REGISTRATION SUMMARY ---");

        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        for (Event e : events) {

            System.out.println("\nEvent Name: " + e.getEventName());
            System.out.println("Event ID: " + e.getEventId());
            System.out.println("Total Capacity: " + e.getCapacity());

            System.out.println("Total Registrations: "
                    + e.getParticipants().size());

            System.out.println("Available Seats: "
                    + (e.getCapacity() - e.getParticipants().size()));
        }
    }

    // Find Event using Event ID
    public static Event findEvent(String id) {

        for (Event e : events) {

            if (e.getEventId().equalsIgnoreCase(id)) {
                return e;
            }
        }

        return null;
    }
}

