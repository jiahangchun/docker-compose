package com.docker.excel.plate;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.docker.excel.ding.DingDingSendKeyDto;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : JIA
 */
@Component
@Slf4j
public class ReadUrl {


    public List<CombineData> readPlateExcel(String path) {

        List<PlateDTO> plateDTOS = Lists.newArrayList();
        List<StudentInfo> studentInfos = Lists.newArrayList();
        List<ClassInfo> classInfos = Lists.newArrayList();
        // 写法1
        try (ExcelReader excelReader = EasyExcel.read(path).build()) {
            ReadSheet readSheet1 =
                EasyExcel.readSheet(0).head(PlateDTO.class).registerReadListener(new PageReadListener<PlateDTO>(
                    plateDTOS::addAll)).build();
            ReadSheet readSheet2 =
                EasyExcel.readSheet(1).head(StudentInfo.class).registerReadListener(new PageReadListener<StudentInfo>(
                    studentInfos::addAll)).build();
            ReadSheet readSheet3 =
                EasyExcel.readSheet(2).head(ClassInfo.class).registerReadListener(new PageReadListener<ClassInfo>(
                    classInfos::addAll)).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2, readSheet3);
        }

        //sql 查询数据
        String dingUserIdStr = plateDTOS.stream().map(PlateDTO::getDingUserId).map(Object::toString).distinct()
            .collect(Collectors.joining(",", "(", ")"));
        String classIdStr = plateDTOS.stream().map(PlateDTO::getClassId).map(Object::toString).distinct()
            .collect(Collectors.joining(",", "(", ")"));
        System.out.println(dingUserIdStr);
        System.out.println(classIdStr);

        //数据匹配
        Map<Long, String> studentNameMap = studentInfos.stream()
            .collect(Collectors.toMap(StudentInfo::getStudentId, StudentInfo::getName));
        Map<Long, String> classNameMap = classInfos.stream()
            .collect(Collectors.toMap(ClassInfo::getClassId, ClassInfo::getNick));

        for (PlateDTO plateDTO : plateDTOS) {
            Long studentId = plateDTO.getDingUserId();
            Long classId = plateDTO.getClassId();
            String studentName = studentNameMap.get(studentId);
            String className = classNameMap.get(classId);

            plateDTO.setClassName(className);
            plateDTO.setDingName(studentName);
        }

        //写数据
        return plateDTOS.stream().map(x -> {
                CombineData combineData = new CombineData();
                BeanUtils.copyProperties(x, combineData);
                return combineData;
            }).distinct().sorted((o1, o2) -> (int) (o1.getClassId() - o2.getClassId()))
            .collect(Collectors.toList());

    }

    @SneakyThrows
    public void write(String filePath, List<CombineData> combineDataList) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        EasyExcel.write(filePath, CombineData.class).sheet("数据导出").doWrite(combineDataList);
    }

}
