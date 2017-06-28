package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import com.github.e13mort.stf.model.device.Provider;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.github.e13mort.stf.adapter.filters.ProviderPredicate.Type.EXCLUDE;
import static com.github.e13mort.stf.adapter.filters.ProviderPredicate.Type.INCLUDE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProviderPredicateTest extends SingleMockedStringFieldTest {

    @Test
    public void testIncludeEmptyPredicateEmmit4Values() throws Exception {
        test(create(INCLUDE, ""), 4);
    }

    @Test
    public void testExcludeEmptyPredicateEmmit1Values() throws Exception {
        test(create(EXCLUDE, ""), 1).assertValue(new Predicate<Device>() {
            @Override
            public boolean test(@NonNull Device device) throws Exception {
                return device.getProvider().getName() == null;
            }
        });
    }

    @Test
    public void testIncludeStringProviderWillEmmit4Values() throws Exception {
        test(create(INCLUDE, "Provider"),4);
    }

    @Test
    public void testIncludeString1WillEmmit1Values() throws Exception {
        test(create(INCLUDE, "1"),1);
    }

    @Test
    public void testIncludeString2WillEmmit2Values() throws Exception {
        test(create(INCLUDE, "2"),2);
    }

    @Test
    public void testExcludeStringProviderWillEmmit1Values() throws Exception {
        test(create(EXCLUDE, "Provider"),1);
    }

    @Test
    public void testExcludeString2WillEmmit3Values() throws Exception {
        test(create(EXCLUDE, "2"),3);
    }

    @Test
    public void testIncludeStringAnyOnNullProviderWontEmmitValue() throws Exception {
        getDeviceObservableWithNullProvider()
                .filter(create(INCLUDE, "any"))
                .test().assertValueCount(0);

    }

    @Test
    public void testExcludeStringAnyOnNullProviderWillEmmitValue() throws Exception {
        getDeviceObservableWithNullProvider()
                .filter(create(EXCLUDE, "any"))
                .test().assertValueCount(1);

    }

    private Observable<Device> getDeviceObservableWithNullProvider() {
        Device device = mock(Device.class);
        when(device.getProvider()).thenReturn(null);
        return Observable.just(device);
    }

    @Override
    protected String[] getStrings() {
        return new String[] {null, "provider1", "provider2", "provider2", "provider3"};
    }

    @Override
    protected Device mockDevice(String param) {
        Device mock = mock(Device.class);
        Provider provider = new Provider();
        provider.setName(param);
        when(mock.getProvider()).thenReturn(provider);
        return mock;
    }

    private ProviderPredicate create(ProviderPredicate.Type type, String... templates) {
        return new ProviderPredicate(type, Arrays.asList(templates));
    }

    private TestObserver<Device> test(ProviderPredicate predicate, int count) {
        TestObserver<Device> testObserver = testDataObservable
                .filter(predicate)
                .test();
        testObserver.assertValueCount(count);
        return testObserver;
    }
}