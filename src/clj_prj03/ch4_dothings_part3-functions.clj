; 3.1 Calling Functions
(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])

;; 'or' returns first truthy value
((or + -) 1 2 3 4) ;; adds values


;; 'and' returns first falsey value or last truthy value
((and (= 1 1) +) 1 2 3)

;; numbers and strings can't be functions
(1 2 3 4)
("test" 1 2 3)

;; the inc function increments a number by 1
(inc 1.1)

(map inc [0 1 2 3])

(def failed-movie-titles ["Gone with the Moving Air" "Swellfellas"])

(def severity :mild)
(def error-message "dun dun dun...")
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED"))
  (def error-message (str error-message "DOOMED")))
error-message
(println error-message)

; 3.3 Functions

;; defined with defn, a name, an optional docstring, parameters, the body
(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OMG! " name " YOU ARE MOST DEFINITELY LIKE THE BEST"
     "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY"
     "TO SOMEWHERE"))

(too-enthusiastic "Zelda")

;; the docstring can be viewed with the doc function
(doc map)

;; parameters
(defn no-params
  []
  "I take no parameters")

(defn one-param
  [x]
  (str "I take one param: " x " It'd better be a string!"))

(defn two-params
  [x y]
  (str "Two parameters! That's nothing! They will be crushed!" x y))

(defn three-params
  [x y z]
  (str (one-param x) (two-params y z)))

;; function overloading
(defn multi-arity
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
   (three-params first-arg second-arg third-arg))
  ([x y]
   (two-params x y))
  ([x] (one-param x))
  )

(multi-arity "hi" "bob")
(multi-arity "hi" "you" "stupid")

(defn x-chop
  "describe the kind of chop you're inflicting on someone"
  ([name chop-type] (str "I " chop-type " chop " name "! Take that!"))
  ([name] (x-chop name "karate")))

(x-chop "Kanye West" "slap")
(x-chop "Kanye East")

;; in theory, you could do anything with overloading
(defn weird-arity
  ([]
    "Destiny dressed you this morning my friend, and now Fear is
     trying to pull off your pants. If you give up, if you give in,
     you're gonna end up naked with Fear standing there laughing
     at your dangling unmentionables! - the Tick")
   ([number] (inc number)))

;; rest-param (catch-all for other parameters)
(defn codger-communication
  [whippersnapper]
  (str "Get off my own lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers] ;; ampersand indicates "rest-param"
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")

;; Destructuring: kind of like pattern matching
(defn my-first
  [[first-thing]]
  first-thing)

(my-first ["oven" "bike" "waraxe"]) ;; => oven

;; without destructuring...
(defn my-other-first
  [collection]
  (first collection))

(my-other-first ["nickel" "hair"])

;; destructuring example:

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquamen"])

;; destructuring maps
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
(announce-treasure-location {:lat 28.22 :lng 81.33})

(defn steer-ship! [loc] (println (str "Steering towards" loc)))

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  (steer-ship! treasure-location))

;; function body: returns last form being evaluated
(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(illustrative-function)

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! what a big number!"
    "That number's OK, I guess"))

(number-comment 5)
(number-comment 7)

; 3.4 Anonymous Functions


;; Form is: (fn [param-list] function body)
(map (fn [name] (str "Hi, " name))
  ["Darth Vader" "Mr. Magoo"])

;; Another example
((fn [x] (* x 3)) 8)

;; or define a function using a lambda
(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)

;; you can also use # to use an anonymous function
;; % is the argument
#(* % 3)

;; applying it
(#(* % 3) 8)

(map #(str "Hi, " %)
  ["Darth Vader" "Mr. Magoo"])

#(* % 3)

;; arguments can be distinguished with numbers: %1, %2
(#(str %1 " and " %2) "corn bread" "butter beans")

;; or a rest param as %&
(#(identity %&) 1 "blarg" :yip)

; 3.5 Returning Functions
; functions can return other functions

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))
(inc3 7)
