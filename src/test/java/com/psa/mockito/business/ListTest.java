package com.psa.mockito.business;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pyaesoneaung at 11/18/2021
 */
public class ListTest {

    @Test
    public void letsMockListSize(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
    }

    @Test
    public void letsMockListSize_ReturnMultipleValues(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2,listMock.size());
        assertEquals(3,listMock.size());
    }

    @Test
    public void letsMockListGet(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenReturn("Mockito");
        assertEquals("Mockito",listMock.get(0));
        assertEquals("Mockito",listMock.get(1));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockList_throwException(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something wrong"));
        listMock.get(0);
    }

    @Test
    public void letsMockListGet_withBDD(){
        //given
        List<String> listMock = mock(List.class);
        when(listMock.get(anyInt())).thenReturn("Mockito");

        //when
        String firstElement = listMock.get(0);

        //then
        assertThat(firstElement,is("Mockito"));
    }
}
