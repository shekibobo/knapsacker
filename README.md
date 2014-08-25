# Knapsacker

You are a drug trafficker. Every day you meet with a different nice older lady (the mule) and find out how much weight she can carry in her handbag. You then meet with your supplier who has packed various drugs into a myriad of collectible porcelain dolls. Once packed with drugs, each of the precious dolls has a unique combination of weight and street value. Sometimes your supplier has more dolls than the nice lady can carry, though space in her handbag is never an issue. Your job is to choose which dolls the kind soul will carry, maximizing street value, while not going over her weight restriction.

### Inputs

- A set of dolls with a `name`, and unique combination of `weight` and `value`.
- An overall weight restriction.

### Output

The optimal set of poorcelin dolls which:

- are within the total weight restriction
- maximize the total street value of the drugs delivered


## Installation

1. Install [Leiningen](https://github.com/technomancy/leiningen)
2. Clone the repo `git clone https://github.com/shekibobo/knapsacker`
3. Run `lein spec`

## Usage

See [`core_spec`](spec/knapsacker/core_spec.clj) for usage details.

Given a [properly formatted csv](resources/dolls.csv), this project can be used
to solve the [Knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem#0.2F1_knapsack_problem). A csv file is not required, but several have been provided for example usage.

```clojure
;; Load a csv file
(def my-list (items "file-path/relative-to/project/root.csv"))

;; Determine optimal value given capacity
(optimal-value <capacity> <list>)

;; Determine optimal set of items given capacity
(optimal-set <capacity> <list>)
```

## Examples

```clojure
;; Load a csv file
(def dolls (items "resources/dolls.csv"))

;; Find the highest value achievable given knapsack capacity 345
(optimal-value dolls)
;; => 930

;; Find the optimal set of items to grab given knapsack capacity 200
(optimal-set 200 dolls)
;; => [ {:value 150, :weight 9, :name "luke"}
        {:value 35, :weight 13, :name "anthony"}
        {:value 160, :weight 50, :name "dorothy"}
        {:value 60, :weight 15, :name "puppy"}
        {:value 60, :weight 27, :name "randal"}
        {:value 70, :weight 11, :name "marc"}
        {:value 70, :weight 42, :name "grumpkin"}
        {:value 80, :weight 22, :name "grumpy"}
        {:value 20, :weight 7, :name "eddie"}
        {:value 50, :weight 4, :name "sally"}]
(optimal-set 200 dolls)
;; => 755

```

## License

Copyright © 2014 Joshua Kovach

Distributed under the Eclipse Public License either version 1.0 or any later version.
