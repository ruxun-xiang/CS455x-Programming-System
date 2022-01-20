// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CSCI 455 PA5
// Fall 2020


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H


struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// lookup node in the "list" using the given "key" 
// param list: the list to lookup
// param key: the key to find the node
// return the location of the value
int * listLookup(ListType list, std::string key);


// insert a node including its key and value into the front of the list
// param list: the list to insert (pass by reference)
// param key: the key to insert into the node
// param value the value to insert into the node
void listInsert(ListType & list, std::string key, int value);


// remove a node with the target key
// param list: the list to remove a node (pass by reference)
// param target : the target key
// return true if remove succeed, false if failed
bool listRemove(ListType & list, std::string target);


// print all the nodes of this list
// param list: the list to be printed
void printList(ListType list);


// count for the length of the list
// param list: the list we want to know its length
// return the length of the list
int chainLength(ListType list);






// keep the following line at the end of the file
#endif
