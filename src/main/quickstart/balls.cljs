(ns quickstart.balls
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate (+ 10 (rand-int 100)))
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) (rand 2)) 255)
   :angle (+ (:angle state) (rand 0.5))})

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 0)
  ; Set circle color.
  (q/fill ;(:color (if (> (+ 100 state) 255) 255 (+ 100 state)))
          (:color state) 255 255
          )
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 35 (q/cos angle))
        y (* 35 (q/sin angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y
                 ;(* (q/width) (/ 100.0 (+ 1 (rand-int 100))))
                 ;(* (q/height) (/ 100.0 (+ 1 (rand-int 100))))
                 (* (* 0.64 (q/height)) (/ (+ 30 (rand-int 61)) 100.0))
                 (* (* 0.64 (q/height)) (/ (+ 30 (rand-int 61)) 100.0))
                 ;(+ 30 (rand-int 71))
                 ;(+ 30 (rand-int 71))
                 ))))

; this function is called in home view
(defn ^:export run-sketch [host sc_width sc_height]
  (q/sketch
    :host host
    :size [(* sc_height 0.2)
           (* sc_height 0.2)]
    ;; :size [250 250]
    ; setup function called only once, during sketch initialization.
    :setup setup
    ; update-state is called on each iteration before draw-state.
    :update update-state
    :draw draw-state
    ; This sketch uses functional-mode middleware.
    ; Check quil wiki for more info about middlewares and particularly
    ; fun-mode.
    :middleware [m/fun-mode]
    ))

; uncomment this line to reset the sketch:
; (run-sketch)
