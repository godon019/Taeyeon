package godon.Analyze.Log.AnalyzingProvider;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Godon on 2016-07-25.
 */
public class SamSungTrimmingCaseTest {


    @Test
    public void trimNameForSamSung() throws Exception {
        StringBuilder log = new StringBuilder();
        SamSungTrimmingCase samSungTrimmingCase = new SamSungTrimmingCase(log, "AF16K7975GZS", "삼성 무풍냉방에어컨 Q9500 (52.8㎡) AF16K7975GZ");
        assertEquals("AF16K7975GZ", samSungTrimmingCase.getTrimmedModelName());

        samSungTrimmingCase = new SamSungTrimmingCase(log, "AF16K7975GZS", "삼성 무풍냉방에어컨 Q9500 (52.8㎡) AF16K7975GZS");
        assertEquals("AF16K7975GZS", samSungTrimmingCase.getTrimmedModelName());


        samSungTrimmingCase = new SamSungTrimmingCase(log, "NT900X5L-K39LB", "15\" 대화면의 컴팩트한 삼성노트북 9 metal");
        assertEquals("AF16K7975GZS", samSungTrimmingCase.getTrimmedModelName());

    }
}