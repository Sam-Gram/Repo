/***************
* Sam Graham
* This is from an interview I had recently.
* The goal is to switch around an integer using only math operations
* no strings allowed!!
****************/

#include <iostream>
#include <cstdlib>

int main(int argc, char** argv) {
  int pVal = atoi(argv[1]);
  while( pVal > 0 ) {
    cout << pVal % 10;
    pVal = pVal / 10;
  }
  cout << endl;
  return 0;
}
