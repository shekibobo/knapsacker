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

(describe "optimal dolls2"
          (it "optimizes the dolls2 by value"
              (should== (items "resources/optimized_dolls2.csv")
                        (optimal-set 165 (items "resources/dolls2.csv")))))

(describe "optimal dolls2 value"
          (it "returns the maximum value of dolls2 that can fit in the knapsack"
              (should== (sum (map :value (items "resources/optimized_dolls2.csv")))
                        (optimal-value 165 (items "resources/dolls2.csv")))))

(describe "optimal dolls3"
          (it "optimizes the dolls3 by value"
              (should== (items "resources/optimized_dolls3.csv")
                        (optimal-set 26 (items "resources/dolls3.csv")))))

(describe "optimal dolls3 value"
          (it "returns the maximum value of dolls3 that can fit in the knapsack"
              (should== (sum (map :value (items "resources/optimized_dolls3.csv")))
                        (optimal-value 26 (items "resources/dolls3.csv")))))
(run-specs)
