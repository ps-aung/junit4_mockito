package com.psa.mockito.data.api;

import java.util.List;

/**
 * Created by pyaesoneaung at 11/18/2021
 */
public interface TodoService {
    public List<String> retrieveTodos(String user);
    public void deleteTodo(String todo);
}
