package org.tendiwa.ecoli.alg;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public final class GenomeSequenceTest {
    @Test
    public final void containsRightLetters() throws Exception {
        final StringCharReader source = new StringCharReader("abez");
        Assert.assertEquals(
            "abez",
            new GenomeSequence(
                source,
                source.length()
            ).toString()
        );
    }

    @Test
    public final void hasRightSize() throws Exception {
        final StringCharReader source = new StringCharReader("gama");
        Assert.assertEquals(
            4,
            new GenomeSequence(
                source,
                source.length()
            ).length()
        );
    }

    @Test
    public final void indexAccess() throws Exception {
        final StringCharReader source = new StringCharReader("aazz!@1");
        Assert.assertEquals(
            '!',
            new GenomeSequence(
                source,
                source.length()
            ).charAt(4)
        );
    }

}
