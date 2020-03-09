public class ComplexNumber {
    private double img;
    private double real;        // the real and the imaginary part of a complex number

    ComplexNumber() {
        this.real = 0;      // constructors
        this.img = 0;
    }

    ComplexNumber(double real, double img){
        this.real = real;
        this.img = img;
    }

    ComplexNumber(double real) {
        this.real = real;
        this.img = 0;
    }
    ComplexNumber(ComplexNumber w){
        real = w.getReal();
        img = w.getImg();
    }
    public double getReal() {
        return real;
    }                                       // Getters and setters

    public double getImg() {
        return img;
    }

    public void setImg(double img) {
        this.img = img;
    }

    public void setReal(double real) {
        this.real = real;
    }
    public void add(ComplexNumber w){
        real += w.getReal();
        img += w.getImg();
    }

    public void substract(ComplexNumber w){
        real -= w.getReal();
        img -= w.getImg();
    }
    public void multiply(ComplexNumber w){
        double re = real;
        double im = img;
        real = re*w.getReal() - im*w.getImg();
        img = re*w.getImg() + im*w.getReal();
    }
    public String toString() {          // a nice toString conversion so we can do System.out.print(z) accesible
        if (img > 0) {
            if (real == 0)
                return "i*"+img;
            else return real + " + i*" + img;
        } else if (img == 0) {
            return real + "";           // some smart work-around so we won't have to call toString(real) because we are overriding it right now and it will cause an error
                                        // because it basically enters a loop but it won't work since we are using no parameters here
        } else {
            if(real == 0)
                return "-i*" + -img;
            else return real + " - i*" + -img;
        }
    }
}
