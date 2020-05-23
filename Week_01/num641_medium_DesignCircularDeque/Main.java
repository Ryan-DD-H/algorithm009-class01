package num641_medium_DesignCircularDeque;

public class Main {

    public static void main (String [] args) {


        MyCircularDeque obj = new MyCircularDeque(10);
        boolean param_1 = obj.insertFront(100);
        boolean param_2 = obj.insertLast(200);
        boolean param_3 = obj.deleteFront();
        boolean param_4 = obj.deleteLast();
        int param_5 = obj.getFront();
        int param_6 = obj.getRear();
        boolean param_7 = obj.isEmpty();
        boolean param_8 = obj.isFull();
        System.out.println(param_1);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
        System.out.println(param_5);
        System.out.println(param_6);
        System.out.println(param_7);
        System.out.println(param_8);



    }
}

/**
 *设计实现双端队列。
 * 你的实现需要支持以下操作：
 *
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 * 示例：
 *
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *  
 *  
 *
 * 提示：
 *
 * 所有值的范围为 [1, 1000]
 * 操作次数的范围为 [1, 1000]
 * 请不要使用内置的双端队列库。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-circular-deque
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */



class MyCircularDeque {

    int size, k;
    DoubleListNode head;
    DoubleListNode tail;



    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {

        head = new DoubleListNode(-1);
        tail = new DoubleListNode(-1);
        head.pre = tail;
        tail.next = head;
        this.k = k;
        size = 0;


    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {

        if(size == k)
            return false;

        DoubleListNode node = new DoubleListNode(value);
        node.next = head;
        node.pre = head.pre;
        head.pre.next = node;
        head.pre = node;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size == k)
            return false;

        DoubleListNode node = new DoubleListNode(value);
        node.pre = tail;
        node.next = tail.next;
        tail.next.pre = node;
        tail.next = node;
        size++;
        return true;

    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {

        if(size == 0)
            return false;

        head.pre.pre.next = head;
        head.pre = head.pre.pre;
        size--;
        return true;
    }


    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {

        if(size == 0)
            return false;

        tail.next.next.pre = tail;
        tail.next = tail.next.next;
        size--;

        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {

        return head.pre.value;
    }

    /** Get the last item from the deque. */
    public int getRear() {

        return tail.next.value;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {

        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {

        return size == k;
    }
}

class DoubleListNode {

    DoubleListNode pre;
    DoubleListNode next;
    int value;
    DoubleListNode (int value){
        this.value = value;
    }

}


/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */


