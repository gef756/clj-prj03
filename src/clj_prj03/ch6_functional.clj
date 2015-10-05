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
