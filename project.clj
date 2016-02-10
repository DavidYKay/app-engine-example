(defproject nandxorbox "1.0.0-SNAPSHOT"
  :description "App Engine Dev Sandbox"
  :aot [nandxorbox.core nandxorbox.app_servlet]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.4.0"]
                 [appengine-magic "0.5.0"]
                 [hiccup "1.0.5"]]

)
