1. **Dummy** 在写入的时候有用，在查询的时候用处不大。
2. **EDIT** 的行为是操作前一个 `Node`，do `prev.next = new;`
3. **快慢指针**
4. **反转链表需要**:
   ```java
   ListNode temp; // 保存 cur 的下一个节点
   ListNode cur = head;
   ListNode pre = NULL;
   ```

![alt text](image.png)
