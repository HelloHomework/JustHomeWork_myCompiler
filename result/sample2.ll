@.str.0 = private unnamed_addr constant [7 x i8]c"a = b\0A\00", align 1
@.str.1 = private unnamed_addr constant [9 x i8]c"b == 10\0A\00", align 1
@.str.2 = private unnamed_addr constant [8 x i8]c"b != 10\00", align 1
@.str.3 = private unnamed_addr constant [10 x i8]c"b == a-1\0A\00", align 1
@.str.4 = private unnamed_addr constant [12 x i8]c"other case\0A\00", align 1
; === prologue ====
declare dso_local i32 @printf(i8*, ...)

define dso_local i32 @main()
{
%t0 = alloca i32, align 4
%t1 = alloca i32, align 4
store i32 10, i32* %t1
store i32 10, i32* %t0

%t2 = load i32, i32* %t1
%t3 = load i32, i32* %t0
;[DEBUG] A type: INT; B type: INT
%t4 = icmp eq i32 %t2, %t3
br i1 %t4, label %L1, label %L2

L1:
%t5 = load i32, i32* %t1
%t6 = add nsw i32 %t5, 1
store i32 %t6, i32* %t1
%t7=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([7x i8], [7 x i8]* @.str.0, i64 0, i64 0))

%t8 = load i32, i32* %t0
;[DEBUG] A type: INT; B type: CONST_INT
%t9 = icmp eq i32 %t8, 10
br i1 %t9, label %L4, label %L5

L4:
%t10=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([9x i8], [9 x i8]* @.str.1, i64 0, i64 0))
br label %L6

L5:
%t11=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([8x i8], [8 x i8]* @.str.2, i64 0, i64 0))
br label %L6

br label %L6
L6:
br label %L3

L2:
%t12 = load i32, i32* %t0
%t13 = load i32, i32* %t1
%t14 = sub nsw i32 %t13, 1
;[DEBUG] A type: INT; B type: INT
%t15 = icmp eq i32 %t12, %t14
br i1 %t15, label %L9, label %L10

L9:
%t16 = load i32, i32* %t0
%t17 = add nsw i32 %t16, 1
store i32 %t17, i32* %t1
%t18=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([10x i8], [10 x i8]* @.str.3, i64 0, i64 0))
br label %L3

L10:
%t19 = load i32, i32* %t1
%t20 = add nsw i32 %t19, 2
store i32 %t20, i32* %t1
%t21=call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([12x i8], [12 x i8]* @.str.4, i64 0, i64 0))
br label %L3

br label %L3
L3:

%t22 = load i32, i32* %t1
%t23 = load i32, i32* %t0
;[DEBUG] A type: INT; B type: INT
%t24 = icmp eq i32 %t22, %t23
br i1 %t24, label %L13, label %L14

L13:
%t25 = load i32, i32* %t1
%t26 = add nsw i32 %t25, 1
store i32 %t26, i32* %t1
br label %L15

L14:
%t27 = load i32, i32* %t1
%t28 = load i32, i32* %t0
%t29 = mul nsw i32 %t28, 2
;[DEBUG] A type: INT; B type: INT
%t30 = icmp sgt i32 %t27, %t29
br i1 %t30, label %L16, label %L17

L16:
%t31 = load i32, i32* %t0
%t32 = sub nsw i32 %t31, 1
store i32 %t32, i32* %t0
br label %L15

L17:
%t33 = load i32, i32* %t1
%t34 = load i32, i32* %t0
;[DEBUG] A type: INT; B type: INT
%t35 = icmp sge i32 %t33, %t34
br i1 %t35, label %L18, label %L19

L18:
%t36 = load i32, i32* %t0
%t37 = sub nsw i32 %t36, 1
store i32 %t37, i32* %t0
br label %L15

L19:
%t38 = load i32, i32* %t1
%t39 = load i32, i32* %t1
%t40 = add nsw i32 %t38, %t39
store i32 %t40, i32* %t1
br label %L15

br label %L15
L15:
%t41 = load i32, i32* %t0
%t42 = add nsw i32 %t41, 1
store i32 %t42, i32* %t1
%t43 = load i32, i32* %t1
%t44 = add nsw i32 %t43, 2
%t45 = mul nsw i32 3, 4
%t46 = sub nsw i32 %t44, %t45
%t47 = add nsw i32 %t46, 5
store i32 %t47, i32* %t0

; === epilogue ===
ret i32 0
}
