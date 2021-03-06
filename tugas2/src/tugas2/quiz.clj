(ns tugas2.quiz
  (:require [hiccup.page :refer [html5]]
            [hiccup.form :as form]
            [garden.core :refer [css]]
            [ring.util.anti-forgery :refer (anti-forgery-field)]))

(def style
  (css
   [:body 
    {:background-color "rgb(126, 14, 126)"}]
   [:* {:box-sizing "border-box"}]
   [:.container
    {
     :border-radius "1px"
     :background-color "rgba(182, 92, 182, 0.432)"
     :width "50%"
     :padding "0 10px 10px 10px"}]
   ["input[type=radio]" {:margin-bottom "8px"}]
   ["input[type=submit]"
    {:margin "0 0 50px 650px"
     :background-color "rgb(253, 160, 253)"
     :border-radius "5px"
     :padding "12px"
     :font-size "15px"
     :cursor "pointer"}
    ]))

(defn subject [problems index]
  (cond
    (= (apply str (take 3 (get-in problems [index :problem-id]))) "565") [:div.container
                                                                          [:p (str (inc index) "/8")]
                                                                          [:p (get-in problems [index :soal :soal-text])]
                                                                          (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                                          (form/radio-button (str "no" index) false "A")
                                                                          (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 0 1])))
                                                                          [:br]
                                                                          (form/radio-button (str "no" index) false "B")
                                                                          (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 1 1])))
                                                                          [:br]
                                                                          (form/radio-button (str "no" index) false "C")
                                                                          (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 2 1])))
                                                                          [:br]
                                                                          (form/radio-button (str "no" index) false "D")
                                                                          (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 3 1])))
                                                                          [:br]
                                                                          (form/radio-button (str "no" index) false "E")
                                                                          (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 4 1])))]
    (= (apply str (take 4 (get-in problems [index :problem-id]))) "5699") [:div.container
                                                                           [:p (str (inc index) "/8")]
                                                                           [:p (get-in problems [index :soal :soal-text])]
                                                                           (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                                           (form/radio-button (str "no" index) false "A")
                                                                           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 0 1])))
                                                                           [:br]
                                                                           (form/radio-button (str "no" index) false "B")
                                                                           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 1 1])))]
    (= (apply str (take 4 (get-in problems [index :problem-id]))) "5690") [:div.container
                                                                           [:p (str (inc index) "/8")]
                                                                           [:p (get-in problems [index :soal :soal-text])]
                                                                           (form/hidden-field (str "no" index "-id") (get-in problems [index :problem-id]))
                                                                           (form/radio-button (str "no" index) false "A")
                                                                           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 0 1])))
                                                                           [:br]
                                                                           (form/radio-button (str "no" index) false "B")
                                                                           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 1 1])))
                                                                           [:br]
                                                                           (form/radio-button (str "no" index) false "C")
                                                                           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 2 1])))
                                                                           [:br]
                                                                           (form/radio-button (str "no" index) false "D")
                                                                           (form/label (str "no" index) (str "   " (get-in problems [index :soal :options 3 1])))]))

(defn soal [problems]
  (loop [items [] index 0]
    (if (= index (count problems))
      items
      (recur (conj items (subject problems index))
             (inc index)))))

(defn base-quiz [title problems]
  (html5
   [:head
    [:title title]
    [:style style]]
   [:body
    (form/form-to
     [:post "/quiz"]
     (for [i (range 8)]
       (get (soal problems) i))
     (anti-forgery-field)
     (form/submit-button "Submit"))]))

(defn math-quiz [problems]
  (base-quiz "Math Quiz" problems))

(defn verblogic-quiz [problems]
  (base-quiz "Verbal Logic Quiz" problems))

(defn inggris-quiz [problems]
  (base-quiz "English Quiz" problems))