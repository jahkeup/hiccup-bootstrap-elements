(ns hiccup.bootstrap3.elements
  "Common Bootstrap elements"
  (:require [hiccup.core :as h]
            [clojure.set :refer [union]]
            [hiccup.bootstrap3.utils :as utils]
            [hiccup.bootstrap3.forms :as forms]))


(defn col
  "Create a column with {:size 9} or {:class \"col-sm-9\"}
   or even {:class \"sm-8\"} which will get expanded to
   the right column class"
  [attrs & body]
  (let [colsize (:size attrs)
        classes (:class attrs)
        attrs (dissoc attrs :size)]
    [:div (merge {:class (utils/clss
                          (utils/transform-cls-for-row
                           (union [colsize] (flatten classes))))}
                 attrs)
     body]))

(defn col-row
  "For those who love single column rows,
   same params as col."
  [attrs & body]
  [:div.row
   (apply col attrs body)])

(defn container
  "Nothing special, bootstrap responsive container."
  ([body]
     (container {} body))
  ([attrs & body]
     [:div.container attrs body]))

(defn navbar
  "Bootstrap navbar, tack on some attrs to make it behave.
   See [bootstrap docs](http://getbootstrap.com/components/#navbar)."
  ([body]
     (navbar {} body))
  ([attrs & body]
     [:div.navbar (merge attrs {:role "navigation"})
      (container body)]))

(defn navbar-links
  "Generate links for use in a navbar, typically you'd pass in an
  already prepared coll of links (ie: You already made the :a elem and
  set any other classes on the link)"
  [links]
  [:ul.nav.navbar-nav
   (for [link links]
     [:li link])])

(defn navbar-inverse
  "Create a navbar inverse"
  [attrs body]
  (navbar (merge attrs {:class "navbar-inverse"})))

(defn button
  "Button element using the attrs and with the text"
  ([text]
     (button {} text))
  ([attrs text]
     (if (some utils/bootstrap-class? (:class attrs))
       [:button.btn attrs text]
       [:button.btn.btn-default attrs text])))

(defn button-div
  "Button element (using a div)
   with the attrs and the text"
  ([text]
     (button-div {} text))
  ([attrs text]
     (if (some utils/bootstrap-class? (:class attrs))
       [:div.btn attrs text]
       [:div.btn.btn-default attrs text])))

;; Nice little example
;; (h/html
;;   (container (navbar {:class "navbar-default"} (navbar-links [(utils/link-to "http://google.com" "Google")
;;                                                     (utils/link-to "http://twitter.com/jahkeup" "Twitter")])))
;;   (container (col-row {:size 8} [:h1 "Hello world!"]
;;                       (button-div "Hi there"))
;;              (col-row {:size 12}
;;                       (button "Hi there))))

