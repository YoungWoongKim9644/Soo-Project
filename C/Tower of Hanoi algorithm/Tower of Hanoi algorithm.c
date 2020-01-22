#include<stdio.h>

void move(int num, char from, char tmp, char to);


int main()
{
	char pillar[3] = { 'a','b','c' };
	int num;
	scanf_s("%d", &num);


	move(num,'a','b','c');
}

void move(int num ,char from, char tmp, char to)
{	
	if (num == 0)
	{
		return;
	}
	move(num - 1, from, to, tmp);
	printf("%d번 원판이 %c에서 %c로 이동했습니다.\n", from, to);
	move(num - 1, tmp, from, to);
}