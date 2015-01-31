# netlogo-clojure
NetLogo extension that allows calling Clojure from NetLogo

Just in proof-of-concept stage.  Experimenting.  Some files will be
moved to better places soon.

To install, copy clojure.jar\*, resources/\* and (optionally)
example.nlogo to a directory that you name 'clojure' in the 'extensions'
subdirectory of your NetLogo installation directory.

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
