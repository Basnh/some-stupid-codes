package com.example.sshcontrol.service;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
public class SSHService {

    public String executeCommand(String host, String user, String password, String command) {
        StringBuilder output = new StringBuilder();
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);

            // Bypass host key checking
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(30000); // 30 seconds timeout

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setInputStream(null);
            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] buffer = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(buffer, 0, 1024);
                    if (i < 0) break;
                    output.append(new String(buffer, 0, i));
                }
                if (channel.isClosed()) break;
                Thread.sleep(100);
            }

            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            output.append("Error: ").append(e.getMessage());
        }
        return output.toString();
    }
}
