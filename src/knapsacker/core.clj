(ns knapsacker.core
  (:use [csv-map.core :as csv]))

(def dolls
  (parse-csv (slurp "resources/dolls.csv") :key :keyword))

(def optimized-dolls
  (parse-csv (slurp "resources/optimized_dolls.csv") :key :keyword))


(defn knapsack
  [max-weight available-dolls]
  [])
