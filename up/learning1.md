# Learning 1
### Company Domain
Google Play상에서 *패키지 이름은 중복 불가*
<hr/>
### Minimum SDK Version 선택하기
안드로이드 OS 버전별로 사용할 수 있는 API가 다르다. 너무 낮은 SDK를 설정하면 수 많은 단말기기에서 동작하는지 검증을 해야하고 시간이 오래 걸린다.
사용하고 싶은 API나 사용자 점유율을 살펴 상황에 맞게 결정하는게 좋다.  
결정에 도움이될 버전별 점유율을 볼 수 있는 사이트 : https://developer.android.com/about/dashboards/index.html
<hr/>
### 안드로이드의 폴더구성
#### build.gradle(project)
build.gradle은 gradle이라는 빌드 시스템의 설정파일이다. 여기에 프로젝트의 전체 설정이 기술된다. 구체적으로는 Android Gradle Plugin의 버전과 Maven 리포지터리 설정 등이 여기에 해당한다.  

#### settings.gradle(project)
어떤 디렉토리가 모듈인지 정의한다.  

#### gradlew(gradle의 wrapper파일)
GradleWrapper라는 메커니즘이 있어서 그레이들의 버전을 지정해서 빌드할 수 있다. 프로젝트 팀이 같은 버전의 gradle을 이용하고 싶은 경우에 사용할 수 있다.  

#### build.gradle(module)
gradle의 모듈 설정이 기술된 파일이다. 설정 파일 중에서 편집할 기회가 가장 많다. 여기서 앱의 버전 등 다양한 설정을 할 수 있다.

#### build 폴더
build 폴더에는 빌드 시 생성되는 중간 생성물과 최종 산출물이 저장된다. 안드로이드 애플리케이션 파일로서 최종적으로 apk 파일이 만들어지는데, 이 파일은 build 폴더 아래에 저장된다.

#### src 폴더
src 폴더에는 소스코드나 이미지 등 리소스 파일이 들어간다. 기본적으로 'main'과 'androidTest', 'test'폴더가 만들어진다. 기본적으로 'main'폴더 안에서 파일을 추가하고 편집하면서 개발을 진행한다. 나머지 두 폴더에는 테스트 코드가 들어간다.

#### java폴더
안드로이드 앱의 소스코드 파일이 들어간다.

#### res폴더
안드로이드 앱에서 사용되는이미지와 문자열 등의 리소스를 배치하는 곳이다. res폴더에 들어가는 대표적인 것으로 이미지 파일을 저장하는 *drawable*, 레이아웃의 XML파일을 저장하는 *layout*, 문자열 등의 파일을 저장하는 values라는 폴더가 있다. 안드로이드의 리소스 폴더에는 *Alternative Resource*라는 것이 있어서 단말기 설정 상태에 특화된 리소스를 배치할 수 있다. 이를 이용해 다국어를 지원하거나 태블릿 화면을 지원하는 등 화면 밀도(dpi)별로 이미지를 준비할 수 있다.

#### AndroidManifest.xml
안드로이드 앱 설정을 기술하는 파일이다. Activity와 Service 등과 같은 앱에서 사용되는 클래스 선언과 퍼미션 설정 등을 작성한다.
<hr/>

### 유용한 단축키 (윈도우)
Ctrl + Space : 기본 자동완성  
Ctrl + Shift + Space : 현재 위치의 자료형을 바탕으로 자동완성  
Alt + Enter : 오류 수정  
Ctrl + Shift + Enter : 현재 구문 완성  
Ctrl + P : 파라미터 정보 표시  
Alt + Insert : 코드 자동 생성(ex:toString, getter), 정형화된 코드를 생성한다.  
Ctrl + Alt + V,F,M,C 등 : 지금 커서가 있는 부분을 Extract(추출)할 수 있다.
* V : Variable  
* F : Field  
* M : Method  
* C : Const  
  
변수.notnull + Enter : null을 체크하는 if문을 만들 수 있다.  
.var : 로컬 변수로 만든다.  
.field : 멤버변수에 대입  
.for : for문 만들기  
이 외에는 설정->Editor->Postfix Copletion에서 확인할 수 있다.  
Ctrl + B : 메서드의 선언부로 이동한다.  
Ctrl + Alt + H : 메서드를 호출한 곳 열기.  