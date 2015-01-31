netlogo-clojure
====

NetLogo extension by [Marshall
Abrams](http://members.logical.net/~marshall/) that allows calling
[Clojure](http://clojure.org) from NetLogo.

Just in proof-of-concept stage.  Experimenting.

Please feel free to contact me with questions, suggestions, etc. at:

	mabrams ([at]) uab [(dot)] edu
	marshall ([at]) logical [(dot)] net  

### License

This software is copyright 2015 by [Marshall
Abrams](http://members.logical.net/~marshall/), and is distributed under
the [Gnu General Public License version
3.0](http://www.gnu.org/copyleft/gpl.html) as specified in the file
LICENSE, except where noted.

### Installation

To install, copy target/\*.jar\*, resources/\* and (optionally)
src/netlogo/example.nlogo to a directory that you name 'clojure' in
the 'extensions' subdirectory of your NetLogo installation directory.

Or use `make install`.

Or change to the extensions subdirectory of the NetLogo application
directory and unzip clojure.zip.  (This file includes Java source files
for the extension for reference purposes, but if you want to build the
extension from scratch, you probably want to the original version using
`git` from https://github.com/mars0i/netlogo-clojure, because the
Makefile is there and is set up for the directory structure there.)

### Usage

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

