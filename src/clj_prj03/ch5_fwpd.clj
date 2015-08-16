;;;; Brave Clojure Chapter 5 (FWPD)

(ns clj-prj03.fwpd
  (:require [clojure.string :as s]))

(def filename "suspects.csv")

(def headers->keywords {"Name" :name
                        "Glitter Index" :glitter-index})

;; convert parsed CSV strings to integers

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn parse
  "Convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       (s/split string #"\n")))

;; seriously, what is this guy's obsession with Twilight???

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (let [;; headers become the seq (:name :glitter-index
        headers (map #(get headers->keywords %) (first rows))
        ;; unmapped-rows become the seq (:name :glitter-index)
        unmapped-rows (rest rows)]
        ;; Now return a seq of {:name "X" :glitter-index 10}

    (map (fn [unmapped-row]
           ;; use map to associate each header with its column.
           ;; convert returned seq to map
           (into {}
                 ;; multiple collections sent to map
                 (map (fn [header column]
                        [header ((get conversions header) column)])
                      headers
                      unmapped-row)))
         unmapped-rows)))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def population (mapify (parse (slurp filename))))
population

;; alternatively, we can see it as:
(defn mapify-row
  [headers unmapped-row]
  (map (fn [header column]
    [header ((get conversions header) column)])
    headers
    unmapped-row))
(mapify-row [:name] ["Joe"])

(def suspects (glitter-filter 3 population))
suspects


;; Exercise 1. Get just the names
(defn glitter-filter-names
  [& glitter-filter-args]
  (map #(:name %)
    (apply glitter-filter glitter-filter-args)))

(glitter-filter-names 3 population)

;; Exercise 2. Write a prepend method that adds someone to the top of the list
(defn prepend
  [suspect-name suspect-list]
  (conj suspect-list suspect-name))

(prepend {:name "Dracula" :glitter-index 5} suspects)

;; Exercise 3. Write a validate function to check presence of attributes
;; :name and :glitter-index
(defn validate
  [val-fns record]
  (every? identity (map #((second %) ((first %) record)) val-fns)))

(defn validate-name
  [name]
  (def res (and
            ; name should be longer than 3 chars (stupid check, but whatever)
            (> (count name) 3)
            ; check there are no numbers in the name
            (nil? (re-matches #".*\d.*" name))
            ))
  res)

(validate-name "John Doe")

(defn validate-glitter
  [x]
  ; (println "in validate-glitter with arg" x)
  (and
    (integer? x)
    (>= x 0)
    (<= x 100)
  ))

(def validations {:name validate-name
                  :glitter-index validate-glitter})

(validate validations {:name "John Doe" :glitter-index 12})
(validate validations {:name "John Doe" :glitter-index 120})
(validate validations {:name "John Doe" :glitter-index -12})
(validate validations {:name "John Doe23" :glitter-index 12})
(validate validations {:name "e" :glitter-index -12})
(validate validations {:name "e" :glitter-index 2})
(validate validations {})
(validate validations {:name "John Doe"})
(validate validations {:glitter-index 12})


;; Exercise 4. Write a function that takes your list of maps and converts it to CSV
(defn export-list
  [suspect-list]
  (def header "Name,Glitter Index\n")
  (def suspects-clean (map #(s/join "," (vals %)) suspect-list))
  (def output (str header (s/join "\n" suspects-clean)))
  (println output)
  (spit "suspects-upd.txt" output))

(export-list suspects)
