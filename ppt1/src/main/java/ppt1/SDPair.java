package ppt1;

/* DO NOT CHANGE! */

/**
 * Represents a source-destination pair.
 */
public class SDPair {
    private int src;
    private int dst;

    /**
     * Create a new SDPair
     * @param src
     * @param dst
     */
    public SDPair(int src, int dst) {
        this.src = src;
        this.dst = dst;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SDPair) {
            SDPair other = (SDPair) o;
            return (this.src == other.src && this.dst == other.dst);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return src;
    }

    /**
     * Obtain the source point
     * @return the source point
     */
    public int getSrc() {
        return src;
    }

    /**
     * Obtain the destination point
     * @return the destination point
     */
    public int getDst() {
        return dst;
    }
}
