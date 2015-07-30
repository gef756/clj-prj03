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

  ; ----------------------------------
  ; Part 2: Sequence Abstraction
  ; ----------------------------------

  ; Objects may be treated as a seq:

  (map identity {:name "Bill Compton" :occupation "Dead mopey guy"})
  (seq {:name "Bill Compton" :occupation "Dead mopey guy"})
  ;; -> both return a seq (list of vectors)

  ; map function
  (map inc [1 2 3])

  (map str ["a" "b" "c"] ["A" "B" "C"])
  ;; equivalent to:
  (list (str "a" "A") (str "b" "B") (str "c" "C"))

  (def human-consumption [8.1 7.3 6.6 5.0])
  (def critter-consumption [0.0 0.2 0.3 1.1])
  (defn unify-diet-data
    [human critter]
    {:human human
     :critter critter})

  (map unify-diet-data human-consumption critter-consumption)

  ;; you can also pass a collection of functions to map
  (def sum #(reduce + %))
  (def avg #(/ (sum %) (count %)))
  (defn stats
    [numbers]
    (map #(% numbers) [sum count avg]))

  (stats [3 4 10])
  (stats [80 1 44 13 6])


  ; reduce function
  (reduce (fn [new-map [key val]]
             (assoc new-map key (inc val)))
          {}
          {:max 30 :min 10})

  (reduce (fn [new-map [key val]]
            (if (> val 4)
               (assoc new-map key val)
                new-map))
    {}
    {:human 4.1
     :critter 3.9})

  ; take, drop, take-while, drop-while
  (take 3 [1 2 3 4 5 6 7 8 9 10])  ; [1 2 3]
  (drop 3 [1 2 3 4 5 6 7 8 9 10])  ; [4 5 6 7 8 9 10]

  (def food-journal
    [{:month 1 :day 1 :human 5.3 :critter 2.3}
     {:month 1 :day 2 :human 5.1 :critter 2.0}
     {:month 2 :day 1 :human 4.9 :critter 2.1}
     {:month 2 :day 2 :human 5.0 :critter 2.5}
     {:month 3 :day 1 :human 4.2 :critter 3.3}
     {:month 3 :day 2 :human 4.0 :critter 3.8}
     {:month 4 :day 1 :human 3.7 :critter 3.9}
     {:month 4 :day 2 :human 3.7 :critter 3.6}])

  (take-while #(< (:month %) 3) food-journal)
  (drop-while #(< (:month %) 3) food-journal)
  (take-while #(< (:month %) 4)
              (drop-while #(< (:month %) 2) food-journal))

  ; filter, some

  (filter #(< (:human %) 5) food-journal)  ; goes through all
  (some #(> (:critter %) 5) food-journal)
  (some #(> (:critter %) 3) food-journal)
  ; checks if a condition holds for any element in the sequence

  (some #(and (> (:critter %) 3) %) food-journal)

  ; sort
  (sort [3 1 2])
  (sort-by count ["aaa" "c" "bb"])

  ; concat
  (concat [1 2] [3 4])

  ; 2.3 Lazy seqs
  (def vampire-database
    {0 {:makes-blood-puns? false, :has-pulse? true :name "McFishwich"}
     1 {:makes-blood-puns? false, :has-pulse? true :name "McMackson"}
     2 {:makes-blood-puns? true, :has-pulse? false :name "Damon Salvatore"}
     3 {:makes-blood-puns? true, :has-pulse? true :name "Mickey Mouse"}})

  (defn vampire-related-details
    [ssn]
    (Thread/sleep 1000)
    (get vampire-database ssn))

  (defn vampire?
    [record]
    (and (:makes-blood-puns? record)
         (not (:has-pulse? record))))

  (defn identify-vampire
    [ssn]
    (first (filter vampire?
      (map vampire-related-details ssn))))

  ;; clojure clusters in groups of 32
  ;; so this takes 32 seconds, not 3 seconds
  (time (identify-vampire (range 0 1000000)))


  (def identities
    [{:alias "Batman" :real "Bruce Wayne"}
     {:alias "Spiderman" :real "Peter Parker"}
     {:alias "Santa" :real "Your mom"}
     {:alias "Easter Bunny" :real "Your dad"}
     {:alias "alias 5", :real "real 5"}
     {:alias "alias 6", :real "real 6"}
     {:alias "alias 7", :real "real 7"}
     {:alias "alias 8", :real "real 8"}
     {:alias "alias 9", :real "real 9"}
     {:alias "alias 10", :real "real 10"}
     {:alias "alias 11", :real "real 11"}
     {:alias "alias 12", :real "real 12"}
     {:alias "alias 13", :real "real 13"}
     {:alias "alias 14", :real "real 14"}
     {:alias "alias 15", :real "real 15"}
     {:alias "alias 16", :real "real 16"}
     {:alias "alias 17", :real "real 17"}
     {:alias "alias 18", :real "real 18"}
     {:alias "alias 19", :real "real 19"}
     {:alias "alias 20", :real "real 20"}
     {:alias "alias 21", :real "real 21"}
     {:alias "alias 22", :real "real 22"}
     {:alias "alias 23", :real "real 23"}
     {:alias "alias 24", :real "real 24"}
     {:alias "alias 25", :real "real 25"}
     {:alias "alias 26", :real "real 26"}
     {:alias "alias 27", :real "real 27"}
     {:alias "alias 28", :real "real 28"}
     {:alias "alias 29", :real "real 29"}
     {:alias "alias 30", :real "real 30"}
     {:alias "alias 31", :real "real 31"}
     {:alias "alias 32", :real "real 32"}
     {:alias "alias 33", :real "real 33"}
     {:alias "alias 34", :real "real 34"}])

  (defn snitch
    "Announce real identity to the world"
    [identity]
    (println (:real identity))
    (:real identity))

  (def revealed-identities (map snitch identities))
  (first revealed-identities)

;; doesn't print real 33 and real 34

;; lazy value is also cached:
(first revealed-identities) ;; doesn't reprint whole sequence

(doall revealed-identities)

; Infinite sequences
(concat (take 8 (repeat "na")) ["Batman!"])
(take 3 (repeatedly (fn [] (rand-int 10))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))
(take 10 (even-numbers))

(cons 0 '(2 4 6))
