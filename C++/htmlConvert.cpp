//File name: student.cpp
//Author: Joseph Rosen
//Assignment number: 5, problem 2.
//Description: Program reads input from the input file and converts the file into a HTML friendly formatted file.

#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <cstdlib>
using namespace std;

int main() {
    // The input file to convert to an html friendly format
    string filename = "data.txt";
    //The converted text file to a html file
    string outputfile;

	string pause = "";
    char input;
    ifstream inputStream;
    ofstream outputStream;

   inputStream.open(filename.c_str());
   if (inputStream.fail()) {
       cout << "The file could not be opened." << endl;
       exit(1);
   }

  // Create the output file
  outputfile = filename + ".html";
  outputStream.open(outputfile.c_str());

  //Prints the <PRE> tag to the beginning of the converted file
  outputStream << "<PRE>" << endl;

  //Iterates through the file intil the end of file flag is true.
  while (!inputStream.eof()) {
		//Reads one char from the input file
        inputStream.get(input);   
        //Checks input for: "<, >" if so, replace said characters with &lt, &gt, else output the original character.
      if (input == '<') {
          outputStream << "&lt;";
      }
      else if (input =='>') {
          outputStream << "&gt;";
      }
      else outputStream << input;
  }
  // Conversion finished with an addition of the </PRE> tag
  outputStream << "</PRE>" << endl;
  //Closing input/output streams
  inputStream.close();
  outputStream.close();
  cout << "Text file conversion complete..." << outputfile << endl;
  cout << "Enter any string to end the program" << endl;
  getline(cin,pause);
 }
