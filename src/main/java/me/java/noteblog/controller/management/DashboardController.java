package me.java.noteblog.controller.management;

import me.java.noteblog.constant.NBV5;
import me.java.noteblog.controller.common.BaseController;
import me.java.noteblog.service.interfaces.UserService;
import me.java.noteblog.service.interfaces.log.LogService;
import me.java.noteblog.service.interfaces.msg.CommentService;
import me.java.noteblog.service.interfaces.msg.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class DashboardController extends BaseController {

    private final CommentService commentService;
    private final MessageService messageService;
    private final LogService logService;
    private final UserService userService;

    public DashboardController(CommentService commentService,
                               MessageService messageService, LogService logService, UserService userService) {
        this.commentService = commentService;
        this.messageService = messageService;
        this.logService = logService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("nbv5version", NBV5.VERSION);
        model.addAttribute("latestComment", commentService.findLatestComment());
        model.addAttribute("latestMessage", messageService.findLatestMessage());
        model.addAttribute("ipSummary", logService.ipSummary());
        model.addAttribute("urlSummary", logService.urlSummary());
        model.addAttribute("browserSummary", logService.browserSummary());
        model.addAttribute("todayComment", commentService.findTodayComment());
        model.addAttribute("todayMessage", messageService.findTodayMessage());
        model.addAttribute("todayUser", userService.findTodayUser());
        model.addAttribute("todayVisit", logService.todayVisit());
        return "management/dashboard";
    }
}
