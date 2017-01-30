
package com.github.e13mort.stf.model.device;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Display {

    @SerializedName("density")
    @Expose
    private Float density;
    @SerializedName("fps")
    @Expose
    private Double fps;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("rotation")
    @Expose
    private Integer rotation;
    @SerializedName("secure")
    @Expose
    private Boolean secure;
    @SerializedName("size")
    @Expose
    private Double size;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("xdpi")
    @Expose
    private Double xdpi;
    @SerializedName("ydpi")
    @Expose
    private Double ydpi;

    /**
     * 
     * @return
     *     The density
     */
    public Float getDensity() {
        return density;
    }

    /**
     * 
     * @param density
     *     The density
     */
    public void setDensity(Float density) {
        this.density = density;
    }

    /**
     * 
     * @return
     *     The fps
     */
    public Double getFps() {
        return fps;
    }

    /**
     * 
     * @param fps
     *     The fps
     */
    public void setFps(Double fps) {
        this.fps = fps;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The rotation
     */
    public Integer getRotation() {
        return rotation;
    }

    /**
     * 
     * @param rotation
     *     The rotation
     */
    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    /**
     * 
     * @return
     *     The secure
     */
    public Boolean getSecure() {
        return secure;
    }

    /**
     * 
     * @param secure
     *     The secure
     */
    public void setSecure(Boolean secure) {
        this.secure = secure;
    }

    /**
     * 
     * @return
     *     The size
     */
    public Double getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    public void setSize(Double size) {
        this.size = size;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The xdpi
     */
    public Double getXdpi() {
        return xdpi;
    }

    /**
     * 
     * @param xdpi
     *     The xdpi
     */
    public void setXdpi(Double xdpi) {
        this.xdpi = xdpi;
    }

    /**
     * 
     * @return
     *     The ydpi
     */
    public Double getYdpi() {
        return ydpi;
    }

    /**
     * 
     * @param ydpi
     *     The ydpi
     */
    public void setYdpi(Double ydpi) {
        this.ydpi = ydpi;
    }

}
