package com.example.sshcontrol.service;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class SSHService {

    public String executeCommand(String host, String user, String password, String command) {
    StringBuilder output = new StringBuilder();
    try {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, 22);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect(30000);

        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setErrStream(System.err);

        InputStream in = channel.getInputStream();
        channel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                output.append(new String(tmp, 0, i));
            }
            if (channel.isClosed()) break;
            Thread.sleep(100);
        }
        channel.disconnect();
        session.disconnect();
    } catch (Exception e) {
        output.append(e.getMessage());
    }
    return output.toString();
}

    public String executeCommandWithInput(String host, String user, String password, String command, String input) {
        StringBuilder output = new StringBuilder();
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(30000);

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            // Truyền mật khẩu sudo + nội dung file
            String fullInput = password + "\n" + input;
            channel.setInputStream(new ByteArrayInputStream(fullInput.getBytes(StandardCharsets.UTF_8)));
            channel.setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    output.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) break;
                Thread.sleep(100);
            }
            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            output.append(e.getMessage());
        }
        return output.toString();
    }
}