package org.tendiwa.ecoli.alg;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.tendiwa.ecoli.frontend.Sasha;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public final class CloseOccurrences extends ForwardingList<CharSequence> {

    private final List<CharSequence> ninemeres;

    public CloseOccurrences(GenomeSequence genome) {
        AtomicInteger i = new AtomicInteger(0);
        this.ninemeres = genome
            .subsequences(Sasha.BLOCK_LENGTH)
            .flatMap(
                block -> {
                    if (i.incrementAndGet() % 200000 == 0) {
                        System.out.println(i.get());
                    }
                    return block
                        .subsequences(Sasha.WORD_LENGTH)
                        .collect(
                            Collectors.toCollection(HashMultiset::create)
                        )
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getCount() == Sasha.DESIRED_OCCURRENCES)
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
