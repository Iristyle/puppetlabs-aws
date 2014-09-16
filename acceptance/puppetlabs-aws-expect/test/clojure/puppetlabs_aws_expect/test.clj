(ns clojure.puppetlabs-aws-expect.test
  (:use [amazonica.aws.ec2]
        [amazonica.aws.elasticloadbalancing])
  (:require [environ.core :refer [env]]
            [expectations :refer [expect from-each]]))

(defn in?
  "true if seq contains elm"
  [seq elm]
  (some #(= elm %) seq))

(def region {:endpoint "us-west-2"})

(def groups (:security-groups
  (describe-security-groups region)))

(def groups (:security-groups
  (describe-security-groups region)))

(def group-names (map :group-name
  (:security-groups
    (describe-security-groups region))))

(def loadbalancers (:load-balancer-descriptions
  (describe-load-balancers region)))

(def loadbalancer-names (map :load-balancer-name loadbalancers))

(expect 4 (count groups))

(expect (in? group-names "web-sg"))
(expect (in? group-names "lb-sg"))
(expect (in? group-names "db-sg"))

(expect 1 (count loadbalancers))

(expect (in? loadbalancer-names "lb-1"))