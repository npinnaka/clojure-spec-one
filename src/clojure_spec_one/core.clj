(ns clojure-spec-one.core
  (:require [clojure.spec.alpha :as s]
            [clojure.tools.logging :as log]
            [clojure.string :as strg])
  (:gen-class))

(def not-blank? (complement strg/blank?))

(s/def ::first-name
       (s/and string?
              #(not-blank? %)))

(s/def ::last-name
       (s/and string?
              #(not-blank? %)))

(s/def ::middle-name
       (s/and string?
              #(not-blank? %)))

(s/def ::gender
       (s/and string?
              #(contains? #{"M" "F" "MALE" "FEMALE"}
                %)))

(s/def ::favorite-color
       (s/and string?
              #(contains? #{"BLACK" "WHITE"}
                %)))

(s/def ::person
       (s/keys :req-un
                    [::last-name
                      ::first-name
                      ::gender
                      ::favorite-color]
               :opt [::middle-name]))

(defn valid-person?
  "returns true for valid data and false for invaid, incase of false logging errors"
  [person]
  (if (s/valid? ::person person)
    (identity true)
    (do (log/error (s/explain ::person person))
      (identity false))))

(valid-person? {:first-name "John" :last-name "Doe" :gender "m" :favorite-color "black"})
(valid-person? {:first-name "John" :middle-name "M" :last-name "Doe" :gender "m" :favorite-color "black"})

(s/explain ::first-name "")
(s/explain ::first-name nil)
(s/explain-data ::first-name "" )