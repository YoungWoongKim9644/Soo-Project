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
		
		while ((ch = getc(fp)) != EOF) { //한 글자씩 읽어오기
			
			
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
			
			if (ch == LEFT_BRACkETS) {	//왼쪽 괄호를 읽은 상태라면
				stack.data[stack.top++] = ch;	//스택 ++;
			}
			else if (ch == RIGHT_BRACkETS) {	//오른쪽 괄호를 읽은 상태라면
				if (isMatch(peek(&stack), ch))	//스택의 top에 있는 element가 왼쪽 괄호라면
					ch2 = pop(&stack);			//top의 element를 pop.
			}
		}
		
		fclose(fp);
		printf("[%s] File check result:\n", filename);
		if (isEmpty(&stack) == 0)// stack이 남아있다면  
			printf("  Error found (#line=%d, #char=%d)\n\n", nLine, nChar);
		else
			printf("  Ok (#line=%d, #char=%d)\n\n", nLine, nChar);
		return isEmpty(&stack);
	}
}

//
