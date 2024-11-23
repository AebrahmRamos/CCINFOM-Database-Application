public class Laboratory {
    private int laboratoryID;
    private String laboratoryName;
    private String contactNumber;

    public Laboratory(int laboratoryID, String laboratoryName, String contactNumber) {
        this.laboratoryID = laboratoryID;
        this.laboratoryName = laboratoryName;
        this.contactNumber = contactNumber;
    }

    public int getLaboratoryID() {
        return laboratoryID;
    }

    public void setLaboratoryID(int laboratoryID) {
        this.laboratoryID = laboratoryID;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Laboratory [laboratoryID=" + laboratoryID + ", laboratoryName=" + laboratoryName + ", contactNumber="
                + contactNumber + "]";
    }


}