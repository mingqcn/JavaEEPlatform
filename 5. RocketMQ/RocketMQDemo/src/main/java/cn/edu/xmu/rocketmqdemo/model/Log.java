package cn.edu.xmu.rocketmqdemo.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Log  implements Serializable {
    private Long userId;
    private String ip;
    private String descr;
    private LocalDateTime gmtCreate;
    private Long privId;
}
