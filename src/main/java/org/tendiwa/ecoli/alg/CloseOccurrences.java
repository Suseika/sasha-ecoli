package org.tendiwa.ecoli.alg;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public final class CloseOccurrences extends ForwardingList<CharSequence> {

    private final List<CharSequence> ninemeres;

    public CloseOccurrences(
        SubsequenceableSequence genome,
        int wordLength,
        int blockLength,
        int desiredOccurrences
    ) {
        if (wordLength > blockLength) {
            throw new IllegalArgumentException(
                "Word length can't be greater than block length"
            );
        }
        if (blockLength > genome.length()) {
            throw new IllegalArgumentException(
                "Block length can't be greater than genome length"
            );
        }
        if (desiredOccurrences < 1) {
            throw new IllegalArgumentException(
                "Desired occurrences must be at least 1"
            );
        }
        AtomicInteger i = new AtomicInteger(0);
        this.ninemeres = genome
            .subsequences(blockLength)
            .flatMap(
                block -> {
                    if (i.incrementAndGet() % 200000 == 0) {
                        // For debugging purposes to see the progress
                        System.out.println(i.get());
                    }
                    return block
                        .subsequences(wordLength)
                        .collect(
                            Collectors.toCollection(HashMultiset::create)
                        )
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getCount() == desiredOccurrences)
                        .map(Multiset.Entry::getElement)
                        .map(Object::toString);
                }
            )
            .distinct()
            .collect(Collectors.toList());
    }

    @Override
    protected List<CharSequence> delegate() {
        return this.ninemeres;
    }
}
