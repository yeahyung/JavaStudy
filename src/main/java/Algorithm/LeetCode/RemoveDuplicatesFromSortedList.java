package Algorithm.LeetCode;

public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(3);

        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        printNode(deleteDuplicates(head));
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode resultNode = head;
        while(resultNode != null){
            if(resultNode.next == null){
                break;
            }

            if(resultNode.val == resultNode.next.val){
                resultNode.next = resultNode.next.next;
            }
            else{
                resultNode = resultNode.next;
            }
        }

        return head;
    }

    public static void printNode(ListNode head){
        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
