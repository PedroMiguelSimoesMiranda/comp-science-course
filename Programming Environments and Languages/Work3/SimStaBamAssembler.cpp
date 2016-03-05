/*    Projecto 3 LAP         */
/*    Alunos:                */
/*    Eduarto Duarte nº 25216*/
/*    Pedro Miranda  nº 28289*/

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string>
#include <vector>
#include <map>
#include "Assembler.h"
#include "Machine.h"

using namespace std;	

// Returns true if the first element of the string is "."
// If the first element of the string is "." the string is a label
bool isLabel(const std::string & l){
	if(l[0] == '.')
		return true;
	return false;
}

// Returns a string with the represented delimiters wich can be:
// a breakline, a tab, or a space 
std::string getDelimiters(){
	return "\b\t ";
}

//breaks a string into tokens and adds them to a vector
void Tokenize(const string& str,
                      vector<string>& tokens,
                      const string& delimiters = getDelimiters())
{
    // Skip delimiters at beginning.
    string::size_type lastPos = str.find_first_not_of(delimiters, 0);
    // Find first "non-delimiter".
    string::size_type pos     = str.find_first_of(delimiters, lastPos);

    while (string::npos != pos || string::npos != lastPos)
    {
        // Found a token, add it to the vector.
        tokens.push_back(str.substr(lastPos, pos - lastPos));
        // Skip delimiters.  Note the "not_of"
        lastPos = str.find_first_not_of(delimiters, pos);
        // Find next "non-delimiter"
        pos = str.find_first_of(delimiters, lastPos);
    }
}


int main(){

	std::map<std::string, int> comm;

// Mnemonic table
	comm["CAL"] = X_CAL;
	comm["LOC"] = X_LOC;
	comm["BR"] = X_BR;
	comm["BRF"] = X_BRF;
	comm["RET"] = X_RET;
	comm["HALT"] = X_HALT;
	comm["NOP"] = X_NOP;
	comm["LD"] = X_LD;
	comm["LDA"] = X_LDA;
	comm["LDV"] = X_LDV;
	comm["STV"] = X_STV;
	comm["AND"] = X_AND;
	comm["OR"] = X_OR;
	comm["EQ"] = X_EQ;
	comm["GT"] = X_GT;
	comm["NOT"] = X_NOT;
	comm["ADD"] = X_ADD;
	comm["SUB"] = X_SUB;
	comm["MUL"] = X_MUL;
	comm["DIV"] = X_DIV;
	comm["WR"] = X_WR;
	comm["RD"] = X_RD;

	std::map<std::string, int> label;
	std::map<int, std::string> missingLabel;	

	int i = 0;
	int part = 0;
	std::string line;

// while the input isn't empty(null)
	while(getline(std::cin, line)) {

//if the line is a "empty" line the it jumps
//to the next iteration of the cicle
	if(line.size()<1)
		continue;
	
	part = 0;

	std::vector<std::string> com;
	Tokenize(line,com);
	
// Treats a label
	if(isLabel(com[part])){
// if the string is a lable ".label:" the string is rearranged to ".label"
// and the address in the com vector saved in the label vector
		com[part].resize(com[part].size()-1);
		label[com[part]] = i;
		
		std::map<int,std::string>::iterator it;
// Sabes in a iterated map in each address its' label
		for ( it=missingLabel.begin() ; it != missingLabel.end(); it++ ){
			if((*it).second == com[part])
				code[(*it).first] = i;
		}
// part++ so it starts by evaluating the operators
		part++;
	}

// Treats the operators
	code[i++] = comm[com[part]];
	if(com[part] == "CAL" || com[part] == "LOC" || com[part] == "BRF" || com[part] == "RET" || com[part] == "LD"){
// Treats operators with arguments
		if(isLabel(com[part+1])){
// Treats the call of the specified label or the jump to that label
			if(label[com[part+1]] != 0)// ret 1
				code[i++] = label[com[part+1]];
			else
				missingLabel[i++] = com[part+1];
		}
// 
		else
			code[i++] = atoi(com[part+1].c_str());

	}


	Run();

	return 1;

}
