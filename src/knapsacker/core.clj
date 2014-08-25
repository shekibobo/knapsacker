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

;; Start with a matrix big enough to hold the combinations of things taken
(defn matrix
  [capacity items]
  (to-array-2d (take (count items) (repeat (take (inc capacity) (repeat 0))))))

(defn knapsack
  [matrix item-index size weights values]
  (cond
    (not= 0 (aget matrix item-index size)) (aget matrix item-index size)
    (= 0 item-index) ()))

(declare msack-value)

; Get the value of the items in sack at [item-index size] (or zero)
(defn sack-value
  [item-index size weights values]
  (let []
    (cond
      ; (not= 0 stored-value) stored-value
      (= 0 item-index) (if (<= (get weights 0) size)
                         (get values 0)
                         0)
      :else
      (let [wi (get weights item-index)
            vi (get values item-index)]
        (max
          (if (<= wi size)
            (+ vi msack-value((dec item-index)
                              (- size wi)
                              weights
                              values))
            0)
          (msack-value (dec item-index) size weights values))))))

(def msack-value (memoize sack-value))

(defn optimal-value
  [capacity items]
  (let [values (map :value items)
        weights (map :weight items)
        n-items (count items)]
    (sack-value (dec n-items) capacity values weights)))

; (defn optimal-value
;   [max-weight available-dolls]
;   (let [passes (count available-dolls)
;         optimal-values (take (inc max-weight) (repeat 0))]
;     (loop [passes-remaining passes
;            current-optimal-values optimal-values]
;       (i)))


