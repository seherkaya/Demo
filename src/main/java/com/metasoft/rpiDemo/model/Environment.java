package com.metasoft.rpiDemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

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
    private Integer id;

    @Column(name = "environment_name")
    private String environment_name;

    @Column(name = "environment_info")
    private String environment_info;

    @Column(name = "active")
    private Integer active;

    @Column(name = "environment_ip")
    private String environment_ip;

    @Column(name = "server_ip")
    private String server_ip;

    @Column(name = "server_port")
    private String server_port;

    @Column(name = "environment_ack")
    private String environment_ack;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnvironment_name() {
        return environment_name;
    }

    public void setEnvironment_name(String environment_name) {
        this.environment_name = environment_name;
    }

    public String getEnvironment_info() {
        return environment_info;
    }

    public void setEnvironment_info(String environment_info) {
        this.environment_info = environment_info;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getEnvironment_ip() {
        return environment_ip;
    }

    public void setEnvironment_ip(String environment_ip) {
        this.environment_ip = environment_ip;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getServer_port() {
        return server_port;
    }

    public void setServer_port(String server_port) {
        this.server_port = server_port;
    }

    public String getEnvironment_ack() {
        return environment_ack;
    }

    public void setEnvironment_ack(String environment_ack) {
        this.environment_ack = environment_ack;
    }
}