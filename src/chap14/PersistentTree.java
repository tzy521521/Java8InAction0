package chap14;

/**
 * Created by tzy on 2017/7/28.
 * 14.2.2
 */
public class PersistentTree {

    static class Tree {
        private String key;
        private int val;
        private Tree left, right;

        public Tree(String k, int v, Tree l, Tree r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }
    }

    public static Tree update(String k, int newval, Tree t) {
        if (t == null)
            t = new Tree(k, newval, null, null);
        else if (k.equals(t.key))
            t.val = newval;
        else if (k.compareTo(t.key) < 0)
            t.left = update(k, newval, t.left);
        else
            t.right = update(k, newval, t.right);
        return t;
    }

    public static Tree fupdate(String k, int newval, Tree t) {
        return (t == null) ?
                new Tree(k, newval, null, null) :
                k.equals(t.key) ?
                        new Tree(k, newval, t.left, t.right) :
                        k.compareTo(t.key) < 0 ?
                                new Tree(t.key, t.val, fupdate(k,newval, t.left), t.right) :
                                new Tree(t.key, t.val, t.left, fupdate(k,newval, t.right));
    }
}
