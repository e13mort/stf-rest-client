
package com.github.e13mort.stf.model.device;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Cpu {

    @SerializedName("cores")
    @Expose
    private Integer cores;
    @SerializedName("freq")
    @Expose
    private Float freq;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * 
     * @return
     *     The cores
     */
    public Integer getCores() {
        return cores;
    }

    /**
     * 
     * @param cores
     *     The cores
     */
    public void setCores(Integer cores) {
        this.cores = cores;
    }

    /**
     * 
     * @return
     *     The freq
     */
    public Float getFreq() {
        return freq;
    }

    /**
     * 
     * @param freq
     *     The freq
     */
    public void setFreq(Float freq) {
        this.freq = freq;
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

}
