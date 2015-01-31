# netlogo-clojure
NetLogo extension that allows calling Clojure from NetLogo

Just in proof-of-concept stage.

To install, copy clojure.jar\*, resources/\* and (optionally)
example.nlogo to a directory that you name 'clojure' in the 'extensions'
subdirectory of your NetLogo installation directory.

To use, put this in the Code tab in netlogo:

	extensions [clojure]

Then you can pass strings containing Clojure code to 
`clojure:eval`, which will return a string containing the result
of Clojure eval'ing that input.  For now, that's it.
