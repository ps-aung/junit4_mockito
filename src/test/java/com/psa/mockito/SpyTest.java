package com.psa.mockito;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * Created by pyaesoneaung at 11/19/2021
 */
public class SpyTest {

    @Test
    public void test(){
        List arrayListMock = mock(ArrayList.class);
        assertEquals(0,arrayListMock.size());
        when(arrayListMock.size()).thenReturn(5);
        arrayListMock.add("Dummy");
        assertEquals(5,arrayListMock.size());
    }

    @Test
    public void testSpy(){
        List arrayListSpy = spy(ArrayList.class);
        assertEquals(0,arrayListSpy.size());
        arrayListSpy.add("Dummy");
        assertEquals(1,arrayListSpy.size());
        when(arrayListSpy.size()).thenReturn(5);
        assertEquals(5,arrayListSpy.size());
        verify(arrayListSpy).add("Dummy");
        then(arrayListSpy).should().add("Dummy");
    }
}
