;;; Chapter 6: Functional Programming

;;; Part 1

; Pure Functions
(get [:chumbawuma] 0)
(reduce + [1 10 5])
(str "wax on " "wax off")

; Pure functions
; * always return the same result given the same arguments
; ("referential transparency")
; * don't cause any side effects

;; Section 1.1: Referential Transparency
(+ 1 2)

; this function is referentially transparent
(defn wisdom
  [words]
  (str words ", Daniel-san"))
(wisdom "Always bathe on Fridays")

; this isn't:
(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))

(defn analysis
  [text]
  (str "Character count: " (count text)))

(defn analyze-file
  [filename]
  (analysis (slurp filename)))


;; Section 1.2: Side Effects
; Clojure core data structures are all immutable!

;;; Part 2: Immutable Data structures

;; 2.1 Recursion instead of for/while
; The functional alternative to mutation is recursion

(defn no-mutation
  [x]
  (println x) ; --> original x

  ;; let creates a new scope
  (let [x "Kafka Human"]
    (println x)) ; --> Kafka Human

  ;; Exiting the let scope, x is the same
  (println x)) ; --> original x
(no-mutation "Existential Angst Person")

(defn sum
  ([vals] (sum vals 0))
  ([vals accum]
    (if (empty? vals)
      accum
      (sum (rest vals) (+ (first vals) accum)))))

; evaluation is as follows:
(sum [39 5 1])
(sum [39 5 1] 0)
(sum [5 1] 39)
(sum [1] 44)
(sum [] 45)

; Use recur!
; wait?!?!?! no tail-call optimization????

(defn sum
  ([vals] (sum vals 0))
  ([vals accum]
    (if (empty? vals)
      accum
      (recur (rest vals) (+ (first vals) accum)))))

;; 2.2 Functional Composition instead of Attribute mutation

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))
(clean "My boa constrictor is so sassy lol!")
