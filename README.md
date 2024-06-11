The project directory should kind of look like this:

```
project/
├── src/
│   ├── Main.java
│   ├── Encrypt.java
│   ├── Decrypt.java
│   ├── Hash.java
│   └── Detect.java
└── lib/
    └── bouncycastle.jar
```

----------------------------------------------------------------------------------------------------------------------------

Compile with the following command:

```javac -cp "lib/*" -d bin src/*.java```

----------------------------------------------------------------------------------------------------------------------------

Execute the Main with the following command:

```java -cp "bin:lib/*" Main <args>``` ( for Linux )

```java -cp "bin;lib/*" Main <args>``` ( for Windows )

----------------------------------------------------------------------------------------------------------------------------

You have the following options:

Encryption:<br>
Linux ```java -cp "bin:lib/*" Main encrypt <path/to/textfile> <path/to/keyfile>```<br>
Windows ```java -cp "bin;lib/*" Main encrypt <path/to/textfile> <path/to/keyfile>```<br>

Decryption:<br>
Linux ```java -cp "bin:lib/*" Main decrypt <path/to/textfile> <path/to/keyfile>```<br>
Windows ```java -cp "bin;lib/*" Main decrypt <path/to/textfile> <path/to/keyfile>```<br>

Hash with specific method:<br>
Linux ```java -cp "bin:lib/*" Main hash <path/to/textfile> <HASH_METHOD>```<br>
Windows ```java -cp "bin;lib/*" Main hash <path/to/textfile> <HASH_METHOD>```<br>

Hash with all available methods:<br>
Linux ```java -cp "bin:lib/*" Main hash <path/to/textfile>```<br>
Windows ```java -cp "bin;lib/*" Main hash <path/to/textfile>```<br>

Hash-detection:<br>
Linux ```java -cp "bin:lib/*" Main detect <path/to/textfile> <path/to/hashfile> <HASH_METHOD>```<br>
Windows ```java -cp "bin;lib/*" Main detect <path/to/textfile> <path/to/hashfile> <HASH_METHOD>```<br>

Currently available hash methods are:

- MD5
- SHA3-256
- RIPEMD160