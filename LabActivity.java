import java.time.LocalDateTime;

public class LabActivity {
    private int labActivityID;
    private int labRequestID;
    private int labStaffID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String activityType;
    private int complexity;
    private String status;

    public LabActivity(int labActivityID, int labRequestID, int labStaffID, LocalDateTime startTime, LocalDateTime endTime, String activityType, int complexity, String status) {
        this.labActivityID = labActivityID;
        this.labRequestID = labRequestID;
        this.labStaffID = labStaffID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.complexity = complexity;
        this.status = status;
    }

    public int getLabActivityID() {
        return labActivityID;
    }

    public void setLabActivityID(int labActivityID) {
        this.labActivityID = labActivityID;
    }

    public int getLabRequestID() {
        return labRequestID;
    }

    public void setLabRequestID(int labRequestID) {
        this.labRequestID = labRequestID;
    }

    public int getLabStaffID() {
        return labStaffID;
    }

    public void setLabStaffID(int labStaffID) {
        this.labStaffID = labStaffID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}