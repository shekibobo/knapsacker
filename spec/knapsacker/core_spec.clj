(ns knapsacker.core-spec
  (:require [speclj.core :refer :all]
            [knapsacker.core :refer :all]))

(describe "Truth"
          (it "is true"
              (should true))
          (it "is not false"
              (should-not false)))

(describe "sum"
          (it "adds the numbers in a list"
              (should== 11 (sum [1 2 3 5]))))

(describe "optimal-set"
          (it "optimizes the dolls by value"
              (should== optimized-dolls (optimal-set 400 available-dolls))))
(describe "optimal-size"
          (it "returns the optimal value of dolls"
              (should== (sum (map :value optimized-dolls)) (optimal-value 400 available-dolls))))

(run-specs)
