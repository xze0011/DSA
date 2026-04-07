# LeetCode Solutions (C#) - Microsoft
---

## 1. Two Sum

**Difficulty:** Easy

**Problem:** Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

```csharp
public int[] TwoSum(int[] nums, int target) {
    Dictionary<int,int> map = new();
    for(int i = 0; i < nums.Length; i++){
        int complement = target - nums[i];
        if(map.ContainsKey(complement)){
            return new int[]{map[complement],i};
        }

        map[nums[i]] = i;
    }
    return new int[0];
}
```

---

## 2. Add Two Numbers

**Difficulty:** Medium

**Problem:** You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

```csharp
public ListNode AddTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    int carry = 0;
    while(l1 != null || l2 != null){
        int v1 = l1 == null ? 0 : l1.val;
        int v2 = l2 == null ? 0 : l2.val;
        int sum = v1 + v2 + carry;
        int remains = sum % 10;
        carry = sum / 10;
        ListNode node = new ListNode(remains);
        cur.next = node;
        cur = cur.next;
        if(l1 != null) l1 = l1.next;
        if(l2 != null) l2 = l2.next;
     }
     if(carry != 0){
        ListNode node = new ListNode(carry);
        cur.next = node;
     }

     return dummy.next;
}
```

---

## 3. Longest Substring Without Repeating Characters

**Difficulty:** Medium

**Problem:** Given a string `s`, find the length of the longest substring without repeating characters.

```csharp
public int LengthOfLongestSubstring(string s) {
    int longest = 0;
    int left = 0;
    // HashSet<int> set = new();
    Dictionary<int,int> map = new();
    for(int right = 0; right < s.Length; right++){
        if(map.ContainsKey(s[right]) && map[s[right]] >= left ){
            left =map[s[right]] + 1;
        }
        map[s[right]] = right;
        longest = Math.Max(right - left + 1,longest);
    }

    return longest;
}
```

---

## 6. Zigzag Conversion

**Difficulty:** Medium

**Problem:** The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows. Write the code that will take a string and make this conversion given a number of rows.

```csharp
public string Convert(string s, int numRows) {
    int n = s.Length;
    if(numRows == 1 || n <= numRows) return s;
    StringBuilder[] sbs = new StringBuilder[numRows];
    for(int i = 0; i < numRows; i++) sbs[i] = new StringBuilder();
    int row = 0; int dir = -1;
    foreach (char c in s) {
        sbs[row].Append(c);
        if(row == 0 || row == numRows - 1) dir = -dir;
        row += dir;
    }
    StringBuilder res = new();
    foreach(StringBuilder ss in sbs){
        res.Append(ss);
    }
    return res.ToString();
}
```

---

## 7. Reverse Integer

**Difficulty:** Medium

