# Poker API

This is a exercises for leaning spring boot.

## STEP1 Rest API for poker


```

curl http://localhost:8080/poker/h -X POST -H "Content-Type: application/json" -d '{"cards":"S1 S2 S3 S4 S5"}'

{"cards":"S1 S2 S3 S4 S5","hand":"Straight Flush"}


```

```
curl http://localhost:8080/poker/hands -X POST -H "Content-Type: application/json" -d '{"hands": [{"cards":"S1 S2 S3 S4 S5"},{"cards":"S9 S10 S11 S12 S13"}]}'

{"result":[{"card":"S1 S2 S3 S4 S5","hand":"Straight Flush","best":true},{"card":"S9 S10 S11 S12 S13","hand":"Straight Flush","best":false}]}
```


## STEP2 ADD HISTORY ON DB
