### spider-amazon

---

a simple spider for amazon search response

#### v0.0.1
we now can get:
* asin (alias amazon priduct id)
* product name 
* price
* grade
* number of comment
* detail page of product

#### v0.0.2
provide jar file


#### result

- store the result in db such as mysql

- output the result in excel

#### usage guide
1. package the jar
2. run the jar 
```bash
java -jar xxx.jar -h
```
3. then we will see
```bash
$ java -jar sprider-amazon-0.0.1-SNAPSHOT.jar -h
usage: help [-h] [-k <arg>] [-o <arg>] [-p <arg>] [-r <arg>] [-s <arg>] [-t <arg>] [-u <arg>]
 -h,--help                Print help
 -k,--key <arg>           search key or keys
 -o,--output path <arg>   excel output path
 -p,--password <arg>      mysql password, valid when t is db
 -r,--remote <arg>        base remote url, default is https://www.amazon.com
 -s,--separator <arg>     separator of keys, ignore this when only one key
 -t,--type <arg>          excel or db, default excel
 -u,--user <arg>          mysql user, valid when t is db
```
4. if use db, you should run the **/resources/db.sql**