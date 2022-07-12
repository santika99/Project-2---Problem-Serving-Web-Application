(ns tugas2.result
  (:require [hiccup.page :refer [html5]]
            [hiccup.element :refer [link-to]]
            [garden.core :refer [css]]))

(def style
  (css
   [:body
    {:background-color "rgb(126, 14, 126)"}]
   [:table {:width "100%"}]
   [:th
    {:width "75%"
     :font-size "130px"
     :border "1px solid black"
     :border-radius "0px"
     :padding "90px"
     :background-color "rgba(182, 92, 182, 0.432)"}]
   [:td
    {:text-align "center"
     :border "1px solid black"
     :border-radius "0px"
     
     }]
   [:.absolute
    {:position "absolute"
     :top "280px"
     :left "500px"}
    [:p {:font-size "19px"}]]
   [:.button1
    {:background-color "rgb(253, 160, 253)"
     :width "100%"
     :font-size "17px"
     :margin-top "20px"
     :padding "20px"}]
   ))

(defn accuracy [score]
  (let [acc (* (/ @score 8) 100)]
    (if (ratio? acc)
      (-> acc
          (float)
          (int)
          (str "%"))
      (str acc "%"))))

(defn result-page [score subject]
  (html5
   [:head
    [:title "Result"]
    [:style style]]
   [:body
    [:div.absolute
     [:p "Score"]
     ]
    [:table
     [:tr
      [:th {:rowspan "4"} (str "+" @score)]
      [:td @score [:br] "Soal Benar"]]
     [:tr
      [:td (- 8 @score) [:br] "Soal Salah"]]
     ]
    (link-to "/" [:button.button1 "Home"]) 
    ]))
