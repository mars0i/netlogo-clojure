CLOJURE=$(HOME)/.m2/repository/org/clojure/clojure/1.6.0/clojure-1.6.0.jar 

ifeq ($(origin NETLOGO), undefined)
  NETLOGO=../..
endif

ifneq (,$(findstring CYGWIN,$(shell uname -s)))
  COLON=\;
  JAVA_HOME := `cygpath -up "$(JAVA_HOME)"`
else
  COLON=:
endif

#JAVAC=$(JAVA_HOME)/bin/javac
JAVAC=javac
SRCS=$(wildcard src/*.java)


clojure.jar clojure.jar.pack.gz: $(SRCS) manifest.txt Makefile
	mkdir -p classes
	$(JAVAC) -g -deprecation -Xlint:all -Xlint:-serial -Xlint:-path -encoding us-ascii -source 1.5 -target 1.5 -classpath "$(NETLOGO)"/NetLogo.jar$(COLON)$(CLOJURE) -d classes $(SRCS)
	jar cmf manifest.txt clojure.jar -C classes .
	pack200 --modification-time=latest --effort=9 --strip-debug --no-keep-file-order --unknown-attribute=strip clojure.jar.pack.gz clojure.jar

clojure.zip: clojure.jar
	rm -rf clojure
	mkdir clojure
	cp -rp clojure.jar clojure.jar.pack.gz README.md Makefile src manifest.txt clojure
	zip -rv clojure.zip clojure
	rm -rf clojure

install:
	cp -v *.jar* resources/*.jar* example.nlogo "$(NETLOGO)/extensions/clojure/"
