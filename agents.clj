(def numAgents 5)

;(def gameScore [])

;(def agents (repeat numAgents []))

(defn agent-make-choice
  []
  (if (> (rand) 0.5)
    "a"
    "b"))

(defn round
  []
  (repeatedly numAgents agent-make-choice))

(defn find-winner 
  [rnd]
  (let [x (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} rnd)]
    ;(println x)
    (if (= (count x) 1)
      [x 0]
      (if (< (second (first x)) (second (second x)))
        (first x)
        (second x)))))

(defn game-on
  [the-game rounds]
  (if (= rounds 0)
    the-game
    (game-on (cons 
               (find-winner (round)) the-game) 
             (- rounds 1))))

(defn score-game
  [game]
  (println game)
  (reduce #'+ (map second game)))

;(println agents)
;(println (find-winner (round)))
(println (score-game (game-on [] 150)))
