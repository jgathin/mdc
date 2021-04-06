package com.magnolia.mdc.models.Forms;

import com.magnolia.mdc.models.AbstractEntity;
import com.magnolia.mdc.models.User;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PostMDCForm extends AbstractEntity {

    @NotBlank(message = "Invoice number required.")
    @Size(min = 3, max = 15, message = "Invoice number must be between 3 and 15 characters")
    private String invoiceNumber;

    @NotBlank
    @Size(min = 3, max = 30, message = "Please enter valid Installer")
    private String installer1;

    @NotBlank
    @Size(min = 3, max = 30, message = "Please enter valid Installer")
    private String installer2;


    private String programmer;

    @NotBlank
    @Size(min = 3, max = 30, message = "Please enter valid System Designer")
    private String systemDesigner;


    private Integer speakerWireUsed;
    private Integer catWireUsed;
    private Integer coaxWireUsed;

    @NotBlank(message = "If not parts used type: 'NO PARTS'")
    @Size(min = 2, max = 500, message = "Description too long!")
    private String cPartsUsed;

    @NotBlank(message = "If no network used type: 'NO NETWORK'")
    @Size(min = 2, max = 500, message = "Description too long!")
    private String networkInfo;

    @NotBlank(message = "Please type in description of room summary!")
    @Size(min = 2, max = 1500, message = "Description too long!")
    private String roomSummary;

    @NotBlank(message = "Please type in description of control summary!")
    @Size(min = 2, max = 1500, message = "Description too long!")
    private String controlSummary;

    @NotBlank(message = "Please type in follow up summary!")
    @Size(min = 2, max = 1500, message = "Description too long!")
    private String followUpSummary;

    public PostMDCForm () {};

    public PostMDCForm(String invoiceNumber, String installer1, String installer2, String programmer,
                       String systemDesigner,
                       Integer speakerWireUsed, Integer catWireUsed, Integer coaxWireUsed, String cPartsUsed,
                       String networkInfo, String roomSummary, String controlSummary, String followUpSummary) {
        this.invoiceNumber = invoiceNumber;
        this.installer1 = installer1;
        this.installer2 = installer2;
        this.programmer = programmer;
        this.systemDesigner = systemDesigner;
        this.speakerWireUsed = speakerWireUsed;
        this.catWireUsed = catWireUsed;
        this.coaxWireUsed = coaxWireUsed;
        this.cPartsUsed = cPartsUsed;
        this.networkInfo = networkInfo;
        this.roomSummary = roomSummary;
        this.controlSummary = controlSummary;
        this.followUpSummary = followUpSummary;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInstaller1() {
        return installer1;
    }

    public void setInstaller1(String installer1) {
        this.installer1 = installer1;
    }

    public String getInstaller2() {
        return installer2;
    }

    public void setInstaller2(String installer2) {
        this.installer2 = installer2;
    }

    public String getProgrammer() {
        return programmer;
    }

    public void setProgrammer(String programmer) {
        this.programmer = programmer;
    }

    public String getSystemDesigner() {
        return systemDesigner;
    }

    public void setSystemDesigner(String systemDesigner) {
        this.systemDesigner = systemDesigner;
    }

    public Integer getSpeakerWireUsed() {
        return speakerWireUsed;
    }

    public void setSpeakerWireUsed(Integer speakerWireUsed) {
        this.speakerWireUsed = speakerWireUsed;
    }

    public Integer getCatWireUsed() {
        return catWireUsed;
    }

    public void setCatWireUsed(Integer catWireUsed) {
        this.catWireUsed = catWireUsed;
    }

    public Integer getCoaxWireUsed() {
        return coaxWireUsed;
    }

    public void setCoaxWireUsed(Integer coaxWireUsed) {
        this.coaxWireUsed = coaxWireUsed;
    }

    public String getcPartsUsed() {
        return cPartsUsed;
    }

    public void setcPartsUsed(String cPartsUsed) {
        this.cPartsUsed = cPartsUsed;
    }

    public String getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(String networkInfo) {
        this.networkInfo = networkInfo;
    }

    public String getRoomSummary() {
        return roomSummary;
    }

    public void setRoomSummary(String roomSummary) {
        this.roomSummary = roomSummary;
    }

    public String getControlSummary() {
        return controlSummary;
    }

    public void setControlSummary(String controlSummary) {
        this.controlSummary = controlSummary;
    }

    public String getFollowUpSummary() {
        return followUpSummary;
    }

    public void setFollowUpSummary(String followUpSummary) {
        this.followUpSummary = followUpSummary;
    }
}
