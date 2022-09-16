(ns triangulo.core
  (:require [clojure.math :as math]))

(defn calc-perimetro
  "Calcula o perimetro do triangulo, dado A B e C"
  [a b c]
  (+ a b c))

(defn calc-radianos
  "TODO: Calcular radianos dado lados a b e c de um triangulo"
  [a b c]
  )

(defn calc-angulo
  "TODO: Calcula o ângulo ∠A, dado A B C."
  [a b c]
  (->
    (math/pow b 2)
    (+ (math/pow c 2))
    (- (math/pow a 2))
    (/ (* 2 b c))
    math/acos
    math/to-degrees))

(defn calc-area
  "TODO: Calcula a área de um triângulo usando a formula de Heron."
  [a b c]
  (double (let [s (-> (+ a b c) (/ 2))]
            (->
              (reduce * [(- s a) (- s b) (- s c)])
              (* s)
              math/sqrt))))

(defn calc-altura
  "TODO: Calcula altura de A, dado a AREA."
  [a area]
  (->
    (* area 2)
    (/ a)))

(defn equilateral?
  "TODO: Verifica se o triangulo é equilateral"
  [a b c]
  (and (= a b) (= a c)))

(defn isosceles?
  "TODO: Verifica se pelo menos dois lados sao iguais."
  [a b c]
  (or (= a b) (= a c) (= b c)))

(defn escaleno?
  "TODO: Verifica se os lados dos triangulos sao diferentes entre si."
  [a b c]
  (not (or (equilateral? a b c) (isosceles? a b c))))

(defn retangulo?
  "TODO: Verifica se é um triangulo retangulo, cujos angulos são iguais a 90o.
  O resultado não é exato, dado que cada angulo é arredondado utilizando clojure.math/round."
  [a b c]
  (let [angulo-a (math/round (calc-angulo a b c))
        angulo-b (math/round (calc-angulo b a c))
        angulo-c (math/round (calc-angulo c a b))]
    (if (or (= angulo-a 90) (= angulo-b 90) (= angulo-c 90))
      true
      false)))

(defn obtuso?
  "TODO: Verifica se o triangulo é obtuso, tendo algum angulo >90o."
  [a b c]
  (let [angulo-a (math/round (calc-angulo a b c))
        angulo-b (math/round (calc-angulo b a c))
        angulo-c (math/round (calc-angulo c a b))]
    (if (or (> angulo-a 90) (> angulo-b 90) (> angulo-c 90))
      true
      false)))

(defn agudo?
  "TODO: Verifica se o triangulo é agudo, tendo algum angulo <90o."
  [a b c]
  (let [angulo-a (math/round (calc-angulo a b c))
        angulo-b (math/round (calc-angulo b a c))
        angulo-c (math/round (calc-angulo c a b))]
    (if (and (< angulo-a 90) (< angulo-b 90) (< angulo-c 90))
      true
      false)))

(defn gerar-dados-completos
  [a b c]
  (let [area (calc-area a b c)]
        {:lados [a b c]
         :retagulo (retangulo? a b c)
         :obtuso (obtuso? a b c)
         :agudo (agudo? a b c)
         :escaleno (escaleno? a b c)
         :isosceles (isosceles? a b c)
         :equilateral (equilateral? a b c)
         :area area
         :altura [(calc-altura a area)
                  (calc-altura b area)
                  (calc-altura c area)]
         :angulos [(calc-angulo a b c)
                   (calc-angulo b c a)
                   (calc-angulo c a b)]}))

(comment
  (require 'clojure.pprint)
  (escaleno? 60 51.96152 30)
  (retangulo? 60 51.96152 30)
  (clojure.pprint/pprint (gerar-dados-completos 30 20 44))
  (clojure.pprint/pprint (gerar-dados-completos 60 51.96152 30))
  (clojure.pprint/pprint (gerar-dados-completos 15.14741 28.08887 30))
  )
