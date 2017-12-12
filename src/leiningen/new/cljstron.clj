(ns leiningen.new.cljstron
  (:require [leiningen.new.templates
             :refer [renderer
                     group-name
                     project-name
                     name-to-path ->files]]
            [leiningen.core.main :as main]
            [clojure.string :as string]
            [clojure.java.io :as io]
            ))

(def proj-dir (io/file (System/getProperty "leiningen.original.pwd")))

(defn unpack
  [name-proj name-in name-out]
  (let [p  (string/join "/" ["leiningen" "new" "cljstron" name-in])
        i  (io/resource p)
        o  (io/file proj-dir name-proj name-out)
        _  (io/make-parents o)
        is (io/input-stream i)
        os (io/output-stream o)]
    (io/copy is os)
    (.flush os)))

(def render (renderer "cljstron"))

(defn cljstron
  "FIXME: write documentation"
  [name]
  (let [data {:name (project-name name)
              :group-name (or (group-name name) "AwesomeCompany")
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' cljstron project.")

    (->files  data
              ;; dev_src
              ["dev_src/dev/core.clj" (render "dev_src/dev/core.clj" data)]
              ["dev_src/dev/core.cljs" (render "dev_src/dev/core.cljs" data)]
              ;; electron_src
              ["electron_src/electron/core.cljs" (render "electron_src/electron/core.cljs" data)]
              ;; ui_src
              ["ui_src/ui/core.cljs" (render "ui_src/ui/core.cljs" data)]
              ;; src
              ["src/js/main.js" (render "src/js/main.js" data)]
              ["src/tools/figwheel_middleware.clj" (render "src/tools/figwheel_middleware.clj" data)]
              ["src/user.clj" (render "src/user.clj" data)]

              ;; resources
              ["resources/public/css/main.css" (render "resources/public/css/main.css")]

              ["resources/public/index.html" (render "resources/public/index.html")]

              ;; configs
              ["README.md" (render "README.md" data)]
              ["LICENSE.md" (render "LICENSE.md" data)]
              ["package.json" (render "package.json" data)]
              ["webpack.config.js" (render "webpack.config.js" data)]
              ["project.clj" (render "project.clj" data)])
    (mapv #(unpack (:name data) % %)
          ["resources/public/img/cljs-logo.svg"
           "resources/public/img/reagent-logo.png"
           "resources/public/img/electron-logo.png"])))
