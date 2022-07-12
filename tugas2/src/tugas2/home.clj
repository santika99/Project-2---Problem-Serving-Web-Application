(ns tugas2.home
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.element :refer [image]]
            ))

(defn base-page [& body]
  (html5
   [:head
    [:title "Home"]
    (include-css "css/home.css")]
   [:body
    [:h1 "Welcome to Zencore"]
    body]))

(def category 
  (base-page
   [:table
    [:tr
     [:td
      [:div.card
       (image "images/mat.png" "math")
       [:a {:href (str "/Math")} [:button "start"]]]]
     [:td
      [:div.card
       (image "images/eng.png" "english")
       [:a {:href (str "/english")} [:button "start"]]]]
     [:td
      [:div.card
       (image "images/verblogic.png" "verblogic") 
       [:a {:href (str "/verblogic")} [:button "start"]]]]
     ]]))