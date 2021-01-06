package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ExpenseManagerTest {
    private ExpenseRepository expRp;
    @BeforeEach
    void setUp(){
        expRp = mock(ExpenseRepository.class);
        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(new Expense());
        expenseList.add(new Expense());
        expenseList.add(new Expense());
        expenseList.add(new Expense());
        expenseList.get(0).setAmount(5);
        expenseList.get(1).setAmount(6);
        expenseList.get(2).setAmount(7);
        expenseList.get(3).setAmount(8);
        when(expRp.getExpenses()).thenReturn(expenseList);
    }

    @Test
    void testCalculateTotal(){

        FancyService fs = mock(FancyService.class);

        ExpenseManager expMn = new ExpenseManager(expRp, fs);
        assertEquals(5+6+7+8, expMn.calculateTotal());

    }

    @Test
    void testCalculateTotalForCategory(){
        FancyService fs = mock(FancyService.class);

        ArrayList<Expense> homeExpense = new ArrayList<Expense>();
        ArrayList<Expense> carExpense = new ArrayList<Expense>();

        homeExpense.add(new Expense());
        homeExpense.add(new Expense());

        homeExpense.get(0).setAmount(6);
        homeExpense.get(0).setCategory("Home");
        homeExpense.get(1).setAmount(8);
        homeExpense.get(1).setCategory("Home");

        carExpense.add(new Expense());
        carExpense.add(new Expense());

        carExpense.get(0).setAmount(3);
        carExpense.get(0).setCategory("Car");
        carExpense.get(1).setAmount(5);
        carExpense.get(1).setCategory("Car");

        when(expRp.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(expRp.getExpensesByCategory("Home")).thenReturn(homeExpense);
        when(expRp.getExpensesByCategory("Car")).thenReturn(carExpense);

        // kolejnosc oczekiwan ma znaczenie, w przypadku ustawienia anyString() na ostatniej pozycji wszystkie testy zwracaja 0

        ExpenseManager exMn = new ExpenseManager(expRp, fs);

        assertEquals(0, exMn.calculateTotalForCategory("Food"));
        assertEquals(0, exMn.calculateTotalForCategory("Sport"));
        assertEquals(homeExpense.get(0).getAmount() + homeExpense.get(1).getAmount(), exMn.calculateTotalForCategory("Home"));
        assertEquals(carExpense.get(0).getAmount() + carExpense.get(1).getAmount(), exMn.calculateTotalForCategory("Car"));
    }

    @Test
    void testCalculateTotalInDollars() throws ConnectException {
        FancyService fs = mock(FancyService.class);
        ExpenseManager exMn = new ExpenseManager(expRp, fs);
        when(fs.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Object mock = invocation.getMock();
                        return (Object)((double) args[0] * 0.25);
                    }
                });
        //when(fs.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(new ConnectException());
        long dol = exMn.calculateTotalInDollars();

        assertEquals(6.0, dol);
        //assertEquals(-1, dol);
    }
}
