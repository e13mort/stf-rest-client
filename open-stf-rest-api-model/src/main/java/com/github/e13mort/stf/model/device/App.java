
package com.github.e13mort.stf.model.device;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class App {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("selected")
    @Expose
    private Boolean selected;
    @SerializedName("system")
    @Expose
    private Boolean system;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("developer")
    @Expose
    private String developer;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * 
     * @return
     *     The system
     */
    public Boolean getSystem() {
        return system;
    }

    /**
     * 
     * @param system
     *     The system
     */
    public void setSystem(Boolean system) {
        this.system = system;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The developer
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * 
     * @param developer
     *     The developer
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

}
