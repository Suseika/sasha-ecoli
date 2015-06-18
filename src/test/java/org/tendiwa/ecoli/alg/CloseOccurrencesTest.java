package org.tendiwa.ecoli.alg;

import org.junit.Assert;
import org.junit.Test;

public final class CloseOccurrencesTest {

    @Test
    public void findsCloseOccurrences() throws Exception {
        final StringCharReader gat4times =
            new StringCharReader("GATGATGATGATGAT");
        final CloseOccurrences occurrences =
            new CloseOccurrences(
            new GenomeSequence(
                gat4times,
                gat4times.length()
            ),
            3,
            10,
            3
        );
        Assert.assertTrue(occurrences.contains("GAT"));
        Assert.assertTrue(occurrences.contains("ATG"));
        Assert.assertTrue(occurrences.contains("TGA"));
    }
}
