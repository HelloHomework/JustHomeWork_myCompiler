all: antlr-3.5.3-complete.jar myCompiler.g myCompiler_test.java ./Testfile/sample1.c ./Testfile/sample2.c ./Testfile/sample3.c
	java -jar ./antlr-3.5.3-complete.jar ./myCompiler.g
	javac -cp ./antlr-3.5.3-complete.jar:. ./*.java

	if [ -d "./result" ]; then \
				rm -rf result; \
  fi

	mkdir result

	java -cp ./antlr-3.5.3-complete.jar:. myCompiler_test ./Testfile/sample1.c > ./result/sample1.ll
	java -cp ./antlr-3.5.3-complete.jar:. myCompiler_test ./Testfile/sample2.c > ./result/sample2.ll
	java -cp ./antlr-3.5.3-complete.jar:. myCompiler_test ./Testfile/sample3.c > ./result/sample3.ll

	if [ -d "./result_run" ]; then \
				rm -rf result_run; \
  fi

	mkdir result_run
	clang ./result/sample1.ll -o ./result_run/sample1.out
	clang ./result/sample2.ll -o ./result_run/sample2.out
	clang ./result/sample3.ll -o ./result_run/sample3.out
clang:
	if [ -d "./clang_result" ]; then \
				rm -rf clang_result; \
  fi

	mkdir clang_result
	clang -S -emit-llvm ./Testfile/sample1.c -o ./clang_result/sample1.ll
	clang -S -emit-llvm ./Testfile/sample2.c -o ./clang_result/sample2.ll
	clang -S -emit-llvm ./Testfile/sample3.c -o ./clang_result/sample3.ll
clean:
	rm -rf ./result
	rm -rf ./result_run
	rm -rf ./clang_result
	rm myCompilerLexer.java myCompilerParser.java
	rm ./*.class
	rm ./*.tokens