(ns ui.core
  (:require [reagent.core :as r :refer [atom]]
            [bundle]))

(enable-console-print!)

;; we can get external library from deps
;; (def Awesome (aget js/deps "awesome-component"))
;; (def view []
;;   [:> Awesome {:name "me"}])

(defn test-atom []
  (let [c (atom 1)]
    (fn []
      [:div
       [:p "count is"]
       [:h3 @c]
       [:button {:on-click #(swap! c inc)} "increase"]])))

(defn logo [src]
  [:span {:class "logos"}
   [:img {:src src}]])

(defn root-component []
  [:div
   [:h1 "hello electron and cljs!"]
   [logo "img/cljs-logo.svg"]
   [logo "img/reagent-logo.png"]
   [logo "img/electron-logo.png"]
   [test-atom]
   ])


(r/render
 [root-component]
 (js/document.getElementById "app-container"))
