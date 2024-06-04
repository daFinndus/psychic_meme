The project directory should kind of look like this:

project/
├── src/
│   ├── Main.java
│   ├── Encrypt.java
│   ├── Decrypt.java
│   ├── Hash.java
│   └── Detect.java
└── lib/
    └── bouncycastle.jar

----------------------------------------------------------------------------------------------------------------------------

Compile with the following command:

javac -cp "lib/*" -d bin src/*.java

----------------------------------------------------------------------------------------------------------------------------

Execute the Main with the following command:

java -cp "bin:lib/*" Main <args>

