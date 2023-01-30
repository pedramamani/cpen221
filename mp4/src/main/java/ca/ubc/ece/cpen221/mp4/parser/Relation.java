package formula;

public class Relation implements Boolean {

    Name left;
    Name right;
    String rel;

    public Relation (String rel, Name left, Name right) {
        this.left = left;
        this.right = right;
        this.rel = rel;
    }

    public boolean eval() {
        String l = this.left.eval();
        String r = this.right.eval();

        switch (this.rel) {
            case ">":
                return l > r;
            case "<":
                return l < r;
            case "=":
                return l == r;
            case "!=":
                return l != r;
            case ">=":
                return l >= r;
            case "<=":
                return l <= r;
        }
        return null;
    }
}
