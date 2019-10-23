package me.java.noteblog.service.impl.content;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.java.noteblog.service.interfaces.content.NoteService;
import me.java.noteblog.mapper.NoteMapper;
import me.java.noteblog.model.entity.Note;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {
}
