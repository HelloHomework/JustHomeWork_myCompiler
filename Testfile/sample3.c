void main()
{
	int num, result;
	int tmp;

   result = 10;
   num = 9;


	if(num>result){
		result = 3 * (num - 1);
	} else {
  	result = num * (num - 2);
	}
	printf("The result is %d\n", result);

	printf("while:\n");

	num = 10

	while(num > 0){
		printf("num: %d\n", num);
		num = num - 2;
	}

	num = 10

	printf("Do-while:\n");

	do{
		num = num - 1;
		printf("num: %d\n", num);
	}while(num>10);

	printf("for:\n");

	for(num = 10; num >= 5; num = num - 1){
			if(num % 2 == 0)printf("num: %d\n", num);
	}
}