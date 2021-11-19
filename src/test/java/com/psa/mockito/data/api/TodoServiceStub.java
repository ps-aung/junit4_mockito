package com.psa.mockito.data.api;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pyaesoneaung at 11/18/2021
 */
public class TodoServiceStub implements TodoService {

    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC","Learn Spring","Learn Dance");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}