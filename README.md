# 10DAY Poker Exercise For SPRING BOOT


This is a exercises for leaning spring boot.


## STEP1 Crate Rest API for poker

* STEP1 is not kyorohiro original exercise.

```

curl http://localhost:8080/poker/hand -X POST -H "Content-Type: application/json" -d '{"cards":"S1 S2 S3 S4 S5"}'

{"cards":"S1 S2 S3 S4 S5","hand":"Straight Flush"}


```

```
curl http://localhost:8080/poker/hands -X POST -H "Content-Type: application/json" -d '{"hands": [{"cards":"S1 S2 S3 S4 S5"},{"cards":"S9 S10 S11 S12 S13"}]}'

{"result":[{"card":"S1 S2 S3 S4 S5","hand":"Straight Flush","best":true},{"card":"S9 S10 S11 S12 S13","hand":"Straight Flush","best":false}]}
```


## STEP2 Save request log on DB and show this log at Rest API and Webpage




## STEP3 ADD USER AND LOGIN




## STEP4 GAME PLAY AND CHAT




## STEP5 FOLLOW USER AND STAR 




## EX USE MEMCACHE FOR PERFORMANCE






