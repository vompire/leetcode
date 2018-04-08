"""You are given two non-empty linked lists representing two non-negative integers. The digits are stored

in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself."""


class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    """错误理解题意，本题需要对链表对象进行操作"""
    def addTwoNumbers(self, l1, l2):
        carry = 0
        result = []
        for i in range(max(len(l1),len(l2))):

            if i+1>(min(len(l1),len(l2))):
                if len(l1)>len(l2):
                    result.append(l1[i]+carry)
                else:
                    result.append(l2[i]+carry)
            else:
                result.append((l1[i]+l2[i]+carry)%10)
                carry = (l1[i]+l2[i]+carry)//10
        return result

class Solution2(object):
    def addTwoNumbers(self, l1, l2):
        carry = 0
        res = n = ListNode(0)
        while l1 or l2 or carry:
            if l1:
                carry += l1.val
                l1 = l1.next
            if l2:
                carry += l2.val
                l2 = l2.next
            carry, val = divmod(carry, 10)
            n.next = n = ListNode(val)
        return res.next


if __name__ == '__main__':
    a = [2,4,3]
    b = [5,6,4]
    s = Solution()
    print(s.addTwoNumbers(a,b))