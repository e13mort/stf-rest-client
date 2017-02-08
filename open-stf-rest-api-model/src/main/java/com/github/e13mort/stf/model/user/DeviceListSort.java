
package com.github.e13mort.stf.model.user;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class DeviceListSort {

    private List<SortType> fixed = new ArrayList<SortType>();
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
