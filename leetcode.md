# LeetCode Solutions (C#) - Microsoft

---

## 1. Two Sum

**Difficulty:** Easy

**Problem:** Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

```csharp
public int[] TwoSum(int[] nums, int target) {
    Dictionary<int,int> map = new();
    for(int i = 0; i < nums.Length;i++){
        int complement = target - nums[i];
        if(map.ContainsKey(complement)){
            return new int[]{i,map[complement]};
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
        var v1 = l1 != null ? l1.val : 0;
        var v2 = l2 != null ? l2.val : 0;
        var sum = v1 + v2 + carry;
        carry = sum / 10;
        var remain = sum % 10;
        ListNode node = new ListNode(remain);
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
    int left = 0;
    int longest = 0;
    Dictionary<char,int> map = new();
    for(int right = 0; right < s.Length; right++){
        if(map.ContainsKey(s[right]) && map[s[right]] >= left){
            left = map[s[right]] + 1;
        }
        map[s[right]] = right;
        longest = Math.Max(longest,right - left + 1);
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
    if(numRows == 1 || numRows >= s.Length) return s;
    StringBuilder[] rows = new StringBuilder[numRows];
    for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();
    int row = 0, dir = -1;
    foreach (char c in s) {
        rows[row].Append(c);
        if (row == 0 || row == numRows - 1) dir = -dir;
        row += dir;
    }
    StringBuilder res = new();
    foreach (StringBuilder sb in rows) res.Append(sb);
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
    if (strs.Length == 0) return "";
    string prefix = strs[0];
    for (int i = 1; i < strs.Length; i++) {
        while (strs[i].IndexOf(prefix) != 0) {
            prefix = prefix.Substring(0, prefix.Length - 1);
            if (prefix == "") return "";
        }
    }
    return prefix;
}
```

---

## 15. 3Sum

**Difficulty:** Medium

