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

;; Exercise 1: Create a new function called attr that can be
;; called like (attr :intelligence)

(defn attr
  [attr-name]
  (fn [x] (attr-name (:attributes x))))

((attr :intelligence) character)
((attr :strength) character)

;; Exercise 2: implement the comp function

(defn gabe-comp
  "my implementation of the comp function"
  [& fns]
  (fn [& args]
    (if (empty? fns)
      args
      ((last fns) (apply (gabe-comp (butlast fns)) args)))))

((gabe-comp inc *) 6 2)  ; --> should be 13

;; Exercise 3: implement assoc-in function

(defn gabe-assoc-in
  [m [k & ks] v]
  (if (empty? k)
    (assoc m ks v)
    (assoc m (first k) (gabe-assoc-in (get m (first k)) [(rest k) ks] v))))

(def users [{:name "James" :age 26}  {:name "John" :age 43}])
(assoc-in users [1 :age] 44)
(assoc-in users [1 :password] "nhoJ")
(assoc-in users [2] {:name "Jack" :age 19})

;; Exercise 4: use the update-in function
(def users [{:name "James" :age 26}  {:name "John" :age 43}])
(update-in users [0 :age] inc)
(update-in users [1 :name] clojure.string/upper-case)

