package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Observable;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public abstract class SingleMockedStringFieldTest {
    protected Observable<Device> testDataObservable;

    @BeforeEach
    public void setup() {
        String[] strings = getStrings();
        ArrayList<Device> devices = new ArrayList<>();
        for (String string : strings) {
            devices.add(mockDevice(string));

        }
        testDataObservable = Observable.fromIterable(devices);
    }

    protected abstract String[] getStrings();

    protected abstract Device mockDevice(String param);
}
