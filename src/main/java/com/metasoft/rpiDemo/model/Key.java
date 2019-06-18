package com.metasoft.rpiDemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "key")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "key_id")
    private int id;

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "key_info")
    private String keyInfo;

    @Column(name = "active")
    private int active;

    @Column(name = "key_ip")
    private String keyIp;

    @Column(name = "server_ip")
    private String serverIp;

    @Column(name = "server_port")
    private String serverPort;

    @Column(name = "key_ack")
    private String keyAck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyInfo() {
        return keyInfo;
    }

    public void setKeyInfo(String keyInfo) {
        this.keyInfo = keyInfo;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {  this.active = active; }

    public String getKeyIp() {
        return keyIp;
    }

    public void setKeyIp(String keyIp) {
        this.keyIp = keyIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getKeyAck() {
        return keyAck;
    }

    public void setKeyAck(String keyAck) {
        this.keyAck = keyAck;
    }
}