
package com.github.e13mort.stf.model.user;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Settings {

    @SerializedName("deviceListActiveTabs")
    @Expose
    private DeviceListActiveTabs deviceListActiveTabs;
    @SerializedName("deviceListColumns")
    @Expose
    private List<DeviceListColumn> deviceListColumns = new ArrayList<DeviceListColumn>();
    @SerializedName("deviceListSort")
    @Expose
    private DeviceListSort deviceListSort;
    @SerializedName("lastUsedDevice")
    @Expose
    private String lastUsedDevice;
    @SerializedName("platform")
    @Expose
    private String platform;

    /**
     * 
     * @return
     *     The deviceListActiveTabs
     */
    public DeviceListActiveTabs getDeviceListActiveTabs() {
        return deviceListActiveTabs;
    }

    /**
     * 
     * @param deviceListActiveTabs
     *     The deviceListActiveTabs
     */
    public void setDeviceListActiveTabs(DeviceListActiveTabs deviceListActiveTabs) {
        this.deviceListActiveTabs = deviceListActiveTabs;
    }

    /**
     * 
     * @return
     *     The deviceListColumns
     */
    public List<DeviceListColumn> getDeviceListColumns() {
        return deviceListColumns;
    }

    /**
     * 
     * @param deviceListColumns
     *     The deviceListColumns
     */
    public void setDeviceListColumns(List<DeviceListColumn> deviceListColumns) {
        this.deviceListColumns = deviceListColumns;
    }

    /**
     * 
     * @return
     *     The deviceListSort
     */
    public DeviceListSort getDeviceListSort() {
        return deviceListSort;
    }

    /**
     * 
     * @param deviceListSort
     *     The deviceListSort
     */
    public void setDeviceListSort(DeviceListSort deviceListSort) {
        this.deviceListSort = deviceListSort;
    }

    /**
     * 
     * @return
     *     The lastUsedDevice
     */
    public String getLastUsedDevice() {
        return lastUsedDevice;
    }

    /**
     * 
     * @param lastUsedDevice
     *     The lastUsedDevice
     */
    public void setLastUsedDevice(String lastUsedDevice) {
        this.lastUsedDevice = lastUsedDevice;
    }

    /**
     * 
     * @return
     *     The platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 
     * @param platform
     *     The platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

}
