# spring test poka

```

> curl http://localhost:8080/poka/score -X POST -H "Content-Type: application/json" -d '{"cards":"S1 S2 S3 S4 S5"}'

{"scoreName":"Straight Flush"}

```

```
curl http://localhost:8080/poka/scores -X POST -H "Content-Type: application/json" -d '{"hands": [{"cards":"S1 S2 S3 S4 S5"},{"cards":"S9 S10 S11 S12 S13"}]}'

{"result":[{"card":"S1 S2 S3 S4 S5","hand":"Straight Flush","best":true},{"card":"S9 S10 S11 S12 S13","hand":"Straight Flush","best":false}]}
```

