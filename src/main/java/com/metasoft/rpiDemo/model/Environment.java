package com.metasoft.rpiDemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "environment")
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "environment_id")
    private int id;

    @Column(name = "environment_name")
    private String environmentName;

    @Column(name = "environment_info")
    private String environmentInfo;

    @Column(name = "active")
    private Integer active;

    @Column(name = "environment_ip")
    private String environmentIp;

    @Column(name = "server_ip")
    private String serverIp;

    @Column(name = "server_port")
    private String serverPort;

    @Column(name = "environment_ack")
    private String environmentAck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnvironment_name() {
        return environmentName;
    }

    public void setEnvironment_name(String environment_name) {
        this.environmentName = environment_name;
    }

    public String getEnvironment_info() {
        return environmentInfo;
    }

    public void setEnvironment_info(String environment_info) {
        this.environmentInfo = environment_info;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getEnvironment_ip() {
        return environmentIp;
    }

    public void setEnvironment_ip(String environment_ip) {
        this.environmentIp = environment_ip;
    }

    public String getServer_ip() {
        return serverIp;
    }

    public void setServer_ip(String server_ip) {
        this.serverIp = server_ip;
    }

    public String getServer_port() {
        return serverPort;
    }

    public void setServer_port(String server_port) {
        this.serverPort = server_port;
    }

    public String getEnvironment_ack() {
        return environmentAck;
    }

    public void setEnvironment_ack(String environment_ack) {
        this.environmentAck = environment_ack;
    }
}