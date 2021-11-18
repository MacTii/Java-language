## About the program
Program takes a list of files on the command line and displays a line count of each. The program create one thread for each file and use these threads to count the number of lines for each file simultaneously. Program reads files sequentially but not simultaneously. Comparing the performance of a multi-threaded and a single-threaded program using System.currentTimeMillis () to determine the execution time. It compares for two, three and five files.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
