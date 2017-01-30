
package com.github.e13mort.stf.model.device;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Memory {

    @SerializedName("ram")
    @Expose
    private Integer ram;
    @SerializedName("rom")
    @Expose
    private Integer rom;

    /**
     * 
     * @return
     *     The ram
     */
    public Integer getRam() {
        return ram;
    }

    /**
     * 
     * @param ram
     *     The ram
     */
    public void setRam(Integer ram) {
        this.ram = ram;
    }

    /**
     * 
     * @return
     *     The rom
     */
    public Integer getRom() {
        return rom;
    }

    /**
     * 
     * @param rom
     *     The rom
     */
    public void setRom(Integer rom) {
        this.rom = rom;
    }

}
