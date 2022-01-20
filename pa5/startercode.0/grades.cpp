// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CSCI 455 PA5
// Fall 2020

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

// insert a student's name and score into the table
void gradesInsert (Table * & grades){
   string name = ""; // used to receive student's name from user input
   int score = 0; // used to receive students's score from user input 
   bool insertResult = false; //used to display operation result 

   cin >> name; // get the name to be inserted
   cin >> score; // get the score to be inserted
   insertResult = grades->insert(name, score); // get the result of the insert opreation

   if (insertResult == false){ 
      cout << "Insert failed. Student " << name << " already existed." << endl; // report error information
   }
}

// change the given student's score 
void gradesChange (Table * & grades){
   string name = "";
   int score = 0;

   cin >> name;
   cin >> score;
   int *current = grades->lookup(name); // find the score of the node, return the address of it, and assign it to a pointer 
   if (current != NULL){ // the node (score) exists
      *current = score; // change the value of score the pointer points to  
   }
   else { // the node doen't exist
      cout << "Change failed. Student " << name << " is not in the table." << endl; // report error information
   }
}

// lookup a given student's score
void gradesLookup (Table * & grades){
   string name = "";

   cin >> name;
   int * current = grades->lookup(name);  // find the score of the node, return the address of it, and assign it to a pointer 
   if(current != NULL){ // the node(score) exists
      cout << "Student "<< name << "'s score is " << *current << "." << endl;
   }
   else { 
      cout << "Student " << name << " is not in the table." << endl;
   }
}

void gradesRemove (Table * & grades){
   string name = "";
   bool removeResult = false;

   cin >> name;
   removeResult = grades->remove(name); 
   if(removeResult == false){ // remove failed
      cout << "Removed failed. Student " << name << " is not in the table." << endl; // report error information
   }   
}

// print all the students' information
void gradesPrint (Table * & grades){
   grades->printAll(); // print all the nodes in a list in all buckets if exist   
}

// print the size of the table
void gradesSize (Table * & grades){
   int size = grades->numEntries(); // return number of entries of the table
   cout << "The table size is " << size << ". " << endl;
}

// print the status of the table
void gradesStats (Table * & grades){
   grades->hashStats(cout); // report the table status
}

// helper method used to output help information for users
void helpInfo(){ 
   cout << "insert name score    " << "Insert this name and score in the grade table." << endl;
   cout << "change name newscore " << "Change the score for name." << endl;
   cout << "lookup name          " << "Lookup the name, and print out his or her score." << endl;
   cout << "remove name          " << "Remove this student." << endl;
   cout << "print                " << "Print out all names and scores in the table." << endl;
   cout << "size                 " << "Print out the number of entries in the table." << endl;
   cout << "stats                " << "Print out statistics about the hash table at this point." << endl; 
   cout << "quit                 " << "Exit the program." << endl;
}

// execute commands from users in a loop until they quit
void commandExecution (Table * & grades){
   string command = ""; // used to receive "command" from user input

   bool goOn = true; // a flag used to decide whether continue the while loop
   
   while (goOn){
      cout << "cmd > ";
      cin >> command;
      if (command == "insert"){
         gradesInsert (grades);
      }
      else if (command == "change"){
         gradesChange (grades);
      }
      else if (command == "lookup"){
         gradesLookup (grades);
      }
      else if (command == "remove"){
         gradesRemove (grades);
      }
      else if (command == "print"){
         gradesPrint (grades);
      }
      else if (command == "size"){
         gradesSize (grades);
      }
      else if (command == "stats"){
         gradesStats (grades);
      }
      else if (command == "help"){
         helpInfo(); // output help information
      }
      else if (command == "quit"){
         goOn = false; // set the flag to false, and will exit the loop
      }
      else {
         cout << "ERROR: command input error" << endl;
      }
      
   }
}


int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }


   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   
   commandExecution (grades);

   return 0;
}


