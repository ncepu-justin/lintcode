package com.company;

import com.company.ListNode;
import com.company.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 暴力破解，不推荐，但比较容易理解。通过对Lists中所有节点遍历，
 * 寻找当前最小头结点并记录该下标值pos，之后将该值加入新的头结点中，并修改lists中pos对应的节点，使其指向下一个节点
 * Created by Justin
 * 2019/4/6  17:50
 */
public class MergeKLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        // write your code here
        ListNode curl=null;
        ListNode result = null;
        int pos = 0;
        while (lists.size() != 0) {
            int temp = Integer.MAX_VALUE;
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i) == null) {
                    lists.remove(i);
                }
                if (lists.size() > i && temp > lists.get(i).val) {
                    pos = i;
                    temp = lists.get(i).val;
                }
            }
            if (lists.size() == 0) {
                break;
            }
            ListNode node = lists.get(pos);
            if (result == null) {
                curl = node;
                result = curl;
            } else {
                curl.next= node;
                curl=curl.next;
            }
            node = node.next;
            if (node != null) {
                lists.remove(pos);
                lists.add(pos, node);
            }
            if (node == null) {
                lists.remove(pos);
            }
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
       /* int[] numbers = {9, 3, 2, 4, 8, 13, 1, 11, 10};
        Solution sl = new Solution();
        System.out.println(sl.kthLargestElement(3, numbers));*/
        ListNode listnode1 = new ListNode(0);
        ListNode test=listnode1;
        for (int i = 1; i <=8 ; i++) {
            listnode1.next=new ListNode(i);
            listnode1=listnode1.next;
        }
        // ListNode listnode2 = new ListNode(-1);
        //ListNode listnode3 = null;
        List<ListNode> list = new ArrayList<>();
        list.add(test);
        //  list.add(listnode2);
        // list.add(listnode3);
        Solution sl = new Solution();
        sl.mergeKLists(list);
    }
}
