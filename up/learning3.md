#### ContentProvider

ContentProvider는 앱 사이에서 각종 데이터를 공유할 수 있게 해주는 컴포넌트입니다.

안드로이드 표준 시스템에서는 연락처인 Contacts나 이미지, 동영상 등의 데이터를 보관하는 MediaStore 등이 ContentProvider로 공개돼 있습니다. 데이터를 검색, 추가, 갱신, 삭제할 수 있으며, 주로 SQLite 등의 관계형 데이터 베이스 이용을 염두에 두고 설계됐습니다. 그러므로 관계형 데이터 베이스를 다룬 경험이 있다면 이해하는데 도움이 될 것이라 생각됩니다.

ContentProvider로부터 데이러를 읽어오려면 해당 ContentProvider가 어디에 있는지 알아야 합니다.

경로는 **'**content://스키마'를 가진 URI(Universal Resource Identifier)로 지정되고, 일반적으로 접근할 대상 앱에서 정의됩니다. 또한 URI는 authority로 불리며, ContentProvider를 직접 만들 때는 AndroidManifest.xml에 기술해야 합니다.

#### ContentResolver

ContentProvider가 제공하는 데이터에는 ContentResolver를 통해 접근하도록 설계돼 있고, ContentProvider 자신에 대한 참조는 필요없습니다. ContentResolver의 인스턴스는 getContentReslolver() 메서드로 가져옵니다.

ContentResolver에 URI를 전달함으로써 ContentProvider의 데이터에 접근할 수 있습니다. 데이터는 ContentResolver.query()를 이용해 가져옵니다.

```
Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
```

uri : ContentProvider가 관리하는 uri

projection : 가져오고 싶은 칼럼명 (select에 해당)

selection : 필터링할 칼럼명을 지정 (where에 해당)

selectionArgs : selection으로 지정한 칼럼명의 조건을 설정 (프리페어드 스테이트먼트에 해당)

sortOrder : 정렬하고 싶은 칼럼명을 지정 (order by에 해당)

#### Cursor

query()의 반환값인 Cursor는 데이터에 접근하는 포인터입니다.

2차원 표를 떠올려서 생각해보면 행과 열이 있을 때 어느 행을 가리키는지 나타내는 것이 Cursor입니다.

###### **'갤러리' 앱에서 이용되는 ContentProvider에서 이미지를 가져오기**

ContentProvier에 접근할 때 기본적으로 다른 앱이 이용할 수 있도록 필요한 상수는 정의돼 있습니다.

Authority를 나타내는 Uri는 보통 CONTENT\_URI, EXTERNAL\_CONTENT\_URI 같은 상수명으로 공개됩니다.

또한 가져오고 싶은 칼럼명도 마찬가지로 정의돼 있으므로 이를 이용합니다. 우선 필요한 projection등을 작성하고, ContentResolver.query()를 호출해 Cursor를 가져옵니다.

<https://gist.github.com/seunghyunbaek/d10ebcb5bae386b1702d70c3afc0d010>

19행에 Cursor.moveToFirst()를 호출해 커서를 맨 앞으로 이동해 true가 반환된 경우에만 Cursor에서 데이터를 가져옵니다. false가 반환된 경우 데이터는 비어있으므로 그 이후의 처리는 필요 없습니다. 

Cursor로부터 데이터를 가져오려면 두 단계가 필요합니다.

1.  가져오고 싶은 칼럼의 인덱스를 얻습니다.
2.  Cursor.getString(int)를 호출해 데이터를 가져옵니다.

문자열인 경우는 getString(), 숫자인 경우는 getInt() 등 자료형에 따른 메서드가 준비돼 있고, 인수에는 가져오고 싶은 칼럼의 인덱스를 전달합니다.  Cursor의 사용이 끝나면 close()를 호출합니다. Cursor는 ContentProvider로부터 가져온 데이터에 대한 참조를 가지고 있으므로 닫아서 참조하는 데이터를 해제할 필요가 있습니다.

정리하기

1.  ContentResolver 구하기 : getContentResolver, 46행
2.  Cursor 구하기 : getImage(), 58행
3.  Cursor에서 데이터 가져오기 : try{}, 26~28행
4.  Cursor 해제하기 : cursor.close(), 38행

Reference

-   [안드로이드 개발 레벨업 교과서](https://book.naver.com/bookdb/book_detail.nhn?bid=12107989)