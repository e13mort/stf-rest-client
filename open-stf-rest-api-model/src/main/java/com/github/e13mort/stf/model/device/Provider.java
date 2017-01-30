
package com.github.e13mort.stf.model.device;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Provider {

    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("name")
    @Expose
    private String name;

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
