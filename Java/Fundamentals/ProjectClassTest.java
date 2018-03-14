public class ProjectClassTest {
    public static void main(String[] args) {
        ProjectClass project = new ProjectClass("name1", "description1");
        System.out.println(project.getName());
        System.out.println(project.getDescription());
        project.setName("another name");
        project.setDescription("another description");
        System.out.println(project.getName());
        System.out.println(project.getDescription());
    }
}