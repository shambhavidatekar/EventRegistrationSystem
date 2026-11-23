

public class Participant {

    private String participantId;
    private String participantName;

    // Constructor
    public Participant(String participantId, String participantName) {
        this.participantId = participantId;
        this.participantName = participantName;
    }

    // Getter methods
    public String getParticipantId() {
        return participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    // Display participant details
    public void displayParticipant() {
        System.out.println("Participant ID: " + participantId);
        System.out.println("Participant Name: " + participantName);
    }
}
