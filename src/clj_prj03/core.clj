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
