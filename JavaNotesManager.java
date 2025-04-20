package mynotes;

import java.io.*;
import java.util.Scanner;

public class JavaNotesManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create JavaFile1.txt
        String file1Name = "JavaFile1.txt";
        String file1Content = """
                Java is an object-oriented programming language.
                It supports encapsulation, inheritance, and polymorphism.
                File handling in Java allows for efficient reading and searching of text.
                Keep learning and mastering Java!
                """;
        writeToFile(file1Name, file1Content);

        // Step 2: Display JavaFile1.txt
        System.out.println("Contents of JavaFile1.txt:");
        readFile(file1Name);

        // Step 3: Create JavaFile2.txt with first line
        String file2Name = "JavaFile2.txt";
        String file2FirstLine = "This is the first line in this JavaFile2.txt file.\n";
        writeToFile(file2Name, file2FirstLine);

        // Step 4: Copy contents from JavaFile1.txt to JavaFile2.txt using FileInputStream/FileOutputStream
        copyFileContents(file1Name, file2Name);

        // Step 5: Analyze JavaFile1.txt
        analyzeFile(file1Name, "polymorphism");

        scanner.close();
    }

    public static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to " + filename);
        }
    }

    public static void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename);
        }
    }

    public static void copyFileContents(String sourceFile, String destFile) {
        try (
            FileInputStream inStream = new FileInputStream(sourceFile);
            FileOutputStream outStream = new FileOutputStream(destFile, true)
        ) {
            outStream.write('\n');
            int content;
            while ((content = inStream.read()) != -1) {
                outStream.write(content);
            }
            System.out.println("\nContents copied from " + sourceFile + " to " + destFile);
        } catch (IOException e) {
            System.out.println("Error copying content.");
        }
    }

    public static void analyzeFile(String filename, String wordToFind) {
        int charCount = 0, lineCount = 0, wordCount = 0;
        int wordOccurrences = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                lineCount++;
                charCount += line.length();
                String[] words = line.split("\\s+");
                wordCount += words.length;

                if (line.toLowerCase().contains(wordToFind.toLowerCase())) {
                    System.out.println("Word '" + wordToFind + "' found at line: " + lineNumber);
                    for (String word : words) {
                        if (word.equalsIgnoreCase(wordToFind)) {
                            wordOccurrences++;
                        }
                    }
                }
            }

            System.out.println("\n--- File Analysis of " + filename + " ---");
            System.out.println("Total Characters: " + charCount);
            System.out.println("Total Lines: " + lineCount);
            System.out.println("Total Words: " + wordCount);
            System.out.println("Total Occurrences of '" + wordToFind + "': " + wordOccurrences);
        } catch (IOException e) {
            System.out.println("Error analyzing file.");
        }
    }
}
