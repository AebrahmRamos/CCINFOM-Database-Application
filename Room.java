import java.time.LocalDate;

public class Room {
    private int roomID;
    private String roomType;
    private boolean isAvailable;
    private LocalDate lastMaintenance;

    public Room(int roomID, String roomType, boolean isAvailable, LocalDate lastMaintenance) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.lastMaintenance = lastMaintenance;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", roomType='" + roomType + '\'' +
                ", isAvailable=" + isAvailable +
                ", lastMaintenance=" + lastMaintenance +
                '}';
    }
}