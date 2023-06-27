package com.docker.excel.plate;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import java.io.Serializable;
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
public class CombineData  implements Serializable {


    @ExcelIgnore
    private Long dingUserId;

    @ExcelProperty("学生名称")
    private String dingName;

    @ExcelIgnore
    private Long classId;

    @ExcelProperty("班级名称")
    private String className;

    @ExcelProperty("光盘次数")
    private Integer clearTime;

    @ExcelProperty("总就餐次数")
    private Integer totalCount;

    @ExcelProperty("光盘率")
    private Double rate;


}
