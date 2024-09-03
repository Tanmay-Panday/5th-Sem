#include <stdio.h>
#include <math.h>

double f(double x) {
    return 2 * exp(x) * sin(x) - 3;
}

int main() {
    double a, b, c;
    int tolerence;

    printf("Lower guess = ");
    scanf("%lf", &a);
    printf("Upper guess = ");
    scanf("%lf", &b);
    printf("Correct up to ___ decimal places? = ");
    scanf("%d", &tolerence);

    double toleranceValue = pow(10, -tolerence);

    if (f(a) * f(b) > 0) {
        printf("Incorrect guesses\n");
        return 0;
    }

    do {
        c = (a * f(b) - b * f(a)) / (f(b) - f(a));  // Regula Falsi method
        if (f(c) < 0)
            a = c;
        else
            b = c;
    } while (fabs(b - a) > toleranceValue);

    printf("Root is approximately %.6f\n", c);

    return 0;
}
