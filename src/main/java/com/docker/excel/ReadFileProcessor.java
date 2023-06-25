package com.docker.excel;

import java.io.BufferedReader;
import java.io.FileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : JIA
 */
@Slf4j
@Component
public class ReadFileProcessor {

    /**
     * 读取txt文件
     */
    public String readTxt(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                stringBuilder.append(tempString).append("\r\n");
            }
        } catch (Exception e) {
            log.error("com.docker.excel.ReadFileProcessor.readTxt {} ", e.getMessage(), e);
        }
        return stringBuilder.toString();
    }

}
