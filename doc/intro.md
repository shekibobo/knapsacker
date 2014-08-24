# Introduction to Knapsacker

You are a drug trafficker. Every day you meet with a different nice older lady (the mule) and find out how much weight she can carry in her handbag. You then meet with your supplier who has packed various drugs into a myriad of collectible porcelain dolls. Once packed with drugs, each of the precious dolls has a unique combination of weight and street value. Sometimes your supplier has more dolls than the nice lady can carry, though space in her handbag is never an issue. Your job is to choose which dolls the kind soul will carry, maximizing street value, while not going over her weight restriction.

## Inputs

- A set of dolls with a `name`, and unique combination of `weight` and `value`.
- An overall weight restriction.

## Output

The optimal set of poorcelin dolls which:

- are within the total weight restriction
- maximize the total street value of the drugs delivered

# Requirements

- JVM 1.6+
- Leiningen

This application was built using Leiningen 2.4.3 on Java 1.8.0_05 Java HotSpot(TM) 64-Bit Server VM.
