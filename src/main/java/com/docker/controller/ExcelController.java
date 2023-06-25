package com.docker.controller;

import com.docker.excel.DingDingSendKeyDto;
import com.docker.excel.ReadExcelProcessor;
import com.docker.excel.ReadFileProcessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Slf4j
@RestController
public class ExcelController {

    @Resource
    private ReadExcelProcessor readExcelProcessor;
    @Resource
    private ReadFileProcessor readFileProcessor;

    /**
     * https://blog.csdn.net/qq_34565436/article/details/123899845?ydreferer=aHR0cHM6Ly9jbi5iaW5nLmNvbS8%3D
     */
    @Autowired
    ResourceLoader resourceLoader;


    @GetMapping("/ding/key/test")
    public String dingKey() {
        String txt = "";
        try {
            String path = "classpath:" + "excel" + File.separator + "notice_temp.xlsx";
            org.springframework.core.io.Resource resource=resourceLoader.getResource(path);
            File file = resource.getFile();

            String templateCodePath = "classpath:" + "excel" + File.separator + "template_code.txt";
            List<DingDingSendKeyDto> dingDingSendKeyDtoList = readExcelProcessor.readDingDingKeyExcel(file.getPath());
            if (dingDingSendKeyDtoList.isEmpty()) {
                return "excel failed";
            }
            File codeFile = ResourceUtils.getFile(templateCodePath);
            txt = readFileProcessor.readTxt(codeFile.getPath());
            if (Objects.isNull(txt)) {
                return "txt failed";
            }

            for (DingDingSendKeyDto keyDto : dingDingSendKeyDtoList) {
                txt = txt.replaceAll(keyDto.getOnlineKey(), keyDto.getTestKey());
            }
        } catch (Exception e) {
            log.error("error {} ", e.getMessage(), e);
        }
        return txt;
    }


    @SneakyThrows
    @GetMapping("/ding/key/prod")
    public String dingKeyProd() {
        String path = "classpath:" + "excel" + File.separator + "notice_temp.xlsx";
        String templateCodePath = "classpath:" + "excel" + File.separator + "template_code.txt";
        File file = ResourceUtils.getFile(path);
        List<DingDingSendKeyDto> dingDingSendKeyDtoList = readExcelProcessor.readDingDingKeyExcel(file.getPath());
        if (dingDingSendKeyDtoList.isEmpty()) {
            return "excel failed";
        }
        File codeFile = ResourceUtils.getFile(templateCodePath);
        String txt = readFileProcessor.readTxt(codeFile.getPath());
        if (Objects.isNull(txt)) {
            return "txt failed";
        }

        for (DingDingSendKeyDto keyDto : dingDingSendKeyDtoList) {
            txt = txt.replaceAll(keyDto.getOnlineKey(), keyDto.getProdKey());
        }
        log.info(txt);
        return txt;
    }

}
