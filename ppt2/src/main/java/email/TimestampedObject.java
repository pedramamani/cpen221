package email;

/**
 * A timestamped object has an integer timestamp.
 */
public interface TimestampedObject {
    /**
     * Obtain the timestamp of the object
     * @return the timestamp of the object
     */
    public int getTimestamp();
}
