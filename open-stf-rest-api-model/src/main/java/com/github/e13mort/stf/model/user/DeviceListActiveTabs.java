
package com.github.e13mort.stf.model.user;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DeviceListActiveTabs {

    @SerializedName("details")
    @Expose
    private Boolean details;
    @SerializedName("icons")
    @Expose
    private Boolean icons;

    /**
     * 
     * @return
     *     The details
     */
    public Boolean getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *     The details
     */
    public void setDetails(Boolean details) {
        this.details = details;
    }

    /**
     * 
     * @return
     *     The icons
     */
    public Boolean getIcons() {
        return icons;
    }

    /**
     * 
     * @param icons
     *     The icons
     */
    public void setIcons(Boolean icons) {
        this.icons = icons;
    }

}
