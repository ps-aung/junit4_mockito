package com.psa.mockito.business;

import com.psa.mockito.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

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
public class TodoBusinessImplMockitoInjectMockTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TodoService mockTodoService;

    @InjectMocks
    TodoBusinessImpl todoBusiness;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodoRelatedToString_UsingAMock(){

        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");

        when(mockTodoService.retrieveTodos("Dummy")).thenReturn(todos);

        List<String> filteredTodos = todoBusiness.retrieveTodoRelatedToString("Dummy");
        assertEquals(2,filteredTodos.size());
    }

    @Test
    public void testRetrieveTodoRelatedToString_WithEmpthList(){

        List<String> todos = Arrays.asList();

        when(mockTodoService.retrieveTodos("Dummy")).thenReturn(todos);

        List<String> filteredTodos = todoBusiness.retrieveTodoRelatedToString("Dummy");
        assertEquals(0,filteredTodos.size());
    }

    @Test
    public void testRetrieveTodoRelatedToString_UsingMock_withBDD(){
        //given

        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");
        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);

        //when
        List<String> filteredTodos = todoBusiness.retrieveTodoRelatedToString("Dummy");

        //then
        assertThat(filteredTodos.size(),is(2));
    }

    @Test
    public void testDeleteTodoNotRelatedToString_UsingBDD(){
        //given

        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");

        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);

        //when
        todoBusiness.deleteTodoNotRelatedToSpring("Dummy");

        //then
        then(mockTodoService).should().deleteTodo("Learn Dance");

        then(mockTodoService).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodoNotRelatedToString_UsingBDD_argumentCapture(){
        //given

        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");

        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);

        //when
        todoBusiness.deleteTodoNotRelatedToSpring("Dummy");

        //then
        then(mockTodoService).should().deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(),is("Learn Dance"));
    }

    @Test
    public void testDeleteTodoNotRelatedToString_UsingBDD_argumentCaptureMultiple(){
        //given

        List<String> todos = Arrays.asList("Learn Hip Hop","Learn Spring","Learn Dance");

        given(mockTodoService.retrieveTodos("Dummy")).willReturn(todos);

        //when
        todoBusiness.deleteTodoNotRelatedToSpring("Dummy");

        //then
        then(mockTodoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
    }
}