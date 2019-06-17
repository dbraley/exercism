public class Triangle {
    private final TriangleKind kind;

    public Triangle(double a, double b, double c) throws TriangleException {
        checkIsPositive(a);
        checkIsPositive(b);
        checkIsPositive(c);
        checkInequality(a, b, c);
        if (isEquilateral(a, b, c)) {
            this.kind = TriangleKind.EQUILATERAL;
        } else if(isIsosceles(a,b,c)) {
            this.kind = TriangleKind.ISOSCELES;
        } else {
            this.kind = TriangleKind.SCALENE;
        }
    }

    private void checkInequality(double a, double b, double c) throws TriangleException {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new TriangleException();
        }
    }

    private void checkIsPositive(double side) throws TriangleException {
        if(side <= 0) {
            throw new TriangleException();
        }
    }

    private boolean isIsosceles(double a, double b, double c) {
        return a == b || b == c || a == c;
    }

    private boolean isEquilateral(double a, double b, double c) {
        return a == b && b == c;
    }

    public TriangleKind getKind() {
        return kind;
    }
}
