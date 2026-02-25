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

|                                               | Olivia | Laasya | Gabriel | Daniel | Sofia  |
| --------------------------------------------- | :----: | :----: | :-----: | :----: | :----: |
| plenary discussions/meetings                  |        |        |         |        |        |
| discussions within parts of the group         |        |        |         |        |        |
| reading documentation                         |        |        |         |        |        |
| configuration and setup                       |        |        |         |        |        |
| &nbsp;&nbsp;&#8594; Git clone (Windows)       |        |        |         |        | 50 min |
| &nbsp;&nbsp;&#8594; IntelliJ (install/learn)  |        |        |         |        | 40 min |
| &nbsp;&nbsp;&#8594; Follow setup instructions |        |        |         |        |        |
| &nbsp;&nbsp;&#8594; **AddDependency**         |        |        |         |        |        |
| &nbsp;&nbsp;&#8594; **AddDependency**         |        |        |         |        |        |
| analyzing code/output                         |        |        |         |        |        |
| writing documentation                         |        |        |         |        | 20 min |
| writing code                                  |        |        |         |        |        |
| running code                                  |        |        |         |        |        |

For setting up tools and libraries (step 4), enumerate all dependencies
you took care of and where you spent your time, if that time exceeds
30 minutes.

## Overview of issue(s) and work done

Title: Make abbreviations also working for conferences #12728

URL: [github.com/JabRef/jabref/issues/12728](https://github.com/JabRef/jabref/issues/12728)

Abbreviations are listed at [github.com/JabRef/abbrv.jabref.org](https://github.com/JabRef/abbrv.jabref.org).
The system is currently not used for conference proceedings (field booktitle)

Scope (functionality and code affected).

## Requirements for the new feature or requirements affected by functionality being refactored

Optional (point 3): trace tests to requirements.

## Code changes

### Patch

(copy your changes or the add git command to show them)

git diff ...

Optional (point 4): the patch is clean.

Optional (point 5): considered for acceptance (passes all automated checks).

## Test results

Overall results with link to a copy or excerpt of the logs (before/after
refactoring).

## UML class diagram and its description

### Key changes/classes affected

Optional (point 1): Architectural overview.

Optional (point 2): relation to design pattern(s).

## Overall experience

What are your main take-aways from this project? What did you learn?

How did you grow as a team, using the Essence standard to evaluate yourself?

Optional (point 6): How would you put your work in context with best software engineering practice?

Optional (point 7): Is there something special you want to mention here?
