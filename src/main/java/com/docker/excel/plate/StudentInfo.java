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
public class StudentInfo implements Serializable {

    private Long studentId;

    private Long orgId;

    private Long classId;

    private String name;

    private String studentNo;

    private Integer status;

    private String gmtCreate;

    private String gmtModify;



}
