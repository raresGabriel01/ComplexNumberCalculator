import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public final class Menu {       // We are creating a Menu class wich shouldn't be extended any further
    private Menu(){             // By making the constructor private we make sure that there won't be any instances of this class
                                // We are trying to replicate a "top-level static class"
    }


    private static ComplexNumber z =new  ComplexNumber(0,0); // It is a read value by the user, our "current number"

    static Scanner scanner = new Scanner(System.in);


    public static void start() {// Displaying the options

        displayOptions();
        String option  = "";
        while(!option.equals("0")){     // 0 = the quit option
            System.out.println("Insert your menu option: (10 - menu options list)");
            option = scanner.nextLine();
            switch (option) { // a simple switch-case menu
                   case "0":
                       exit();
                       break;
                   case "1":
                       read(z);
                       System.out.println("Your current number is " + z); //we printed this here bcs we want to use the read method in other places and it would cause us
                       break;                                               // some problems
                   case "2":
                       add();
                       break;
                   case "3":
                       substract();
                       break;
                   case "4":
                       multiplyConst();
                       break;
                   case "5":
                       multiplyComplex();
                       break;
                   case "6":
                       divideConst();
                       break;
                   case "7":
                       divideComplex();
                       break;
                   case "8":
                       nthPower();
                       break;
                   case "9":
                       nthRoot();
                       break;
                   case "10":
                       displayOptions();
                       break;
                   default:
                       System.out.println("Invalid menu value, try again");
                       break;
               }

        }
    }

    private static void displayOptions(){       // method to display the menu options
        System.out.println("Menu: ");
        System.out.println("0: Exit \n" +
                "1: Read a new current value \n" +
                "2: Add something to the current value \n" +
                "3: Substract something from the current value \n" +
                "4: Multiply the current value by a real constant \n" +
                "5: Multiply the current value by another complex number \n" +
                "6: Divide the current value by a real constant \n" +
                "7: Divide the current value by another complex number \n" +
                "8: Get the n-th power of the current value \n" +
                "9: Get the n-th root of the current value \n");
    }
    private static void read(ComplexNumber w){     // method for reading a complex number
        System.out.println("Insert real part: ");
        //scanner.next();
        if(scanner.hasNextDouble()){
            double re = scanner.nextDouble();
            scanner.nextLine();
            w.setReal(re);
            System.out.println("Real part successfully set to " + w.getReal());
            System.out.println("Insert imaginary part: ");
            if(scanner.hasNextDouble()){
                double img = scanner.nextDouble();
                scanner.nextLine();
                w.setImg(img);
                System.out.println("Imaginary part successfully set to " + w.getImg());
            }
            else {
                System.out.println("Invalid value ! (real number required)");
                scanner.nextLine();
            }
        }
        else {
            System.out.println("Invalid value ! (real number required)");
            scanner.nextLine();
        }

    }
    private static void add(){
        System.out.println("Read a new number to add to the current number: ");
        ComplexNumber w = new ComplexNumber(0,0);
        read(w);
        System.out.print("(" + z + ") + (" + w + ") = ");
        z.add(w);
        System.out.print(z + "\n");
        System.out.println("(Current number was successfully set to " + z +")");

    }
    private static void substract(){
        System.out.println("Read a new number to substract from the current number: ");
        ComplexNumber w = new ComplexNumber(0,0);
        read(w);
        System.out.print("(" + z + ") - (" + w + ") = ");
        z.substract(w);
        System.out.print(z + "\n");
        System.out.println("(Current number was successfully set to " + z +")");

    }
    private static void multiplyConst(){
        System.out.println("Read a constant to multiply current number by: ");
        if(scanner.hasNextDouble()){
            double con = scanner.nextDouble();
            scanner.nextLine();
            ComplexNumber w = new ComplexNumber(con,0);
            System.out.print("("+z+") * " + con + " = ");
            z.multiply(w);
            System.out.print(z +"\n");
            System.out.println("(Current number was successfully set to " + z +")");
        }
        else {
            System.out.println("Invalid value ! (required real number)");
            scanner.nextLine();
        }
    }
    private static void multiplyComplex(){
        System.out.println("Read a complex number to multiply our current number with: ");
        ComplexNumber w = new ComplexNumber(0,0);
        read(w);
        System.out.print("("+z+") * ("+w+") = ");
        z.multiply(w);
        System.out.print(z + "\n");
        System.out.println("(Current number was successfully set to " + z +")");
    }
    private static void divideConst(){
        System.out.println("Read a constant to divide current number by: ");
        if(scanner.hasNextDouble()){
            double con = scanner.nextDouble();
            scanner.nextLine();
            if(con != 0){
            ComplexNumber w = new ComplexNumber(1.0/con,0);
            System.out.print("("+z+") / " + con + " = ");
            z.multiply(w);
            System.out.print(z +"\n");
            System.out.println("(Current number was successfully set to " + z +")");}
            else {
                System.out.println("Invalid value ! (non zero real number required)");
            }
        }
        else {
            System.out.println("Invalid value ! (non-zero real number required)");
            scanner.nextLine();
        }
    }
    private static void divideComplex(){
        System.out.println("Read a complex number to divide the current number by: ");
        ComplexNumber w = new ComplexNumber(0,0);
        read(w);
        if(w.getReal() == 0 && w.getImg() == 0)
            System.out.println("Invalid value ! Non-zero complex number required");
        else {

            System.out.print("("+z+") / ("+w+") = ");
            w.setImg(-w.getImg());
            z.multiply(w);
            double val = w.getReal()*w.getReal() + w.getImg()*w.getImg();
            z.multiply(new ComplexNumber(1.0/val,0));
            System.out.println(z+"\n");
            System.out.println("Current value is now set to: "+z);
        }
    }
    private static void nthPower(){
        System.out.println("Insert n:");
        int n ;
        if(scanner.hasNextInt()){
            n = scanner.nextInt();
            scanner.nextLine();
            if(n > 0){
                ComplexNumber w = new ComplexNumber(z);
            System.out.print("("+z+")^"+n+" = ");
            for(int i = 0; i < n-1 ; i++)
                z.multiply(w);
            System.out.print(z+"\n");
            System.out.println("Current value is now set to: "+z);
            }
            else if (n == 0){
                System.out.print(z+"^"+n+" = ");
                z.setImg(0);
                z.setImg(1);

                System.out.print(z + "\n");
                System.out.println("Current value is now set to: " + z);
            }
            else {
                System.out.println("Invalid value ! Please insert non-negative integer !");
            }
        }
        else {
            System.out.println("Invalid value! Please insert non negative integer!");
            scanner.nextLine();
        }
    }

    // we are going to use some some maths in order to get al the n nthRoots of a complex number
    // details can be found here: http://mathonline.wikidot.com/nth-roots-of-complex-numbers

    // note: we are going to use the pow() method, even though it can loose precision at some point, for the purpose of this project it is more than enough

    // so we know that z = a + bi
    // we want z = w(cost + i * sint )
    // z = a + bi = r(a/r + i* b/r)
    // we chose r so that there exists some t such that z = r(cost + isint)
    // or in other words, cost = a/r && sint = b/r
    // but how do we choose r ?
    // cos^2(t) + sin^2(t) = 1 <=> (a/r)^2 + (b/r)^2 = 1 <=> r = sqrt(a^2 +b^2)
    // cost = a/r => t = arccos(a/r) (obviously, -1 <= a/r <= 1)
    // the same thing with : sint = b/r => t = arcsin(b/r)


    // in short terms, if z = a+bi
    // then z = r( cos(arccos(a/r)) + i * sin(arcsin(b/r)) ), where r = sqrt(a^2+b^2)
    private static void nthRoot(){
        System.out.println("Insert n: ");
        int n;
        if(scanner.hasNextInt()){
            n = scanner.nextInt();
            scanner.nextLine();
            if(n>=2){
                double r = Math.sqrt(z.getReal()*z.getReal() + z.getImg()*z.getImg());
                double t = Math.acos(z.getReal()/r);
                System.out.println("There are " +n+" nthRoots for " +z+": ");

                for(int i=0; i <n; i ++){
                    double arg = (t + 2*Math.PI*i) / n;
                    ComplexNumber w = new ComplexNumber(round(Math.pow(r,1.0/n)*Math.cos(arg),2),round(Math.pow(r,1.0/n)*Math.sin(arg),2));
                    System.out.println("Root number "+ (i+1) +": " + w);
                }
            }else {
                System.out.println("Invalid value ! Insert integer value greater or equal to 2 !");
            }
        }
        else {
            System.out.println("Invalid value ! Insert non-negative integer !");
            scanner.nextLine();
        }
    }
    private static void exit(){
        System.out.println("\n \n Successfully exited menu ! \n \n");
    }



    private static double round(double value, int places) {

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
