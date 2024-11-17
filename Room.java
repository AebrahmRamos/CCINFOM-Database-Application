import java.time.LocalDate;

public class Room {
    private int roomID;
    private String roomType;
    private Boolean isAvailable;
    private LocalDate lastMaintenanceDate;

    public Room (int roomID, String roomType, Boolean isAvailable, LocalDate lastMaintenanceDate) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.lastMaintenanceDate = lastMaintenanceDate;
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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}