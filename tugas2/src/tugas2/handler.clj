(ns tugas2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [tugas2.home :as home]
            [ring.util.response :as resp]
            [tugas2.result :as result]
            [tugas2.problems :as problem]
            [tugas2.quiz :as quiz]))

(defroutes app-routes
  (GET "/" [] home/category)
  (GET "/Math" [] (quiz/math-quiz (problem/take-soal (shuffle problem/math))))
  (GET "/english" [] (quiz/inggris-quiz (problem/take-soal (shuffle problem/English))))
  (GET "/verblogic" [] (quiz/verblogic-quiz (problem/take-soal (shuffle problem/verblogic))))

  (POST "/quiz" [no0-id no0 no1-id no1 no2-id no2 no3-id no3 no4-id no4 no5-id no5 no6-id no6 no7-id no7] (do (problem/reset-score)
                                                                                                              (problem/change-subject no0-id)
                                                                                                              (problem/check-jawaban no0-id no0)
                                                                                                              (problem/check-jawaban no1-id no1)
                                                                                                              (problem/check-jawaban no2-id no2)
                                                                                                              (problem/check-jawaban no3-id no3)
                                                                                                              (problem/check-jawaban no4-id no4)
                                                                                                              (problem/check-jawaban no5-id no5)
                                                                                                              (problem/check-jawaban no6-id no6)
                                                                                                              (problem/check-jawaban no7-id no7)
                                                                                                              (resp/redirect "/result")))
  (GET "/result" [] (result/result-page problem/score problem/subject))
  (route/not-found "Belum ada"))

(def app
  (wrap-defaults app-routes site-defaults))
