package me.java.noteblog.controller.management.content;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.java.noteblog.controller.common.BaseController;
import me.java.noteblog.model.LayuiTable;
import me.java.noteblog.model.ResultBean;
import me.java.noteblog.model.entity.Note;
import me.java.noteblog.service.interfaces.content.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/management/note")
public class AdminNoteController extends BaseController {

    private final HttpServletRequest request;
    private final NoteService noteService;

    public AdminNoteController(HttpServletRequest request, NoteService noteService) {
        this.request = request;
        this.noteService = noteService;
    }

    @GetMapping("/add")
    public String publishArticlePage() {
        return "management/note/add";
    }

    @GetMapping("/page")
    public String notePage() {
        return "management/note/list";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Long id) {
        Note note = noteService.getById(id);
        model.addAttribute("editNote", note);
        return "management/note/edit";
    }

    @GetMapping("/list")
    @ResponseBody
    public LayuiTable<Note> noteListPage(Page<Note> page, String sort, String order, String title, String clearContent) {
        addPageOrder(page, order, sort);
        IPage<Note> articlePage = noteService.page(page,
                Wrappers.<Note>query()
                        .like(StrUtil.isNotEmpty(title), "title", title)
                        .like(StrUtil.isNotEmpty(clearContent), "clear_content", clearContent));
        return new LayuiTable<>(articlePage.getTotal(), articlePage.getRecords());
    }

    @PostMapping("/create")
    @ResponseBody
    public ResultBean createNote(@Valid Note note, BindingResult result) {
        if (result.getErrorCount() == 0) {
            note.setClearContent(HtmlUtil.cleanHtmlTag(note.getContent()));
            noteService.decorateNote(note);
            boolean res = noteService.save(note);
            return handle(res, "保存成功！", "保存失败！");
        } else {
            return ajaxJsr303(result.getFieldErrors());
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultBean updateNote(@Valid Note nbNote, BindingResult result) {
        if (result.getErrorCount() == 0) {
            nbNote.setPost(null);
            nbNote.setModify(new Date());
            nbNote.setClearContent(HtmlUtil.cleanHtmlTag(nbNote.getContent()));
            boolean res = noteService.updateById(nbNote);
            return handle(res, "修改成功！", "修改失败！");
        } else {
            return ajaxJsr303(result.getFieldErrors());
        }
    }

    @PostMapping("/update/field")
    @ResponseBody
    public ResultBean top(Long id, Boolean status, String field) {
        boolean res = noteService.update(
                Wrappers.<Note>update().set(StrUtil.format("`{}`", field), status).eq("id", id));
        return handle(res, "修改成功！", "修改失败！");
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultBean delete(Long id) {
        boolean res = noteService.removeById(id);
        return handle(res, "删除成功！", "删除失败！");
    }
}
