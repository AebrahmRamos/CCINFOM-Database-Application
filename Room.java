import java.time.LocalDate;

public class Room {
    private int roomID;
    private String roomType;
    private boolean isAvailable;
    private LocalDate lastMaintenance;
    private double cost;

    public Room(int roomID, String roomType, boolean isAvailable, LocalDate lastMaintenance, double cost) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.lastMaintenance = lastMaintenance;
        this.cost = cost;
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

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        return "Room [roomID=" + roomID + ", roomType=" + roomType + ", isAvailable=" + isAvailable
                + ", lastMaintenance=" + lastMaintenance + ", cost=" + cost + "]";
    }

    
}