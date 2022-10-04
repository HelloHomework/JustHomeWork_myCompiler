@.str.0 = private unnamed_addr constant [18 x i8]c"The result is %d\0A\00", align 1
@.str.1 = private unnamed_addr constant [8 x i8]c"while:\0A\00", align 1
@.str.2 = private unnamed_addr constant [9 x i8]c"num: %d\0A\00", align 1
@.str.3 = private unnamed_addr constant [11 x i8]c"Do-while:\0A\00", align 1
@.str.4 = private unnamed_addr constant [9 x i8]c"num: %d\0A\00", align 1
@.str.5 = private unnamed_addr constant [6 x i8]c"for:\0A\00", align 1
@.str.6 = private unnamed_addr constant [9 x i8]c"num: %d\0A\00", align 1
; === prologue ====
declare dso_local i32 @printf(i8*, ...)

define dso_local i32 @main()
{
%t0 = alloca i32, align 4
%t1 = alloca i32, align 4
%t2 = alloca i32, align 4
store i32 10, i32* %t0
store i32 9, i32* %t2

%t3 = load i32, i32* %t2
%t4 = load i32, i32* %t0
;[DEBUG] A type: INT; B type: INT
%t5 = icmp sgt i32 %t3, %t4
br i1 %t5, label %L1, label %L2

L1:
%t6 = load i32, i32* %t2
%t7 = sub nsw i32 %t6, 1
%t8 = mul nsw i32 3, %t7
store i32 %t8, i32* %t0
br label %L3

L2:
%t9 = load i32, i32* %t2
%t10 = load i32, i32* %t2
%t11 = sub nsw i32 %t10, 2
%t12 = mul nsw i32 %t9, %t11
store i32 %t12, i32* %t0
br label %L3

br label %L3
L3:
%t13 = load i32, i32* %t0
%t14=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([18x i8], [18 x i8]* @.str.0, i64 0, i64 0), i32 %t13)
%t15=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([8x i8], [8 x i8]* @.str.1, i64 0, i64 0))
store i32 10, i32* %t2
br label %L6

L6:
%t16 = load i32, i32* %t2
;[DEBUG] A type: INT; B type: CONST_INT
%t17 = icmp sgt i32 %t16, 0
br i1 %t17, label %L7, label %L8

L7:
%t18 = load i32, i32* %t2
%t19=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([9x i8], [9 x i8]* @.str.2, i64 0, i64 0), i32 %t18)
%t20 = load i32, i32* %t2
%t21 = sub nsw i32 %t20, 2
store i32 %t21, i32* %t2

br label %L6
L8:
store i32 10, i32* %t2
%t22=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([11x i8], [11 x i8]* @.str.3, i64 0, i64 0))
br label %L9

L9:
%t23 = load i32, i32* %t2
%t24 = sub nsw i32 %t23, 1
store i32 %t24, i32* %t2
%t25 = load i32, i32* %t2
%t26=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([9x i8], [9 x i8]* @.str.4, i64 0, i64 0), i32 %t25)
%t27 = load i32, i32* %t2
;[DEBUG] A type: INT; B type: CONST_INT
%t28 = icmp sgt i32 %t27, 10
br i1 %t28, label %L9, label %L10

L10:
%t29=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([6x i8], [6 x i8]* @.str.5, i64 0, i64 0))

store i32 10, i32* %t2
br label %L11

L11:
%t30 = load i32, i32* %t2
;[DEBUG] A type: INT; B type: CONST_INT
%t31 = icmp sge i32 %t30, 5
br i1 %t31, label %L13, label %L14

L12:    ;counter
%t32 = load i32, i32* %t2
%t33 = sub nsw i32 %t32, 1
store i32 %t33, i32* %t2
br label %L11
L13:    ;trueLabel

%t34 = load i32, i32* %t2
%t35 = srem i32 %t34, 2
;[DEBUG] A type: INT; B type: CONST_INT
%t36 = icmp eq i32 %t35, 0
br i1 %t36, label %L15, label %L16

L15:
%t37 = load i32, i32* %t2
%t38=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([9x i8], [9 x i8]* @.str.6, i64 0, i64 0), i32 %t37)
br label %L17

L16:

br label %L17
L17:
br label %L12

L14:    ;endLabel

; === epilogue ===
ret i32 0
}
