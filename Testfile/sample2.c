void main()
{
		int a;
		int b;

		a = 10;
      b = 10;
		

		if(a == b){
			a = a + 1;
			printf("a = b\n");
			if(b == 10){
				printf("b == 10\n");
			} else 
			printf("b != 10");
		} else if(b == a-1){
			a = b + 1;
			printf("b == a-1\n");
		} else {
			a = a + 2;
			printf("other case\n");
		}

		if (a==b) a = a + 1;
		else if (a>b * 2) b = b - 1;
		else if (a>=b) b = b - 1;
		else a = a + a;

		a = b+1;
		b = a + 2 - 3 * 4 + 5;
}
