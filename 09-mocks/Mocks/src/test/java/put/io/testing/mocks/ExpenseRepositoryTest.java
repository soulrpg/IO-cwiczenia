package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;

public class ExpenseRepositoryTest {
    private ExpenseRepository expRp;
    private IFancyDatabase database;

    @BeforeEach
    void setUp(){
        database = mock(IFancyDatabase.class);
        when(database.queryAll()).thenReturn(Collections.emptyList());
        expRp = new ExpenseRepository(database);
        expRp.loadExpenses();
    }


    @Test
    void testLoadExpenses(){
        InOrder inOrder = inOrder(database);
        inOrder.verify(database).connect();
        inOrder.verify(database).queryAll();
        inOrder.verify(database).close();
        assertEquals(true, expRp.getExpenses().isEmpty());
    }

    @Test
    void testSaveExpenses(){
        for(int i = 0; i < 5; i++) {
            expRp.addExpense(new Expense());
        }
        expRp.saveExpenses();
        InOrder inOrder = inOrder(database);
        inOrder.verify(database).connect();
        inOrder.verify(database).queryAll();
        inOrder.verify(database).close();
        verify(database, times(5)).persist(any(Expense.class));
    }
}
