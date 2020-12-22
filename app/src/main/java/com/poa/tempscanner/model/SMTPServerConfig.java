package com.poa.tempscanner.model;

public class SMTPServerConfig {
    private boolean isSSL;

    private boolean isTLS;

    private String password;

    private String port;
    private String fromEmail;

    private String server;

    private String user;

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPort() {
        return this.port;
    }

    public String getServer() {
        return this.server;
    }

    public String getUser() {
        return this.user;
    }

    public boolean isSSL() {
        return this.isSSL;
    }

    public boolean isTLS() {
        return this.isTLS;
    }

    public void setPassword(String paramString) {
        this.password = paramString;
    }

    public void setPort(String paramString) {
        this.port = paramString;
    }

    public void setSSL(boolean paramBoolean) {
        this.isSSL = paramBoolean;
    }

    public void setServer(String paramString) {
        this.server = paramString;
    }

    public void setTLS(boolean paramBoolean) {
        this.isTLS = paramBoolean;
    }

    public void setUser(String paramString) {
        this.user = paramString;
    }
}