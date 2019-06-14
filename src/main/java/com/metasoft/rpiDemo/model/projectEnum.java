package com.metasoft.rpiDemo.model;


public class projectEnum {
    public enum Roles {
        ADMIN(1, "ADMIN"),
        USER (2, "USER");

        private int id;
        private String role;

        Roles(int id, String role) {
            this.id = id;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }
    public enum Environment {
        ;

        private int id;

        private String environmentName;

        private String environmentInfo;

        private Integer active;

        private String environmentIp;

        private String serverIp;

        private String serverPort;


        private String environmentAck;

        Environment (int id, String environmentName,String environmentInfo, Integer active,
                     String environmentIp,  String serverIp, String serverPort, String  environmentAck) {
            this.id = id;
            this.environmentName = environmentName;
            this.environmentInfo = environmentInfo;
            this.active = active;
            this.environmentIp = environmentIp;
            this.serverIp = serverIp;
            this.serverPort = serverPort;
            this.environmentAck = environmentAck;

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEnvironmentName() {
            return environmentName;
        }

        public void setEnvironmentName(String environmentName) {
            this.environmentName = environmentName;
        }

        public String getEnvironmentInfo() {
            return environmentInfo;
        }

        public void setEnvironmentInfo(String environmentInfo) {
            this.environmentInfo = environmentInfo;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public String getEnvironmentIp() {
            return environmentIp;
        }

        public void setEnvironmentIp(String environmentIp) {
            this.environmentIp = environmentIp;
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

        public String getEnvironmentAck() {
            return environmentAck;
        }

        public void setEnvironmentAck(String environmentAck) {
            this.environmentAck = environmentAck;
        }
    }


}