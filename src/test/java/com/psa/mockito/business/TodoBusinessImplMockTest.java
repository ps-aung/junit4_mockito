package com.psa.mockito.business;

import com.psa.mockito.data.api.TodoService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * Created by pyaesoneaung at 11/18/2021
 */
public class TodoBusinessImplMockTest {

    @Test
    public void testRetrieveTodoRelatedToString_UsingAMock(){
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");

        when(mockTodoService.retrieveTodos("Dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);
        List<String> filteredTodos = todoBusiness.retrieveTodoRelatedToString("Dummy");
        assertEquals(2,filteredTodos.size());
    }

    @Test
    public void testRetrieveTodoRelatedToString_WithEmpthList(){
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList();

        when(mockTodoService.retrieveTodos("Dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);
        List<String> filteredTodos = todoBusiness.retrieveTodoRelatedToString("Dummy");
        assertEquals(0,filteredTodos.size());
    }

    @Test
    public void testRetrieveTodoRelatedToString_UsingMock_withBDD(){
        //given
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");
        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);

        //when
        List<String> filteredTodos = todoBusiness.retrieveTodoRelatedToString("Dummy");

        //then
        assertThat(filteredTodos.size(),is(2));
    }

    @Test
    public void testDeleteTodoNotRelatedToString_UsingBDD(){
        //given
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");
        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);

        //when
        todoBusiness.deleteTodoNotRelatedToSpring("Dummy");

        //then
        then(mockTodoService).should().deleteTodo("Learn Dance");

        then(mockTodoService).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodoNotRelatedToString_UsingBDD_argumentCapture(){

        //Declare ArgumentCapture
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //given
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");
        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);

        //when
        todoBusiness.deleteTodoNotRelatedToSpring("Dummy");

        //then
        then(mockTodoService).should().deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(),is("Learn Dance"));
    }

    @Test
    public void testDeleteTodoNotRelatedToString_UsingBDD_argumentCaptureMultiple(){

        //Declare ArgumentCapture
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //given
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Hip Hop","Learn Spring","Learn Dance");
        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);

        //when
        todoBusiness.deleteTodoNotRelatedToSpring("Dummy");

        //then
        then(mockTodoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
    }
}