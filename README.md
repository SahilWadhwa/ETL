# ETL

Note: Code uses Streams, so run with Java 8. 

**Design:**

1. Document.java - used as primary data trasnfer object between different layers of Extract, Transformer and Loader instances. Holds all the information to process upon.
2. Extract.java - abstracts out the logic to extract data from any source to Stream of Document. One implementor for now - FileExtractor.java
3. Transformer.java - abstracts out the logic for data transformation in a document. Two exists: UpperCaseTransformer.java and WordCountTransformer.java
4. Loader.java - abstracts logic to load Document data to any source. Two exists: FileLoader.java (loads to a diff file) and ConsoleLoader.java (prints on console)
5. ETLProcessor.java - Uses instances of Extract, Transform and Loader to complete ETL chain. Also contains a static main helper method.

**How to Run, two ways:**

1. Use static main helper method in ETLProcessor.java
2. Try running different scenarios in ETLProcessorTest.java
