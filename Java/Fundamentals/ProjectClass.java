public class ProjectClass {
    public String name;
    public String description;
    public ProjectClass() {}
    public ProjectClass(String name) {
        this.name = name;
    }
    public ProjectClass(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String elevatorPitch() {
        return this.name + " : " + this.description;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
}