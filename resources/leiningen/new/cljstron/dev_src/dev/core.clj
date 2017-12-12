(ns dev.core
  (:require [figwheel-sidecar.repl-api :as ra]))

(defn start []
  (ra/start-figwheel!))

(defn repl
  ([]
   (repl "frontend-dev"))
  ([id]
   (ra/cljs-repl id)))
