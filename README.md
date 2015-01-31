# netlogo-clojure
NetLogo extension that allows calling Clojure from NetLogo

Just in proof-of-concept stage.  Experimenting.  Some files will be
moved to better places soon.

To install, copy *target/\*.jar\**, *resources/\** and (optionally)
*src/netlogo/example.nlogo* to a directory that you name 'clojure' in
the 'extensions' subdirectory of your NetLogo installation directory.

Or use `make install`.

Or change to the *extensions* subdirectory of NetLogo application
directory, and unzip clojure.zip.  (This includes Java source files for
the extension, but if you want to build the extension from scratch, you
should get the original version from
https://github.com/mars0i/netlogo-clojure .)

To use, put this in the Code tab in netlogo:

	extensions [clojure]

Then you can pass strings containing Clojure code to 
`clojure:eval`, which will return a string containing the result
of Clojure eval'ing that input.  

The Clojure instance persists once it's loaded, so definitions from
earlier calls from NetLogo can be used later:

	observer> show clojure:eval "(defn foo [x] (* x (inc x)))"
	observer: "#'clojure.core/foo"
	observer> show clojure:eval "(foo 2)"
	observer: "6"

For now, that's it.

#### Note:

*clojure-\<version number\>.jar* is the main Clojure jar file.  This is
what's needed to run Clojure in any manner at all.

*clojure.jar* is the NetLogo extension jar file.

Similar remarks apply to *\*.jar.pack.gz*, which are needed for Applets.

