#pragma once
#include <stdio.h>
#include <stdlib.h>
#define MAX_STACK_SIZE	100
#define LEFT_BRACkETS ('{'||'['||'(')
#define RIGHT_BRACkETS ('}'||']'||')')


#define Element	char
#define printElem(e) printf("(%c)", e)
#define ERROR_STACKEMPTY	printf("Stack is empty");
#define ERROR_STACKOVER		printf("Stack overflow");
typedef struct ArrayStack {
	Element	data[MAX_STACK_SIZE];
	int		top;
} Stack;

extern void error( char* str );
extern void initStack( Stack* s );
extern int isEmpty( Stack* s );
extern int isFull( Stack* s );
extern void push ( Stack* s, Element e );
extern Element pop ( Stack* s );
extern Element peek ( Stack* s );
extern int size( Stack* s );
extern void display ( Stack* s, char* msg );
extern int isMatch(Element e1, Element e2);