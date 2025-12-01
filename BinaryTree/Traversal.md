# 20. Valid Parentheses

```java
    public List<Integer> Traversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root,list);
        return list;
    }

// PreorderTraversal
    private void dfs(TreeNode node, List<Integer> list) {
        if(node == null) return;
        list.add(node.val);
        dfs(node.left,list);
        dfs(node.right,list);
    }

// inorderTraversal
    private void dfs(TreeNode node, List<Integer> list) {
        if(node == null) return;
        dfs(node.left,list);
        list.add(node.val);
        dfs(node.right,list);
    }

// postorderTraversal
    private void dfs(TreeNode node, List<Integer> list) {
        if(node == null) return;
        dfs(node.left,list);
        dfs(node.right,list);
        list.add(node.val);
    }

```
