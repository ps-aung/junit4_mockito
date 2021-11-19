package com.psa.mockito.business;

import com.psa.mockito.data.api.TodoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyaesoneaung at 11/18/2021
 */
public class TodoBusinessImpl{

    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodoRelatedToString(String user) {
        List<String> filteredTodos = new ArrayList<String>();
        List<String> todos = todoService.retrieveTodos(user);
        for(String todo: todos){
            if(todo.contains("Spring")){
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void deleteTodoNotRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        for(String todo: todos){
            if(!todo.contains("Spring")){
                todoService.deleteTodo(todo);
            }
        }
    }
}
