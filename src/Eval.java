import org.nlogo.api.*;
import java.net.*;

//import java.util.List;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.pprint.*;
//import clojure.lang.PersistentHashMap;
//import clojure.lang.Keyword;

public class Eval extends DefaultReporter {

	// take string as input, report a string
	public Syntax getSyntax() {
		return Syntax.reporterSyntax(new int[] {Syntax.StringType()}, Syntax.StringType());
	}

	public Object report(Argument args[], Context context)
		throws ExtensionException {
		try {
			addPath("extensions/clojure/clojure-1.6.0.jar"); // NetLogo is run relative to its home directory, which contains the extensions directory.

			// These will throw extensions without the preceding line, which depends on methods defined below.
			IFn cljReadString = Clojure.var("clojure.core", "read-string");
			IFn cljEval = Clojure.var("clojure.core", "eval");
			// IFn cljCLformat = Clojure.var("clojure.pprint", "cl-format"); // doesn't seem to be right--can't use the function below

			String clojInput = args[0].getString();  // whatever NetLogo passed in
			Object retObj = cljEval.invoke(cljReadString.invoke(clojInput)); // now Clojure-eval it.
			//Object retObj = cljCLformat.invoke("nil", "~a", cljEval.invoke(cljReadString.invoke(clojInput)));  // throwing unbound function exception

			// Return to NetLogo

			if (retObj == null) {  // cheap kludge because nils become nulls.
				return "nil";
			}{
				return retObj.toString();
			}

		} catch (Throwable e) {
			throw new ExtensionException( e.getMessage() ) ;
		}
	}

	// Slightly modified from http://jimlife.wordpress.com/2007/12/19/java-adding-new-classpath-at-runtime. (Thanks Jim!)
	// (There's supposed to be a way to do this without reflection, but I haven't gotten it to work with NetLogo.
	//  See e.g. http://www.coderanch.com/t/384068/java/java/Adding-JAR-file-Classpath-at.)
	public static void addPath(String s) throws Exception {
		java.io.File f = new java.io.File(s);
		URL url = f.toURI().toURL();
		URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class<?> urlClass = URLClassLoader.class;
		java.lang.reflect.Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
		method.setAccessible(true);
		method.invoke(urlClassLoader, new Object[]{url});
	}
}

class AddToClassLoader extends URLClassLoader{
 
    /**
     * @param urls accepts the existing classpath
     */
    public AddToClassLoader(URL[] urls) {
        super(urls);
    }
    
    @Override
    /**
     * add casspath to the loader.
     */
    public void addURL(URL url) {
        super.addURL(url);
    }
}
