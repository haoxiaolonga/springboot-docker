package com.excel_;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/19 10:30
 */
@Data
public class DataDTO {

    @ColumnWidth(20)
    @ExcelProperty(value = "号码")
    private Integer number;

    @ColumnWidth(20)
    @ExcelProperty(value = "名字")
    private String name;

    @ColumnWidth(20)
    @ExcelProperty(value = "昵称")
    private String nick;

    @Override
    public String toString() {
        return "DataDTO{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
