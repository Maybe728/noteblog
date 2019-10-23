package me.java.noteblog.service.interfaces.content;

import com.baomidou.mybatisplus.extension.service.IService;
import me.java.noteblog.model.entity.Note;
import org.springframework.util.StringUtils;

import java.util.Date;

public interface NoteService extends IService<Note> {

    /**
     * 装饰note
     *
     * @param note
     */
    default void decorateNote(Note note) {
        if (StringUtils.isEmpty(note.getPost())) {
            note.setPost(new Date());
        }
        if (StringUtils.isEmpty(note.getShow())) {
            note.setShow(true);
        }
        if (StringUtils.isEmpty(note.getTop())) {
            note.setTop(false);
        }
    }
}
