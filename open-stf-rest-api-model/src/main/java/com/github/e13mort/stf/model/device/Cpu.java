
package com.github.e13mort.stf.model.device;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Cpu {

    private Integer cores;
    private Float freq;
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
