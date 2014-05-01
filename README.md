 Coding Challenge Notes
---------------

If you want to see where things were at at the end of the challenge, check out the 'end_of_time' tag.  The master branch
has a more cleaned up version of the project.

Presentation:
https://docs.google.com/presentation/d/1NH99nSZLzqNBxqdCkgmB77gDjOJ0r1fMCCwtM6Bz4fo/edit#slide=id.g945ce73_0_16

Lessons learned:
- Use setListAdapter instead of getListView().setAdapter() in a ListFragment to save yourself some pain/bugs.
- The current binding adapter could be simpler
- It would be nice for the ViewHolder to be more tightly bound to a view...lost 10 min because I was still referencing an old view

Follow ups:
- Landscape detail fragment has a scroll view, portrait did not.  Ran into some weird ClassCastExceptions on rotate.  Not sure why...

Don't follow this pattern:
- The otherwise unmanged image cache living in the application scope is a bad idea!

If I had more time:
- CWAC endless adapter would help with the infinite list

---------------






The following are a couple of pointers to building the project.

Development Requirements
---------------
- Git on path
- Maven 3.1.1 (or later) on path
- Latest version of the ADT (19+) on path with Android 4.0+ artifacts and all extra artifacts downloaded.
    - HAXM installed (see: http://developer.android.com/tools/devices/emulator.html)
- IntelliJ 13+ is recommended for the IDE.

Project Setup
---------------
- Download a zip copy of the repository.
- You'll want to run something like `projectSetup.sh "App Name" "com.yourcompany.appname"`.
- Delete projectSetup.xml once the project files have been updated and laid out for you.
- Import the project to git (`git init` and then add the remotes, push, etc)
- Open the project up in your favorite IDE.
- You'll also probably need a release key at some point.  Go to the distribution/keys directory and run `generateKey.sh release`
- To complete Crashlytics setup, you'll need to run the app once where Crashlytics.start() is called outside of the BuildConfig code block.

Emulator
---------------
Ensure that you have a emulator with Android 4.1 or higher running or an equivalent device attached. If you would like
the build to start and emulator ensure that an avd with the name `16instrumentationtest` exists.  If you are using an
emulator there is value in setting up HAXM so you use the much faster x86 emulator images.

IntelliJ Setup
---------------
You will need to setup several run configurations to be useful.  The following are recommended:

- Add a new "Android Application" run configuration called "app".  Run this when you want to run the app.
    - Set the "Module" to "app".
    - Set the "Target device" to "Show chooser dialog".
- Add a new "JUnit" run configuration called "app tests".  Run this when you want to run the fast unit tests.
    - Set the "Test kind" to "All in package".
    - Set "Search for tests" to "In a single module".
    - Set "Working directory" to "$MODULE_DIR$".
    - Set "Use classpath of module" to "app".
- Add a new "Android Tests" run configuration called "instrumentation".  Run this when you want to run the slow instrumentation tests.
    - Set the "Module" to "instrumentation".
    - Set the "Target device" to "Show chooser dialog".

Full Build
---------------
A full build can be executed with the command:

`mvn clean install`

This will build everything, run unit tests, and run integration tests, and generate project reports.
The APK should not be treated as a shippable (internal or external build); the value is in the build completion, test execution, and site reports.

Project Reports
---------------
Project reports can be generated with the command:

`mvn clean site`

Release Build
---------------
The release build can be invoked with something like this (ideally on a CI environment):

``mvn clean install -P release -Dbuild.version=1.0.0 -Dbuild.number=123 -Dfingerprint=`git rev-parse HEAD` ``
