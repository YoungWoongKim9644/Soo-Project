# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""


import re

plg_sentences = []

def plagiarism_checker(origin, test):
    
    cnt = 0
    tmp = 0
    start_point = 0
    end_point = 0
    
    lest = abs(len(origin) - len(test))
    
    if len(origin) > len(test) :
        test.extend([' ']*lest)
    else :
        origin.extend([' ']*lest)
    
    for x in origin:
        for y in test:
            if (x == y)and tmp == 0: #첫 단어 겹침
                start_point = test.index(y)
                for o,t in list(zip(origin,test))[test.index(y):]:
                    if o != t :
                        end_point = start_point+list(zip(origin,test))[test.index(y):].index((o,t))-1
                        break
                    cnt = cnt +1
                if cnt>= 4 :
                    show_plagiarism(start_point,end_point, test)
                    cnt = 0
                cnt = 0
                
    if not(len(plg_sentences)) :
        print("Not plagiarism")
    print("Plagiarized line : ")
    for _ in plg_sentences:
        print(' '.join(_))
        print(" ")          
               

def show_plagiarism(start_point,end_point,test):
    tmp = test[start_point : end_point+1]
    if not(sublist(plg_sentences,tmp)):
        plg_sentences.append(tmp)

            
def sublist(plg_sentences, tmp):
    if len(plg_sentences) == 0:
        return False;
    for sentence in plg_sentences :
        for x in sentence :
            for y in  sentence :
                if sentence[sentence.index(x):sentence.index(y)+1] == tmp :
                    return True
    return False





f = open(r"C:\Users\duddn\OneDrive - 광운대학교\바탕 화면\PJ_SOO\Soo-Project\Python\d1.txt",'rt', encoding='UTF8')
f_test = open(r"C:\Users\duddn\OneDrive - 광운대학교\바탕 화면\PJ_SOO\Soo-Project\Python\d2.txt",'rt', encoding='UTF8')
read = f.read()
read_test = f_test.read()
#split = read.split('\W+')
origin_txt = re.split('[!]+|[?]+|[.]', read)
test_txt = re.split('[!]+|[?]+|[.]', read_test)

print(len(origin_txt))

for x in origin_txt:
    for y in test_txt:
        plagiarism_checker(x.split(),y.split())
