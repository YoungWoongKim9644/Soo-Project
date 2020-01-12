#include "ArrayStack.h"

int bracketChecker(char* filename) {
	//(
   /*[[[*/
	int nLine = 1, nChar = 0;
	int bSingle, bDouble, bLineCmt, bMLineCmt;
	char	ch, ch2;
	int excp = 0;
	int stch = 0;
	int tmp = 0;
	Stack	stack;

	FILE* fp = fopen(filename, "r");
	if (fp == NULL)
		error("Error: The file does not exist.\n");
	else {
		initStack(&stack);
		bSingle = bDouble = bLineCmt = bMLineCmt = 0;

		while ((ch = getc(fp)) != EOF) { //�� ���ھ� �о����
			if (!(ch == '/' || ch == '*'))
				excp=0;
			if (ch == '\n')
				nLine++;
			printf("1nLine :%d, ch : %c \n", nLine, ch);

			//-----exception-------// 
			if (excp == 1 && (ch == '/'))//	1.(//) ����ó��
			{
				excp = 0;
				while (!((ch = getc(fp) == '\n') || (ch == EOF))) {
					printf("����\n");
					printf("nLine :%d, ch : %c \n", nLine, ch);
					tmp++;
				}
				if (tmp != 0) {
					nLine++;
					tmp = 0;
				}
				printf("2nLine :%d, ch : %c \n", nLine, ch);
			}
			if (excp == 1 && (ch == '*')) {//2. /**/ ����ó��
				do
				{
					while ((ch = getc(fp)) != '*') {
						if (ch == '\n')
							nLine++;
						printf("3nLine :%d, ch : %c \n", nLine, ch);
					}
						printf("4nLine :%d, ch : %c \n", nLine, ch);
					if ((ch = getc(fp)) == '/') {
						stch = 1; excp = 0;
						printf("5nLine :%d, ch : %c \n", nLine, ch);
					}
					else if (ch == '*')
					{
						if ((ch = getc(fp)) == '/') {
							stch = 1; excp = 0;
							printf("6nLine :%d, ch : %c \n", nLine, ch);
						}
					}
				} while (stch == 0);
				stch = 0;
			}

			if (ch == '\"')
			{
				while (ch = getc(fp) != '\"') {
					if (ch == '\n')
						nLine++;
				}
			}

			if (ch == '\'')
			{
				while (ch = getc(fp) != '\'') {
					if (ch == '\n')
						nLine++;
				}
			}
				

			if (excp == 0 && ch == '/')		 
				excp++;
			//-----exception-------//


			if (ch == '(' || ch == '{' || ch == '[') {	//���� ��ȣ�� ���� ���¶��
				push(&stack, ch);	//���� ++;
				printf("top : %d\n", stack.top);
			}
			else if (ch == ')' || ch == '}' || ch == ']') {	//������ ��ȣ�� ���� ���¶��
				if (isMatch(peek(&stack), ch))	//������ top�� �ִ� element�� ���� ��ȣ���
				{
					ch2 = pop(&stack);	//top�� element�� pop.
					//printf("�����������");
				}
				else if (peek(&stack) == 'z')
				{
					break;
				}
				else
				{
					printf("error occured!\n");
					break;
				}
			}
		}

		fclose(fp);
		display(&stack, "���ÿ���");
		nChar = stack.data[stack.top];
		printf("[%s] File check result:\n", filename);
		if (isEmpty(&stack) == 0)// stack�� �����ִٸ�  
			printf("  Error found (#line=%d, #char=%c)\n\n", nLine, nChar);
		else {
			nChar = 0;
			printf("  Ok (#line=%d, #char=%c)\n\n", nLine, nChar);
		}
		return isEmpty(&stack);
	}
}