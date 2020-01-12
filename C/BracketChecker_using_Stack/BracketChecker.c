#include "ArrayStack.h"

int bracketChecker( char* filename ) {
	 //(
	/*[[[*/
	int nLine = 1, nChar = 0;
	int bSingle, bDouble, bLineCmt, bMLineCmt;
	char	ch, ch2;
	int excp = 0;
	Stack	stack;
	
	FILE *fp = fopen( filename, "r" );
	if( fp == NULL )
		error("Error: The file does not exist.\n");
	else {
		initStack(&stack);
		bSingle = bDouble = bLineCmt = bMLineCmt = 0;
		
		while ((ch = getc(fp)) != EOF) { //�� ���ھ� �о����
			
			
			//-----exception-------// 
			if (excp == 0 && ch == '/') 
				excp++;
			if (excp == 1 && (ch == '/'))
			{
				while ((ch = getc(fp)) != '\n') {
					if(ch == EOF)
						break;
				}
				break;
			}
			if (excp == 1 && (ch == '*')) {
				while ((ch = getc(fp)) != '*') {
					;
				}
				if ((ch = getc(fp)) == '/') {
					break;
				}
			}
			//-----exception-------//
			
			if (ch == LEFT_BRACkETS) {	//���� ��ȣ�� ���� ���¶��
				stack.data[stack.top++] = ch;	//���� ++;
			}
			else if (ch == RIGHT_BRACkETS) {	//������ ��ȣ�� ���� ���¶��
				if (isMatch(peek(&stack), ch))	//������ top�� �ִ� element�� ���� ��ȣ���
					ch2 = pop(&stack);			//top�� element�� pop.
			}
		}
		
		fclose(fp);
		printf("[%s] File check result:\n", filename);
		if (isEmpty(&stack) == 0)// stack�� �����ִٸ�  
			printf("  Error found (#line=%d, #char=%d)\n\n", nLine, nChar);
		else
			printf("  Ok (#line=%d, #char=%d)\n\n", nLine, nChar);
		return isEmpty(&stack);
	}
}

//
