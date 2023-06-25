package com.docker.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson2.JSON;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : JIA
 */
@Slf4j
@Component
public class ReadExcelProcessor {


    /**
     * 读取钉钉数据
     */
    public List<DingDingSendKeyDto> readDingDingKeyExcel(String path) {
        List<DingDingSendKeyDto> dingDingSendKeyDtoList = Lists.newArrayList();
        EasyExcel.read(path, DingDingSendKeyDto.class, new PageReadListener<DingDingSendKeyDto>(
            dingDingSendKeyDtoList::addAll)).sheet().doRead();
        return dingDingSendKeyDtoList.stream().filter(Objects::nonNull)
            .filter(x -> Objects.nonNull(x.getOnlineKey()) && Objects.nonNull(x.getTestKey()) && Objects.nonNull(
                x.getProdKey()))
            .collect(Collectors.toList());
    }
}
