import java.time.LocalDate;
public class Room {
    private int roomID;
    private String roomName;
    private Boolean isOccupied;
    private LocalDate lastMaintenance;

    public Room(int roomID, String roomName, Boolean isOccupied, LocalDate lastMaintenance) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.isOccupied = isOccupied;
        this.lastMaintenance = lastMaintenance;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }
}
