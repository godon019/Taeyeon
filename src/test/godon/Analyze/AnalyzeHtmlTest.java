package godon.Analyze;

import org.junit.Test;

/**
 * Created by Godon on 2016-07-22.
 */
public class AnalyzeHtmlTest {
    @Test
    public void perform() throws Exception {
        AnalyzeHtml analyzeHtml = new AnalyzeHtml("13UD360-LX10L");
        StringBuilder log = new StringBuilder();
        log.append("로그 갱신 전");
        try {
            log = analyzeHtml.getLog_new(log, "모델명만 포함 13ZD950-LX20L");

        } catch (Exception e) {
            e.printStackTrace();
            log.append(e.toString() + "\n 갱신 오류");
        }
        System.out.println(log.toString());
    }



}

