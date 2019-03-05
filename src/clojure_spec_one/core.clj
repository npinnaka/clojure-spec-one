(ns clojure-spec-one.core
  (:require [clojure-spec-one.spec-validator :as sv]
            [clojure-spec-one.Person :refer [create-person]])
  (:gen-class))

(def rahul {:first-name "Rahul" :last-name "Dravid" :gender "M" :favorite-color "white" })

(def sachin {:first-name "Sachin" :last-name "Tendulkar" :middle-name "Ramesh" :gender "M" :favorite-color "black" })

(def rahul-upper {:first-name "Rahul" :last-name "Dravid" :gender "m" :favorite-color "WHITE" })

(def sachin-upper {:first-name "Sachin" :last-name "Tendulkar" :middle-name "Ramesh" :gender "M" :favorite-color "black" })

(sv/valid-person? rahul)
