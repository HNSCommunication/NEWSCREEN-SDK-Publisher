# NEWSCREEN SDK for Android

* 뉴스크린을 안드로이드 어플리케이션에 연동하기 위한 라이브러리
* 안드로이드 버전 지원 : Android 2.3(API Level 9) 이상 (com.android.support:appcompat-v7 사용 필수)
* 연동을 하기 위해 발급받아야 하는 키
* `sdk_key` : 뉴스크린 담당자에게 발급 받아야합니다 (테스트 sdk_key : d67d8ab4f4c10bf22aa353e27879133c)


## 뉴스크린 SDK 연동 가이드 - 기본

### 1. 설정

#### newscreen_sample 프로젝트를 다운로드 합니다. 아래 3개 파일을 확인합니다
- newscreen_x.x.jar : 뉴스크린 jar 파일 (newscreen_x.x > newscreen_x.x.jar)
- AndroidManifest.xml : 뉴스크린 메니페스트 샘플파일 (app > src > main > AndroidManifest.xml)
- newscreen_activity.xml : 뉴스크린 레이아웃 파일 (app > src > main > res > layout > newscreen_activity.xml)
- notiscreen_noti_view.xml : 노티스크린(알림바) 레이아웃 파일 (app > src > main > res > layout > notiscreen_noti_view.xml)


#### 안드로이드 프로젝트에 `newscreen_x.x.jar` 을 import 합니다

#### `build.gradle` 설정
- `compile project(':newscreen_x.x')` 추가
- `compile 'com.android.support:appcompat-v7:25.+'`추가

앱의 minSdkVersion이 14 미만인 경우 appcompat-v7:25 이하 버전으로 컴파일 해야 합니다. (appcompat-v7:26 이상버전은 minSdkVersion 14이상 지원)

#### `AndroidManifest.xml` 에 다음 코드를 추가합니다.
- 뉴스크린을 위한 퍼미션 설정
- 뉴스크린 광고를 호출하기 위한 설정
```Xml
<manifest>
  
    <!--S:NEWSCREEN 퍼미션 설정  -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_SERVICE" />
    <!--E:NEWSCREEN 퍼미션 설정  -->
    
    <application>
        ...
        
  
        <!--S: NEWSCREEN 설정 사항-->
        <activity
            android:name="com.tnplanet.newscreen_sdk.AD.NewscreenActivity"
            android:excludeFromRecents="true"
        >
        </activity>

        <activity android:name="com.tnplanet.newscreen_sdk.AD.NewscreenAvoidPatternActivity" />
        <service android:name="com.tnplanet.newscreen_sdk.Service.NewscreenService"/>
        <service android:name="com.tnplanet.newscreen_sdk.Service.NotiscreenService"/>

        <service android:exported="false" android:name="com.tnplanet.newscreen_sdk.Service.NewscreenForeGroundService" android:process=":locker" />
        <receiver
            android:name="com.tnplanet.newscreen_sdk.Service.NewscreenRestartService"
            android:enabled="true"
            android:exported="false"
            android:label="RestartService"
            android:process=":remote" >
            <intent-filter>
                <action android:name="ACTION.RESTART.NewscreenService" />
                <action android:name="com.tnplanet.newscreen.NEWSCREEN_BROADCAST_DATA" />
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
        </receiver>
        <!--E: NEWSCREEN 설정 사항 -->
  
    </application>
</manifest>
```

#### `newscreen_activity.xml` 파일을 res > layout 폴더에 복사 합니다
파일이름과 구성요소 ID는 반드시 지켜져야 합니다

```Xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fadeScrollbars="true">

    <WebView
        android:id="@+id/newscreen_webView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#000000">

    </WebView>
</RelativeLayout>

```

#### `notiscreen_noti_view.xml` 파일을 res > layout 폴더에 복사 합니다
파일이름과 구성요소 ID는 반드시 지켜져야 합니다

```Xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="80dp"
    android:background="#ffffff"
    android:orientation="horizontal">

    <LinearLayout
        android:id="@+id/notiscreen_noti_article_btn"
        android:layout_width="1dp"
        android:layout_height="match_parent"
        android:layout_weight="7.5"
        android:orientation="horizontal">

        <LinearLayout
            android:id="@+id/notiscreen_noti_img_view"
            android:layout_width="1dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:gravity="center_vertical|center_horizontal"
            android:orientation="horizontal">

            <ImageView
                android:id="@+id/notiscreen_noti_img"
                android:layout_width="wrap_content"
                android:layout_height="80dp"
                android:adjustViewBounds="false"
                android:scaleType="centerCrop" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="1dp"
            android:layout_height="match_parent"
            android:layout_weight="2.2"
            android:gravity="center_vertical"
            android:orientation="horizontal"
            android:paddingLeft="10dp"
            android:paddingRight="10dp">

            <TextView
                android:id="@+id/notiscreen_noti_tv"
                android:layout_width="wrap_content"
                android:layout_height="80dp"
                android:ellipsize="end"
                android:gravity="center_vertical"
                android:maxLines="2"
                android:textColor="#000000"
                android:textSize="20sp"
                android:textStyle="bold" />
        </LinearLayout>
    </LinearLayout>

    <LinearLayout
        android:id="@+id/notiscreen_noti_more_btn"
        android:layout_width="1dp"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:gravity="center_vertical|center_horizontal"
        android:orientation="horizontal">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="..."
            android:textColor="#000000"
            android:textSize="17sp"
            android:textStyle="bold|italic" />
    </LinearLayout>
</LinearLayout>

```

### 2. 뉴스크린 호출
- 뉴스크린을 호출하기 위해서는 `sdk_key`를 발급 받아야 합니다.

```Java
...

  NewscreenAD newscreenAD = new NewscreenAD(this);           //뉴스크린 정의
  newscreenAD.init("뉴스크린 담당자에게 발급받은 sdk_key");         //뉴스크린 시작
  newscreenAD.stopAd();                                      //뉴스크린 종료
  newscreenAD.isRunningHnsAd();                              //뉴스크린 동작중인 여부 확인
  newscreenAD.setInitCallback(new NewscreenAD.InitCallBack() {  //뉴스크린 콜백
           @Override
            public void initCallBack(boolean valid, String msg) {

            }
        });
  
  //(테스트 sdk_key : d67d8ab4f4c10bf22aa353e27879133c)
...
```

- 자사 뉴스크린 담당자에게 뉴스크린 및 알림바 콘텐츠 노출 요청 승인이 되어야 실행 됩니다.
- 디버깅 후 정해진 노출 빈도수(5회, 10회, 15회) 만큼 Screen을 off 하면 뉴스크린이 호출 됩니다.
- 디버깅 후 뉴스크린을 실행하면 알림바에 콘텐츠가 호출 됩니다.

- 콜백 메시지 

'success' : 호출 성공
'undefined_key' : sdk_key 오류
'undefined_channel' : 뉴스크린 및 알림바 콘텐츠 노출 요청 승인이 되지 않은 상태
'network_error' : 서버 연결 실패
