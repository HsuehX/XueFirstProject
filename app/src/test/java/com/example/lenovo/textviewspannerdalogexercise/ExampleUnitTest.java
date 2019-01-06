package com.example.lenovo.textviewspannerdalogexercise;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private String testTime = "2018-11-11 22:14:00";
    private String wrongTimeFormat = "这是错误的时间测试格式";

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testTime() throws Exception {
        assertEquals("测试结果", TestJunitUnits.dateToStamp(testTime));
    }

    @Test
    public void testWrongTime() throws Exception {
        assertEquals("测试结果", TestJunitUnits.dateToStamp(wrongTimeFormat));
    }
}