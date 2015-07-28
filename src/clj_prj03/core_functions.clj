(ns clj-prj03.core_functions
  (:gen-class))

(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(map titleize ["Hamsters" "Ragnarok"])  ; vector
(map titleize '("Empathy" "Decorating")) ; list
(map titleize #{"Elbows" "Soap Carving"}) ; set

;; map can also be used on data structures
(defn label-key-val
  [[key val]]
  (str "key: " key ", val: " val))

(map label-key-val {:name "Edward"
                    :occupation "perennial high-schooler"})

(map (fn [[key val]] [key (inc val)])
   {:max 30 :min 10}) ; but this returns a list

;; casting can be done with (into)
(into {} (map (fn [[key val]] [key (inc val)])
  {:max 30 :min 10}))
