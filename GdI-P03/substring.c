#include "substring.h"
/*int substrings(char* str, char* substr, char** ptr, int n)
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
}*/
int substrings(char* str, char* substr, char** ptr, int n)
{
	int temp,k = 0;
	for(int i= 0; i < strlen(str); i++)
	{
		for(int j = 0; j < strlen(substr); j++)
		{
			if(str[i+j] == substr[j])
				temp++;
			else
				temp = 0;
		}
		if(temp == strlen(substr))
		{
			 k++;
		}
	}
return k;
}

