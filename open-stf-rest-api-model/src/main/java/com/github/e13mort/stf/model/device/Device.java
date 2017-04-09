
package com.github.e13mort.stf.model.device;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Device {

    private String abi;
    private Boolean airplaneMode;
    private Battery battery;
    private Browser browser;
    private String channel;
    private String createdAt;
    private Display display;
    private String manufacturer;
    private String model;
    private Network network;
    private String notes;
    private Object operator;
    private Object owner;
    private Phone phone;
    private String platform;
    private String presenceChangedAt;
    private Boolean present;
    private String product;
    private Provider provider;
    private Boolean ready;
    private Object remoteConnectUrl;
    private List<Object> reverseForwards = new ArrayList<Object>();
    private int sdk;
    private String serial;
    private Integer status;
    private String statusChangedAt;
    private Object usage;
    private String usageChangedAt;
    private String version;
    private Boolean using;
    private String name;
    private String releasedAt;
    private String image;
    private Cpu cpu;
    private Memory memory;
    private Boolean remoteConnect;

    /**
     * 
     * @return
     *     The abi
     */
    public String getAbi() {
        return abi;
    }

    /**
     * 
     * @param abi
     *     The abi
     */
    public void setAbi(String abi) {
        this.abi = abi;
    }

    /**
     * 
     * @return
     *     The airplaneMode
     */
    public Boolean getAirplaneMode() {
        return airplaneMode;
    }

    /**
     * 
     * @param airplaneMode
     *     The airplaneMode
     */
    public void setAirplaneMode(Boolean airplaneMode) {
        this.airplaneMode = airplaneMode;
    }

    /**
     * 
     * @return
     *     The battery
     */
    public Battery getBattery() {
        return battery;
    }

    /**
     * 
     * @param battery
     *     The battery
     */
    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    /**
     * 
     * @return
     *     The browser
     */
    public Browser getBrowser() {
        return browser;
    }

    /**
     * 
     * @param browser
     *     The browser
     */
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    /**
     * 
     * @return
     *     The channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 
     * @param channel
     *     The channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The display
     */
    public Display getDisplay() {
        return display;
    }

    /**
     * 
     * @param display
     *     The display
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

    /**
     * 
     * @return
     *     The manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 
     * @param manufacturer
     *     The manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * 
     * @return
     *     The model
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     *     The network
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * 
     * @param network
     *     The network
     */
    public void setNetwork(Network network) {
        this.network = network;
    }

    /**
     * 
     * @return
     *     The notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 
     * @param notes
     *     The notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 
     * @return
     *     The operator
     */
    public Object getOperator() {
        return operator;
    }

    /**
     * 
     * @param operator
     *     The operator
     */
    public void setOperator(Object operator) {
        this.operator = operator;
    }

    /**
     * 
     * @return
     *     The owner
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    public void setOwner(Object owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The phone
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *     The phone
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
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

    /**
     * 
     * @return
     *     The presenceChangedAt
     */
    public String getPresenceChangedAt() {
        return presenceChangedAt;
    }

    /**
     * 
     * @param presenceChangedAt
     *     The presenceChangedAt
     */
    public void setPresenceChangedAt(String presenceChangedAt) {
        this.presenceChangedAt = presenceChangedAt;
    }

    /**
     * 
     * @return
     *     The present
     */
    public Boolean getPresent() {
        return present;
    }

    /**
     * 
     * @param present
     *     The present
     */
    public void setPresent(Boolean present) {
        this.present = present;
    }

    /**
     * 
     * @return
     *     The product
     */
    public String getProduct() {
        return product;
    }

    /**
     * 
     * @param product
     *     The product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * 
     * @return
     *     The provider
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * 
     * @param provider
     *     The provider
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    /**
     * 
     * @return
     *     The ready
     */
    public Boolean getReady() {
        return ready;
    }

    /**
     * 
     * @param ready
     *     The ready
     */
    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    /**
     * 
     * @return
     *     The remoteConnect
     */
    public Boolean getRemoteConnect() {
        return remoteConnect;
    }

    /**
     * 
     * @param remoteConnect
     *     The remoteConnect
     */
    public void setRemoteConnect(Boolean remoteConnect) {
        this.remoteConnect = remoteConnect;
    }

    /**
     * 
     * @return
     *     The remoteConnectUrl
     */
    public Object getRemoteConnectUrl() {
        return remoteConnectUrl;
    }

    /**
     * 
     * @param remoteConnectUrl
     *     The remoteConnectUrl
     */
    public void setRemoteConnectUrl(Object remoteConnectUrl) {
        this.remoteConnectUrl = remoteConnectUrl;
    }

    /**
     * 
     * @return
     *     The reverseForwards
     */
    public List<Object> getReverseForwards() {
        return reverseForwards;
    }

    /**
     * 
     * @param reverseForwards
     *     The reverseForwards
     */
    public void setReverseForwards(List<Object> reverseForwards) {
        this.reverseForwards = reverseForwards;
    }

    /**
     * 
     * @return
     *     The sdk
     */
    public int getSdk() {
        return sdk;
    }

    /**
     * 
     * @param sdk
     *     The sdk
     */
    public void setSdk(int sdk) {
        this.sdk = sdk;
    }

    /**
     * 
     * @return
     *     The serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * 
     * @param serial
     *     The serial
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The statusChangedAt
     */
    public String getStatusChangedAt() {
        return statusChangedAt;
    }

    /**
     * 
     * @param statusChangedAt
     *     The statusChangedAt
     */
    public void setStatusChangedAt(String statusChangedAt) {
        this.statusChangedAt = statusChangedAt;
    }

    /**
     * 
     * @return
     *     The usage
     */
    public Object getUsage() {
        return usage;
    }

    /**
     * 
     * @param usage
     *     The usage
     */
    public void setUsage(Object usage) {
        this.usage = usage;
    }

    /**
     * 
     * @return
     *     The usageChangedAt
     */
    public String getUsageChangedAt() {
        return usageChangedAt;
    }

    /**
     * 
     * @param usageChangedAt
     *     The usageChangedAt
     */
    public void setUsageChangedAt(String usageChangedAt) {
        this.usageChangedAt = usageChangedAt;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The using
     */
    public Boolean getUsing() {
        return using;
    }

    /**
     * 
     * @param using
     *     The using
     */
    public void setUsing(Boolean using) {
        this.using = using;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The releasedAt
     */
    public String getReleasedAt() {
        return releasedAt;
    }

    /**
     * 
     * @param releasedAt
     *     The releasedAt
     */
    public void setReleasedAt(String releasedAt) {
        this.releasedAt = releasedAt;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The cpu
     */
    public Cpu getCpu() {
        return cpu;
    }

    /**
     * 
     * @param cpu
     *     The cpu
     */
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    /**
     * 
     * @return
     *     The memory
     */
    public Memory getMemory() {
        return memory;
    }

    /**
     * 
     * @param memory
     *     The memory
     */
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Device{" +
                "abi='" + abi + '\'' +
                ", airplaneMode=" + airplaneMode +
                ", battery=" + battery +
                ", browser=" + browser +
                ", channel='" + channel + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", display=" + display +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", network=" + network +
                ", notes='" + notes + '\'' +
                ", operator=" + operator +
                ", owner=" + owner +
                ", phone=" + phone +
                ", platform='" + platform + '\'' +
                ", presenceChangedAt='" + presenceChangedAt + '\'' +
                ", present=" + present +
                ", product='" + product + '\'' +
                ", provider=" + provider +
                ", ready=" + ready +
                ", remoteConnectUrl=" + remoteConnectUrl +
                ", reverseForwards=" + reverseForwards +
                ", sdk='" + sdk + '\'' +
                ", serial='" + serial + '\'' +
                ", status=" + status +
                ", statusChangedAt='" + statusChangedAt + '\'' +
                ", usage=" + usage +
                ", usageChangedAt='" + usageChangedAt + '\'' +
                ", version='" + version + '\'' +
                ", using=" + using +
                ", name='" + name + '\'' +
                ", releasedAt='" + releasedAt + '\'' +
                ", image='" + image + '\'' +
                ", cpu=" + cpu +
                ", memory=" + memory +
                ", remoteConnect=" + remoteConnect +
                '}';
    }
}
