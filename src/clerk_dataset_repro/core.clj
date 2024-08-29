(ns clerk-dataset-repro.core
  {:nextjournal.clerk/visibility {:code :hide}}
  (:require
   [nextjournal.clerk :as clerk]
   [tech.v3.dataset :as dataset]))


(comment
  (clerk/serve! {:host "0.0.0.0"
                 :watch-paths ["src"]})
  (clerk/halt!)

  )

;; This works
(let [ds (tech.v3.dataset/mapseq-parser)]
  (doseq [x (for [i (range 10)]
              {:x i
               :xts (java.time.Instant/now)})]
    (ds x))
  (ds))

;; This throws:
;; Unhandled java.lang.ClassCastException
;; class clojure.lang.Reduced cannot be cast to class clojure.lang.ITransientCollection
;; (clojure.lang.Reduced and clojure.lang.ITransientCollection are in unnamed module of loader 'app')
(let [ds (tech.v3.dataset/mapseq-parser)]
  (doseq [x (for [i (range 100)]
              {:x i
               :xts (java.time.Instant/now)})]
    (ds x))
  (ds))
