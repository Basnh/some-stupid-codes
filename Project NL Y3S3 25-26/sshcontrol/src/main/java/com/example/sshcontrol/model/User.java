package com.example.sshcontrol.model;

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<ServerInfo> servers;

    public User() {}

    public User(String username, String password, List<ServerInfo> servers) {
        this.username = username;
        this.password = password;
        this.servers = servers;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<ServerInfo> getServers() { return servers; }
    public void setServers(List<ServerInfo> servers) { this.servers = servers; }
}