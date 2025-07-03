package com.example.sshcontrol.sshcontrol;

import com.example.sshcontrol.model.SSHRequest;
import com.example.sshcontrol.service.SSHService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class SSHController {

    @Autowired
    private SSHService sshService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sshRequest", new SSHRequest());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/execute-page")
    public String showExecutePage(Model model, HttpSession session) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        SSHRequest sshRequest = new SSHRequest();
        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);

        model.addAttribute("sshRequest", sshRequest);
        return "execute-page";
    }

    @PostMapping("/execute-page")
    public String executeCommand(@ModelAttribute SSHRequest sshRequest, Model model, HttpSession session) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);

        String command = sshRequest.getCommand();
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

    @GetMapping("/list-services")
    public String listServices(Model model, HttpSession session) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        // Lấy danh sách tất cả dịch vụ (đầy đủ thông tin)
        String result = sshService.executeCommand(
            host,
            username,
            password,
            "systemctl list-units --type=service --all --no-pager --no-legend"
        );
        System.out.println("Kết quả lấy dịch vụ:\n" + result);
        // Tách từng dòng thành mảng
        String[] services = result.split("\\r?\\n");
        model.addAttribute("services", services);

        return "list-services";
    }

    @PostMapping("/list-services")
    public String listServices(@ModelAttribute SSHRequest sshRequest, Model model, HttpSession session) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);

        String result = sshService.executeCommand(
            sshRequest.getHost(),
            sshRequest.getUsername(),
            sshRequest.getPassword(),
            "systemctl list-units --type=service --all --no-pager --no-legend"
        );
        String[] services = result.split("\\r?\\n");
        model.addAttribute("services", services);
        model.addAttribute("sshRequest", sshRequest);
        return "list-services";
    }

    @GetMapping("/select-config")
    public String showSelectConfig() {
        return "select-config";
    }

    @PostMapping("/select-config")
    public String submitSelectConfig(@RequestParam String configFile) {
        return "redirect:/edit-config?configFile=" + configFile;
    }

    @GetMapping("/edit-config")
    public String showEditConfig(@RequestParam String configFile, Model model, HttpSession session) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        SSHRequest sshRequest = new SSHRequest();
        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);

        String command = "cat " + configFile;
        String content = sshService.executeCommand(host, username, password, command);

        model.addAttribute("configFile", configFile);
        model.addAttribute("content", content);
        model.addAttribute("sshRequest", sshRequest);
        return "edit-config";
    }

    @PostMapping("/save-config")
    public String saveConfig(
        @ModelAttribute SSHRequest sshRequest,
        @RequestParam String content,
        @RequestParam String configFile,
        @RequestParam(required = false) String customCommand,
        Model model,
        HttpSession session
    ) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);

        String result = null;
        if (customCommand != null && !customCommand.trim().isEmpty()) {
            result = sshService.executeCommand(
                sshRequest.getHost(),
                sshRequest.getUsername(),
                sshRequest.getPassword(),
                customCommand
            );
        } else {
            String command = "sudo -S tee " + configFile;
            String fullInput = sshRequest.getPassword() + "\n" + content;
            result = sshService.executeCommandWithInput(
                sshRequest.getHost(),
                sshRequest.getUsername(),
                sshRequest.getPassword(),
                command,
                fullInput
            );
        }
        if (result != null && result.contains("Permission denied")) {
            model.addAttribute("error", "Lưu cấu hình thất bại! Không đủ quyền ghi file.");
        } else if (result != null && result.trim().length() > 0) {
            model.addAttribute("message", "Thao tác thành công.");
        } else {
            model.addAttribute("error", "Lưu cấu hình thất bại!");
        }
        model.addAttribute("sshRequest", sshRequest);
        model.addAttribute("configFile", configFile);
        return "save-config";
    }

    @GetMapping("/control-service")
    public String showControlService(Model model, HttpSession session) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        SSHRequest sshRequest = new SSHRequest();
        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);

        // Lấy danh sách dịch vụ đầy đủ thông tin
        String serviceListRaw = sshService.executeCommand(
            host, username, password,
            "systemctl list-units --type=service --all --no-pager --no-legend"
        );
        String[] services = serviceListRaw.split("\\r?\\n");

        model.addAttribute("sshRequest", sshRequest);
        model.addAttribute("services", services);
        return "control-service";
    }

    @PostMapping("/control-service")
    public String controlService(
        @ModelAttribute SSHRequest sshRequest,
        @RequestParam String serviceName,
        @RequestParam String action,
        Model model,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "redirect:/login";
        }

        sshRequest.setHost(host);
        sshRequest.setUsername(username);
        sshRequest.setPassword(password);

        String command = "";
        String successMessage = null;
        String errorMessage = null;

        switch (action) {
            case "start":
                command = "sudo systemctl start " + serviceName;
                successMessage = "Dịch vụ '" + serviceName + "' đã được khởi động thành công.";
                break;
            case "stop":
                command = "sudo systemctl stop " + serviceName;
                successMessage = "Dịch vụ '" + serviceName + "' đã được dừng thành công.";
                break;
            case "restart":
                command = "sudo systemctl restart " + serviceName;
                successMessage = "Dịch vụ '" + serviceName + "' đã được khởi động lại thành công.";
                break;
            case "status":
                command = "systemctl status " + serviceName;
                break;
            default:
                command = "systemctl status " + serviceName;
        }

        String result = sshService.executeCommand(
            sshRequest.getHost(),
            sshRequest.getUsername(),
            sshRequest.getPassword(),
            command
        );

        // Lấy lại danh sách dịch vụ đầy đủ thông tin
        String serviceListRaw = sshService.executeCommand(
            host, username, password,
            "systemctl list-units --type=service --all --no-pager --no-legend"
        );
        String[] services = serviceListRaw.split("\\r?\\n");
        model.addAttribute("services", services);

        // Xử lý kết quả và thông báo
        if ((action.equals("start") || action.equals("stop") || action.equals("restart"))) {
            if (result == null || result.trim().isEmpty() || result.toLowerCase().contains("failed") || result.toLowerCase().contains("error")) {
                errorMessage = "Thao tác " + action + " dịch vụ '" + serviceName + "' thất bại. " + result;
                model.addAttribute("error", errorMessage);
            } else {
                model.addAttribute("message", successMessage);
            }
        }
        model.addAttribute("result", result); // Always show the raw result for status or detailed errors

        model.addAttribute("sshRequest", sshRequest);
        model.addAttribute("serviceName", serviceName);
        model.addAttribute("action", action);
        return "control-service";
    }

    @PostMapping("/api/control-service")
    @ResponseBody
    public String apiControlService(
        @RequestParam String serviceName,
        @RequestParam String action,
        HttpSession session
    ) {
        String host = (String) session.getAttribute("host");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        if (host == null || username == null || password == null) {
            return "Chưa đăng nhập!";
        }

        String command;
        switch (action) {
            case "start":
                command = "echo '" + password + "' | sudo -S systemctl start " + serviceName;
                break;
            case "stop":
                command = "echo '" + password + "' | sudo -S systemctl stop " + serviceName;
                break;
            case "status":
                command = "systemctl status " + serviceName;
                break;
            default:
                return "Hành động không hợp lệ!";
        }

        String result = sshService.executeCommand(host, username, password, command);

        // Nếu là start/stop, kiểm tra lại trạng thái dịch vụ sau khi thực hiện
        if ((action.equals("start") || action.equals("stop"))) {
            String statusCmd = "systemctl is-active " + serviceName;
            String status = sshService.executeCommand(host, username, password, statusCmd);
            if (status != null && status.trim().equals("active") && action.equals("start")) {
                return "Dịch vụ đã được khởi động thành công!";
            } else if (status != null && status.trim().equals("inactive") && action.equals("stop")) {
                return "Dịch vụ đã được dừng thành công!";
            } else {
                return "Không xác định được trạng thái dịch vụ sau khi thực hiện. Kết quả: " + (result == null ? "" : result);
            }
        }

        // Nếu là status, trả về kết quả luôn
        return (result == null || result.trim().isEmpty()) ? "Không có kết quả trả về!" : result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}


