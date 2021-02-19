package com.excel_;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/19 10:28
 */
@RestController
@Slf4j
@RequestMapping("/excel/test")
public class ExcelDemo {


    @GetMapping("/template")
    public void template(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DataDTO.class).sheet("模板").doWrite(data());
    }

    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link }
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {

        AnalysisEventListener<DataDTO> eventListener = ExcelReadUtil.getListener(list -> {
            // 处理逻辑
            dealData(list);
        }, 50);

        EasyExcel.read(file.getInputStream(), DataDTO.class, eventListener).sheet().doRead();
        return "success";
    }

    private void dealData(List<DataDTO> datas) {
        if (Objects.nonNull(datas)) {
            datas.stream().forEach(t -> System.out.print(t));
        }
    }


    private List<DataDTO> data() {
        List<DataDTO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataDTO data = new DataDTO();
            data.setName("name" + i);
            data.setNumber(i);
            data.setNick("nick" + i);
            list.add(data);
        }
        return list;
    }


}
