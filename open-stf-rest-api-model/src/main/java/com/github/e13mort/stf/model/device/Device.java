
package com.github.e13mort.stf.model.device;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Device {

    @SerializedName("abi")
    @Expose
    private String abi;
    @SerializedName("airplaneMode")
    @Expose
    private Boolean airplaneMode;
    @SerializedName("battery")
    @Expose
    private Battery battery;
    @SerializedName("browser")
    @Expose
    private Browser browser;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("display")
    @Expose
    private Display display;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("network")
    @Expose
    private Network network;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("operator")
    @Expose
    private Object operator;
    @SerializedName("owner")
    @Expose
    private Object owner;
    @SerializedName("phone")
    @Expose
    private Phone phone;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("presenceChangedAt")
    @Expose
    private String presenceChangedAt;
    @SerializedName("present")
    @Expose
    private Boolean present;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("provider")
    @Expose
    private Provider provider;
    @SerializedName("ready")
    @Expose
    private Boolean ready;
    @SerializedName("remoteConnect")
    @Expose
    private Boolean remoteConnect;
    @SerializedName("remoteConnectUrl")
    @Expose
    private Object remoteConnectUrl;
    @SerializedName("reverseForwards")
    @Expose
    private List<Object> reverseForwards = new ArrayList<Object>();
    @SerializedName("sdk")
    @Expose
    private String sdk;
    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("statusChangedAt")
    @Expose
    private String statusChangedAt;
    @SerializedName("usage")
    @Expose
    private Object usage;
    @SerializedName("usageChangedAt")
    @Expose
    private String usageChangedAt;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("using")
    @Expose
    private Boolean using;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("releasedAt")
    @Expose
    private String releasedAt;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("cpu")
    @Expose
    private Cpu cpu;
    @SerializedName("memory")
    @Expose
    private Memory memory;

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
    public String getSdk() {
        return sdk;
    }

    /**
     * 
     * @param sdk
     *     The sdk
     */
    public void setSdk(String sdk) {
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

}
