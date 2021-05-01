# 📌 지원문법 (Supported Syntax)
  
Syntax | Supported
  --------- | ---------
  if | ✅
  int | ✅
 console log | ✅ 
expression| ✅
compare operator | ✅
calculations(+,-,*,/) | ✅
loop | ❌
function | ❌
string | ❌

# 키워드
Syntax | Description
  --------- | ---------
if | 조건문 키워드
print | 콘솔출력
@@ | 좌변과 우변의 값이 같음
 != | 좌변과 우변의 값이 같지않음
 \> | 보다큼
< | 보다작음
>= | 크거나같음
<= | 작거나같음
= | 대입
+ | 더하기
- | 뺴기
* | 곱하기
/ | 나누기
골뱅이 언어인 이유가 비교연산자가 @@라서..

# 🖌 예제코드 (Example Source)
~~~
Var = 10  
PlusVar = Var + 20

if Var <= PlusVar {  
print 1  
}

PV =  PlusVar - 20  * 10  
print PV  

if PV @@ Var {  
print 10000  
}
~~~
# 🛠 컴파일 과정
1. Read File (.at)
2. Tokenizing
3. Lexer
4. AST
5. AST to Middleware
6. Run middle code on stack machine
# 🎫 미들웨어
어셈블리와 비슷한 원리라고 생각하면 됩니다.

### < 스택 머신 제어 명령 >
1. **push identifier | digit** - 스택 머신에 값 푸시
2. **_jump <label>**
3. **_jnz <label>**
4. **_jz <label>**
5. **_jbl <label>**
6. **_jbleq <label>**
7. **_jbr <label>**
8. **_jbreq <label>**

### < 연산 명령 >
1. **_add**  
2. **_subtract**
3. **_multiply**
4. **_divide**

### < 메모리 접근 명령 >
1. **alloc identifier** - 스택머신 top에 있는 값을 identifier 이름으로 할당

### < 라벨 >
1. **label identifier**

### < 비교명령 >
1. **_eq** - stack에서 두 개의 값을 빼서 뺄셈을 했을 때 0이면 true
2. **_neq** - stack에서 두 개의 값을 빼서 뺄셈을 했을떄 0이 아니면 true
3. **_bl** (bigger then left)
4. **_bleq** (bigger then left or equals)
5. **_br** (bigger then right)
6. **_breq** (bigger then right or equals)

### < 콘솔제어명령 >
1. **conbuf** - 스택 top에 있는 값을 콘솔 버퍼에 넣음
2. **conflush** - 콘솔 버퍼를 비우고 출력

# 🎲 스택머신
미들웨어 코드를 스택머신 위에서 실행합니다.  
스택머신은 push와 pop만으로 코드를 실행합니다.

# 🧩 가상 메모리
8096B (MB 아닙니다 :)의 메모리를 할당하여 가상메모리에 데이터를 할당합니다  
alloc, free, access 다 할수 있습니다.

alloc시 해당 데이터 크기만큼 메모리를 할당하고 끝 주소를 반환합니다  
free시 메모리 해제를 하고 offset을 해제한 메모리 시작점으로 맞춰놓습니다.  
access시 해당 메모리 시작점에서 데이터를 불러옵니다.

모든 데이터를 byte로 변환합니다.

# 🚎 가상 메모리 레지스터
변수명과 해당 변수의 주소값이 담겨있습니다. (Map 기반)

# ❗️ 유의할점
스페이스바에 민감합니다. 오류나면 에러메세지고 뭐고 없습니다.  
주의해서 코딩하세요 (매우 불친절한 언어)  
변수선언할떄 변수명 영어만 쓰세요  
계산식 쓸때는 다 띄워쓰기 ex) var = 10 + 20 + ( 10 * 20 )

# 💬 연락처
wsnam0507@gmail.com  
개발자 - 남대영 😡
