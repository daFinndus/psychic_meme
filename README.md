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

Encryption:
```java -cp "bin;lib/*" Main encrypt <path/to/textfile> <path/to/keyfile>```

Decryption:
```java -cp "bin;lib/*" Main decrypt <path/to/textfile> <path/to/keyfile>```

Hash with specific method:
```java -cp "bin;lib/*" Main hash <path/to/textfile> <HASH_METHOD>```

Hash with all available methods:
```java -cp "bin;lib/*" Main hash <path/to/textfile>```

Hash-detection:
```java -cp "bin;lib/*" Main detect <path/to/textfile> <path/to/hashfile> <HASH_METHOD>```

Currently available hash methods are:

- MD5
- SHA3-256
- RIPEMD160