// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CSCI 455 PA5
// Fall 2020

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

// initialize the hash table with a default HASH_SIZE capacity
Table::Table() {
   hashTable = new ListType[HASH_SIZE]();
   hashSize = HASH_SIZE;
   entries = 0;
}

// initialize the hash table with a given hSize capacity
Table::Table(unsigned int hSize) {
   hashTable = new ListType[hSize]();
   hashSize = hSize;
   entries = 0;
}


int * Table::lookup(const string &key) {
   int location = hashCode(key); // get the hash code of the given key, which is the location(index) of the node in the table if exists
   int * result = listLookup(hashTable[location], key); // lookup the node using the given key, return the value if the node exists, or return null

   return result;
}

bool Table::remove(const string &key) {
   int location = hashCode(key); 
   bool result = listRemove(hashTable[location], key); // remove the node with the key, return true if remove succeed, false if failed
   if (result == true){
      entries--; // decrease one entry if remove succeed
   }
   return result;  
}

bool Table::insert(const string &key, int value) {
   int * exist = lookup(key); // see if the node exist
   if (exist == NULL){ // if no, then we can safely insert it
      int location = hashCode(key);
      listInsert(hashTable[location], key, value); // insert the key and value into its certain location in the hash table
      entries++; // increase one entry when insert succeed
      return true; // to represent insert succeed
   }
   return false; // to represent insert failed because the node already existed
}

int Table::numEntries() const {
   return entries;      // return the number of entry
}


void Table::printAll() const {
   for (int i = 0; i < hashSize; i++){
      printList(hashTable[i]); // print all the nodes in the list in all buckets
   }

}

void Table::hashStats(ostream &out) const {
   int nonEmptyBucket = 0; // count for the non empty bucket
   int longestChain = 0; // record the longest chain
   int length = 0; // count for the length of each chain
   for (int i = 0; i < hashSize; i++){ // iterate the table to count for non empty bucket and chain length
      if (hashTable[i] != NULL){ 
         nonEmptyBucket++;
      } 
      length = chainLength(hashTable[i]); // return the length of the chain in bucket i 
      if (length > longestChain){ // compare the length with currently longest  
         longestChain = length; 
      }
      length = 0;	
   }
   // output hash status
   out << "number of buckets: " << hashSize << endl;
   out << "number of entries: " << entries << endl;
   out << "number of non-empty buckets: " << nonEmptyBucket << endl;
   out << "longest chain: " << longestChain << endl;
}


// add definitions for your private methods here
