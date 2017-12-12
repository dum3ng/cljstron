
Based on the work of [Gonzih](https://github.com/Gonzih/cljs-electron)
# Clojurified Electron

![](https://raw.githubusercontent.com/Gonzih/cljs-electron/master/demo.gif)

## What is currently included

* ClojureScript (init script and ui code)
* Figwheel for interactive development
* Reagent for UI
* Advanced compilation with externs inference in release compilation targets

## Running it


```shell
# install electron binaries
$ npm install electron-prebuilt -g 

# compile cljs for nodejs
$ lein trampoline cljsbuild auto electron-dev

# in another terminal session
$ rlwrap lein trampoline run -m clojure.main  # or if you are using emacs cider, just 'C-c M-j'

# the repl will prompt in user namespace
 user=> (dev)
 dev.core=> (start)  # will start figwheel
 dev.core=> (repl)  # will make a cljs repl for *frontend-dev* as default

# in another terminal
$ electron .

# now we can test in the repl
cljs.user=> (js/alert "hello")


```

## Releasing

```shell
lein do clean, cljsbuild once frontend-release, cljsbuild once electron-release
electron . # start electron to test that everything works
```

After that you can follow [distribution guide for the electron.](https://github.com/atom/electron/blob/master/docs/tutorial/application-distribution.md)

The easiest way to package an electron app is by using [electron-packager](https://github.com/maxogden/electron-packager):

```shell
npm install electron-packager -g                                            # install electron packager
electron-packager . HelloWorld --platform=darwin --arch=x64 --version=1.4.8 # package it!
```
