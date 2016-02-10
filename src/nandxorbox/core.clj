(ns nandxorbox.core
  (:require [appengine-magic.core :as ae]
            [clojure.string :refer [split]]
            [compojure.core :refer [defroutes GET]]
            [hiccup.middleware :refer [wrap-base-url]]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css]]
            [compojure.route :as route :refer [resources not-found]])
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn index-page
  ([name]
     (html
      [:head
       [:title (str "Hello " name)]
       (include-css "/css/style.css")]
      [:body
       [:h1 (str "Hello " name)]]))
  ([] (index-page "World")))

(def match-opperator
  { "add"      +
    "subtract" -
    "multiply" *
    "divide"   /})

(defroutes hello-routes
  (GET "/calculator/:f/*" [f & x]
       (index-page (apply (match-opperator f)
                          (map #(Integer/parseInt %)
                               (split #" " (:* x))))))
  (GET "/" [] (index-page))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app (wrap-base-url hello-routes))

(ae/def-appengine-app nandxorbox-app #'app)

(defn start []
  (ae/serve nandxorbox-app))

(defn stop []
  (ae/stop))
