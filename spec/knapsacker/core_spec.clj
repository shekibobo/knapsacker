(ns knapsacker.core-spec
  (:require [speclj.core :refer :all]
            [knapsacker.core :refer :all]))

(describe "Truth"
          (it "is true"
              (should true))
          (it "is not false"
              (should-not false)))

(run-specs)
