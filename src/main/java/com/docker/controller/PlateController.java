package com.docker.controller;

import com.alibaba.fastjson.JSON;
import com.docker.excel.ding.ReadFileProcessor;
import com.docker.excel.plate.CombineData;
import com.docker.excel.plate.PlateDTO;
import com.docker.excel.plate.ReadUrl;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : JIA
 */
@Slf4j
@RestController
public class PlateController {

    @Resource
    private ReadUrl readUrl;
    @Autowired
    ResourceLoader resourceLoader;


    @SneakyThrows
    @GetMapping("/plate/test")
    public String dingKey() {

        String path = "classpath:" + "excel" + File.separator + "plate.xlsx";
        org.springframework.core.io.Resource resource = resourceLoader.getResource(path);
        File file = resource.getFile();

        List<CombineData> combineData = readUrl.readPlateExcel(file.getPath());

        //写数据
        org.springframework.core.io.Resource writeResource = new ClassPathResource("");
        String targetPath = writeResource.getFile().getParentFile().getAbsolutePath();
        readUrl.write(targetPath + File.separator + "统计结果.xlsx", combineData);
        return JSON.toJSONString("success");
    }


}
