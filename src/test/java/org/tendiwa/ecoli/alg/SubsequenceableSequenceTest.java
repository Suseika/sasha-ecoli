package org.tendiwa.ecoli.alg;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public final class SubsequenceableSequenceTest {
    @Test
    public void canBeSubsequenced() {
        final SubsequenceableSequence.Basic sequence =
            new SubsequenceableSequence.Basic("GATTACA");
        Assert.assertEquals(
            "TTAC",
            sequence.subSequence(2, 6)
        );
    }

    @Test
    public void generatesSubsequences() {
        final SubsequenceableSequence.Basic sequence =
            new SubsequenceableSequence.Basic("GATTACA");
        Assert.assertEquals(
            5,
            sequence.subsequences(3).count()
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsAtTooLongSubsequences() {
        new SubsequenceableSequence.Basic("GATTACA").subsequences(8);
    }

}