**Problem:** Given a signed 32-bit integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-2^31, 2^31 - 1]`, then return `0`.

```csharp
public int Reverse(int x) {
    int rev = 0;
    while (x != 0) {
        int pop = x % 10;
        x /= 10;
        if (rev > int.MaxValue / 10 || (rev == int.MaxValue / 10 && pop > 7)) return 0;
        if (rev < int.MinValue / 10 || (rev == int.MinValue / 10 && pop < -8)) return 0;
        rev = rev * 10 + pop;
    }
    return rev;
}
```

---

## 12. Integer to Roman

**Difficulty:** Medium

**Problem:** Given an integer, convert it to a Roman numeral.

```csharp
public string IntToRoman(int num) {
    int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    string[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    StringBuilder sb = new();
    for (int i = 0; i < values.Length; i++) {
        while (num >= values[i]) {
            num -= values[i];
            sb.Append(symbols[i]);
        }
    }
    return sb.ToString();
}
```

---

## 14. Longest Common Prefix

**Difficulty:** Easy

**Problem:** Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string `""`.

```csharp
public string LongestCommonPrefix(string[] strs) {
    StringBuilder sb = new StringBuilder(strs[0]);
    int read= 0 ;
    int n = strs.Length;
    for(int i = 1 ; i < n; i++){
        while(!strs[i].StartsWith(sb.ToString())){
            sb.Remove(sb.Length- 1,1);
            if(sb.Length == 0) return "";
        }
    }
    return sb.ToString();
}
```

---

## 15. 3Sum

**Difficulty:** Medium

**Problem:** Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`. The solution set must not contain duplicate triplets.

```csharp
public IList<IList<int>> ThreeSum(int[] nums) {
    Array.Sort(nums);
    IList<IList<int>> res = new List<IList<int>>();

    for(int i = 0; i < nums.Length - 2; i++){
        if (nums[i] > 0) break;
        HashSet<int> set = new();
        if(i > 0 && nums[i] == nums[i-1]) continue;
        for(int j = i+1; j < nums.Length; j++){
            int target = 0 - nums[i] - nums[j];
            if(set.Contains(target)){
                List<int> list = new List<int>{nums[i],nums[j],target};
                res.Add(list);
                while(j+ 1 < nums.Length && nums[j+1] == nums[j]){
                    j++;
                }
            }
            set.Add(nums[j]);
        }
    }
    return res;
}
```

---

## 22. Generate Parentheses

**Difficulty:** Medium

**Problem:** Given `n` pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

```csharp
public IList<string> GenerateParenthesis(int n) {
    IList<string> res = new List<string>();

    backTrack(res,new StringBuilder(),0,0,n);
    return res;
}

public void backTrack(IList<string> res, StringBuilder sb, int open, int close,int n){
    if(sb.Length == n * 2){
        res.Add(sb.ToString());
        return;
    }

    if(open < n){
        sb.Append('(');
        open++;
        backTrack(res,sb,open,close,n);
        sb.Remove(sb.Length-1,1);
        open--;
    }

    if(close < open){
        sb.Append(')');
        close++;
        backTrack(res,sb,open,close,n);
        sb.Remove(sb.Length-1,1);
        close--;
    }
}
```

---

## 26. Remove Duplicates from Sorted Array

**Difficulty:** Easy

**Problem:** Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Return the number of unique elements.

```csharp
public int RemoveDuplicates(int[] nums) {
    int write = 1;
    for(int right = 1; right < nums.Length; right++){
        if(nums[right] != nums[right-1]){
            nums[write] = nums[right];
            write++;
        }
    }
    return write;
}
```

---

## 31. Next Permutation

**Difficulty:** Medium

**Problem:** Given an array of integers `nums`, find the next permutation of `nums`. The replacement must be in place and use only constant extra memory.

```csharp
public void NextPermutation(int[] nums) {
    int targetLeftIndex = -1;
    int targetRightIndex = -1;
    int n = nums.Length;
    for(int i = n - 2; i >= 0; i--){
        if(nums[i] < nums[i+1]){
            targetLeftIndex = i;
            break;
        }
    }

    if(targetLeftIndex == -1){
    Reverse(nums, 0, n - 1);
    return;
    }

    for(int i = n - 1; i > 0; i--){
        if(nums[i] > nums[targetLeftIndex]){
            targetRightIndex = i;
            break;
        }
    }
    Swap(nums,targetLeftIndex,targetRightIndex);
    Reverse(nums,targetLeftIndex+1,n-1);
}

public void Swap(int[] nums, int left, int right){
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
}

public void Reverse(int[] nums, int left, int right){
    while(left < right){
        Swap(nums,left,right);
        left++;
        right--;
    }
}
```

---

## 33. Search in Rotated Sorted Array

**Difficulty:** Medium

**Problem:** Given the array `nums` after the possible rotation and an integer `target`, return the index of `target` if it is in `nums`, or `-1` if it is not in `nums`.

```csharp
public int Search(int[] nums, int target) {
    int left = 0, right = nums.Length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;

        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    return -1;
}
```

---

## 42. Trapping Rain Water

**Difficulty:** Hard

**Problem:** Given `n` non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

```csharp
public int Trap(int[] height) {
    // monotonic stack, from bottom to top , desc -> top is botoom;
    Stack<int> stack = new();
    int sum = 0;
    for(int i = 0; i < height.Length; i++){
        while(stack.Count != 0 &&  height[i] > height[stack.Peek()]){
            int bottom = stack.Pop();
            if(stack.Count == 0) break;
            int left = stack.Peek();
            int h = Math.Min(height[i],height[left]) - height[bottom];
            int area = (i - left - 1) * h;
            sum += area;
        }
        stack.Push(i);
    }
    return sum;
}
```

---

## 45. Jump Game II

**Difficulty:** Medium

**Problem:** You are given a 0-indexed array of integers `nums` of length `n`. You are initially positioned at `nums[0]`. Return the minimum number of jumps to reach `nums[n - 1]`.

```csharp
public int Jump(int[] nums) {
    int jumps = 0, curEnd = 0, farthest = 0;
    for (int i = 0; i < nums.Length - 1; i++) {
         farthest = Math.Max(farthest, i + nums[i]);
         if(i == curEnd){
            jumps++;
            curEnd = farthest;
         }
    }
    return jumps;
}
```

---

## 46. Permutations

**Difficulty:** Medium

**Problem:** Given an array `nums` of distinct integers, return all the possible permutations. You can return the answer in any order.

```csharp
public IList<IList<int>> Permute(int[] nums) {
    IList<IList<int>> res = new List<IList<int>>();
    bool[] visited = new bool[nums.Length];
    backTrack(nums,res, new List<int>(), visited);
    return res;
}

public void backTrack(int[] nums, IList<IList<int>> res,List<int> path, bool[] visited){
    if(path.Count == nums.Length){
        res.Add(new List<int>(path));
        return;
    }
    for(int i = 0; i < nums.Length; i++){
        if(visited[i]) continue;
        visited[i] = true;
        path.Add(nums[i]);
        backTrack(nums,res,path,visited);
        visited[i] =false;
        path.RemoveAt(path.Count - 1);
    }
}
```

---

## 48. Rotate Image

**Difficulty:** Medium

**Problem:** You are given an `n x n` 2D `matrix` representing an image, rotate the image by 90 degrees (clockwise). You have to rotate the image in-place.

```csharp
public void Rotate(int[][] matrix) {
    int n = matrix.Length;
    // Transpose
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }
    // Reverse each row
    for (int i = 0; i < n; i++) {
        Array.Reverse(matrix[i]);
    }
}
```

---

## 51. N-Queens

**Difficulty:** Hard

**Problem:** The n-queens puzzle is the problem of placing `n` queens on an `n x n` chessboard such that no two queens attack each other. Given an integer `n`, return all distinct solutions to the n-queens puzzle.

```csharp
public IList<IList<string>> SolveNQueens(int n) {
    IList<IList<string>> res = new List<IList<string>>();
    int[] queens = new int[n];
    Array.Fill(queens, -1);

    HashSet<int> cols  = new();
    HashSet<int> diag1 = new(); // row - col
    HashSet<int> diag2 = new(); // row + col

    Backtrack(0, n, queens, cols, diag1, diag2, res);
    return res;
}

private void Backtrack(int row, int n, int[] queens,
                       HashSet<int> cols, HashSet<int> diag1, HashSet<int> diag2,
                       IList<IList<string>> res) {
    if (row == n) {
        res.Add(BuildBoard(queens, n));
        return;
    }

    for (int col = 0; col < n; col++) {
        if (cols.Contains(col)) continue;
        if (diag1.Contains(row - col)) continue;
        if (diag2.Contains(row + col)) continue;

        queens[row] = col;
        cols.Add(col);
        diag1.Add(row - col);
        diag2.Add(row + col);

        Backtrack(row + 1, n, queens, cols, diag1, diag2, res);

        queens[row] = -1;
        cols.Remove(col);
        diag1.Remove(row - col);
        diag2.Remove(row + col);
    }
}

private IList<string> BuildBoard(int[] queens, int n) {
    IList<string> board = new List<string>();
    for (int row = 0; row < n; row++) {
        char[] line = new char[n];
        Array.Fill(line, '.');
        line[queens[row]] = 'Q';
        board.Add(new string(line));
    }
    return board;
}
```

---

## 56. Merge Intervals

**Difficulty:** Medium

**Problem:** Given an array of `intervals` where `intervals[i] = [starti, endi]`, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

```csharp
public int[][] Merge(int[][] intervals) {
    List<int[]> res = new();
    Array.Sort(intervals,(a,b) => a[0] - b[0]);

    for(int i = 0; i < intervals.Length; i++){
        if(res.Count == 0 || res[res.Count-1][1]  < intervals[i][0]){
            res.Add(intervals[i]);
        }else{
            int end = Math.Max(res[res.Count-1][1] ,intervals[i][1]);
            res[res.Count-1][1] = end;
        }
    }

    return res.ToArray();
}
```

---

## 73. Set Matrix Zeroes

**Difficulty:** Medium

**Problem:** Given an `m x n` integer matrix `matrix`, if an element is `0`, set its entire row and column to `0`'s. You must do it in place.

```csharp
public void SetZeroes(int[][] matrix) {
    int m = matrix.Length, n = matrix[0].Length;
    bool firstRowZero = false, firstColZero = false;

    for (int i = 0; i < m; i++) {
        if (matrix[i][0] == 0) firstColZero = true;
    }
    for (int j = 0; j < n; j++) {
        if (matrix[0][j] == 0) firstRowZero = true;
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }

    if (firstColZero) {
        for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }
    if (firstRowZero) {
        for (int j = 0; j < n; j++) matrix[0][j] = 0;
    }
}
```

---

## 76. Minimum Window Substring

**Difficulty:** Hard

**Problem:** Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window.

```csharp
public string MinWindow(string s, string t) {
    if (s.Length == 0 || t.Length == 0) return "";

    Dictionary<char, int> dictT = new Dictionary<char, int>();
    foreach (char c in t) {
        dictT[c] = dictT.GetValueOrDefault(c, 0) + 1;
    }
    // 记录t中每个字符的需求频率

    int required = dictT.Count;
    // t中不同字符的数量

    int left = 0, right = 0;
    int formed = 0;
    // formed表示当前窗口中满足频率要求的字符种类数

    Dictionary<char, int> windowCounts = new Dictionary<char, int>();
    // 记录当前窗口中每个字符的频率

    int[] ans = {-1, 0, 0};
    // ans[0]存储最小窗口长度，ans[1]和ans[2]存储左右边界

    while (right < s.Length) {
        char c = s[right];
        windowCounts[c] = windowCounts.GetValueOrDefault(c, 0) + 1;
        // 将右边界字符加入窗口

        if (dictT.ContainsKey(c) && windowCounts[c] == dictT[c]) {
            formed++;
        }
        // 如果当前字符满足频率要求，增加formed计数

        while (left <= right && formed == required) {
            c = s[left];

            if (ans[0] == -1 || right - left + 1 < ans[0]) {
                ans[0] = right - left + 1;
                ans[1] = left;
                ans[2] = right;
            }
            // 更新最小窗口

            windowCounts[c]--;
            if (dictT.ContainsKey(c) && windowCounts[c] < dictT[c]) {
                formed--;
            }
            // 移除左边界字符，如果导致不满足要求则减少formed

            left++;
        }

        right++;
    }

    return ans[0] == -1 ? "" : s.Substring(ans[1], ans[0]);
}
```

---

## 83. Remove Duplicates from Sorted List

**Difficulty:** Easy

**Problem:** Given the `head` of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

```csharp
public ListNode DeleteDuplicates(ListNode head) {
    ListNode cur = head;
    while (cur != null && cur.next != null) {
        if (cur.val == cur.next.val) {
            cur.next = cur.next.next;
        } else {
            cur = cur.next;
        }
    }
    return head;
}
```

---

## 105. Construct Binary Tree from Preorder and Inorder Traversal

**Difficulty:** Medium

**Problem:** Given two integer arrays `preorder` and `inorder` where `preorder` is the preorder traversal of a binary tree and `inorder` is the inorder traversal of the same tree, construct and return the binary tree.

```csharp
public Dictionary<int,int> map = new();
int preIndex = 0;
public TreeNode BuildTree(int[] preorder, int[] inorder) {
    for(int i = 0; i < inorder.Length; i++) map[inorder[i]] = i;
     return dfs(preorder, 0, inorder.Length - 1);
}

public TreeNode dfs(int[] preorder, int left, int right) {
    if (left > right) return null;
     int rootVal = preorder[preIndex++];
    TreeNode root = new TreeNode(rootVal);
    int mid = map[rootVal];
    root.left = dfs(preorder, left, mid -1);
    root.right = dfs(preorder, mid + 1, right);
    return root;
}
```

---

## 121. Best Time to Buy and Sell Stock

**Difficulty:** Easy

**Problem:** You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`th day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve.

```csharp
public int MaxProfit(int[] prices) {
    int lowPrice = prices[0];
    int maxProfit = 0;
    for(int i = 1; i < prices.Length; i++){
        maxProfit = Math.Max(maxProfit, prices[i] - lowPrice);
        lowPrice = Math.Min(lowPrice, prices[i]);
    }
    return maxProfit;
}
```

---

## 125. Valid Palindrome

**Difficulty:** Easy

**Problem:** A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

```csharp
public bool IsPalindrome(string s) {
    int left = 0;
    int right = s.Length -1;

    while(left < right){
        while(left < right && !char.IsLetterOrDigit(s[left])){
            left++;
        }
        while(left < right && !char.IsLetterOrDigit(s[right])){
            right--;
        }
        if(char.ToLower(s[left]) != char.ToLower(s[right])) return false;
        left++;
        right--;
    }

    return true;
}
```

---

## 139. Word Break

**Difficulty:** Medium

**Problem:** Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

```csharp
public bool WordBreak(string s, IList<string> wordDict) {
    HashSet<string> set = new HashSet<string>(wordDict);
    int n = s.Length;
    bool[] dp = new bool[n+1];
    dp[0] = true;

    for(int i = 1;  i < n + 1; i++){
        for(int j = 0; j < n; j++){
            if(dp[j] && set.Contains(s.Substring(j,i-j))){
                dp[i] = true;
            }
        }
    }

    return dp[n];
}
```

---

## 146. LRU Cache

**Difficulty:** Medium

**Problem:** Design a data structure that follows the constraints of a Least Recently Used (LRU) cache. Implement the `LRUCache` class with `get` and `put` operations in O(1) time complexity.

```csharp
public interface IEvictionPolicy {
    void OnAccess(int key);
    void OnInsert(int key);
    int Evict();
}

public class LRUPolicy : IEvictionPolicy {
    private LinkedList<int> _list = new();
    private Dictionary<int, LinkedListNode<int>> _map = new();

    public void OnAccess(int key) {
        if (_map.ContainsKey(key)) {
            _list.Remove(_map[key]);
            _map[key] = _list.AddFirst(key);
        }
    }

    public void OnInsert(int key) {
        var node = _list.AddFirst(key);
        _map[key] = node;
    }

    public int Evict() {
        var last = _list.Last.Value;
        _list.RemoveLast();
        _map.Remove(last);
        return last;
    }
}

public class LRUCache {
    private int _capacity;
    private Dictionary<int, int> _store = new();
    private IEvictionPolicy _policy;

    public LRUCache(int capacity) {
        _capacity = capacity;
        _policy = new LRUPolicy();
    }

    public int Get(int key) {
        if (!_store.ContainsKey(key)) return -1;
        _policy.OnAccess(key);
        return _store[key];
    }

    public void Put(int key, int value) {
        if (_store.ContainsKey(key)) {
            _store[key] = value;
            _policy.OnAccess(key);
        } else {
            if (_store.Count == _capacity) {
                int evicted = _policy.Evict();
                _store.Remove(evicted);
            }
            _store[key] = value;
            _policy.OnInsert(key);
        }
    }
}
```

---

## 167. Two Sum II - Input Array Is Sorted

**Difficulty:** Medium

**Problem:** Given a 1-indexed array of integers `numbers` that is already sorted in non-decreasing order, find two numbers such that they add up to a specific `target` number.

```csharp
public int[] TwoSum(int[] numbers, int target) {
    int left = 0, right = numbers.Length - 1;
    while (left < right) {
        int sum = numbers[left] + numbers[right];
        if (sum == target) {
            return new int[] { left + 1, right + 1 };
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    return new int[0];
}
```

---

## 199. Binary Tree Right Side View

**Difficulty:** Medium

**Problem:** Given the `root` of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

```csharp
public IList<int> RightSideView(TreeNode root) {
    List<int> res = new();
    if (root == null) return res;

    Queue<TreeNode> queue = new();
    queue.Enqueue(root);

    while (queue.Count > 0) {
        int size = queue.Count;
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.Dequeue();
            if (i == size - 1) res.Add(node.val);
            if (node.left != null) queue.Enqueue(node.left);
            if (node.right != null) queue.Enqueue(node.right);
        }
    }

    return res;
}
```

---

## 200. Number of Islands

**Difficulty:** Medium

**Problem:** Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.

```csharp
public int NumIslands(char[][] grid) {
    if (grid == null || grid.Length == 0) {
        return 0;
        // 处理空网格的边界情况
    }
    int count = 0;
    // 岛屿计数器
    int m = grid.Length;
    // 网格行数
    int n = grid[0].Length;
    // 网格列数
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            // 遍历网格的每个位置
            if (grid[i][j] == '1') {
                count++;
                // 发现新岛屿，计数+1
                DFS(grid, i, j);
                // 使用DFS标记整个岛屿
            }
        }
    }
    return count;
    // 返回岛屿总数
}

