#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	setbuf(stdout, NULL);
	unsigned long long int zahl,merker;
	int count = 0;
	char input;
	while(1)
	{
		printf("Bitte geben Sie eine Zahl ein.\n");
		gets(&input);
		if(strchr(&input, '.') == NULL && strchr(&input, ',') == NULL && strchr(&input, '-') == NULL)
		{
			zahl = atol(&input);
			merker = zahl;
			do
			{
				count++;
				if(zahl%2 == 0)
				{
					zahl /= 2;
				}
				else if(zahl%2 != 0 && zahl != 1)
					zahl = 3*zahl+1;
			}
			while(zahl != 1);
			printf("Der Anfangswert fuer n war: %llu\nDer Algorithmus wurde %d mal durchlaufen\n",merker,count);
			return EXIT_SUCCESS;
		}
		else
			printf("Bitte eine ganze positive Zahl eingeben!\n");
	}
}
