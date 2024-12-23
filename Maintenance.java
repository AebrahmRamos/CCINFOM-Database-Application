import java.time.LocalDateTime;

public class Maintenance {
    private int maintenanceID;
    private int roomID;
    private LocalDateTime scheduleDate;
    private LocalDateTime completionDate;
    private String status;
    private String notes;

    public Maintenance(int maintenanceID, int roomID, LocalDateTime scheduleDate, LocalDateTime completionDate, String status, String notes) {
        this.maintenanceID = maintenanceID;
        this.roomID = roomID;
        this.scheduleDate = scheduleDate;
        this.completionDate = completionDate;
        this.status = status;
        this.notes = notes;
    }

    public int getMaintenanceID() {
        return maintenanceID;
    }

    public void setMaintenanceID(int maintenanceID) {
        this.maintenanceID = maintenanceID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}