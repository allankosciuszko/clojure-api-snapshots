(defproject clj-tests "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [funcool/cats "2.3.2"]
                 [org.clojure/data.json "0.2.6"]
                 [cheshire "5.8.1"]
                 ;; [http-kit "2.3.0"] ; Add to your project.clj.
                 [http-kit "2.4.0-alpha2"]
                 [clj-http "3.9.1"]
                 [environ "1.1.0"]
                 [co.paralleluniverse/quasar-core "0.7.9"]
                 [co.paralleluniverse/pulsar "0.7.9"]
 [lambdaisland/deep-diff "0.0-25"]]
  :plugins [[refactor-nrepl "2.4.0"]
            [lein-environ "1.1.0"]
            [cider/cider-nrepl "0.18.0"]]
  :jvm-opts ["-Dhttps.protocols=TLSv1.2,TLSv1.1"]
  :main  clj-tests.core)
