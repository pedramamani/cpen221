package email;

import java.util.Comparator;
import java.util.List;

public class TimestampComparator {

    public static final Comparator<TimestampedObject> ASCENDING = new Comparator<TimestampedObject>() {
        public int compare(TimestampedObject ts1, TimestampedObject ts2) {
            return ts1.getTimestamp() - ts2.getTimestamp();
        }
    };

    public static final Comparator<TimestampedObject> DESCENDING = new Comparator<TimestampedObject>() {
        public int compare(TimestampedObject ts1, TimestampedObject ts2) {
            return ts2.getTimestamp() - ts1.getTimestamp();
        }
    };

    public static final Comparator<List<Email>> LIST_DESCENDING = new Comparator<List<Email>>() {
        public int compare(List<Email> list1, List<Email> list2) {
            return list2.get(0).getTimestamp() - list1.get(0).getTimestamp();
        }
    };
}
