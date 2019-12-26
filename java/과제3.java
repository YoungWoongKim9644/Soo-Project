package hello;

import java.util.Scanner;

public class H1 {   
   public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      
      int first_num;
      int second_num;
      int result = 0;
      char call;
      
      first_num = scanner.nextInt();
      second_num = scanner.nextInt();
      call = scanner.next().charAt(0);
      
      switch(call) {
      case '+':
         Add cal = new Add();
         cal.setValue(first_num,second_num);
         result = cal.calculate();
         break;
      case '-':
         Sub cal1 = new Sub();
         cal1.setValue(first_num,second_num);
         result = cal1.calculate();
         break;
      case '*':
         Mul cal2 = new Mul();
         cal2.setValue(first_num,second_num);
         result = cal2.calculate();
         break;
      case '/':
         Div cal3 = new Div();
         cal3.setValue(first_num,second_num);
         result = cal3.calculate();
         break;
      }
      
      System.out.println("value = "+result);
      
      }
      
   }


class Add {
   int a;
   int b;
   public void setValue(int a, int b) {
      this.a = a;
      this.b = b;
   }
   public int calculate(){
      return a+b;
   }
}


class Sub {
   int a;
   int b;
   public void setValue(int a, int b) {
      this.a = a;
      this.b = b;
   }
   public int calculate(){
      return a-b;
}
}
   
class Mul {
   int a;
   int b;
   public void setValue(int a, int b) {
      this.a = a;
      this.b = b;
   }
   public int calculate(){
      return a*b;}
}


class Div {
   int a;
   int b;
   public void setValue(int a, int b) {
      this.a = a;
      this.b = b;
   }
   public int calculate(){
      return (int) ((float)a/b);}
}

