(ns user)

(defn dev []
  (require 'dev.core)
  (in-ns 'dev.core)
  :loaded)
