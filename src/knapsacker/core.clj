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

(def available-dolls (vec (dolls "resources/dolls.csv")))
(def optimized-dolls (vec (dolls "resources/optimized_dolls.csv")))

(declare msack-value)

; Get the value of the items in sack at [item-index size] (or zero)
(defn sack-value
  [item-index size]
  (let [a1 (println item-index)
        a2 (println size)]
    (cond
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

(def msack-value (memoize sack-value))

(defn optimal-value
  [capacity items]
  (let [values (map :value items)
        weights (map :weight items)
        n-items (count items)]
    (sack-value (dec n-items) capacity)))
