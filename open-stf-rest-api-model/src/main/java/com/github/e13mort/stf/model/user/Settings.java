
package com.github.e13mort.stf.model.user;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Settings {

    private DeviceListActiveTabs deviceListActiveTabs;
    private List<DeviceListColumn> deviceListColumns = new ArrayList<DeviceListColumn>();
    private DeviceListSort deviceListSort;
    private String lastUsedDevice;
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
