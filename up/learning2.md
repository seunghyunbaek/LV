# Learning 2
## 액티비티란?
액티비티라는 단어의 의미인 '활동'이 나타내는 것처럼 액티비티는 전화를 걸고, 메일을 작성하고, 사진을 찍는 등 사용자가 어떤 활동을 할 때 실행되는 애플리케이션의 컴포넌트를 가리킵니다.  

액티비티에는 윈도우가 있고, 그 윈도우에 텍스트나 이미지를 표시해 사용자 조작에 반응할 수 있습니다. UI가 없는 액티비티도 있지만 기본적으로 한 액티비티가 한 화면을 표시합니다.

### 액티비티 만들기
액티비티를 만들려면 우선 액티비티를 상속한 클래스를 만들어야 합니다.  
AppCompatActivity는 액티비티를 상속하며, 액티비티를 상속함으로써 머티리얼 디자인(Material Design)의 가이드라인에 따른 AppCompat 라이브러리를 제대로 활용할 수 있습니다.
<hr/>

## 액티비티의 Lifecycle
액티비티에는 몇 가지 사앹가 있고, 상태를 변경할 때 onCreate() 등의 콜백이 안드로이드 프레임워크에서 호출됩니다. Lifecycle의 종류와 흐름을 비롯해 상황에 따라 각각 무엇을 구현해야 하는지 살펴보겠습니다.  
  
| 메서드명     | 시점  | 처리 예                              |
| -------- | --- | --------------------------------- |
| onCreate | 생성시 | 초기화 처리와 뷰 생성(setContentView 호출) 등 |
| onStart   | 비표시 시           | 통신이나 센서 처리를 시작                                               |
| onRestart | 표시 시(재시작만) | 보통은 아무것도 하지 않아도 된다.                                          |
| onResume  | 최전면 표시          | 필요한 애니메이션 실행 등의 화면 갱신 처리(※)                                  |
| onPause   | 일부 표시(일시정지) 상태  | 애니메이션 등 화면 갱신 처리를 정지 또는 일시정지할 때 필요없는 리소스를 해제하거나 필요한 데이터를 영속화 |
| onStop    | 비표시(정지) 상태      | 통신이나 센서처리를 정지                                                |
| onDestroy | 폐기 시            | 필요없는 리소스를 해제, 액티비티 참조는 모두 정리한다.                              |

Android N부터 멀티윈도우가 도입되었습니다. 멀티윈도우를 지원하는 경우 애니메이션 실행 등 화면 갱신 처리의 정지는 onStop에서 합니다.

**시스템 메모리가 모자랄 경우 시스템은 onStop, onDestroy를 콜백하지 않고 액티비티를 강제로 종료**시켜 메모리를 확보할 때가 있습니다.  
**이러한 경우 데이터를 영속적으로 보존하려면 액티비티가 일시정지 상태로 전환되는 onPause에서 지를 처리할 필요가 있습니다**.

### 그렇다면 Lifecycle에 따라 어떤 작업을 수행하는게 좋을까요?
쌍으로 생각하면 좋습니다.  
**onCreate - onDestroy**  
onCreate에서 뷰를 만들면 onDestroy에서 해제합니다.  
뷰는 액티비티가 폐기된 다음, 가비지 콜렉션에 의해 자동으로 메모리에서 해제됩니다.  
**onStart - onStop**  
onStart에서 위치 정보 취득을 시작했다면 onStop에서(만약 정보 취득을 완료하지 않다면) 취득을 정지하는 식입니다.  
**onResume - onPause**  

onDestroy에서 액티비티가 폐기되면 가비지 콜렉션이 메모리 영역에서 해제합니다.  
단, 액티비티의 인스턴스가 다른 클래스에서 참조되고 있을 때는 폐기된 후에도 메모리에 남아 결국 메모리 누수가 발생합니다.

<hr/>

## 디바이스 설정의 갱신 탐지
액티비티는 디바이스 설정에 변경이 발생하면 기본적으로 시스템에서 현재 액티비티를 폐기하고 새로 생성합니다.  
예를들어 
- 화면을 세로에서 가로로 돌리기
- 언어 설정 변경
- 단말기 SIM 교체에 따른 전화번호 변경 등

좀 더 구체적으로 설명해 보겠습니다.  
만약 언어 설정이 한국어에서 영어로 바뀐다면 액티비티에 표시되는 문자열도 영어로 변경하고 싶을 것입니다. **안드로이드에서는 액티비티가 시작될 때 단말기 상태에 맞게 리소스를 선택하는 기능이 있습니다**. 영어 버전 전용 문자열 리소스 파일을 만들어 두면 언어 설정이 영어로 바뀌었을 때 영어 리소스 파일을 읽어옵니다.

