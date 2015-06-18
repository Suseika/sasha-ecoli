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

    public static final int WORD_LENGTH = 9;
    public static final int DESIRED_OCCURRENCES = 3;
    public static final int BLOCK_LENGTH = 500;
    private final List<CharSequence> ninemeres;

    public CloseOccurrences(GenomeSequence genome) {
        AtomicInteger i = new AtomicInteger(0);
        this.ninemeres = genome
            .subsequences(BLOCK_LENGTH)
            .flatMap(
                block -> {
                    if (i.incrementAndGet() % 200000 == 0) {
                        System.out.println(i.get());
                    }
                    return block
                        .subsequences(WORD_LENGTH)
                        .collect(
                            Collectors.toCollection(HashMultiset::create)
                        )
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getCount() == DESIRED_OCCURRENCES)
                        .map(Multiset.Entry::getElement);
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
