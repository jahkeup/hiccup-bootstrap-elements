(ns hiccup.bootstrap3.utils
  (:require [clojure.string :as s]
            [clojure.set :refer [union]]
            [hiccup.util :refer [to-uri]]))

(defn prefixer
  "Create a prefixer func"
  [pfx]
  (fn [s] (str pfx (name s))))

(defn prefix-with
  "Prefix coll with the prefix _pfx_"
  [pfx coll]
  (map (prefixer pfx) coll))

(defn clss
  "Prepare class string from any number of coll's"
  [& classes]
  (doall (s/join " " (flatten classes))))

(def column-classes
  (vec (for [c (range 1 13)
         p ["xs" "sm" "md" "lg"]]
         (s/join "-" (vector "col" p  c)))))

(def visibility-classes
  (vec (for [prefix ["visible-" "hidden-"]
             k ["xs" "sm" "md" "lg" "print"]]
         (str prefix k))))

(def text-classes
  (conj (union (vec (for [prefix ["text-"]
                    k ["left" "center" "justify" "right"]]
                      (str prefix k)))
               (vec (for [p ["h"]
                          l (range 1 7)] (str p l))))

        "lead" "small"))

(def helper-classes
  ["center-block" "pull-left" "pull-right" "close" "caret" "clearfix"
   "show" "hidden" "sr-only" "text-hide"])

(def table-classes
  (conj (vec (prefix-with "table-"
                          ["striped" "bordered" "hover" "condensed"]))
        "table"))

(def coloring-classes
  (vec (for [prefix ["text-" "alert-" "btn-" "bg-" ""]
             level ["primary" "success" "info" "warning" "danger"]]
         (str prefix level))))

(def bootstrap-classes (set (union column-classes
                               table-classes
                               coloring-classes
                               text-classes
                               helper-classes
                               visibility-classes)))

(defn bootstrap-class?
  "Helper function, does the class belong to Bootstrap?"
  [class-name]
  (contains? bootstrap-classes class-name))

(def not-bootstrap-class? (complement bootstrap-class?))

(defn short-row-class? [shrt-cls]
  (< 0 (count (re-matches #"^(xs|sm|md|lg)-[0-9]+$" (str shrt-cls)))))

(defn col-class-for
  "Find or make the column for colsize"
  [colcls]
  (cond
   (and (number? colcls) (<= colcls 12)) (str "col-md-" colcls)
   (bootstrap-class? colcls) colcls
   (short-row-class? colcls) (str "col-" colcls)))


(defn transform-cls-for-row
  "If there are any row short classes in here, expand them and return
  the whole coll"
  [coll]
  (vec (map #(or (col-class-for %) %) coll)))

(defn link-to
  "Link creation helper"
  ([url body]
     (link-to url {} body))
  ([url attrs body]
     (let [attrs (merge attrs {:href (to-uri url)})]
       [:a attrs body])))

