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
        assertEquals(samSungTrimmingCase.trimNameForSamSung(), "AF16K7975GZ");

    }

    @Test
    public void removeLastS() throws Exception {
        StringBuilder log = new StringBuilder();
        SamSungTrimmingCase samSungTrimmingCase = new SamSungTrimmingCase(log, "AF16K7975GZS", "삼성 무풍냉방에어컨 Q9500 (52.8㎡) AF16K7975GZ");
        assertEquals(samSungTrimmingCase.removeLastS("AF16K7975GZS"), "AF16K7975GZ");

    }

}