
package com.github.e13mort.stf.model.device;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Browser {

    @SerializedName("apps")
    @Expose
    private List<App> apps = new ArrayList<App>();
    @SerializedName("selected")
    @Expose
    private Boolean selected;

    /**
     * 
     * @return
     *     The apps
     */
    public List<App> getApps() {
        return apps;
    }

    /**
     * 
     * @param apps
     *     The apps
     */
    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    /**
     * 
     * @return
     *     The selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * 
     * @param selected
     *     The selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
