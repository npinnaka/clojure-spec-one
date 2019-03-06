(ns clojure-spec-one.core
  (:require [clojure-spec-one.spec-validator :as sv]
            [clojure.string :as strg]
            [clojure-spec-one.Person :refer [create-person]]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io])
  (:gen-class))

(def my-collection [{:first-name "Rahul" :last-name "Dravid" :gender "M" :middle-name nil}
                    {:first-name "Sachin" :last-name "Tendulkar" :gender "M" :middle-name "Ramesh"}
                    {:first-name "Rohit" :last-name "Sharma" :gender "M" :middle-name nil}
                    {:first-name "Virat" :last-name "Kohli" :gender "M" :middle-name nil}
                    {:first-name "Sania" :last-name "Mirza" :gender "F" :middle-name nil}])


(defn wirte-file [collection file-name]
  (spit file-name
        (str (strg/join ", " (map name (keys (first my-collection)))) "\n"))
  (map
   #(spit file-name
     (str
      (->>
       (vals %)
       (strg/join ","))
      "\n")
     :append true)
   my-collection))


(wirte-file my-collection "resources/test.csv")

(defn read-csv [file-name]
  (with-open [reader (io/reader file-name)]
    (doall
     (csv/read-csv reader))))

(defn write-csv[file-name csv-data]
  (with-open [writer (io/writer file-name)]
    (csv/write-csv writer
                   csv-data)))

(write-csv "resources/newfile.csv"
           (concat (vector (into [] (map name (keys (first my-collection)))))
                   (mapv
                    #(->>
                      (vals %))
                    my-collection)))

(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data) ;; First row is the header
            (map keyword) ;; Drop if you want string keys instead
            repeat)
       (rest csv-data)))

(vec (csv-data->maps (read-csv (io/reader "resources/newfile.csv"))))
