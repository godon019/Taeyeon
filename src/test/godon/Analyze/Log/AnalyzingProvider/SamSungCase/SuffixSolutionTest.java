package godon.Analyze.Log.AnalyzingProvider.SamSungCase;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Godon on 2016-07-26.
 */
public class SuffixSolutionTest {

    String modelNameWithoutSuffix = "ASD";
    String suffix1 = "/B2B";
    String modelName1  = "ASD"+suffix1;
    String productName1 = "WETWT WET "+modelNameWithoutSuffix+"WERWR";



    @Test
    public void hasSuffix() throws Exception {
        SuffixSolution suffixSolution = new SuffixSolution(new StringBuilder(), modelName1, productName1, suffix1);

        assertTrue(suffixSolution.hasSuffix(modelName1));
        assertFalse(suffixSolution.hasSuffix(modelName1+"dummy"));
    }

    @Test
    public void removeSuffix() throws Exception {
        SuffixSolution suffixSolution = new SuffixSolution(new StringBuilder(), modelName1, productName1, suffix1);
        assertEquals(modelNameWithoutSuffix, suffixSolution.removeSuffix(modelName1));
    }

    @Test
    public void isProductNameNotNull() throws Exception {
        SuffixSolution suffixSolution = new SuffixSolution(new StringBuilder(), modelName1, productName1, suffix1);
        assertTrue(suffixSolution.isProductNameNotNull());

        suffixSolution = new SuffixSolution(new StringBuilder(), modelName1, "", suffix1);
        assertFalse(suffixSolution.isProductNameNotNull());
    }

    @Test
    public void assertProductNameDosentContainModelName() throws Exception {
        SuffixSolution suffixSolution = new SuffixSolution(new StringBuilder(), modelName1, productName1, suffix1);
        assertTrue(suffixSolution.assertProductNameDosentContainModelName());

    }


    @Test
    public void solve() throws Exception {
        SuffixSolution suffixSolution = new SuffixSolution(new StringBuilder(), modelName1, productName1, suffix1);

        assertEquals(SolutionEnum.BEFORE_SOLVE, suffixSolution.getEnumAboutResult());
        suffixSolution.solve();
        assertEquals(SolutionEnum.SOLVED, suffixSolution.getEnumAboutResult());

        suffixSolution = new SuffixSolution(new StringBuilder(), "asd/", "asdfadf asd/ asdf", suffix1);
        assertEquals(SolutionEnum.BEFORE_SOLVE, suffixSolution.getEnumAboutResult());
        suffixSolution.solve();
        assertEquals(SolutionEnum.NOTHING_DONE, suffixSolution.getEnumAboutResult());


    }

}