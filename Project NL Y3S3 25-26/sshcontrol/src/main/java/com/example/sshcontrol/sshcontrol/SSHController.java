package com.example.sshcontrol.sshcontrol;

import com.example.sshcontrol.model.SSHRequest;
import com.example.sshcontrol.service.SSHService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SSHController {

    @Autowired
    private SSHService sshService;

    @GetMapping("/")
    public String index(Model model) {
    model.addAttribute("sshRequest", new SSHRequest());
    return "index";
}

    

    @PostMapping("/login")
public String login(@ModelAttribute SSHRequest sshRequest, Model model, HttpSession session) {
    String result = sshService.executeCommand(
        sshRequest.getHost(),
        sshRequest.getUsername(),
        sshRequest.getPassword(),
        "echo login_success"
    );
    if (result != null && result.contains("login_success")) {
        session.setAttribute("host", sshRequest.getHost());
        session.setAttribute("username", sshRequest.getUsername());
        session.setAttribute("password", sshRequest.getPassword());
        return "dashboard";
    } else {
        model.addAttribute("error", "Đăng nhập thất bại! Kiểm tra lại thông tin.");
        model.addAttribute("sshRequest", new SSHRequest());
        return "index";
    }
}

    @PostMapping("/execute-page")
public String executeCommand(@ModelAttribute SSHRequest sshRequest, Model model, HttpSession session) {
    String host = (String) session.getAttribute("host");
    String username = (String) session.getAttribute("username");
    String password = (String) session.getAttribute("password");

    if (host != null && username != null && password != null) {
        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);
    }

    String command = sshRequest.getCommand();
    // Nếu lệnh bắt đầu bằng sudo thì truyền mật khẩu vào
    if (command != null && command.trim().startsWith("sudo")) {
        command = "echo '" + sshRequest.getPassword() + "' | sudo -S " + command.substring(5).trim();
    }

    String result = sshService.executeCommand(
        sshRequest.getHost(),
        sshRequest.getUsername(),
        sshRequest.getPassword(),
        command
    );
    sshRequest.setCommand("");
    model.addAttribute("result", result);
    model.addAttribute("sshRequest", sshRequest);
    return "execute-page";
}
    
    @GetMapping("/execute-page")
    public String showExecutePage(Model model, HttpSession session) {
    String host = (String) session.getAttribute("host");
    String username = (String) session.getAttribute("username");
    String password = (String) session.getAttribute("password");

    if (host == null || username == null || password == null) {
        return "redirect:/";
    }

    SSHRequest sshRequest = new SSHRequest();
    sshRequest.setHost(host);
    sshRequest.setUsername(username);
    sshRequest.setPassword(password);

    model.addAttribute("sshRequest", sshRequest);
    return "execute-page";
}

    

    

@PostMapping("/list-services")
public String listServices(@ModelAttribute SSHRequest sshRequest, Model model, HttpSession session) {
    String host = (String) session.getAttribute("host");
    String username = (String) session.getAttribute("username");
    String password = (String) session.getAttribute("password");

    // Nếu chưa đăng nhập, chuyển về trang login
    if (host == null || username == null || password == null) {
        return "redirect:/";
    }

    sshRequest.setHost(host);
    sshRequest.setUsername(username);
    sshRequest.setPassword(password);

    String result = sshService.executeCommand(
            sshRequest.getHost(),
            sshRequest.getUsername(),
            sshRequest.getPassword(),
            "systemctl list-units --type=service --no-pager"
    );
    model.addAttribute("result", result);
    model.addAttribute("sshRequest", sshRequest);
    return "list-services";
}

@GetMapping("/list-services")
public String showListServicesForm(Model model, HttpSession session) {
    String host = (String) session.getAttribute("host");
    String username = (String) session.getAttribute("username");
    String password = (String) session.getAttribute("password");

    // Nếu chưa đăng nhập, chuyển về trang login
    if (host == null || username == null || password == null) {
        return "redirect:/";
    }

    SSHRequest sshRequest = new SSHRequest();
    sshRequest.setHost(host);
    sshRequest.setUsername(username);
    sshRequest.setPassword(password);

    model.addAttribute("sshRequest", sshRequest);
    return "list-services";
}





    @PostMapping("/edit-config")
    public String editConfig(@ModelAttribute SSHRequest sshRequest, @RequestParam String configFile, Model model) {
        String command = "cat " + configFile;
        String content = sshService.executeCommand(sshRequest.getHost(), sshRequest.getUsername(), sshRequest.getPassword(), command);
        model.addAttribute("configFile", configFile);
        model.addAttribute("content", content);
        model.addAttribute("sshRequest", sshRequest);
        return "edit-config";
    }

    @PostMapping("/save-config")
    public String saveConfig(@ModelAttribute SSHRequest sshRequest, @RequestParam String content, @RequestParam String configFile, Model model) {
        String command = "echo \"" + content.replace("\"", "\\\"") + "\" | sudo tee " + configFile;
        String result = sshService.executeCommand(sshRequest.getHost(), sshRequest.getUsername(), sshRequest.getPassword(), command);
        if (result != null) {
            model.addAttribute("message", "Cấu hình đã được lưu thành công.");
        } else {
            model.addAttribute("error", "Lưu cấu hình thất bại!");
        }
        model.addAttribute("sshRequest", sshRequest);
        return "save-config";
    }

    
}