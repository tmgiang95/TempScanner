package com.poa.tempscanner.ui.main.CDCSetting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CDCSettingModel {

    @SerializedName("cdcQuestionnaire")
    @Expose
    private boolean cdcQuestionnaire;

    @SerializedName("cdcMask")
    @Expose
    private boolean cdcMask;

    @SerializedName("cdcRequireRoleEmployee")
    @Expose
    private boolean cdcRequireRoleEmployee;

    @SerializedName("cdcRequireRoleVisitor")
    @Expose
    private boolean cdcRequireRoleVisitor;

    @SerializedName("cdcRequireRoleUnregister")
    @Expose
    private boolean cdcRequireRoleUnregister;


    public boolean isCdcRequireRoleEmployee() {
        return cdcRequireRoleEmployee;
    }

    public void setCdcRequireRoleEmployee(boolean cdcRequireRoleEmployee) {
        this.cdcRequireRoleEmployee = cdcRequireRoleEmployee;
    }

    public boolean isCdcRequireRoleVisitor() {
        return cdcRequireRoleVisitor;
    }

    public void setCdcRequireRoleVisitor(boolean cdcRequireRoleVisitor) {
        this.cdcRequireRoleVisitor = cdcRequireRoleVisitor;
    }

    public boolean isCdcRequireRoleUnregister() {
        return cdcRequireRoleUnregister;
    }

    public void setCdcRequireRoleUnregister(boolean cdcRequireRoleUnregister) {
        this.cdcRequireRoleUnregister = cdcRequireRoleUnregister;
    }

    public boolean isCdcQuestionnaire() {
        return cdcQuestionnaire;
    }

    public void setCdcQuestionnaire(boolean cdcQuestionnaire) {
        this.cdcQuestionnaire = cdcQuestionnaire;
    }

    public boolean isCdcMask() {
        return cdcMask;
    }

    public void setCdcMask(boolean cdcMask) {
        this.cdcMask = cdcMask;
    }

    //    @Override
//    public String toString() {
//        return "EmailSettingModel{" +
//                "emailAlert=" + emailAlert +
//                ", useCustomStmp=" + useCustomStmp +
//                ", emailMask=" + emailMask +
//                ", smtpServer='" + smtpServer + '\'' +
//                ", smtpPort='" + smtpPort + '\'' +
//                ", smtpUser='" + smtpUser + '\'' +
//                ", smtpPassword='" + smtpPassword + '\'' +
//                ", emailRecipient='" + emailRecipient + '\'' +
//                ", emailRegisterUser=" + emailRegisterUser +
//                ", visitorStranger=" + visitorStranger +
//                ", showPhoto=" + showPhoto +
//                ", smtpSsl=" + smtpSsl +
//                ", smtpTls=" + smtpTls +
//                ", showUserType=" + showUserType +
//                ", sendTestEmail=" + sendTestEmail +
//                '}';
//    }
}
