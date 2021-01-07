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

    @Test
    void testLoadExpensesPrev(){
        IFancyDatabase database = new MyDatabase();
        expRp = new ExpenseRepository(database);
        expRp.loadExpenses();
        assertEquals(expRp.getExpenses(), Collections.emptyList());
    }

    @Test
    void testLoadExpenses(){
        IFancyDatabase database = mock(IFancyDatabase.class);
        when(database.queryAll()).thenReturn(Collections.emptyList());
        expRp = new ExpenseRepository(database);
        expRp.loadExpenses();
        InOrder inOrder = inOrder(database);
        inOrder.verify(database).connect();
        inOrder.verify(database).queryAll();
        inOrder.verify(database).close();
        assertEquals(true, expRp.getExpenses().isEmpty());
    }

    @Test
    void testSaveExpenses(){
        IFancyDatabase database = mock(IFancyDatabase.class);
        when(database.queryAll()).thenReturn(Collections.emptyList());
        expRp = new ExpenseRepository(database);
        expRp.loadExpenses();
        for(int i = 0; i < 5; i++) {
            expRp.addExpense(new Expense());
        }
        expRp.saveExpenses();
        InOrder inOrder = inOrder(database);
        inOrder.verify(database).connect();
        inOrder.verify(database).queryAll();
        inOrder.verify(database, times(5)).persist(any(Expense.class));
        inOrder.verify(database).close();

    }
}
