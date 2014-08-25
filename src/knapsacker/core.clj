;; Written by Joshua Kovach
;; 24 August 2014
(ns knapsacker.core
  (:use [csv-map.core :as csv]))

;; Converts a string to an integer.
;; Used in the data import process, since all values are imported as strings.
(defn str->int
  "Convert a string to an integer"
  [str]
  (Integer. str))

;; Map for determining which keys of the data should be converted and how.
;; Name can remain a string, but value and weight need to be converted to
;; integers (in the given data) in order to work. This is probably insufficient
;; in the long run, and should be converted to use a decimal format.
(def conversions {:name identity
                  :value str->int
                  :weight str->int})

;; Utility method to help keep our tests clean. Probably not necessary.
(def sum #(reduce + %))

;; Use csv-map to import a CSV file. See /resources/*.csv
(defn imported-dolls
  "Expects path to a csv file relative to project root"
  [filename]
  (parse-csv (slurp filename) :key :keyword))

;; Convert dolls imported from CSV so that values and weights are integers.
;; When importing from CSV, all values are considered strings, and we can't do
;; arithmetic on strings.
(defn convert-doll
  "Converts raw csv imported properties to the correct type. Expects a doll."
  [doll]
  (into {}
        (map (fn [[key val]] [key ((get conversions key) val)])
             doll)))

(defn dolls
  "Expects a csv path relative to project root. Returns a usable doll map."
  [filename]
  (map convert-doll (imported-dolls filename)))

;; Helpers to quickly access the dolls we use for testing.
(def available-dolls (vec (dolls "resources/dolls.csv")))
(def optimized-dolls (vec (dolls "resources/optimized_dolls.csv")))

;; This is declared so it will be accessible within the primary sack-value
;; function. msack-value is a memoized sack value, which will cache the
;; arguments and return values. Explained more below.
(declare msack-value)

; Get the value of the items in sack at [item-index size] (or zero)
(defn sack-value
  "Expects item-count - 1 and the capacity of the knapsack."
  [item-index size]
  (let [debug1 (println (clojure.string/join ", " [item-index size]))]
    (cond
      ;; provide endpoints of the grid
      (< item-index 0) 0
      (= size 0) 0
      :else
      (let [{weight :weight value :value} (get available-dolls item-index)]
        (if (> weight size)
          (msack-value (dec item-index) size)
          (let [value-leave (msack-value (dec item-index) size)
                value-take (msack-value (dec item-index) (- size weight))]
            (if (> (+ value-take value) value-leave)
              (+ value-take value)
              value-leave)))))))

;; Since we're in an immutable ecosystem, we can't
;; just use an outside variable, since it can't change anyway. We could also
;; pass state, but that can become very complicated. Instead, we will just
;; use msack-value from within the sack-value function, since it will remember
;; the values that it has already found for us.
(def msack-value (memoize sack-value))

;; Retrieve the optimal value of the dolls that can fit in the knapsack.
(defn optimal-value
  [capacity items]
  (let [n-items (count items)]
    (sack-value (dec n-items) capacity)))
