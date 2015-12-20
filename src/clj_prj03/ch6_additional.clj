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
  (if (empty? ks)
    (assoc m k v)
    (assoc m k (gabe-assoc-in (get m k) ks v))))

(def users [{:name "James" :age 26}  {:name "John" :age 43}])
(gabe-assoc-in users [1 :age] 44)
(gabe-assoc-in users [1 :password] "nhoJ")
(gabe-assoc-in users [2] {:name "Jack" :age 19})

;; Exercise 4: use the update-in function
(def users [{:name "James" :age 26}  {:name "John" :age 43}])
(update-in users [0 :age] inc)
(update-in users [1 :name] clojure.string/upper-case)
(update-in users [0 :age] + 10)

;; Exercise 5: implement update-in
(defn gabe-update-in
  [m k f & args]
  (assoc-in m k (apply f (get-in m k) args)))

(gabe-update-in users [0 :age] inc)
(gabe-update-in users [1 :name] clojure.string/upper-case)
(gabe-update-in users [0 :age] + 10)

