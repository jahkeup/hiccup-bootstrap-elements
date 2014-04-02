(ns hiccup.bootstrap3.forms
  "Implements helpers for use with Bootstrap 3 Forms

   http://getbootstrap.com/css/#forms"
  (:require [hiccup.core :as h]
            [clojure.string :as string]
            [hiccup.bootstrap3.utils :as util]))

(defn form
  "Create a bootstrap form with classes and form-body with default
  bootstrap form classes"
  ([form-body]
     (form "" form-body))
  ([classes & form-body]
     (let [classes (util/clss classes)]
       [:form {:class classes :role "form"}
        form-body])))

(def form-horizontal (partial form "form-horizontal"))
(def form-inline (partial form "form-inline"))


(defn label
  "Form label"
  [name text]
  [:label {:for name} text])

(defn input
  "Generate an input group for Forms"
  ([name label-text]
     (input name label-text "text"))

  ([name label-text type]
     (input name label-text type ""))

  ([name label-text type value]
     (input name label-text type value "" []))

  ([name label-text type value placeholder classes]
     (input name label-text type value "" classes {}))

  ([name label-text type value placeholder classes input-attrs]
     [:div {:class (util/clss "form-group" classes)}
      (label name label-text)
      [:input (merge {:class (util/clss "form-control" classes)
                      :type type :name name} input-attrs)]]))


(defn textarea
  "Create a textarea input with the name, label, value, and optionally
  a map of attributes to be applied to the input.

  Eg: (textarea \"comments\" \"Comments\" \"\" {:rows 80})
      will produce a textare input with 80 rows."
  ([name label value]
     (textarea name label value {}))
  ([name label value attrs]
     (input name label "textarea" value "" "textarea" attrs)))

(defn select-alone [name label-text options]
  [(label name label-text)
   [:select.form-control
    (for [option options]
      (let [name (or (and (map? option) (:name option))
                     option)
            val (or (and (map? option) (:value option))
                    option)]
        [:option {:value val} name]))]])


(select-input "my_select" "My Select"
              [{:name "Option 1" :value 1}
               "option2"])