액티비티를 재생성할 때는 현재 상태를 일시적으로 저장해서 이용하고 싶은 경우가 있습니다.  
액티비티에는 onSaveInstanceState / onRestoreInstanceState 라는 콜백 메서드가 있어서 일시적으로 데이터를 저장하고 복귀시 저장한 데이터를 가져올 수 있습니다.

onSaveInstanceState() 메서드의 인수로 전달되는 Bundle형 인스턴스에 저장하고 싶은 데이터를 설정할 수 있습니다.

onRestoreInstanceState() 메서드로 가져올 수 있습니다.  
설정할 수 있는 자료형은 기본형과 문자열, 리스트와 Parcelable 형을 구현한 인스턴스입니다.

Parcelable이란 '작은 화물'이라는 의미에 'able(가능하다)'라는 접미사를 붙여 '짐으로서 운반할 수 있는 것'이 됩니다.  
onSaveInstanceState() / onRestoreInstaneState()는 사용자가 뒤로가기(Back) 키로 액티비티를 명시적으로 폐기한 경우에는 호출되지 않습니다. 영속적으로 저장하고 싶은 데이터는 onPause 시정에서 저장해두면 됩니다.

## 액티비티의 백스태을 이해하자
새로운 액티비티가 시작되면 실행중이던 액티비티는 백스택에 들어갑니다.  
또한 시작한 액티비티는 태스크라는 그룹에 속합니다. 이 항목은 안드로이드 OS의 버전에 따라서 미묘하게 동작이 달라 다 이해하기는 어렵습니다. 그린 여기서는 3가지만 알아두세요.  
- 같은 앱에서 시작된 액티비티는 같은 백스택에 쌓인다.
- taskAffinity의 속성에 따라 소속되는 태스크가 달라진다.
- launchMode에 따라 액티비티 생성의 여부, 새로운 태스크에 속하는 등 액티비티의 시작이 달라진다.

백스택에 쌓인 액티비티는 뒤로가기 등으로 액티비티를 종료하면 위에서부터 차례로 꺼내집니다. 또한 taskAffinity는 태스크 친화성이라는 의미지만, 대체로 '태스크 이름' 으로 바꿔읽는 것이 일해하기 쉽습니다. **taskAffinity가 지정되지 않은 경우에는 자기 앱의 패키지 이름이 태스크 이름**이 됩니다. taskAffinity를 설정하지 않으면 그 앱의 taskAffinity는 모두 같아집니다.

singleTask는 브라우저 앱이나 게임 앱 등 여러 액티비티를 만들고 싶지 않을 때 사용합니다.  
#### launchMode의 종류
standard
- 매번 액티비티의 인스턴스를 새로 생성한다. 기본값이다.

singleTop
- 같은 액티비티가 최상위에서 실행중이면 액티비티를 생성하지 않고, 그 대신 최상위 인스턴스의 onNewIntent()를 호출한다.

singleTask
- 1개의 태스크에 인스턴스가 존재한다. 
- 이미 같은 액티비티가 실행 중이면 액티비티를 생성하지 않는다.

singleInstance
- 1개의 태스크에 1개의 인스턴스만 존재한다. 
- 다른 액티비티를 태스크에 포함하지 않는다. 
- 이미 같은 액티비티가 실행중이면 액티비티를 생성하지 않는다.
<hr/>

## 뷰와 레이아웃
뷰란 UI를 구성하는 바탕이 되는 컴포넌트로서 네모난 그리기 영역을 가집니다.  
TextView, Button, EditText, ImageView 등 다양한 종류가 있다.  
기본적으로 XMl로 기술하는 것이 자바 코드보다 코드의 양도 적고 읽기에도 편해 유지 및 관리에 유리합니다.

<hr/>

## 프래그먼트 Lifecycle
프래그먼트는 액티비티와 마찬가지로 Lifecycle을 가집니다.  

onAttach  
- 프래그먼트와 액티비티가 연결될 때
- 이 시점에서 getActivity 메서드는 null을 반환합니다.  

onCreate  
- 생성 시
- 초기화 처리

onCreateView  
- 생성시
- 뷰 생성

onactivityCreated  
- 생성시
- 초기화 처리, 뷰 생성(setContentView의 호출) 등

onStart  
- 비표시 상태
- 표시 전 시점

onResume  
- 표시 시
- 필요한 애니메이션 등 실행 화면 갱신 처리

onPause
- 일부 표시(일시정지) 상태
- 애니메이션 등 화면 갱신 처리 정지
- 일시정지 시에 불필요한 리소스 해제
- 필요한 데이터 영속화

onStop
- 비표시 상태
- 비표시된 시점

onDestroyView
- 폐기 시
- 필요 없는 리소스 해제

onDestroy
- 폐기 시
- 필요 없는 리소스 해제

onDetach
- 폐기 시
- 필요 없는 리소스 해제

<hr/>