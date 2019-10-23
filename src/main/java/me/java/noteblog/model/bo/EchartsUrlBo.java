package me.java.noteblog.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EchartsUrlBo implements Serializable {

    private String item;
    private long cnt;

}
