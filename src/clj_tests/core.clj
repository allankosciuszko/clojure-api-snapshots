(ns clj-tests.core
  ;;(:gen-class)
  (:require [clj-http.client :as http]
            [environ.core :refer [env] ] ) )

(def url "https://jsonplaceholder.typicode.com/posts")

(defn delete-files-rec [fname]
  (doseq [f (reverse (file-seq (clojure.java.io/file fname)))]
    (clojure.java.io/delete-file f)))

(def run-mode
  (env :run-mode))

(def id-list (range 1 10))

(defn get-snapshoot[id]
  (http/get (str url "/" id) {:insecure? true}))

(defn save-snaphost[id content]
  (do
    (with-open [w (clojure.java.io/writer (str "./" save-dir "/post_" id ".json") ) ]
      (.write w content))
      (print (str "saving for id: " id ) )
        #{:done } ) )

(defn get-and-save-snaphsot[id]
  (do (-> (get-snapshoot id) (:body) ((partial save-snaphost id) ) ) )
  #{:done_saved})

(defn  run-saving-agents [] (map (fn[id] (send-off
                                 (agent #{} )
                                 (fn [status id] (get-and-save-snaphsot id)) id ) )
                        id-list ) )



(def save-dir (cond
                 (= run-mode "pre") "./pre"
                 (= run-mode "post") "./post"))

(defn ensure-clean-save-dir[]
  (do (if (.exists (java.io.File. save-dir))
        (delete-files-rec save-dir ) )
      (.mkdir (java.io.File. save-dir) ) ) )

(defn -main[& args]
  (do (ensure-clean-save-dir)
      (println (str "save to dir: " save-dir ) )
      (let [sv-agents (run-saving-agents)] ) ) )

