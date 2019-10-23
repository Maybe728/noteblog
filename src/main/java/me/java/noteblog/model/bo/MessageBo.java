package me.java.noteblog.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import me.java.noteblog.model.entity.Message;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageBo extends Message {

    private String nickname;
    private String avatar;
    private String role;
}
