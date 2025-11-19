public class LinkedList {

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    Node head;

    // 增：在尾部插入新节点
    public void add(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    // 查：按值查找是否存在
    public boolean contains(int value) {
        Node curr = head;
        while (curr != null) {
            if (curr.value == value) return true;
            curr = curr.next;
        }
        return false;
    }

    // 改：将第一个匹配值改为新值
    public boolean update(int oldValue, int newValue) {
        Node curr = head;
        while (curr != null) {
            if (curr.value == oldValue) {
                curr.value = newValue;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // 删：删除第一个匹配的节点
    public boolean delete(int value) {
        if (head == null) return false;
        if (head.value == value) {
            head = head.next;
            return true;
        }
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.value == value) {
                curr.next = curr.next.next;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // 打印链表
    public void printList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.value + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    // main 函数演示 CRUD
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        System.out.println("添加节点:");
        list.add(10);
        list.add(20);
        list.add(30);
        list.printList(); // 10 -> 20 -> 30 -> null

        System.out.println("\n查找节点 20:");
        System.out.println(list.contains(20)); // true

        System.out.println("\n更新节点 20 为 25:");
        list.update(20, 25);
        list.printList(); // 10 -> 25 -> 30 -> null

        System.out.println("\n删除节点 25:");
        list.delete(25);
        list.printList(); // 10 -> 30 -> null
    }
}