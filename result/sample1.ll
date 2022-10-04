@.str.0 = private unnamed_addr constant [7 x i8]c"a: %d\0A\00", align 1
@.str.1 = private unnamed_addr constant [7 x i8]c"b: %d\0A\00", align 1
@.str.2 = private unnamed_addr constant [7 x i8]c"c: %d\0A\00", align 1
@.str.3 = private unnamed_addr constant [7 x i8]c"a: %d\0A\00", align 1
@.str.4 = private unnamed_addr constant [7 x i8]c"b: %d\0A\00", align 1
@.str.5 = private unnamed_addr constant [7 x i8]c"c: %d\0A\00", align 1
@.str.6 = private unnamed_addr constant [6 x i8]c"%d !\0A\00", align 1
; === prologue ====
declare dso_local i32 @printf(i8*, ...)

define dso_local i32 @main()
{
%t0 = alloca i32, align 4
%t1 = alloca i32, align 4
%t2 = alloca i32, align 4
store i32 1, i32* %t2
store i32 2, i32* %t1
store i32 3, i32* %t0
%t3 = load i32, i32* %t2
%t4=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7x i8], [7 x i8]* @.str.0, i64 0, i64 0), i32 %t3)
%t5 = load i32, i32* %t1
%t6=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7x i8], [7 x i8]* @.str.1, i64 0, i64 0), i32 %t5)
%t7 = load i32, i32* %t0
%t8=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7x i8], [7 x i8]* @.str.2, i64 0, i64 0), i32 %t7)
%t9 = load i32, i32* %t1
%t10 = mul nsw i32 2, %t9
%t11 = load i32, i32* %t2
%t12 = sub nsw i32 %t11, 1
%t13 = add nsw i32 %t10, %t12
%t14 = add nsw i32 %t13, 1
store i32 %t14, i32* %t2
%t15 = load i32, i32* %t2
store i32 %t15, i32* %t1
%t16 = load i32, i32* %t2
%t17=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7x i8], [7 x i8]* @.str.3, i64 0, i64 0), i32 %t16)
%t18 = load i32, i32* %t1
%t19=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7x i8], [7 x i8]* @.str.4, i64 0, i64 0), i32 %t18)
%t20 = load i32, i32* %t0
%t21=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7x i8], [7 x i8]* @.str.5, i64 0, i64 0), i32 %t20)
%t22 = load i32, i32* %t2
%t23=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([6x i8], [6 x i8]* @.str.6, i64 0, i64 0), i32 %t22)

; === epilogue ===
ret i32 0
}
