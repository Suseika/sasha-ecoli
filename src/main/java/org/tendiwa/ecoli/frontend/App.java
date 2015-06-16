package org.tendiwa.ecoli.frontend;

import com.google.common.base.Stopwatch;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.tendiwa.ecoli.alg.Genome;
import org.tendiwa.ecoli.alg.GenomeSequence;
import org.tendiwa.ecoli.alg.Word;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $id$
 */
public final class App {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                "You must provide filename as an argument"
            );
        }
        final File file = new File(
            args[0]
        );
        Stopwatch watch = Stopwatch.createStarted();
        final int fileSize = (int) Files.size(file.toPath());
        final Collection<Word> words = new TripleNinemeres(
            new Genome(
                new GenomeSequence(
                    new FileReader(
                        file
                    ),
                    fileSize
                )
            )
        );
        System.out.println(
            "Thrice-occuring ninemeres found: " + words.size() + " items"
        );
        System.out.println("Here they are:");
        words.forEach(System.out::println);
        System.out.println(
            "Computation took " + watch.elapsed(TimeUnit.MILLISECONDS)
                + " ms"
        );
    }
}