**Problem:** Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`. The solution set must not contain duplicate triplets.

```csharp
public IList<IList<int>> ThreeSum(int[] nums) {
    IList<IList<int>> res = new List<IList<int>>();
    Array.Sort(nums);
    for(int i = 0; i < nums.Length - 2; i++){
        if(i > 0 && nums[i] == nums[i-1]) continue;
        HashSet<int> set = new();
        for(int j = i + 1; j < nums.Length; j++){
            int complement = 0 - nums[i] - nums[j];
            if(set.Contains(complement)){
               res.Add(new List<int>{nums[i],nums[j],complement});
                while(j+1 < nums.Length && nums[j] == nums[j+1]){
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
    StringBuilder sb = new StringBuilder();
    backTrack(res, n, 0, 0, sb);
    return res;
}

public void backTrack(IList<string> res, int n, int open, int close, StringBuilder sb){
    if(n*2 == sb.Length){
        res.Add(sb.ToString());
        return;
    }

    if(open < n){
        sb.Append('(');
        backTrack(res,n,open+1,close,sb);
        sb.Remove(sb.Length - 1, 1);
    }

    if(close < open){
        sb.Append(')');
        backTrack(res,n,open,close+1,sb);
        sb.Remove(sb.Length - 1, 1);
    }
}
```

---

## 26. Remove Duplicates from Sorted Array

**Difficulty:** Easy

**Problem:** Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Return the number of unique elements.

```csharp
public int RemoveDuplicates(int[] nums) {
    if (nums.Length == 0) return 0;
    int i = 0;
    for (int j = 1; j < nums.Length; j++) {
        if (nums[j] != nums[i]) {
            i++;
            nums[i] = nums[j];
        }
    }
    return i + 1;
}
```

---

## 31. Next Permutation

**Difficulty:** Medium

**Problem:** Given an array of integers `nums`, find the next permutation of `nums`. The replacement must be in place and use only constant extra memory.

```csharp
public void NextPermutation(int[] nums) {
    int n = nums.Length;
    int i = n - 2;
    while (i >= 0 && nums[i] >= nums[i+1]) {
        i--;
    }
    if (i >= 0) {
        int j = n - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        Swap(nums, i, j);
    }
    Reverse(nums, i + 1, n - 1);
}

private void Swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

private void Reverse(int[] nums, int start, int end) {
    while (start < end) {
        Swap(nums, start, end);
        start++;
        end--;
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
    // monotonic stack
    Stack<int> stack= new();
    int maxArea =0;
    for(int i= 0 ; i < height.Length; i++){
        while(stack.Count > 0 && height[i] > height[stack.Peek()]){
            int bottom = stack.Pop();
            if(stack.Count == 0) break;
            int left = stack.Peek();
            int area = (i - left-1) * (Math.Min(height[left], height[i]) - height[bottom]);
            maxArea += area;
        }

        stack.Push(i);
    }

    return maxArea;
}
```

---

## 45. Jump Game II

**Difficulty:** Medium

**Problem:** You are given a 0-indexed array of integers `nums` of length `n`. You are initially positioned at `nums[0]`. Return the minimum number of jumps to reach `nums[n - 1]`.

```csharp
public int Jump(int[] nums) {
    int jumps = 0, currentEnd = 0, farthest = 0;
    for (int i = 0; i < nums.Length - 1; i++) {
        farthest = Math.Max(farthest, i + nums[i]);
        if (i == currentEnd) {
            jumps++;
            currentEnd = farthest;
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
    Backtrack(nums, new List<int>(), new bool[nums.Length], res);
    return res;
}

private void Backtrack(int[] nums, List<int> path, bool[] used, IList<IList<int>> res) {
    if (path.Count == nums.Length) {
        res.Add(new List<int>(path));
        return;
    }
    for (int i = 0; i < nums.Length; i++) {
        if (used[i]) continue;
        used[i] = true;
        path.Add(nums[i]);
        Backtrack(nums, path, used, res);
        path.RemoveAt(path.Count - 1);
        used[i] = false;
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
    Array.Sort(intervals,(a,b) => a[0] - b[0]);
    List<int[]> list = new();

    for(int i = 0; i < intervals.Length; i++){
        if(list.Count == 0 || intervals[i][0] > list[list.Count-1][1]){
            list.Add(intervals[i]);
        }else{
            list[list.Count-1][1] = Math.Max(list[list.Count-1][1],intervals[i][1]);
        }
    }

    return list.ToArray();
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
    if (s.Length < t.Length) return "";

    Dictionary<char, int> need = new(), window = new();
    foreach (char c in t) {
        need[c] = need.GetValueOrDefault(c, 0) + 1;
    }

    int left = 0, right = 0, valid = 0;
    int start = 0, len = int.MaxValue;

    while (right < s.Length) {
        char c = s[right];
        right++;
        if (need.ContainsKey(c)) {
            window[c] = window.GetValueOrDefault(c, 0) + 1;
            if (window[c] == need[c]) valid++;
        }

        while (valid == need.Count) {
            if (right - left < len) {
                start = left;
                len = right - left;
            }
            char d = s[left];
            left++;
            if (need.ContainsKey(d)) {
                if (window[d] == need[d]) valid--;
                window[d]--;
            }
        }
    }

    return len == int.MaxValue ? "" : s.Substring(start, len);
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
    return Dfs(preorder, 0, inorder.Length - 1);
}

public TreeNode Dfs(int[] preorder, int left, int right) {
    if (left > right) return null;
    int rootVal = preorder[preIndex++];
    TreeNode root = new TreeNode(rootVal);
    int mid = map[rootVal];
    root.left = Dfs(preorder, left, mid -1);
    root.right = Dfs(preorder, mid + 1, right);
    return root;
}
```

---

## 121. Best Time to Buy and Sell Stock

**Difficulty:** Easy

**Problem:** You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`th day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve.

```csharp
public int MaxProfit(int[] prices) {
    int max = 0;
    int minPrice = prices[0];
    for(int i = 1; i < prices.Length; i++){
        if(minPrice > prices[i]){
            minPrice = prices[i];
        }else{
            max = Math.Max(prices[i]-minPrice,max);
        }
    }
    return max;
}
```

---

## 125. Valid Palindrome

**Difficulty:** Easy

**Problem:** A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

```csharp
public bool IsPalindrome(string s) {
    int left = 0, right = s.Length - 1;
    while (left < right) {
        while (left < right && !char.IsLetterOrDigit(s[left])) left++;
        while (left < right && !char.IsLetterOrDigit(s[right])) right--;
        if (char.ToLower(s[left]) != char.ToLower(s[right])) return false;
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
public class Node{
    public int key;
    public int val;
    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}

public class LRUCache {
    int _capacity;
    Dictionary<int,LinkedListNode<Node>> _map;
    LinkedList<Node> _list;

    public LRUCache(int capacity) {
        _capacity = capacity;
        _map = new();
        _list = new();
    }

    public int Get(int key) {
        if(_map.ContainsKey(key)){
            var node = _map[key];
            _list.Remove(node);
            _list.AddFirst(node);
            return node.Value.val;
        }else{
            return -1;
        }
    }

    public void Put(int key, int value) {
        if(_map.ContainsKey(key)){
            var node = _map[key];
            node.Value.val = value;
            _list.Remove(node);
            _list.AddFirst(node);
        }else{
            if(_map.Count == _capacity){
                var tail = _list.Last;
                _map.Remove(tail.Value.key);
                _list.Remove(tail);
            }
            var node = new Node(key,value);
            var first = _list.AddFirst(node);
            _map[key] = first;
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
    int n = grid.Length;
    int m = grid[0].Length;
    bool[,] visit = new bool[n,m];
    int island = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(grid[i][j] == '1' && !visit[i,j]){
                island++;
                Bfs(grid,visit,i,j,n,m);
            }
        }
    }
    return island;
}

public void Bfs(char[][] grid, bool[,] visit, int i, int j, int n, int m){
    Queue<int[]> q = new();
    q.Enqueue(new int[]{i,j});
    int[][] dirs = new int[][] {
        new int[] {0, 1},
        new int[] {1, 0},
        new int[] {-1, 0},
        new int[] {0, -1}
    };

    while(q.Count != 0){
        int[] cur = q.Dequeue();
        foreach(int[] dir in dirs){
            int x = dir[0] + cur[0];
            int y = dir[1] + cur[1];
            if(x < 0 || y < 0 || x >=n || y >= m || visit[x,y] || grid[x][y] == '0') continue;
            visit[x,y] = true;
            q.Enqueue(new int[]{x,y});
        }
    }
}
```

---

## 202. Happy Number

**Difficulty:** Easy

**Problem:** Write an algorithm to determine if a number `n` is happy. A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1, or it loops endlessly in a cycle.

```csharp
public bool IsHappy(int n) {
    HashSet<int> set = new();

    while(n != 1){
        if(set.Contains(n)) return false;
        set.Add(n);
        int cur = 0;
        while(n>0){
            int temp = n % 10;
            cur += temp * temp;
            n = n /10;
        }
        n = cur;
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
    while(cur != null){
        ListNode nextStep = cur.next;
        cur.next = prev;
        prev = cur;
        cur = nextStep;
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
    private Stack<int> stackIn;
    private Stack<int> stackOut;

    public MyQueue() {
        stackIn = new Stack<int>();
        stackOut = new Stack<int>();
    }

    public void Push(int x) {
        stackIn.Push(x);
    }

    public int Pop() {
        if (stackOut.Count == 0) {
            while (stackIn.Count > 0) {
                stackOut.Push(stackIn.Pop());
            }
        }
        return stackOut.Pop();
    }

    public int Peek() {
        if (stackOut.Count == 0) {
            while (stackIn.Count > 0) {
                stackOut.Push(stackIn.Pop());
            }
        }
        return stackOut.Peek();
    }

    public bool Empty() {
        return stackIn.Count == 0 && stackOut.Count == 0;
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
    LinkedList<int> deque = new();

    for(int i = 0; i < n; i++){
        while(deque.Count > 0 && deque.First.Value < i - k +1){
            deque.RemoveFirst();
        }
        while(deque.Count > 0 && nums[deque.Last.Value] < nums[i]){
            deque.RemoveLast();
        }
        deque.AddLast(i);
        if(i >= k -1){
            res[i-k+1] = nums[deque.First.Value];
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
        if(maxStack.Count == 0 || num <= maxStack.Peek()){
            maxStack.Enqueue(num,-num);
        }else{
            minStack.Enqueue(num,num);
        }
        if(minStack.Count + 1 < maxStack.Count){
            int temp = maxStack.Dequeue();
            minStack.Enqueue(temp,temp);
        }else if(maxStack.Count < minStack.Count){
            int temp = minStack.Dequeue();
            maxStack.Enqueue(temp,-temp);
        }
    }

    public double FindMedian() {
        int count = minStack.Count + maxStack.Count;
        return count % 2 == 0
            ? (double)(minStack.Peek() + maxStack.Peek()) / 2
            : maxStack.Peek();
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
    map[0] = 1;
    int res = 0; int sum =0;
    foreach(int num in nums){
        sum += num;
        if(map.ContainsKey(sum - k)){
            res += map[sum-k];
        }
        map[sum] = map.GetValueOrDefault(sum,0) + 1;
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
    // Either the three largest, or two smallest (most negative) and the largest
    return Math.Max(
        nums[n-1] * nums[n-2] * nums[n-3],
        nums[0] * nums[1] * nums[n-1]
    );
}
```

---

## 678. Valid Parentheses String

**Difficulty:** Medium

**Problem:** Given a string `s` containing only three types of characters: `'('`, `')'` and `'*'`, return `true` if `s` is valid. `'*'` could be treated as a single right parenthesis `')'` or a single left parenthesis `'('` or an empty string `""`.

```csharp
public bool CheckValidString(string s) {
    var left = new Stack<int>();
    var star = new Stack<int>();
    for (int i = 0; i < s.Length; i++) {
        if(s[i] == '('){
            left.Push(i);
        }else if(s[i] == '*'){
            star.Push(i);
        }else{
            if(left.Count > 0) left.Pop();
            else if (star.Count > 0) star.Pop();
            else return false;
        }
    }
    while(left.Count > 0 && star.Count > 0){
        if(left.Pop() > star.Pop()) return false;
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
    List<int[]> res = new();
    int i = 0; int j = 0;

    while (i < firstList.Length && j < secondList.Length) {
        int start = Math.Max(firstList[i][0],secondList[j][0]);
        int end = Math.Min(firstList[i][1], secondList[j][1]);
        if(start <= end) res.Add(new int[]{start,end});
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
    for(int i = 0; i < prices.Length; i++){
        while(stack.Count > 0 && prices[stack.Peek()] >= prices[i]){
            int idx = stack.Pop();
            prices[idx] -= prices[i];
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
