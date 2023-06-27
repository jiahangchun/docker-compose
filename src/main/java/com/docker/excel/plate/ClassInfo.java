package com.docker.excel.plate;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : JIA
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo  implements Serializable {

    private Long classId;

    private Long orgId;

    private Long gradeId;

    private Integer classLevel;

    private String name;

    private String nick;

    private Integer status;

    private String gmtCreate;

    private String gmtModify;

}
