// Simple experiments calling Clojure 1.6.0 from Java
//
// see http://www.falkoriemenschneider.de/a__2014-03-22__Add-Awesomeness-to-your-Legacy-Java.html
// for more sophistication (and efficiency)

import java.util.List;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.PersistentHashMap;
import clojure.lang.Keyword;

public class Main {
	public static void main(String[] args) {

		IFn cljPlus = Clojure.var("clojure.core", "+");
		System.out.println(cljPlus.invoke(1, 2)); // java-print result of Clojure function call to +

		// now let's print from Clojure
		IFn cljPrintln = Clojure.var("clojure.core", "println");
		cljPrintln.invoke("Yo!");  // clojure-print

		// We can map inc over a seq of ints (from awesomeness example ref'ed above):
		IFn cljMap = Clojure.var("clojure.core", "map");
		IFn cljInc = Clojure.var("clojure.core", "inc");
		@SuppressWarnings("unchecked") // needed to make Java happy with next line
		final List<Integer> output = (List<Integer>) cljMap.invoke(cljInc, new Integer[] { 1, 2, 3});
		System.out.println(output); // not informative, but worth seeing what happens
		cljPrintln.invoke(output);  // this is what we want to see

		// Want a Clojure REPL available from Java? Here are its guts:
		IFn cljReadString = Clojure.var("clojure.core", "read-string");
		IFn cljEval = Clojure.var("clojure.core", "eval");
		cljPrintln.invoke(cljEval.invoke(cljReadString.invoke("((hash-map :a 25 :b 32.3) :b)")));

		// Same thing, but store the intermediates.  Casts are needed because anything can come out of eval.
		PersistentHashMap hmp = (PersistentHashMap) cljEval.invoke(cljReadString.invoke("(hash-map :a :hij :b :xyz)"));
		Keyword b = (Keyword) cljEval.invoke(cljReadString.invoke(":b"));
		cljPrintln.invoke(hmp.invoke(b)); // Look! We can treat a Clojure map as a function, because it is a function--as far as Clojure's concerned.
	}
}
