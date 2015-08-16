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

(mapify (parse (slurp filename)))

;; alternatively, we can see it as:
(defn mapify-row
  [headers unmapped-row]
  (map (fn [header column]
    [header ((get conversions header) column)])
    headers
    unmapped-row))
(mapify-row [:name] ["Joe"])

(glitter-filter 3 (mapify (parse (slurp filename))))
