
package com.github.e13mort.stf.model.user;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AdbKey {

    @SerializedName("fingerprint")
    @Expose
    private String fingerprint;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * 
     * @return
     *     The fingerprint
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * 
     * @param fingerprint
     *     The fingerprint
     */
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
