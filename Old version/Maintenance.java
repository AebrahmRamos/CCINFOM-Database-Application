import java.time.LocalDate;

public class Maintenance {
    private int maintenanceID;
    private int roomID;
    private LocalDate scheduleDate;
    private LocalDate completionDate;
    private String status;
    private String notes;

    public Maintenance(int maintenanceID, int roomID, LocalDate scheduleDate, LocalDate completionDate, String status, String notes) {
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

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
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
