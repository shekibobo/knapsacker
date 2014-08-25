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

(describe "optimal dolls"
          (it "optimizes the dolls by value"
              (should== (items "resources/optimized_dolls.csv")
                        (optimal-set 400 (items "resources/dolls.csv")))))

(describe "optimal dolls value"
          (it "returns the maximum value of dolls that can fit in the knapsack"
              (should== (sum (map :value (items "resources/optimized_dolls.csv")))
                        (optimal-value 400 (items "resources/dolls.csv")))))

(describe "optimal boxes"
          (it "optimizes the boxes by value"
              (should== (items "resources/optimized_boxes.csv")
                        (optimal-set 400 (items "resources/boxes.csv")))))

(describe "optimal boxes value"
          (it "returns the maximum value of boxes that can fit in the knapsack"
              (should== (sum (map :value (items "resources/optimized_boxes.csv")))
                        (optimal-value 400 (items "resources/boxes.csv")))))
(run-specs)
