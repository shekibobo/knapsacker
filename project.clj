(defproject knapsacker "0.1.0-SNAPSHOT"
  :description "Maximize the value of items carried given a weight limit."
  :url "https://www.github.com/shekibobo/knapsacker"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [csv-map "0.1.1-SNAPSHOT"]]
  :plugins [[speclj "3.1.0"]]
  :test-paths ["spec"]
  :main ^:skip-aot knapsacker.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[speclj "3.1.0"]]}})
