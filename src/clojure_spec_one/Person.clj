(ns clojure-spec-one.Person)

(defrecord Person [first-name last-name middle-name gender favorite-color])

(defn create-person[person-map]
  (Person. (:first-name person-map)
           (:last-name person-map)
           (:middle-name person-map)
           (:gender person-map)
           (:favorite-color person-map)))