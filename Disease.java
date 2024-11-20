public class Disease {
    private int diseaseID;
    private String name;
    private String category;
    private String icdCode;
    private String description;

    public Disease(int diseaseID, String name, String category, String icdCode, String description) {
        this.diseaseID = diseaseID;
        this.name = name;
        this.category = category;
        this.icdCode = icdCode;
        this.description = description;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}