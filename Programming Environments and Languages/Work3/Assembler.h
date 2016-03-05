/*    Projecto 3 LAP         */
/*    Alunos:                */
/*    Eduarto Duarte nº 25216*/
/*    Pedro Miranda  nº 28289*/

#include <iostream>
#include <string>
#include <vector>
#include <map>

/* Interface */
class Assembler{ 

//Data members of class assembler.cpp
private:
using namespace std;

// Functional members of class assembler.cpp
public:	

Assembler()const;
virtual ~Assembler();
virtual bool isLabel(const std::string & l);
virtual std::string removeComments(std::string & l);
virtual std::string getDelimitaters();
virtual std::vector<std::string> splitLine ( const std::string & l) ;
};

#endif