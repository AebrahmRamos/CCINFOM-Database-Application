public class LabStaff {
    private int staffID;
    private int laboratoryID;
    private String name;
    private String role;
    private String shift;

    public LabStaff(int staffID, int laboratoryID, String name, String role, String shift) {
        this.staffID = staffID;
        this.laboratoryID = laboratoryID;
        this.name = name;
        this.role = role;
        this.shift = shift;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getLaboratoryID() {
        return laboratoryID;
    }

    public void setLaboratoryID(int laboratoryID) {
        this.laboratoryID = laboratoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}