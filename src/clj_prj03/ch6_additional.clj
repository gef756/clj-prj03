(assoc-in {} [:cookie :monster :vocals] "Finntroll")
(get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
(assoc-in {} [1 :connections 4] 2)


;; create a new single function from composition of any number of functions
((comp inc *) 2 3)

;; Role Playing Game
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)
(c-str character)
(c-dex character)

; alternatively could have done this
(fn [c] (:strength (:attributes c)))

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))
(spell-slots character)

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

; comp creates multiple
(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

