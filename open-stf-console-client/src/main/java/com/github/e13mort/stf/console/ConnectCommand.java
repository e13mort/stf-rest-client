package com.github.e13mort.stf.console;

import com.github.e13mort.stf.client.FarmClient;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Notification;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

class ConnectCommand implements Commands.Command {
    private final FarmClient client;

    ConnectCommand(FarmClient client) {
        this.client = client;
    }

    @Override
    public void execute(RunOptions options) {
        client.connectToDevices(options.getDeviceParams())
                .subscribe(new ConnectionNotificationConsumer());
    }

    private static class ConnectionNotificationConsumer implements Consumer<Notification<Device>> {

        static final String UNKNOWN_ERROR = "Unknown error";

        @Override
        public void accept(@NonNull Notification<Device> deviceNotification) throws Exception {
            if (deviceNotification.isOnNext()) {
                handleConnectedDevice(deviceNotification.getValue());
            } else if (deviceNotification.isOnError()) {
                handleNotConnectedDevice(deviceNotification.getError());
            }
        }

        private void handleConnectedDevice(Device device) {
            System.out.println(String.format("Connected to %s", device.getName()));
        }

        private void handleNotConnectedDevice(Throwable error) {
            System.out.println(String.format("Failed to connect: %s", error != null ? error.getMessage() : UNKNOWN_ERROR));
        }
    }
}
