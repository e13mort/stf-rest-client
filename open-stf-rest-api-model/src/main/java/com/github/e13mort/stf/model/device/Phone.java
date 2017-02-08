
package com.github.e13mort.stf.model.device;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Phone {

    private Object iccid;
    private String imei;
    private String network;
    private Object phoneNumber;

    /**
     * 
     * @return
     *     The iccid
     */
    public Object getIccid() {
        return iccid;
    }

    /**
     * 
     * @param iccid
     *     The iccid
     */
    public void setIccid(Object iccid) {
        this.iccid = iccid;
    }

    /**
     * 
     * @return
     *     The imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * 
     * @param imei
     *     The imei
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * 
     * @return
     *     The network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * 
     * @param network
     *     The network
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * 
     * @return
     *     The phoneNumber
     */
    public Object getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     *     The phoneNumber
     */
    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
