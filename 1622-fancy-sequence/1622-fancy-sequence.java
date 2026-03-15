class Node {
    Node left;
    Node right;
    int l;
    int r;
    int mid;
    long value;
    long lazyAdd;
    long lazyMultiply = 1;
    public Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}
class SegmentTree {
    private Node root = new Node(1, (int) 1e5 + 1);
    private static final int MOD = (int) 1e9 + 7;
    public SegmentTree() {
    }
    public void modifyAdd(int l, int r, int inc) {
        modifyAdd(l, r, inc, root);
    }
    private void modifyAdd(int l, int r, int inc, Node node) {
        if (l > r) return;
        if (node.l >= l && node.r <= r) {
            node.value = (node.value + (long)(node.r - node.l + 1) * inc) % MOD;
            node.lazyAdd = (node.lazyAdd + inc) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node.mid) modifyAdd(l, r, inc, node.left);
        if (r > node.mid) modifyAdd(l, r, inc, node.right);
        pushup(node);
    }
    public void modifyMul(int l, int r, int m) {
        modifyMul(l, r, m, root);
    }
    private void modifyMul(int l, int r, int m, Node node) {
        if (l > r) return;
        if (node.l >= l && node.r <= r) {
            node.value = (node.value * m) % MOD;
            node.lazyAdd = (node.lazyAdd * m) % MOD;
            node.lazyMultiply = (node.lazyMultiply * m) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node.mid) modifyMul(l, r, m, node.left);
        if (r > node.mid) modifyMul(l, r, m, node.right);
        pushup(node);
    }
    public int query(int l, int r) {
        return query(l, r, root);
    }
    private int query(int l, int r, Node node) {
        if (l > r) return 0;
        if (node.l >= l && node.r <= r) return (int) node.value;
        pushdown(node);
        int result = 0;
        if (l <= node.mid) result = (result + query(l, r, node.left)) % MOD;
        if (r > node.mid) result = (result + query(l, r, node.right)) % MOD;
        return result;
    }
    private void pushup(Node node) {
        node.value = (node.left.value + node.right.value) % MOD;
    }
    private void pushdown(Node node) {
        if (node.left == null) node.left = new Node(node.l, node.mid);
        if (node.right == null) node.right = new Node(node.mid + 1, node.r);
        if (node.lazyAdd != 0 || node.lazyMultiply != 1) {
            Node left = node.left;
            Node right = node.right;
            left.value = (left.value * node.lazyMultiply + (long)(left.r - left.l + 1) * node.lazyAdd) % MOD;
            left.lazyAdd = (left.lazyAdd * node.lazyMultiply + node.lazyAdd) % MOD;
            left.lazyMultiply = (left.lazyMultiply * node.lazyMultiply) % MOD;
            right.value = (right.value * node.lazyMultiply + (long)(right.r - right.l + 1) * node.lazyAdd) % MOD;
            right.lazyAdd = (right.lazyAdd * node.lazyMultiply + node.lazyAdd) % MOD;
            right.lazyMultiply = (right.lazyMultiply * node.lazyMultiply) % MOD;
            node.lazyAdd = 0;
            node.lazyMultiply = 1;
        }
    }
}
class Fancy {
    private int sequenceLength;
    private SegmentTree segmentTree = new SegmentTree();
    public Fancy() {
        
    }
    
    public void append(int val) {
        ++sequenceLength;
        segmentTree.modifyAdd(sequenceLength, sequenceLength, val);
    }
    
    public void addAll(int inc) {
        segmentTree.modifyAdd(1, sequenceLength, inc);
    }
    
    public void multAll(int m) {
        segmentTree.modifyMul(1, sequenceLength, m);
    }
    
    public int getIndex(int idx) {
        if (idx >= sequenceLength) return -1;
        return segmentTree.query(idx + 1, idx + 1);
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */