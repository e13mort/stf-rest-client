
package com.github.e13mort.stf.model.device;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Battery {

    @SerializedName("health")
    @Expose
    private String health;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("scale")
    @Expose
    private Integer scale;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("temp")
    @Expose
    private Float temp;
    @SerializedName("voltage")
    @Expose
    private Double voltage;

    /**
     * 
     * @return
     *     The health
     */
    public String getHealth() {
        return health;
    }

    /**
     * 
     * @param health
     *     The health
     */
    public void setHealth(String health) {
        this.health = health;
    }

    /**
     * 
     * @return
     *     The level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 
     * @param level
     *     The level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 
     * @return
     *     The scale
     */
    public Integer getScale() {
        return scale;
    }

    /**
     * 
     * @param scale
     *     The scale
     */
    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The temp
     */
    public Float getTemp() {
        return temp;
    }

    /**
     * 
     * @param temp
     *     The temp
     */
    public void setTemp(Float temp) {
        this.temp = temp;
    }

    /**
     * 
     * @return
     *     The voltage
     */
    public Double getVoltage() {
        return voltage;
    }

    /**
     * 
     * @param voltage
     *     The voltage
     */
    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

}
