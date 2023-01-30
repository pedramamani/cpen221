package ca.ubc.ece.cpen221.mp4.db221;

import org.junit.Test;
import java.util.Arrays;

public class DB221Tests {

    @Test
    public void test1() {
        DB221 db1 = new DB221("db1");
        DB221 db2 = new DB221("db2", Arrays.asList("src/books.json"));
        db2.print("books");
        db2.store("books", "");
        db2.snapshot();
    }
}
