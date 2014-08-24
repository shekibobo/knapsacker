(ns knapsacker.core-spec
  (:require [speclj.core :refer :all]
            [knapsacker.core :refer :all]))

(describe "Truth"
          (it "is true"
              (should true))
          (it "is not false"
              (should-not false)))

(describe "Knapsacker"
          (it "optimizes the dolls by value"
              (should== optimized-dolls (knapsack 400 dolls))))

(run-specs)
