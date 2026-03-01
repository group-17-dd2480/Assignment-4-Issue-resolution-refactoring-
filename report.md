# Report for assignment 4

## Project

Name: JabRef Bibliography Management

URL: [github.com/JabRef/jabref](https://github.com/JabRef/jabref)

JabRef is an open-source, cross-platform citation and reference management tool.
JabRef helps you to collect and organize sources, find the paper you need and discover the latest research.

## Onboarding experience

Did you choose a new project or continue on the previous one?
**We choose a new project**

If you changed the project, how did your experience differ from before?

For our groups sole Windows user, Sofia, the experience differed a lot. When cloning from GitHub on Windows there are several errors like this: `error: unable to create file jablib/src/main/java/org/jabref/logic/importer/fetcher/transformers/CollectionOfComputerScienceBibliographiesQueryTransformer.java: Filename too long` due to filenames being too long. This is due to Git on Windows being compiled with msys which has a filename limit of 260 characters for a filename (for Git the limit is 4096 characters). The solution is to add [longpaths = true](https://stackoverflow.com/a/74289583) to the Git configuration. A relatively easy fix, but there is no mention anywhere in the original repo or FAQ about this problem, which should be an issue for every Windows user who tries to clone the repository.

Another thing is that JabRef has very particular requirements for [which IDE to use](https://devdocs.jabref.org/getting-into-the-code/guidelines-for-setting-up-a-local-workspace/).
> We highly encourage using IntelliJ IDEA, as it provides the most reliable experience for this project. Other IDEs may have compatibility issues, particularly Visual Studio Code.
> We do not recommend using VS Code, because it does not offer all important features essential for JabRef development.

## Effort spent

For each team member, how much time was spent in

|                                                     | Olivia | Laasya | Gabriel | Daniel  |   Sofia   |
| --------------------------------------------------- | :----: | :----: | :-----: | :-----: | :-------: |
| plenary discussions/meetings                        | 2 hours|        | 2 hours | 2 hours |  1 hour   |
| discussions within parts of the group               | 1 hour |        | 30 min  | 1 hour  |           |
| reading documentation                               | 6 hours|        | 7 hours | 2 hours | 4 ½ hours |
| configuration and setup                             | 3 hours|        | 1 hours | 1 hour  | 2 ½ hours |
| &nbsp;&nbsp;&#8594; Git clone (Windows)             |        |        |         |         |  50 min   |
| &nbsp;&nbsp;&#8594; IntelliJ (install/learn)        |        |        |         |         |  40 min   |
| &nbsp;&nbsp;&#8594; Follow setup instructions       |        |        |         |         |  20 min   |
| &nbsp;&nbsp;&#8594; Troubleshoot setup instructions |        |        |         |         |  35 min   |
| analyzing code/output                               | 3 hours|        | 7 hours | 5 hours |  2 hours  |
| writing documentation                               | 2 hours|        | 5 hours | 1 hour  |  7 hours  |
| writing code                                        | 4 hours|        | 5 hours | 9 hours | 2 ½ hours |
| running code                                        | 2 hours|        | 3 hours | 1 hour  |  1 hour   |
| Incident                                            |config 2|        |         |         |           |

For setting up tools and libraries (step 4), enumerate all dependencies
you took care of and where you spent your time, if that time exceeds
30 minutes.

## Overview of issue(s) and work done

Title: Make abbreviations also working for conferences #12728

URL: [github.com/JabRef/jabref/issues/12728](https://github.com/JabRef/jabref/issues/12728)

Abbreviations are listed at [github.com/JabRef/abbrv.jabref.org](https://github.com/JabRef/abbrv.jabref.org).
The system is currently not used for conference proceedings (field booktitle)

The scope for this issue is these folders:

```bash
jabgui/src/main/java/org/jabref/gui/
jablib/src/main/java/org/jabref/logic/
jablib/src/main/resources/
```

The functionality is similar to existing functions, notably `JournalAbbreviation`, just implemented for a different field in the user interface and for different abbreviations.

## Requirements for the new feature or requirements affected by functionality being refactored

The following requirements are based on the issue description. Note that tests are implemented at first to be used as a validation tool of all other requirements, as required by the assignment instructions `Part 2: Issue Resolution, 2-3`. The order of all other requirements are based on the order they are mentioned in the [issue thread](https://github.com/JabRef/jabref/issues/12728).

**[req 1](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749311)** - Done

Before shortening:

```bibtex
@inproceedings{DBLP:conf/bpm/Rinderle-MaM21,
  author    = {Stefanie Rinderle{-}Ma and
               Juergen Mangler},
  title     = {Process Automation and Process Mining in Manufacturing},
  booktitle = {International Conference on Business Process Management},
  series    = {Lecture Notes in Computer Science},
  volume    = {12875},
  pages     = {3--14},
  publisher = {Springer},
  year      = {2021}
}
```

After shortening:

```bibtex
@inproceedings{DBLP:conf/bpm/Rinderle-MaM21,
  author    = {Stefanie Rinderle{-}Ma and
               Juergen Mangler},
  title     = {Process Automation and Process Mining in Manufacturing},
  booktitle = {BPM},
  series    = {Lecture Notes in Computer Science},
  volume    = {12875},
  pages     = {3--14},
  publisher = {Springer},
  year      = {2021}
}
```

There will be a CSV file:
International Conference on Business Process Management,BPM

**Test**
Test by using the input above and asserting that the output is correct.

- `abbreviateBookTitleField()` in `AbbreviateBookTitleField()`
- `unabbreviateBookTitleSuccessful()` in `UnabbreviateJournalCleanupTest.java`

**[req 2](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749311)** - Done

- conferences use booktitle instead of journaltitle
- they have different abbreviations and reside in different fields

**Test**
Assert `booktitle` abbreviations are handled by the conference abbreviation repo while `journaltitle` uses the journal repo.

**[req 3](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749311)** - Done

- a csv has to be filled with example conferences

**Test**
More of a chore, not really testable in classic sense.

**[req 4](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749311)** - Partially Implemented

- create a ConferenceAbbreviationRepository based on JournalAbbreviationRepository - Done
- Added ConferenceAbbreviationRepositoryTest - Done
- rename journal-lists.mv to abbreviations.mv in both groovy and java code

**Test**
Loader can assert that the loader reads the expected MV resource name.

**[req 5](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749311)** - Done

- write tests based on custom abbreviations

**Test**
Verifiable by running the tests and making sure they fail before implementation and work after implementation.

- covered in `abbreviateBookTitleField()`, `unabbreviateBookTitleSuccessful()`, `abbreviateJournalTitleAndBookTitleInOneRun()`, `checkEntryDoesNotComplainAboutAbbreviatedBooktitleWhenAbbreviationIsAllowed()`.

**[req 6](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749311)** - Partially Implemented

- the csv file should be imported into JabRef similar to journal abbreviations
- refactor JournalAbbreviationConverter.groovy to be AbbreviationConverter.groovy

**Test**
Test idea: load a tiny conference CSV and check abbreviations resolve, converter rename is just review.

**[req 7](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749311)** - Done

- the UI should offer abbreviating
- rename "Abbreviate journal names" to "Abbreviate journals and book titles"
- rename "Unabbreviate journal names" to "Expand journals and book titles"
- Implementation: Run both abbreviators (instead of checking type)

**Test**
Test: check the cleanup UI strings changed and a cleanup run updates both journal and booktitle in one go.

- `abbreviateJournalTitleAndBookTitleInOneRun()` in `AbbreviateJournalCleanupTest.java`.
- `checkEntryDoesNotComplainAboutAbbreviatedBooktitleWhenAbbreviationIsAllowed()` in `AbbreviateCheckerTest.java`.

**[req 8](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749376)** - Done

- JournalAbbreviationPreferences and ConferenceAbbreviationPreferences have a list of abbreviations in common, List\<Abbreviations\>, since Abbreviation object is the same for conference and journal.
- Introduce AbbreviationPreferences and new ConferenceAbbreviationPreferences (inheriting from AbbreviationPreferences).
- The JournalAbbreviationPreferences need also inherit from AbbreviationPreferences.

**Test**
Test (light): create both prefs and confirm they accept the same list type, even if it is mostly design.

**[req 9](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749376)** - Done

- externalLists should be in AbbreviationPreferences, because both conference abbreviation and the journal abbreviations make use of the list.

**Test**
Test: set `externalLists` in base prefs and see both journal and conference loaders pick them up.

**[req 10](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749376)** - Done

- useFjournalField is very specific to journals, thus it has to be in JournalAbbreviationPreferences

**Test**
Not really testable, just verify in review that `useFJournalField` stays in journal prefs.

```mermaid
classDiagram
    AbbreviationPreferences <|-- JournalAbbreviationPreferences
    AbbreviationPreferences <|-- ConferenceAbbreviationPreferences
    <<Abstract>> AbbreviationPreferences

    AbbreviationPreferences : -externalLists
    JournalAbbreviationPreferences : -useFJournalField

```

**[req 11](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749376)** - Done

- rename externalJournalLists to externalLists (in JournalAbbreviationPreferences) and move up the class hierarchy to AbbreviationPreferences
- rename the respective setter, getter and constructor paramtere - and move up the class hierarchy to AbbreviationPreferences

**Test**
Not unit-testable, just make sure rename compiles and base prefs expose `externalLists`.

**[req 12](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749376)** - Done

- JournalAbbreviationRepository has no journal specifics
- rename JournalAbbreviationRepository to AbbreviationRepository

**Test**
Not unit-testable, class rename is checked by compile and review.

**[req 13](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749376)** - Incomplete

- JournalAbbreviationLoader has some journal specifics
- The journal specifics are journal-list.mv and /journals/journal-list.mv
- Hide these internals in a class hierarchy

**Test**
Test: conference loader should read the conference MV resource and journal loader should read the journal MV.

```mermaid
classDiagram
    AbbreviationLoader<|-- JournalAbbreviationLoader
    AbbreviationLoader<|-- ConferenceAbbreviationLoader
    <<Abstract>> AbbreviationLoader

    AbbreviationLoader: +readListFromFile
  AbbreviationLoader: AbbreviationLoader(String mvName)
  AbbreviationLoader: +AbbreviationRepository loadloadRepository(AbbreviationPreferences)
    AbbreviationLoader: -mvName
```

- Use AbbreviationPreferences works since fjournal is not needed here.
- Using the variable mvName, the variable tempJournaList and the path to JournalAbbreviationRepository.class.getResourceAsStream("/journals/journal-list.mv") can be dynaically made. The tempDir can be named "jabref-abbreviation-loading" (instead of jabref-journal)

**[req 14](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749376)** - Incomplete

constructors:

- public JournalAbbreviationLoader(super("journal-list.mv"))
- public ConferenceAbbreviationLoader(super("conference-list.mv"))

**Test**
Constructor signatures are structural, so compilation is the check.

**[req 15](https://github.com/JabRef/jabref/issues/12728#issuecomment-2720749399)** - Incomplete

- heuristics

**Test**
Can't test heuristics until the rule is written down.

Optional (point 3): trace tests to requirements.

## Code changes

### Patch

This is the comparison to what was in the main branch before we did our stuff

```bash
git diff main dd2480/report ':(exclude)report.md'
```

This is the comparison the what is in the main branch now\
[github.com/JabRef/jabref/compare/main...group-17-dd2480:Assignment-4-Issue-resolution-refactoring-:main](https://github.com/JabRef/jabref/compare/main...group-17-dd2480:Assignment-4-Issue-resolution-refactoring-:main)

## Test results

Overall results with link to a copy or excerpt of the logs (before/after
refactoring).

## UML class diagram and its description

![UML class diagram](report-UML.png)

## Overall experience

What are your main take-aways from this project? What did you learn?\
Our main take-aways from this project is that even if a function is already implemented, it can still be difficult and time consuming to extend the function to accept inputs from other places, in this case another field in the GUI.
We have learnt more about working on an open source project. The importance of reading up on the project standards and following them, even things like the IDE you use to code might need to be changed to do the work in a way that is up to the project requirements for changes.

How did you grow as a team, using the Essence standard to evaluate yourself?\
According to the Essence standard, our team is currently in the "Performing" state of the Team. We have established a shared and documented way of working that is understood and consistently followed by all group members. We had some difficulties with the onboarding of the project, but everything after has worked well within the group. We divided up the work and the group members have all either done their parts on time or have communicated in a timely fashion if the work would not be done for the internal deadlines. We've used GitHub issues and assigning ourselves to the different issues, preventing wasted work by several people working on the same things.
The next state is "Adjourned" which is for when the project is over, so there are no obstacles to reach the next state.
Our teamwork and collaboration has improved during the course. There is more room for improvement in handling merge conflicts and issues.
