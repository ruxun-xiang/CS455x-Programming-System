// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CSCI 455 PA5
// Fall 2020


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below



int * listLookup (ListType list, string key){
   ListType p = list; // p for iterating the list
   while(p != NULL){
      if (p->key == key){ // found the node
         return &(p->value); // return the location of the value
      }
      p = p->next;
   }
   return NULL; // return NULL if didn't find
}



void listInsert (ListType & list, string key, int value) {
   list = new Node(key, value, list); // create a new node, and link it to the front of the list
   // and update the list pointer to point to the new front
}



bool listRemove (ListType & list, string target) {
   if (list == NULL)// if there's no node in the list, return false to represent failed to remove
   {
      return false;
   }
   // if the first node is what we want
   if (list->key == target){
      ListType nuked = list; // assign a new pointer pointing to this node that we want to remove
      list = list->next; // change the list pointer to point to its next node
      delete nuked; // release the memory that stores this to-be-removed node
      return true; // to represent remove succeed
   }

   // if the first node is not what we want
   ListType p = list; // p for iterating the list
   // find the previous node of the to-be-removed node, so after the while loop, p would either pointing 
   // to the last node (next is NULL) or pointing to this previous node
   while (p->next != NULL && p->next->key != target){
      p = p->next;
   }
   // if p is pointing to the last node, the we failed to find the to-be-removed node, return false
   if (p->next == NULL){
      return false;
   } else { // else p must pointing to the previous node, and then we remove it
      ListType nuked = p->next;
      p->next = p->next->next;
      delete nuked;
   }	
   return true;
}


void printList (ListType list){
   ListType p = list; // p for iterating the list
   while (p != NULL){
      cout << p->key << " " << p->value << endl; // print the key and the value of a node
      p = p->next;
   }
}


int chainLength (ListType list){
   int length = 0;
   ListType p = list;
   while (p != NULL){// iterate the nodes, and count the number of nodes in the list as length
      length++;
      p = p->next;
   }
   return length;
}
