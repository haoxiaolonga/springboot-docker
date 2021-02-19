package com.excel_;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/19 10:51
 */
public class ExcelReadUtil {

    private static final Integer SIZE = 10;

    public static <T> AnalysisEventListener<T> getListener(final Consumer<List<T>> consumer, final Integer threshold) {
        return new AnalysisEventListener<T>() {
            private LinkedList<T> linkedList = new LinkedList();

            @Override
            public void invoke(T t, AnalysisContext context) {
                this.linkedList.add(t);
                if (this.linkedList.size() == threshold) {
                    consumer.accept(this.linkedList);
                    this.linkedList.clear();
                }

            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                if (this.linkedList.size() > 0) {
                    consumer.accept(this.linkedList);
                }

            }
        };
    }

    public static <T> AnalysisEventListener<T> getListener(Consumer<List<T>> consumer) {
        return getListener(consumer, SIZE);
    }

    private ExcelReadUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
