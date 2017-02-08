package com.github.e13mort.stf.model;

public class RemoteConnectUserDeviceResponse {

    private String remoteConnectUrl = null;

    private String serial = null;

    public String getRemoteConnectUrl() {
        return remoteConnectUrl;
    }

    public void setRemoteConnectUrl(String remoteConnectUrl) {
        this.remoteConnectUrl = remoteConnectUrl;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RemoteConnectUserDeviceResponse {\n");

        sb.append("    remoteConnectUrl: ").append(toIndentedString(remoteConnectUrl)).append("\n");
        sb.append("    serial: ").append(toIndentedString(serial)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
