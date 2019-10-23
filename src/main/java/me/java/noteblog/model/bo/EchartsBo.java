package me.java.noteblog.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EchartsBo implements Serializable {

    private String name;
    private String value;
}
