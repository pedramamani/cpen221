package formula;

public class Bool implements Boolean {

    Boolean left;
    Boolean right;
    String bool;

    public Bool (String bool, Boolean left, Boolean right) {
        this.left = left;
        this.right = right;
        this.bool = bool;
    }

    public boolean eval() {
        boolean l = this.left.eval();
        boolean r = this.right.eval();

        switch (this.bool) {
            case "and":
                return l && r;
            case "or":
                return l || r;
        }
        return null;
    }
}
