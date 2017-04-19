package com.github.e13mort.stf.console;

import com.github.e13mort.stf.client.FarmClient;
import io.reactivex.Notification;
import io.reactivex.annotations.NonNull;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

class ConnectCommand implements Commands.Command {
    private final FarmClient client;

    ConnectCommand(FarmClient client) {
        this.client = client;
    }

    @Override
    public void execute(RunOptions options) {
        client.connectToDevices(options.getDeviceParams())
                .subscribe(new ConnectionNotificationSubscriber());
    }

    private static class ConnectionNotificationSubscriber implements Subscriber<Notification<String>> {

        static final String UNKNOWN_ERROR = "Unknown error";

        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(@NonNull Notification<String> deviceNotification) {
            if (deviceNotification.isOnNext()) {
                handleConnectedDevice(deviceNotification.getValue());
            } else if (deviceNotification.isOnError()) {
                handleNotConnectedDevice(deviceNotification.getError());
            }
        }

        @Override
        public void onError(Throwable t) {
            System.out.println("Failed to perform connection: " + t.getMessage());
        }

        @Override
        public void onComplete() {

        }

        private void handleConnectedDevice(String deviceIp) {
            System.out.println(String.format("%s", deviceIp));
        }

        private void handleNotConnectedDevice(Throwable error) {
            System.out.println(String.format("Failed to connect: %s", error != null ? error.getMessage() : UNKNOWN_ERROR));
        }
    }
}
