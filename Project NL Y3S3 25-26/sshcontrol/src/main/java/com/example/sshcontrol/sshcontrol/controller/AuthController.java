package com.example.sshcontrol.sshcontrol.controller;

import com.example.sshcontrol.model.User;
import com.example.sshcontrol.model.ServerInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AuthController {

    // Giả lập danh sách user (nên thay bằng DB thực tế)
    private static List<User> users = new ArrayList<>();
    static {
        List<ServerInfo> servers = new ArrayList<>();
        servers.add(new ServerInfo("Server 1", "192.168.1.10", "ubuntu", "123456"));
        servers.add(new ServerInfo("Server 2", "192.168.1.20", "ubuntu", "123456"));
        users.add(new User("admin", "admin", servers));
    }

    // Bước 1: Đăng nhập tài khoản hệ thống
    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        Optional<User> found = users.stream()
            .filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
            .findFirst();
        if (found.isPresent()) {
            session.setAttribute("user", found.get());
            return "redirect:/server-list";
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            model.addAttribute("user", new User());
            return "login";
        }
    }

    // Bước 2: Hiển thị danh sách máy chủ đã đăng ký
    @GetMapping("/server-list")
    public String serverList(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("servers", user.getServers());
        return "server-list";
    }

    // Bước 3: Đăng nhập SSH vào máy chủ
    @GetMapping("/server-login")
    public String showServerLogin(@RequestParam String ip, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        ServerInfo server = user.getServers().stream().filter(s -> s.getIp().equals(ip)).findFirst().orElse(null);
        if (server == null) return "redirect:/server-list";
        model.addAttribute("server", server);
        return "server-login";
    }

    @PostMapping("/server-login")
    public String serverLogin(@RequestParam String ip,
                              @RequestParam String sshUsername,
                              @RequestParam String sshPassword,
                              HttpSession session) {
        // Có thể kiểm tra SSH ở đây nếu muốn
        session.setAttribute("host", ip);
        session.setAttribute("username", sshUsername);
        session.setAttribute("password", sshPassword);
        return "redirect:/dashboard";
    }

    @GetMapping("/add-server")
public String showAddServer(Model model) {
    model.addAttribute("server", new ServerInfo());
    return "add-server";
}

    @PostMapping("/add-server")
    public String addServer(@ModelAttribute ServerInfo server, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        if (user.getServers() == null) user.setServers(new ArrayList<>());
        user.getServers().add(server);
    return "redirect:/server-list";
}

@PostMapping("/delete-server")
public String deleteServer(@RequestParam String ip, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) return "redirect:/login";
    if (user.getServers() != null) {
        user.getServers().removeIf(server -> server.getIp().equals(ip));
    }
    return "redirect:/server-list";
}
}