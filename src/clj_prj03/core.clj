(ns clj-prj03.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot"))

(println "Cleanliness is next to godliness")

(defn train [] (println "Choo choo!o"))

(+ 1 (* 2 3) 4)


; Control structures

(println (if true
            "abra cadabra"
            "hocus pocus"))

(if true
  (do (println "Success!")
      "abra cadabra")
  (do (println "Failure:(")
      "hocus pocus"))

(when true
   (println "Success!")
   "abra cadabra")

(def failed-protagonist-names
    ["Larry Potter"
     "Doreen the Explorer"
     "The Incredible Bulk"])

; Equality testing
(println (nil? 1))
(println (nil? nil))
(= 1 1)
(= nil nil)
(= 1 2)

; Numbers
93
1.2
1/5

; String examples
"Lord Voldemort"
"\"He who must not be named\""
"\"Great cow of Moscow!\" - Hermes Conrad"

; String interpolation
(def name "Chewbacca")
(println (str "\"Rawwwwwwwwrrrrr\" - " name))

; 2.5 Maps
;; an empty map
{}
;; keywords denoted by :
{:a 1
 :b "boring example"
 :c []}

;; associate "string-key" with the plus function
{"string-key" +}

;; maps can be nested
{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}

(get {:a 0 :b 1} :b)
(get {:a 0 :b {:c "ho hum"}} :b)

;; returns nil if key not found
(get {:a 0 :b 1} :c)

;; but you can specify a default
(get {:a 0 :b 1} :c "UNICORNS")

;; get-in allows you to dive into maps
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])

;; not used often
({:name "The Human Coffee Pot"} :name)

; Keywords
;; Look up :a in map
(:a {:a 1 :b 2 :c 3})
;; which is the same as:
(get {:a 1 :b 2 :c 3} :a)

;; or provide a default value with keywords
(:d {:a 1 :b 2 :c 3} "FAERIES")

;; hash-maps
(hash-map :a 1 :b 2)

; 2.6 Vectors (can be mixed-type)
[3 2 1]
(get [3 2 1] 0)
(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)
(vector "creepy" "full" "moon")

;; add to the end of a vector
(conj [1 2 3] 4)

; 2.7 Lists
;; use the single quote or 'list'
'(1 2 3 4)
(list 1 2 3 4)

;; get doesn't work
;; instead use nth
(nth '(100 200 300 400) 3)

(conj ('1 2 3) 4) ;; added to the beginning

;; Lists can be extended dynamically in either direction,
;; but have O(n) access (similar to Linked Lists)

;; Vectors on the other hand, are more like arrays: O(1) access,
;; but have to be preallocated

; 2.8 Sets
;; Literal Notation
#{"hannah montana" "miley cirus" 20 45}
(conj #{:a :b} :b) ; won't add duplciate element

;; getting elements (returns either the element or nil)
(get #{:a :b} :a)
(:a #{:a :b})
(get #{:a :b} "hannah montana")

(set [3 3 3 4 4])
;; test if element exists in list
(def test-set [3 3 3 4 4])
(get (set test-set) 3)
(get (set test-set) 5)

;; hashable or sorted versions available
(hash-set 1 1 3 1 2)
(sorted-set :b :a :c)

; 2.9 Symbols and Naming
(def failed-movie-titles
     ["Gone With the Moving Air" 
      "Swellfellas"])

(identity 'test)

; 2.10 Quoting
failed-protagonist-names
(first failed-protagonist-names)

;; using a quote means using the symbol itself as a structure
;; rather than the object
'failed-protagonist-names
(eval 'failed-protagonist-names)
(first 'failed-protagonist-names) ;; doesn't eval
(first ['failed-protagonist-names 'failed-antagonist-names])

;; items will remain unevaluated
'(failed-protagonist-names 0 1)
(first '(failed-protagonist-names 0 1))
(second '(failed-protagonist-nanmes 0 1))

