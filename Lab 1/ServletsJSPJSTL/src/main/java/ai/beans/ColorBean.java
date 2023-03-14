/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ai.beans;

/**
 *
 * @author student
 */
public class ColorBean {
    private String foregroundColor;

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }
    private String backgroundColor;
    
    private Boolean hasBorder;

    public void setHasBorder(Boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    public Boolean getHasBorder() {
        return hasBorder;
    }
    
    public ColorBean() {
    }
}