private void DFS(char[][] grid, int i, int j) {
    int m = grid.Length;
    // 网格行数
    int n = grid[0].Length;
    // 网格列数
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
        return;
        // 边界检查和水域检查
    }
    grid[i][j] = '0';
    // 标记当前位置为已访问（改为水域）
    DFS(grid, i - 1, j);
    // 向上搜索
    DFS(grid, i + 1, j);
    // 向下搜索
    DFS(grid, i, j - 1);
    // 向左搜索
    DFS(grid, i, j + 1);
    // 向右搜索
}
```

---

## 202. Happy Number

**Difficulty:** Easy

**Problem:** Write an algorithm to determine if a number `n` is happy. A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1, or it loops endlessly in a cycle.

```csharp
public bool IsHappy(int n) {
    HashSet<int> seen = new();
    while(n != 1){
        if(seen.Contains(n)) return false;
        seen.Add(n);
        int newNumber = 0;
        while(n > 0){
            int remains = n % 10;   // 19 -> 9
            newNumber += remains * remains;
            n = n / 10;
        }
        n = newNumber;
    }

    return true;
}
```

---

## 206. Reverse Linked List

**Difficulty:** Easy

**Problem:** Given the `head` of a singly linked list, reverse the list, and return the reversed list.

```csharp
public ListNode ReverseList(ListNode head) {
    ListNode prev = null;
    ListNode cur = head;
    // prev 1 2
    // 2 1 prev.   cur = 2
    // null<-1   prev 2 3
    // cur 2 prev;
    // null <- 1 <- 2.   cur = 3;
    // null
    while(cur != null){
       ListNode nextNode = cur.next;
       cur.next = prev;
       prev = cur;
       cur = nextNode;
    }
    return prev;
}
```

---

## 208. Implement Trie (Prefix Tree)

**Difficulty:** Medium

**Problem:** A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. Implement the Trie class.

```csharp
public class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public bool isEnd = false;
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void Insert(string word) {
        TrieNode node = root;
        foreach (char c in word) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public bool Search(string word) {
        TrieNode node = SearchPrefix(word);
        return node != null && node.isEnd;
    }

    public bool StartsWith(string prefix) {
        return SearchPrefix(prefix) != null;
    }

    private TrieNode SearchPrefix(string prefix) {
        TrieNode node = root;
        foreach (char c in prefix) {
            int idx = c - 'a';
            if (node.children[idx] == null) return null;
            node = node.children[idx];
        }
        return node;
    }
}
```

---

## 224. Basic Calculator

**Difficulty:** Hard

**Problem:** Given a string `s` representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation. The expression string may contain `+`, `-`, `(`, `)`, digits and spaces.

```csharp
public int Calculate(string s) {
    Stack<int> stack = new Stack<int>();
    int result = 0;
    int sign = 1;
    int i = 0;

    while(i < s.Length){
        char c = s[i];
        if(char.IsDigit(c)){
            int num = 0;
            while (i < s.Length && char.IsDigit(s[i])) {
                num = num * 10 + (s[i] - '0');
                i++;
            }
            result += sign * num;
            continue;
        }else if (c == '+'){
            sign = 1;
        }else if (c == '-'){
            sign = -1;
        }else if (c == '('){
            stack.Push(result);
            stack.Push(sign);
            result = 0;
            sign = 1;
        }else if(c == ')'){
            int prevsign = stack.Pop();
            int prevresult = stack.Pop();
            result = prevresult  + prevsign * result;
        }
        i++;
    }
    return result;
}
```

---

## 232. Implement Queue using Stacks

**Difficulty:** Easy

**Problem:** Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (`push`, `peek`, `pop`, and `empty`).

```csharp
public class MyQueue {
    Stack<int> inStack;
    Stack<int> outStack;
    public MyQueue() {
        inStack = new();
        outStack = new();
    }

