# Most Active Cookie

A command-line tool that finds the most active cookie(s) for a specific date based on a provided log file.

## Build

Make sure you have Java 11+ and Maven installed, then run:

```bash
mvn clean package
```

## Run

Use the following command to run the program:

```bash
java -jar target/most-active-cookie-1.0.0.jar -f cookie_log/sample.csv -d 2018-12-09
```

## Features

- Supports log files with multiple cookies per day
- Returns all cookies tied for most active on a given date
- Graceful handling of missing or malformed entries
- CLI arguments:
  - `-f` : Path to the CSV log file
  - `-d` : Date to query, in format `yyyy-MM-dd`

## Test

Run all unit tests using:

```bash
mvn test
```
