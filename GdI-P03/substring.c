#include "substring.h"
int substrings(char* str, char* substr, char** ptr, int n)
{
	int k =0;
	*ptr = strstr(str, substr);
	for(int i= 0; i < n; i++)
	{
		if(*ptr)
		{
		 *ptr = strstr(*ptr+1,substr);
		 k++;
		}
	}
return k;
}