    public void Push(int x) {
        inStack.Push(x);
    }

    public int Pop() {
        if(outStack.Count == 0){
            while(inStack.Count > 0){
                outStack.Push(inStack.Pop());
            }
        }
        return outStack.Pop();
    }

    public int Peek() {
        if(outStack.Count == 0){
            while(inStack.Count > 0){
                outStack.Push(inStack.Pop());
            }
        }
        return outStack.Peek();
    }

    public bool Empty() {
        return inStack.Count == 0 && outStack.Count == 0;
    }
}
```

---

## 239. Sliding Window Maximum

**Difficulty:** Hard

**Problem:** You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right. You can only see the `k` numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

```csharp
public int[] MaxSlidingWindow(int[] nums, int k) {
    if(k == 1) return nums;
    int n = nums.Length;
    int[] res = new int[n - k + 1];
    // monotinic double end queue
    LinkedList<int> deque = new();

    for(int i = 0; i < n; i++){
        while(deque.Count > 0 && i - k + 1 > deque.First.Value){
            deque.RemoveFirst();
        }
        while(deque.Count > 0 && nums[deque.Last.Value] < nums[i]){
              deque.RemoveLast();
        }
        deque.AddLast(i);
        if(i - k +1  >= 0){
            res[i - k +1] = nums[deque.First.Value];
        }
    }
    return res;
}
```

---

## 253. Meeting Rooms II

**Difficulty:** Medium

**Problem:** Given an array of meeting time intervals `intervals` where `intervals[i] = [starti, endi]`, return the minimum number of conference rooms required.

```csharp
public int MinMeetingRooms(int[][] intervals) {
    Array.Sort(intervals,(a,b) => a[0] - b[0]);
    // minHeap: the number of meetings are needed.
    PriorityQueue<int, int> minHeap = new();
    for(int i = 0; i < intervals.Length; i++){
        if(minHeap.Count == 0 || intervals[i][0] < minHeap.Peek()){
            minHeap.Enqueue(intervals[i][1],intervals[i][1]);
        }else{
            minHeap.Dequeue();
            minHeap.Enqueue(intervals[i][1], intervals[i][1]);
        }
    }
    return minHeap.Count;
}
```

---

## 295. Find Median from Data Stream

**Difficulty:** Hard

**Problem:** The median is the middle value in an ordered integer list. Design a data structure that supports adding integers and finding the median of all elements.

```csharp
public class MedianFinder {
    PriorityQueue<int,int> minStack;
    PriorityQueue<int,int> maxStack;
    public MedianFinder() {
        minStack = new();
        maxStack = new();
    }

