package oddevenbag;
import java.util.*;

class OddEvenBag {

    private Map<Integer, Integer> bag;
    private int countEven;
    private int countOdd;
    private int sum;

    // Create an empty OddEvenBag
    OddEvenBag() {
        this.bag = new HashMap<>();
        this.countEven = 0;
        this.countOdd = 0;
        this.sum = 0;
    }

    // Create an OddEvenBag using the elements in the provided array
// requires: seedArray is not null
    OddEvenBag(int[] seedArray) {
        this.bag = new HashMap<>();
        this.countOdd = 0;
        this.countEven = 0;
        this.sum = 0;

        for (int seed : seedArray) {
            if (this.bag.keySet().contains(seed)) {
                this.bag.replace(seed, this.bag.get(seed) + 1);
            } else {
                this.bag.put(seed, 0);
            }

            if (seed % 2 == 0) {
                this.countEven++;
            } else {
                this.countOdd++;
            }

            this.sum += seed;
        }
    }

    // add x to the OddEvenBag
    void add (int x) {
        if (bag.keySet().contains(x)) {
            bag.replace(x, bag.get(x) + 1);
        } else {
            bag.put(x, 0);
        }

        if (x % 2 == 0) {
            countEven++;
        } else {
            countOdd++;
        }

        sum += x;
    }

    // remove x from the OddEvenBag
// if x does not exist in the Bag then do nothing
    void remove(int x) {
        if (x % 2 == 0) {
            countEven--;
        } else {
            countOdd--;
        }
        sum -= x;

        if (bag.get(x) == 1) {
            bag.remove(x);
        } else {
            bag.replace(x, bag.get(x) - 1);
        }
    }

    // increment each value in the OddEvenBag by 1
    void increment() {
        Map<Integer, Integer> newBag = new HashMap<>();

        for (int key : bag.keySet()) {
            newBag.put(key, bag.get(key) + 1);

            if (key % 2 == 0) {
                countEven++;
            } else {
                countOdd++;
            }

            sum += 1;
        }

        bag = newBag;
    }

    // decrement each value in the OddEvenBag by 1
    void decrement() {
        Map<Integer, Integer> newBag = new HashMap<>();

        for (int key : bag.keySet()) {
            if (bag.get(key) == 1) {
                bag.remove(key);
            } else {
                newBag.put(key, bag.get(key) - 1);
            }

            if (key % 2 == 0) {
                countEven--;
            } else {
                countOdd--;
            }

            sum -= 1;
        }

        bag = newBag;
    }

    // return true if this OddEvenBag contains x
// and false otherwise
    boolean contains(int x) {
        return bag.keySet().contains(x);
    }

    // count the occurrences of x in the OddEvenBag
    int getCount(int x) {
        if (bag.keySet().contains(x)) {
            return bag.get(x);
        } else {
            return 0;
        }
    }

    // return the sum of the values in the OddEvenBag
    long sum() {
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OddEvenBag)) {
            return false;
        }

        OddEvenBag otherBag = (OddEvenBag) obj;
        return (otherBag.countEven == countEven && otherBag.countOdd == countOdd);
    }

    @Override
    public int hashCode() {
        return countOdd + countEven;
    }
}