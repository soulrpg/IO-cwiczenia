package put.io.testing.mocks;

import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyDatabase implements IFancyDatabase {
    public void connect() {
    }

    public <T> void persist(T obj) {
    }

    public <T> List<T> queryAll() {
        return Collections.emptyList();
    }

    public void close() {
    }
}