    public void AddNum(int num) {
        if(minStack.Count == 0 || num <= minStack.Peek()){
            minStack.Enqueue(num, num);
        }else{
            maxStack.Enqueue(num, -num);
        }
        if(minStack.Count < maxStack.Count){
            int temp = maxStack.Dequeue();
            minStack.Enqueue(temp, -temp);
        }else if(maxStack.Count + 1 < minStack.Count){
            int temp = minStack.Dequeue();
            maxStack.Enqueue(temp, temp);
        }
    }

    public double FindMedian() {
        bool isBool = (minStack.Count + maxStack.Count) % 2 == 0;
        return isBool ? (double)(minStack.Peek() + maxStack.Peek()) / 2 : minStack.Peek();
    }
}
```

---

## 543. Diameter of Binary Tree

**Difficulty:** Easy

**Problem:** Given the `root` of a binary tree, return the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the `root`.

```csharp
int max = 0;

public int DiameterOfBinaryTree(TreeNode root) {
    Dfs(root,0);
    return max;
}

public int Dfs(TreeNode root, int height){
    if(root == null) return 0;

    int left = Dfs(root.left,height +1);
    int right = Dfs(root.right,height +1);
    max = Math.Max(max,left + right);
    return Math.Max(left, right) + 1;
}
```

---

## 545. Boundary of Binary Tree

**Difficulty:** Medium

**Problem:** The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.

```csharp
public IList<int> BoundaryOfBinaryTree(TreeNode root) {
    List<int> res = new();
    if (root == null) return res;

    if (!IsLeaf(root)) res.Add(root.val);

    // Left boundary
    TreeNode node = root.left;
    while (node != null) {
        if (!IsLeaf(node)) res.Add(node.val);
        node = node.left ?? node.right;
    }

    // Leaves
    AddLeaves(root, res);

    // Right boundary (in reverse)
    Stack<int> stack = new();
    node = root.right;
    while (node != null) {
        if (!IsLeaf(node)) stack.Push(node.val);
        node = node.right ?? node.left;
    }
    while (stack.Count > 0) res.Add(stack.Pop());

    return res;
}

