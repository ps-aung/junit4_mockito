package com.psa.mockito.business;

import com.psa.mockito.data.api.TodoService;
import com.psa.mockito.data.api.TodoServiceStub;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pyaesoneaung at 11/18/2021
 */
public class TodoBusinessImplStubTest {

    @Test
    public void testRetrieveTodoRelatedToString(){
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);
        List<String> filteredTodos = todoBusiness.retrieveTodoRelatedToString("Dummy");
        assertEquals(2,filteredTodos.size());
    }
}