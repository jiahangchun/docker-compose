package com.docker.excel.ding;

import java.io.Serializable;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : JIA
 */
@Data
public class DingDingSendKeyDto implements Serializable {

    private String onlineKey;

    private String name;

    private String testKey;

    private String prodKey;


}