private bool IsLeaf(TreeNode node) {
    return node.left == null && node.right == null;
}

private void AddLeaves(TreeNode node, List<int> res) {
    if (node == null) return;
    if (IsLeaf(node)) {
        res.Add(node.val);
        return;
    }
    AddLeaves(node.left, res);
    AddLeaves(node.right, res);
}
```

---

## 560. Subarray Sum Equals K

**Difficulty:** Medium

**Problem:** Given an array of integers `nums` and an integer `k`, return the total number of subarrays whose sum equals to `k`.

```csharp
public int SubarraySum(int[] nums, int k) {
    Dictionary<int,int> map = new();
    int sum = 0;
    int res = 0;
    map[0] = 1;
    for(int i = 0; i < nums.Length;i++){
        sum += nums[i];
        if(map.ContainsKey(sum - k)){
            res += map[sum - k];
        }
        map[sum] = map.GetValueOrDefault(sum,0)+ 1;
    }

    return res;
}
```

---

## 628. Maximum Product of Three Numbers

**Difficulty:** Easy

**Problem:** Given an integer array `nums`, find three numbers whose product is maximum and return the maximum product.

```csharp
public int MaximumProduct(int[] nums) {
    Array.Sort(nums);
    int n = nums.Length;
    //. 三个最大正数， ｜｜ 两个最小负数 -99 -100,  一个最大正数 n-1
    int max = Math.Max(nums[n-1] * nums[n-2] * nums[n-3],nums[0] * nums[1] * nums[n-1]);
    return max;
}
```

---

## 678. Valid Parentheses String

**Difficulty:** Medium

**Problem:** Given a string `s` containing only three types of characters: `'('`, `')'` and `'*'`, return `true` if `s` is valid. `'*'` could be treated as a single right parenthesis `')'` or a single left parenthesis `'('` or an empty string `""`.

```csharp
public bool CheckValidString(string s) {
    Stack<int> left= new();
    Stack<int> star = new();

    for(int i = 0; i < s.Length; i++){
        char c = s[i];
        if(c == '('){
            left.Push(i);
        }else if(c == ')'){
            if(left.Count > 0){
                left.Pop();
            }else if(star.Count > 0){
                star.Pop();
            }else{
                return false;
            }
        }else{
            star.Push(i);
        }
    }
    while(left.Count > 0 && star.Count > 0){
        if(left.Peek() > star.Peek()) return false;
        left.Pop();
        star.Pop();
    }

     return left.Count == 0;
}
```

---

## 721. Accounts Merge

**Difficulty:** Medium

**Problem:** Given a list of `accounts` where each element `accounts[i]` is a list of strings, where the first element `accounts[i][0]` is a name, and the rest of the elements are emails representing emails of the account. Merge accounts belonging to the same person.

```csharp
Dictionary<string,string> parent = new();

