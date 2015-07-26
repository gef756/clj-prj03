; Ch 4 Pulling it All Together
; 4.1 The Shire's Next Top Model

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 3}
                             {:name "left-foot" :size 2}])

(defn needs-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn make-matching-part
   [part]
   {:name (clojure.string/replace (:name part) #"^left-" "right-")
    :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps which have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
     (if (empty? remaining-asym-parts)
        final-body-parts
        (let [[part & remaining] remaining-asym-parts final-body-parts
          (conj final-body-parts part)]
          (if (needs-matching-part? part)
            (recur remaining
               (conj final-body-parts
                     (make-matching-part part)))
            (recur remaining final-body-parts))))))

(symmetrize-body-parts asym-hobbit-body-parts)

; 4.2 let statement
(let [x 3] x)

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)
(def x 0)
(let [x 1] x)

(def x 0)
(let [x (inc x)] x)

(let [[pongo & dalmatians] dalmatian-list] [pongo dalmatians])

; 4.3 loop
(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

;; functional alternative
(defn recursive-printer
  ([]
    (recursive-printer 0))
  ([iteration]
    (println (str "Iteration " iteration))
    (if (> iteration 3)
      (println "Goodbye!")
      (recursive-printer (inc iteration)))))
(recursive-printer)

; 4.4 Regular Expressions
;; format is as follows

#"regular-expression"

; 4.5 Explanation of Symmetrizer Code

; 4.6 Shorter version using reduce
(reduce + [1 2 3 4])
(reduce + 15 [1 2 3 4])

(defn my-reduce
  ([f initial coll]
  (loop [result initial
         remaining coll]
    (if (empty? remaining)
      result
      (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

(defn better-symmetrize-body-parts
  "Expects a seq of maps which have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (let [final-bod-parts (conj final-body-parts part)]
              (if (needs-matching-part? part)
                (conj final-body-parts (make-matching-part part))
                final-body-parts)))
  []
  asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)

; 4.7 Hobbit Violence
(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + 0 (map :size sym-parts))
        target (inc (rand body-part-size-sum))]
  (loop [[part & rest] sym-parts
        accumulated-size (:size part)]
     (if (> accumulated-size target)
       part
       (recur rest (+ accumulated-size (:size part)))))))

(hit asym-hobbit-body-parts)
(hit asym-hobbit-body-parts)
