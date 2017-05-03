package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiLevelPredicateTest {

    private Observable<Device> data;

    @Before
    public void setUp() throws Exception {
        ArrayList<Device> objects = new ArrayList<>();
        objects.add(createDeviceMock(21));
        objects.add(createDeviceMock(21));
        objects.add(createDeviceMock(22));
        objects.add(createDeviceMock(19));
        objects.add(createDeviceMock(14));
        objects.add(createDeviceMock(25));
        data = Observable.fromIterable(objects);
    }

    @Test
    public void test21MinLevelEmmits4values() throws Exception {
        data.filter(new ApiLevel(21, ApiLevel.Kind.MIN)).test().assertValueCount(4);
    }

    @Test
    public void test21EqLevelEmmits2values() throws Exception {
        data.filter(new ApiLevel(21, ApiLevel.Kind.EQUALS)).test().assertValueCount(2);
    }

    @Test
    public void test21MaxLevelEmmits4values() throws Exception {
        data.filter(new ApiLevel(21, ApiLevel.Kind.MAX)).test().assertValueCount(4);
    }

    @Test
    public void test22LevelMinEmmits2Values() throws Exception {
        data.filter(new ApiLevel(22, ApiLevel.Kind.MIN)).test().assertValueCount(2);
    }

    @Test
    public void test14LevelMinEmmitsAllValues() throws Exception {
        data.filter(new ApiLevel(14, ApiLevel.Kind.MIN)).test().assertValueCount(6);
    }

    @Test
    public void test9LevelMinEmmitsAllValues() throws Exception {
        data.filter(new ApiLevel(9, ApiLevel.Kind.MIN)).test().assertValueCount(6);
    }

    @Test
    public void test9LevelMaxEmmitsNoValues() throws Exception {
        data.filter(new ApiLevel(9, ApiLevel.Kind.MAX)).test().assertNoValues();
    }

    private Device createDeviceMock(int sdk) {
        Device device = mock(Device.class);
        when(device.getSdk()).thenReturn(sdk);
        return device;
    }
}