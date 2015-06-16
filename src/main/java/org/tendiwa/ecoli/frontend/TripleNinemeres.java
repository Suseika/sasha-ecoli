package org.tendiwa.ecoli.frontend;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.List;
import java.util.stream.Collectors;
import org.tendiwa.ecoli.alg.Genome;
import org.tendiwa.ecoli.alg.Word;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $id$
 */
public final class TripleNinemeres extends ForwardingList<Word> {

    private final List<Word> ninemeres;

    public TripleNinemeres(Genome genome) {
        this.ninemeres = genome.words(Sasha.WORD_LENGTH)
            .collect(
                Collectors.toCollection(HashMultiset::create)
            )
            .entrySet()
            .stream()
            .filter(entry -> entry.getCount() == Sasha.DESIRED_OCCURRENCES)
            .map(Multiset.Entry::getElement)
            .collect(Collectors.toList());
    }

    @Override
    protected List<Word> delegate() {
        return this.ninemeres;
    }
}
