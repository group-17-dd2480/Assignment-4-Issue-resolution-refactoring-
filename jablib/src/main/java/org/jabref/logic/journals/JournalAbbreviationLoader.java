package org.jabref.logic.journals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jabref.logic.journals.ltwa.LtwaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


///
/// This class loads abbreviations from a CSV file and stores them into a MV file ({@link #readAbbreviationsFromCsvFile(Path)}
/// It can also create an {@link JournalAbbreviationRepository} based on an MV file ({@link #loadRepository(JournalAbbreviationPreferences)}.
///
///
/// Abbreviations are available at <a href="https://github.com/JabRef/abbrv.jabref.org/">https://github.com/JabRef/abbrv.jabref.org/</a>.
///
public class JournalAbbreviationLoader extends AbbreviationLoader {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JournalAbbreviationLoader.class);

    public JournalAbbreviationRepository loadRepository(
            JournalAbbreviationPreferences preferences) {

        JournalAbbreviationRepository repository;

        try {
            Path mvFile = copyResource("/journals/journal-list.mv");
            if (mvFile == null) {
                LOGGER.warn("There is no journal-list.mv. We use a default journal list.");
                repository = new JournalAbbreviationRepository();
            } else {
                repository = new JournalAbbreviationRepository(mvFile,loadLtwaRepository());
            }
        } catch (IOException e) {
            LOGGER.error("Error while loading journal abbreviation repository", e);
            return null;
        }
        loadExternalList(preferences, repository);
        return repository;
    }

    private static LtwaRepository loadLtwaRepository() throws IOException {
        try (InputStream resourceAsStream = JournalAbbreviationRepository.class.getResourceAsStream("/journals/ltwa-list.mv")) {
            if (resourceAsStream == null) {
                LOGGER.warn("There is no ltwa-list.mv. We cannot load the LTWA repository.");
                throw new IOException("LTWA repository not found");
            } else {
                Path tempDir = Files.createTempDirectory("jabref-ltwa");
                Path tempLtwaList = tempDir.resolve("ltwa-list.mv");
                Files.copy(resourceAsStream, tempLtwaList);
                LtwaRepository ltwaRepository = new LtwaRepository(tempLtwaList);
                tempDir.toFile().deleteOnExit();
                tempLtwaList.toFile().deleteOnExit();
                return ltwaRepository;
            }
        }
    }

    public static JournalAbbreviationRepository loadBuiltInRepository() {
        JournalAbbreviationLoader loader = new JournalAbbreviationLoader();
        return loader.loadRepository(new JournalAbbreviationPreferences(List.of(), true));
    }
}
