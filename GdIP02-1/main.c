#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	setbuf(stdout, NULL);
	unsigned long long int zahl,merker,rechnung,i;
	int count;
	char input;
	while(1)
	{
		printf("Bitte geben Sie eine Zahl ein.\n");
		gets(&input);
		if(strchr(&input, '.') == NULL && strchr(&input, ',') == NULL && strchr(&input, '-') == NULL)
		{
			zahl = atol(&input);
			printf("Anfangswert || Durchlaeufe\n");
			for(i = 1; i <= zahl; i++)
			{
				merker = i;
				rechnung = i;
				count = 0;
				do
				{
					count++;
					if(rechnung%2 == 0)
					{
						rechnung /= 2;
					}
					else if(rechnung%2 != 0 && rechnung != 1)
						rechnung = 3*rechnung+1;
				}
				while(rechnung != 1);
			printf("%llu\t    || %d\n",merker,count);
			}
			return EXIT_SUCCESS;
		}
		else
			printf("Bitte eine ganze positive Zahl eingeben!\n");
	}
}
