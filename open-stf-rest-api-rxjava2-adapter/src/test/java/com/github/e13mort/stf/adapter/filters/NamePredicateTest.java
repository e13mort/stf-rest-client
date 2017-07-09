package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NamePredicateTest extends SingleMockedStringFieldTest {

    @Test
    public void testThereAreAllItemsEmitedWithoutThePredicate() throws Exception {
        testDataObservable.test().assertValueCount(getStrings().length);
    }

    @Test
    public void testEmptyNameWillFilterNullName() throws Exception {
        TestObserver<Device> observer = testDataObservable.filter(new NamePredicate("")).test();
        observer.assertValueCount(3);
        observer.assertValueAt(0, new TestDevicePredicate("name1"));
        observer.assertValueAt(1, new TestDevicePredicate("another_name"));
        observer.assertValueAt(2, new TestDevicePredicate("name2"));
    }

    @Test
    public void testInvalidNameWontEmitAnyValues() throws Exception {
        testDataObservable.filter(new NamePredicate("invalid"))
                .test().assertNoValues();
    }

    @Test
    void testTwoItemsFilterWillEmmitTwoObjects() {
        TestObserver<Device> observer =
                testDataObservable.filter(new NamePredicate(Arrays.asList("1", "another"))).test();
        observer.assertValueAt(0, new TestDevicePredicate("name1"));
        observer.assertValueAt(1, new TestDevicePredicate("another_name"));
    }

    @Override
    protected String[] getStrings() {
        return new String[]{"name1", "another_name", null, "name2"};
    }

    @Override
    protected Device mockDevice(String param) {
        Device device = mock(Device.class);
        when(device.getName()).thenReturn(param);
        return device;
    }

    private static class TestDevicePredicate implements Predicate<Device> {

        private String name;

        TestDevicePredicate(String name) {
            this.name = name;
        }

        @Override
        public boolean test(@NonNull Device device) throws Exception {
            return device.getName().equals(name);
        }
    }
}