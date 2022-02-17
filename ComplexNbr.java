public class ComplexNbr {

    private double real;
    private double imag;
    private static ComplexNbr ZERO = new ComplexNbr();
    private static ComplexNbr I = new ComplexNbr(0, 1);

    ComplexNbr() {
        real = 0;
        imag = 0;
    }

    ComplexNbr(double r, double i) {
        real = r;
        imag = i;
    }

    ComplexNbr(double r) {
        real = r;
        imag = 0;
    }

    ComplexNbr(ComplexNbr comp1) {
        real = comp1.real;
        imag = comp1.imag;
    }

    public double getReal() {
        return this.real;
    }

    public void setReal(double r) {
        real = r;
        return;
    }

    public double getImag() {
        return this.imag;
    }

    public void setImag(double i) {
        imag = i;
        return;
    }

    public void setValue(double r, double i) {
        real = r;
        imag = i;
    }

    public void setValue(ComplexNbr comp1) {
        real = comp1.real;
        imag = comp1.imag;
    }

    public boolean isReal() {
        if (imag == 0) {
            return true;
        } else return false;
    }

    public boolean isImag() {
        if (real == 0 && imag != 0) {
            return true;
        } else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        ComplexNbr comp1 = (ComplexNbr) o;
        return (Helper.areEqual(this.getReal(), comp1.getReal()) && Helper.areEqual(this.getImag(), comp1.getImag()));
    }

    public double getmodulus() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
    }

    public ComplexNbr getConjugate() {
        return new ComplexNbr(real, -imag);
    }

    public double getArgument() { //arg(z) = arctan(b/a)
        return (Math.atan2(imag, real));
    }

    public void add(ComplexNbr comp1) {
        double newR = this.getReal() + comp1.getReal();
        double newI = this.getImag() + comp1.getImag();
        this.setValue(newR, newI);
    }

    public void add(double r) {
        double newR = this.getReal() + r;
        this.setReal(newR);
    }

    public static ComplexNbr add(ComplexNbr comp1, double r) {
        double newR = comp1.getReal() + r;
        return new ComplexNbr(newR, comp1.getImag());
    }

    public static ComplexNbr add(ComplexNbr comp1, ComplexNbr comp2) {
        double newR = comp1.getReal() + comp2.getReal();
        double newI = comp1.getImag() + comp2.getImag();
        return new ComplexNbr(newR, newI);
    }

    public void subtract(ComplexNbr comp1) {
        double newR = Helper.compare(this.getReal(), comp1.getReal());
        double newI = Helper.compare(this.getImag(), comp1.getImag());
        this.setValue(newR, newI);
    }

    public void subtract(double r) {
        double newR = Helper.compare(this.getReal(), r);
        this.setReal(newR);
    }

    public static ComplexNbr subtract(ComplexNbr comp1, double r) {
        double newR = Helper.compare(comp1.getReal(), r);
        return new ComplexNbr(newR, comp1.getImag());
    }

    public static ComplexNbr subtract(ComplexNbr comp1, ComplexNbr comp2) {
        double newR = Helper.compare(comp1.getReal(), comp2.getReal());
        double newI = Helper.compare(comp1.getImag(), comp2.getImag());
        return new ComplexNbr(newR, newI);
    }


    public void multiply(ComplexNbr comp1) { // (a+bi)(c+di) = ac-bd +(bc+ad)i
        double newR = Helper.compare(this.getReal() * comp1.getReal(), this.getImag() * comp1.getImag());
        double newI = this.getImag() * comp1.getReal() + this.getReal() * comp1.getImag();
        this.setValue(newR, newI);
    }

    public void multiply(double r) {
        double newR = this.getReal() * r;
        double newI = this.getImag() * r;
        this.setValue(newR, newI);
    }

    public static ComplexNbr multiply(ComplexNbr comp1, ComplexNbr comp2) {
        double newR = Helper.compare(comp1.getReal() * comp1.getReal(), comp2.getImag() * comp1.getImag());
        double newI = comp1.getImag() * comp2.getReal() + comp1.getReal() * comp2.getImag();
        return new ComplexNbr(newR, newI);
    }

    public static ComplexNbr multiply(ComplexNbr comp1, double r) {
        double newR = comp1.getReal() * r;
        double newI = comp1.getImag() * r;
        return new ComplexNbr(newR, newI);
    }

    public void divide(ComplexNbr comp1) {
        // (a+bi)/(c+di) = (ac+bd)/(c^2+d^2) + ((bc-ad)/(c^2+d^2))i
        double newR = (this.getReal() * comp1.getReal() + this.getImag() * comp1.getImag()) / (Math.pow(comp1.getReal(), 2) + Math.pow(comp1.getImag(), 2));
        double newI = Helper.compare(this.getImag() * comp1.getReal(), this.getReal() * comp1.getImag()) / (Math.pow(comp1.getReal(), 2) + Math.pow(comp1.getImag(), 2));
        try {
            if (Double.isNaN(newR) && Double.isNaN(newI)) {
                throw new Exceptions.InvalidDenominatorException("");
            } else this.setValue(newR, newI);
        } catch (Exceptions.InvalidDenominatorException e) {
            System.err.println("Denominator should not be equal to ZERO!!");
        }
    }

    public void divide(double r) {
        double newR = this.getReal() / r;
        double newI = this.getImag() / r;
        try {
            if (Double.isInfinite(newR) && Double.isInfinite(newI)) {
                throw new Exceptions.InvalidDenominatorException("");
            } else this.setValue(newR, newI);
        } catch (Exceptions.InvalidDenominatorException e) {
            System.err.println("Denominator should not be equal to ZERO!!");
        }
    }

    public static ComplexNbr divide(ComplexNbr comp1, ComplexNbr comp2) {// (a+bi)/(c+di) = (ac+bd)/(c^2+d^2) + ((bc-ad)/(c^2+d^2))i
        double newR = (comp1.getReal() * comp2.getReal() + comp1.getImag() * comp2.getImag()) / (Math.pow(comp2.getReal(), 2) + Math.pow(comp2.getImag(), 2));
        double newI = Helper.compare(comp1.getImag() * comp2.getReal(), comp1.getReal() * comp2.getImag()) / (Math.pow(comp2.getReal(), 2) + Math.pow(comp2.getImag(), 2));
        try {
            if (Double.isNaN(newR) && Double.isNaN(newI)) {
                throw new Exceptions.InvalidDenominatorException("");
            } else return new ComplexNbr(newR, newI);
        } catch (Exceptions.InvalidDenominatorException e) {
            System.err.println("Denominator should not be equal to ZERO!!");
        }
        return new ComplexNbr(comp1.getReal(), comp1.getImag());

    }

    public static ComplexNbr divide(ComplexNbr comp1, double r) {
        double newR = comp1.getReal() / r;
        double newI = comp1.getImag() / r;
        try {
            if (Double.isInfinite(newR) && Double.isInfinite(newI)) {
                throw new Exceptions.InvalidDenominatorException("");
            } else return new ComplexNbr(newR, newI);
        } catch (Exceptions.InvalidDenominatorException e) {
            System.err.println("Denominator should not be equal to ZERO!!");
        }
        return new ComplexNbr(comp1.getReal(), comp1.getImag());
    }

    public String toString() {
        if (this.equals(ZERO)) {
            return ("z=0");
        } else if (this.equals(I)) {
            return ("z=i");
        } else if (this.isReal()) {
            return ("z=" + this.getReal());
        } else if (this.isImag()) {
            return ("z=" + this.getImag() + "i");
        } else if (this.getImag() == 1) {    //becomes "z=3+i" instead of "z=3+1i"
            return ("z=" + this.getReal() + "+i");
        } else return ("z=" + this.getReal() + "+" + this.getImag() + "i");
    }

    public static void (String[] args){
        System.out.println("1232456");
    }
}