public string Find(string x){
    if(parent[x] != x){
        parent[x] = Find(parent[x]);
    }
    return parent[x];
}

public void Union(string x, string y){
    string px = Find(x);
    string py = Find(y);
    if(px != py) parent[px] = py;
}

public IList<IList<string>> AccountsMerge(IList<IList<string>> accounts) {
    Dictionary<string, string> owner = new();
    foreach (var account in accounts) {
        string name = account[0];
        for (int i = 1; i < account.Count; i++) {
            string email = account[i];
            if (!parent.ContainsKey(email)) {
                parent[email] = email;
            }
            owner[email] = name;
            Union(account[1], email);
        }
    }
    Dictionary<string, List<string>> groups = new();
    foreach (var email in parent.Keys) {
        string root = Find(email);
        if (!groups.ContainsKey(root)) {
            groups[root] = new List<string>();
        }
        groups[root].Add(email);
    }

    IList<IList<string>> res = new List<IList<string>>();
    foreach (var (root, emails) in groups) {
        emails.Sort((a, b) => string.Compare(a, b, StringComparison.Ordinal));
        List<string> account = new() { owner[root] };
        account.AddRange(emails);
        res.Add(account);
    }

    return res;
}
```

---

## 875. Koko Eating Bananas

**Difficulty:** Medium

**Problem:** Koko loves to eat bananas. There are `n` piles of bananas, the `i`th pile has `piles[i]` bananas. Koko can decide her bananas-per-hour eating speed of `k`. Return the minimum integer `k` such that she can eat all the bananas within `h` hours.

```csharp
public int MinEatingSpeed(int[] piles, int h) {
    int left = 1;
    int right = piles.Max();
    while(left < right){
        int mid = left + (right -left) / 2;
        if(Canfinish(piles,h,mid)){
            right = mid;
        }else{
            left = mid +1;
        }
    }
    return left;
}

