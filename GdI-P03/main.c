#include "substring.h"
main() 
{
	char* str = "Rokokokomode";
	char* substr = "oko";
	char* ptr[10];
	int erg = substrings(str, substr, ptr, 10);
	printf("%d\n",erg);
}