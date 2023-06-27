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
public class PlateDTO implements Serializable {
    private Long dingUserId;

    private String dingName;

    private Long classId;

    private String className;

    private Integer clearTime;

    private Integer totalCount;

    private Double rate;
}
