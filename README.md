# Java 8 streams benchmark

Benchmark compares simple operation of finding maximal value in a list of integers using:
* foreach
* stream
* parallel stream
* parallel stream with custom pool

## How to run benchmar?

Execute
```
mvn clean install exec:exec
```