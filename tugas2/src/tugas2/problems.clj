(ns tugas2.problems)

(def math (->> "resources/public/problem/math.edn"
               (slurp)
               (read-string)))

(def verblogic (->> "resources/public/problem/verblogic.edn"
               (slurp)
               (read-string)))

(def English (->> "resources/public/problem/english.edn"
                    (slurp)
                    (read-string)))


(defn get-id [problem-id]
  (loop [index 0
         choice (cond
                  (= (apply str (take 3 problem-id)) "565") math
                  (= (apply str (take 4 problem-id)) "5699") verblogic
                  (= (apply str (take 4 problem-id)) "5690") English
                  )]
    (if (= (:problem-id (get choice index)) problem-id)
      (get choice index)
      (recur (inc index) choice))))

(defn take-soal [problem]
  (subvec problem 0 8))

(def score (atom 0))

(defn reset-score []
  (reset! score 0))

(defn check-jawaban [id jawaban]
  (let [soal (get-id id)]
    (when (= jawaban (get-in soal [:soal :jawaban]))
      (swap! score inc))))

(def subject (atom nil))

(defn change-subject [id]
  (let [choice (cond
                 (= (apply str (take 3 id)) "565") "/math"
                 (= (apply str (take 4 id)) "5699") "/verblogic"
                 (= (apply str (take 4 id)) "5690") "/english"
                 )]
    (reset! subject choice)))
