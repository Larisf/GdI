#include "substring.h"
main()
{
	char* str = "rokokokomode";
	char* substr = "oko";
	char* ptr[10];
	printf("%d\n",substrings(str, substr, ptr, 10));
}