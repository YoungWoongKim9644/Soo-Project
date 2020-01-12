#include "ArrayStack.h"

void error( char* str ) {
	fprintf(stderr, "%s\n", str);
	exit(1);
};

void initStack( Stack* s )	{
	s->top = -1;
}

int isEmpty( Stack* s )		{
	return (s->top == -1);
}

int isFull( Stack* s )		{
	return (s->top == 99);
}

int size( Stack* s )		{
	return (s->top)+1;
}

void push ( Stack* s, Element e ) {
	if (isFull(s)){
		ERROR_STACKOVER;
		return;
	}
	else{
		s->top += 1;
		s->data[s->top] = e;
		return;
	}
}

Element pop ( Stack* s ) {	
	if (isEmpty(s)){
		ERROR_STACKEMPTY;
		return 'z';
	}
	else {
		return s->data[s->top--];
	}
}

Element peek ( Stack* s ){
	if (isEmpty(s)) {
		ERROR_STACKEMPTY;
		return 'z';
	}
	else {
		return s->data[s->top];
	}
}

int isMatch(Element e1, Element e2) {

	if (e1 == '(' && e2 == ')') {
		return 1;
	}
	else if (e1 == '{' && e2 == '}') {
		return 1;
	}
	else if (e1 == '[' && e2 == ']') {
		return 1;
	}
	else {
		return 0;
	}
}


void display ( Stack* s, char* msg ) {
	int i;
	printf("%s[%2d]= ", msg, size(s)) ;
	for (i=0 ; i<=s->top ; i++ )
		printElem(s->data[i]);
	printf("\n");
}