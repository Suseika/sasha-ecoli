package org.tendiwa.ecoli.alg;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public final class GenomeTest {
    @Test
    public void wordAtIndex() throws Exception {
        final StringCharReader source = new StringCharReader("ATGCGTA");
        Assert.assertEquals(
            new Word("GCG"),
            new Genome(
                new GenomeSequence(
                    source,
                    source.length()
                )
            )
                .wordAt(2, 3)
        );
    }

    @Test
    public void allWordsInRightOrder() throws Exception {
        final StringCharReader source = new StringCharReader("CCAATGGAC");
        Assert.assertEquals(
            Arrays.asList(
                "CCA",
                "CAA",
                "AAT",
                "ATG",
                "TGG",
                "GGA",
                "GAC"
            ),
            new Genome(
                new GenomeSequence(
                    source,
                    source.length()
                )
            )
                .words(3)
                .collect(Collectors.toList())
        );
    }


}
