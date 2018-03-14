public class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedList() {
        this.head = null;
    }
    public void add(int x) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(x, null);
        if (this.head == null) {
            this.head = newNode;
        }
        else {
            SinglyLinkedListNode last = this.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
    }
    public int remove() {
        SinglyLinkedListNode last = this.head;
        if (last.next == null) {
            int x = this.head.value;
            this.head = null;
            return x;
        }
        else {
            while(last.next.next != null) {
                last = last.next;
            }
            int x = last.next.value;
            last.next = null;
            return x;
        }
    }
    public void printValues() {
        SinglyLinkedListNode current = this.head;
        String result = "";
        while(current != null) {
            result = result + Integer.toString(current.value) + ",";
            current = current.next;
        }
        System.out.println(result);
    }
}