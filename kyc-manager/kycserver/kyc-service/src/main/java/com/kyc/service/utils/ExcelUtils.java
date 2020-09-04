package com.kyc.service.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import org.springframework.web.multipart.MultipartFile;

import javax.management.OperationsException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    private static List<List<Object>> lineList = new ArrayList<>();

    /**
     * excel 导出工具类
     * @param response
     * @param fileName
     * @param projects
     * @param columnNames
     * @param keys
     * @throws IOException
     */
    public static void export(HttpServletResponse response, String fileName, List<?> projects, String[] columnNames, String[] keys) throws IOException {
        ExcelWriter bigWriter = ExcelUtil.getBigWriter();

        for (int i = 0; i < columnNames.length; i++) {
            bigWriter.addHeaderAlias(columnNames[i], keys[i]);
            bigWriter.setColumnWidth(i, 20);
        }

        // 一次性写出内容，使用默认样式，强制输出标题
        bigWriter.write(projects, true);
        // response 为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        ServletOutputStream outputStream = response.getOutputStream();
        bigWriter.flush(outputStream, true);
        /* 关闭writer，释放内存 */
        bigWriter.close();
        /* 此处记得关闭输出Servlet流 */
        IoUtil.close(outputStream);
    }

    /**
     * excel导入工具类
     * @param file
     * @param columnNames
     * @return
     * @throws OperationsException
     * @throws IOException
     */
    public static List<Map<String, Object>> leading(MultipartFile file, String[] columnNames) throws OperationsException, IOException {
        String fileName = file.getOriginalFilename();
        // 上传文件为空
        if (StrUtil.isEmpty(fileName)) {
            throw new OperationsException("没有导入的文件");
        }

        // 上传的文件大小为1000条数据
        if (file.getSize() > 1024 * 1024 * 10) {
            throw new OperationsException("上传失败：文件大小不能超过10M!");
        }

        // 上传文件格式不正确
        if (fileName.lastIndexOf(".") != -1 && !".xlsx".equals(fileName.substring(fileName.lastIndexOf(".")))) {
            throw new OperationsException("文件名格式不正确，请使用后缀为.xlsx的文件");
        }

        // 读取数据
        ExcelUtil.read07BySax(file.getInputStream(), 0, createRowHandler());
        // 去除excel中的第一行数据
        lineList.remove(0);

        // 将数据封装到list<Map>中
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < lineList.size(); i++) {
            if (lineList.get(i) != null) {
                Map<String, Object> map = new HashMap<>();
                for (int j = 0; j < columnNames.length; j++) {
                    Object property = lineList.get(i).get(j);
                    map.put(columnNames[j], property);
                }
                dataList.add(map);
            } else {
                break;
            }
        }

        return dataList;
    }

    /**
     * 通过实现handle方法编写我们要对每行数据额操作方式
     */
    private static RowHandler createRowHandler() {
        // 清空一下集合中的数据
        lineList.removeAll(lineList);
        return new RowHandler() {
            @Override
            public void handle(int i, long l, List<Object> list) {
                // 将读取到的每一行数据放入到list集合中
                JSONArray jsonArray = new JSONArray(list);
                lineList.add(jsonArray.toList(Object.class));
            }
        };
    }
}
