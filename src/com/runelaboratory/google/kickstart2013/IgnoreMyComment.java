package com.runelaboratory.google.kickstart2013;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class IgnoreMyComment {

    // reference link https://codingcompetitions.withgoogle.com/kickstart/round/0000000000434ad7/0000000000434dfc

//    Good programmers write fabulous comments. Igor is a programmer and he likes the old C-style comments in /* ... */ blocks.
//    For him, it would be ideal if he could use this style as a uniform comment format for all programming languages or
//    even documents, for example Python, Haskell or HTML/XML documents.
//
//    Making this happen doesn't seem too difficult to Igor. What he will need is a comment pre-processor that removes all
//    the comment blocks in /*, followed by comment text, and by another */. Then the processed text can be handed over
//    to the compiler/document renderer to which it belongs—whatever it is.
//
//    Igor's pre-processor isn't quite that simple, though. Here are some cool things it does:
//
//    The comments the pre-processor reads can be nested the same way brackets are nested in most programming languages.
//    It's possible to have comments inside comments. For example, the following code block has an outer level of comments
//    that should be removed by the comment pre-processor. The block contains two inner comments.
//    printf("Hello /* a comment /* a comment inside comment */
//           inside /* another comment inside comment */
//                   string */ world");
//                   After the pre-process step, it becomes:
//                   printf("Hello  world");
//    Igor recognizes comments can appear anywhere in the text, including inside a string "/*...*/",
//    a constant number 12/*...*/34 or even in a character escape \/*...*/n

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        output.append("Case #1:\n");
        int curCommentCount = 0;

        // For local use, IntellJ console cannot exit scanner
        String fileContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\rune\\IdeaProjects\\TestInput\\IgnoreMyComment\\ts2_input.txt")));
        input.append(fileContent);

        // For Google kickstart submit attempt
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            input.append(line + "\n");
//        }

        scanner.close();

        char[] content = input.toString().toCharArray();

        for (int i = 0; i < content.length; i++) {
            if (content[i] == '/' && content[i + 1] == '*') {
                curCommentCount++;
                i++;
            } else if (curCommentCount != 0 && content[i] == '*' && content[i + 1] == '/') {
                curCommentCount--;
                i++;
            } else if (curCommentCount == 0) {
                output.append(content[i]);
            }
        }
        System.out.println(output);
    }
}
