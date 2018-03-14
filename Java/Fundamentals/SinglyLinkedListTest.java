public class SinglyLinkedListTest {
    public static void main(String[] args) {
        SinglyLinkedList xs = new SinglyLinkedList();
        xs.add(5);
        xs.add(10);
        xs.add(15);
        xs.add(20);
        xs.printValues();
        System.out.println(xs.remove());
        xs.printValues();
        System.out.println(xs.remove());
        System.out.println(xs.remove());
        System.out.println(xs.remove());
        xs.printValues();
    }
}