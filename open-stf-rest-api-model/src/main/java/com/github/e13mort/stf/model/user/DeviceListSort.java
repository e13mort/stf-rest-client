
package com.github.e13mort.stf.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class DeviceListSort {

    @SerializedName("fixed")
    @Expose
    private List<SortType> fixed = new ArrayList<SortType>();
    @SerializedName("user")
    @Expose
    private List<SortType> user = new ArrayList<SortType>();

    /**
     * 
     * @return
     *     The fixed
     */
    public List<SortType> getFixed() {
        return fixed;
    }

    /**
     * 
     * @param sortTypes
     *     The sortTypes
     */
    public void setFixed(List<SortType> sortTypes) {
        this.fixed = sortTypes;
    }

    /**
     * 
     * @return
     *     The user
     */
    public List<SortType> getUser() {
        return user;
    }

    /**
     * 
     * @param sortTypes
     *     The sortTypes
     */
    public void setUser(List<SortType> sortTypes) {
        this.user = sortTypes;
    }

}
