package org.tendiwa.ecoli.alg;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $id$
 */
public final class Genome {
    private final transient CharSequence sequence;

    public Genome(GenomeSequence sequence) throws IOException {
        this.sequence = sequence;
    }

    public Word wordAt(int index, int length) {
        return new Word(
            sequence.subSequence(index, index+length)
        );
    }

    public Stream<Word> words(int length) {
        return IntStream.range(0, sequence.length()-length+1)
            .mapToObj(i->wordAt(i, length));
    }
}
