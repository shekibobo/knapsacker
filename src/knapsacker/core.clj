(ns knapsacker.core
  (:use [csv-map.core :as csv]))


(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :value str->int
                  :weight str->int})

(def sum #(reduce + %))

(defn imported-dolls
  [filename]
  (parse-csv (slurp filename) :key :keyword))

(defn convert-doll
  [doll]
  (into {}
        (map (fn [[key val]] [key ((get conversions key) val)])
             doll)))

(defn dolls
  [filename]
  (map convert-doll (imported-dolls filename)))

(def available-dolls (dolls "resources/dolls.csv"))
(def optimized-dolls (dolls "resources/optimized_dolls.csv"))

(defn optimal-set
  [max-weight available-dolls]
  available-dolls)

(defn optimal-value
  [max-weight available-dolls]
  (sum (map :value available-dolls)))


