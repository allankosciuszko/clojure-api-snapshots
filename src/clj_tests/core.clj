(ns clj-tests.core
  ;;(:gen-class)
  (:require [clj-http.client :as http]
            [environ.core :refer [env] ]) )

(def url "https://jsonplaceholder.typicode.com/posts")


(def run-mode
  (env :run-mode))



(def id-list (range 1 10))

(defn get-snapshoot[id]
  (http/get (str url "/" id) {:insecure? true}))

(defn save-snaphost[id content]
  (do
    (println "aaa")
    (with-open [w (clojure.java.io/writer (str "post_" id ".json") ) ]
      (.write w content))
      (print (str "saving for id: " id ) )
        #{:done } ) )
 

;; (defn get-and-save-snaphsot[id]
;;   (do (-> (get-snapshoot id) (:body) ((partial save-snaphost id) ) ))
;;   #{:done_saved})

;;(def saving-agents (map (fn[id]  (send-off (agent #{}) (fn [status id] (get-and-save-snaphsot id)) id ) )  id-list )
;;(def save-snapshot-agents[]  )


;;(apply await-for saving-agents)

(def save-dir (cond
                 (= run-mode "pre") "./pre"
                 (= run-mode "post") "./post"))
(defn test[]
  (do (println "a")
      (with-open [w (clojure.java.io/writer "test.json")]
        (.write w "ole"))
      #{:done}))

(defn -main[& args]
    (print (str "save to dir: " save-dir)))

