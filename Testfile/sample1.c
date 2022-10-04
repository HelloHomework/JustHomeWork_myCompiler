void main()
{
		int a;
		int b;
		int c;

		a = 1;
		b = 2;
		c = 3;

		printf("a: %d\n", a);
		printf("b: %d\n", b);
		printf("c: %d\n", c);

		a = 2 * b + (a - 1) +1;
		b = a;

		printf("a: %d\n", a);
		printf("b: %d\n", b);
		printf("c: %d\n", c);

		printf("%d !\n",a);
}
