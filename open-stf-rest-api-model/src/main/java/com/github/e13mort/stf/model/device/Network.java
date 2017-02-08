
package com.github.e13mort.stf.model.device;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Network {

    private Boolean connected;
    private Boolean failover;
    private Boolean roaming;
    private String subtype;
    private String type;

    /**
     * 
     * @return
     *     The connected
     */
    public Boolean getConnected() {
        return connected;
    }

    /**
     * 
     * @param connected
     *     The connected
     */
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    /**
     * 
     * @return
     *     The failover
     */
    public Boolean getFailover() {
        return failover;
    }

    /**
     * 
     * @param failover
     *     The failover
     */
    public void setFailover(Boolean failover) {
        this.failover = failover;
    }

    /**
     * 
     * @return
     *     The roaming
     */
    public Boolean getRoaming() {
        return roaming;
    }

    /**
     * 
     * @param roaming
     *     The roaming
     */
    public void setRoaming(Boolean roaming) {
        this.roaming = roaming;
    }

    /**
     * 
     * @return
     *     The subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * 
     * @param subtype
     *     The subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
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

}