public bool Canfinish(int[] piles, int h, int k){
    int hours = 0;
    foreach(int pile in piles){
        hours += (int)Math.Ceiling((double)pile / k);
    }
    return hours <= h;
}
```

---

## 986. Interval List Intersections

**Difficulty:** Medium

**Problem:** You are given two lists of closed intervals, `firstList` and `secondList`. Return the intersection of these two interval lists.

```csharp
public int[][] IntervalIntersection(int[][] firstList, int[][] secondList) {
    int i = 0; int j = 0;
    List<int[]> res = new();
    while(i < firstList.Length && j < secondList.Length){
        int start = Math.Max(firstList[i][0],secondList[j][0]);
        int end = Math.Min(firstList[i][1],secondList[j][1]);
        if (start <= end) res.Add(new int[]{ start, end });
        if(firstList[i][1] < secondList[j][1]){
            i++;
        }else{
            j++;
        }
    }
    return res.ToArray();
}
```

---

## 1475. Final Prices With a Special Discount in a Shop

**Difficulty:** Easy

**Problem:** Given an array `prices` where `prices[i]` is the price of the `i`th item in a shop. There is a special discount: if you buy the `i`th item, then you will receive a discount equivalent to `prices[j]` where `j` is the minimum index such that `j > i` and `prices[j] <= prices[i]`.

```csharp
public int[] FinalPrices(int[] prices) {
    Stack<int> stack = new();
    for(int i = 0; i < prices.Length;i++){
        while(stack.Count != 0 && prices[i] <= prices[stack.Peek()]){
            int index = stack.Pop();
            prices[index] = prices[index] -  prices[i];
        }
        stack.Push(i);
    }
    return prices;
}
```

---

## 1727. Largest Submatrix With Rearrangements

**Difficulty:** Medium

**Problem:** You are given a binary matrix `matrix` of size `m x n`, and you are allowed to rearrange the columns of the matrix in any order. Return the area of the largest submatrix within `matrix` where every element of the submatrix is `1` after reordering the columns optimally.

```csharp
public int LargestSubmatrix(int[][] matrix) {
    int n = matrix.Length;
    int m = matrix[0].Length;

    for(int i = 1; i < n; i++){
        for(int j = 0; j < m; j++){
            if(matrix[i][j] == 1){
                matrix[i][j] = matrix[i-1][j] + 1;
            }
        }
    }

    int ans = 0;
    foreach (var row in matrix) {
        Array.Sort(row,(a,b) => b-a);
        for (int j = 0; j < m; j++){
            int area = row[j] * (j + 1);
            ans = Math.Max(ans,area);
        }
    }
    return ans;
}
```

---

## 1888. Minimum Number of Flips to Make the Binary String Alternating

**Difficulty:** Medium

**Problem:** You are given a binary string `s`. You are allowed to perform two types of operations: remove the character at the start of the string and append it to the end, or pick any character and flip it. Return the minimum number of flips required to make `s` alternating.

```csharp
public int MinFlips(string s) {
    int n = s.Length;
    string doubled = s + s;
    int minFlips = int.MaxValue;
    int flipsPattern1 = 0;
    int flipsPattern2 = 0;
    for(int i = 0; i < 2 * n; i++){
        char expectedPattern1 = (i % 2 == 0) ? '0' : '1';
        char expectedPattern2 = (i % 2 == 0) ? '1' : '0';
        if (doubled[i] != expectedPattern1) {
            flipsPattern1++;
        }
        if (doubled[i] != expectedPattern2) {
            flipsPattern2++;
        }
        if (i >= n) {
            int leftIdx = i - n;
            char leftExpected1 = (leftIdx % 2 == 0) ? '0' : '1';
            char leftExpected2 = (leftIdx % 2 == 0) ? '1' : '0';
            if (doubled[leftIdx] != leftExpected1) {
                flipsPattern1--;
            }
            if (doubled[leftIdx] != leftExpected2) {
                flipsPattern2--;
            }
        }
        if (i >= n - 1) {
            minFlips = Math.Min(minFlips, Math.Min(flipsPattern1, flipsPattern2));
        }
    }
    return minFlips;
}
```

---

## 1980. Find Unique Binary String

**Difficulty:** Medium

**Problem:** Given an array of strings `nums` containing `n` unique binary strings each of length `n`, return a binary string of length `n` that does not appear in `nums`. If there are multiple answers, you may return any of them.

```csharp
public string FindDifferentBinaryString(string[] nums) {
    StringBuilder sb = new();
    for(int i = 0; i < nums.Length; i++){
        if(nums[i][i].Equals('0')){
            sb.Append("1");
        }else{
            sb.Append("0");
        }
    }
    return sb.ToString();
}
```

---

## 2977. Minimum Cost to Convert String II

**Difficulty:** Hard

**Problem:** You are given two 0-indexed strings `source` and `target`, both of length `n` and consisting of lowercase English letters. You are also given two 0-indexed string arrays `original` and `changed`, and an integer array `cost`. Return the minimum cost to convert the string `source` to the string `target` using any number of operations.

```csharp
public long MinimumCost(string source, string target, string[] original, string[] changed, int[] cost) {
    int n = source.Length;
    int m = original.Length;

    // Build mapping from string to index
    Dictionary<string, int> strToIdx = new();
    int idx = 0;
    for (int i = 0; i < m; i++) {
        if (!strToIdx.ContainsKey(original[i])) strToIdx[original[i]] = idx++;
        if (!strToIdx.ContainsKey(changed[i])) strToIdx[changed[i]] = idx++;
    }

    int numNodes = idx;
    long INF = long.MaxValue / 2;

    // Floyd-Warshall
    long[,] dist = new long[numNodes, numNodes];
    for (int i = 0; i < numNodes; i++) {
        for (int j = 0; j < numNodes; j++) {
            dist[i, j] = (i == j) ? 0 : INF;
        }
    }

    for (int i = 0; i < m; i++) {
        int u = strToIdx[original[i]];
        int v = strToIdx[changed[i]];
        dist[u, v] = Math.Min(dist[u, v], cost[i]);
    }

    for (int k = 0; k < numNodes; k++) {
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (dist[i, k] + dist[k, j] < dist[i, j]) {
                    dist[i, j] = dist[i, k] + dist[k, j];
                }
            }
        }
    }

    // Collect all possible substring lengths
    HashSet<int> lengths = new();
    foreach (string s in original) lengths.Add(s.Length);

    // DP
    long[] dp = new long[n + 1];
    Array.Fill(dp, INF);
    dp[0] = 0;

    for (int i = 0; i < n; i++) {
        if (dp[i] == INF) continue;

        // If characters match, can skip
        if (source[i] == target[i]) {
            dp[i + 1] = Math.Min(dp[i + 1], dp[i]);
        }

        // Try all possible lengths
        foreach (int len in lengths) {
            if (i + len > n) continue;
            string sub1 = source.Substring(i, len);
            string sub2 = target.Substring(i, len);

            if (strToIdx.ContainsKey(sub1) && strToIdx.ContainsKey(sub2)) {
                int u = strToIdx[sub1];
                int v = strToIdx[sub2];
                if (dist[u, v] < INF) {
                    dp[i + len] = Math.Min(dp[i + len], dp[i] + dist[u, v]);
                }
            }
        }
    }

    return dp[n] >= INF ? -1 : dp[n];
}
```
