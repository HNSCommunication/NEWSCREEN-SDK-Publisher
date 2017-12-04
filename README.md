# NEWSCREEN SDK for Android

* 뉴스크린을 안드로이드 어플리케이션에 연동하기 위한 라이브러리
* 안드로이드 버전 지원 : Android 4.0(API Level 14) 이상
* 연동을 하기 위해 발급받아야 하는 키
* `sdk_key` : 뉴스크린 담당자에게 발급 받아야합니다 ++++++++++++++++(테스트 sdk_key : d67d8ab4f4c10bf22aa353e27879133c)


## 뉴스크린 SDK 연동 가이드 - 기본

### 1. 설정

#### newscreen_sample 프로젝트를 다운로드 합니다. 아래 3개 파일을 확인합니다
- newscreen_x.x.jar : 뉴스크린 jar 파일 (newscreen_x.x > newscreen_x.x.jar)
- AndroidManifest.xml : 뉴스크린 메니페스트 샘플파일 (app > src > main > AndroidManifest.xml)
- newscreen_activity.xml : 뉴스크린 레이아웃 파일 (app > src > main > res > layout > newscreen_activity.xml)
  

#### 안드로이드 프로젝트에 `newscreen_x.x.jar` 을 import 합니다

#### `build.gradle` 설정
- `compile project(':newscreen_x.x')` 추가
- `com.android.support:appcompat-v7:25.+`추가
앱의 minSdkVersion이 14 미만인 경우 appcompat-v7:25 이하 버전으로 컴파일 해야 합니다. (appcompat-v7:26 이상버전은 minSdkVersion 14이상 지원)

#### `AndroidManifest.xml` 에 다음 코드를 추가합니다.
- 뉴스크린을 위한 퍼미션 설정
- 뉴스크린 광고를 호출하기 위한 설정
```Xml
<manifest>
  
    <!--S: NEWSCREEN 퍼미션 설정  -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_SERVICE" />
    <!--E: NEWSCREEN 퍼미션 설정  -->
    
    <application>
        ...
        
  
        <!--S: NEWSCREEN 설정 사항 -->
        <activity
            android:name="com.tnplanet.newscreen_sdk.AD.NewscreenActivity"
            android:launchMode="singleTask"
            android:excludeFromRecents="true"
            />
        <activity android:name="com.tnplanet.newscreen_sdk.AD.NewscreenAvoidPatternActivity" />
        <service android:name="com.tnplanet.newscreen_sdk.Service.NewscreenService">
        </service>
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
파일이름 `newscreen_activity.xml`
WebView id `newscreen_webView`
레이아웃은 반드시 지켜져야 합니다.

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

### 2. 뉴스크린 호출
- 뉴스크린을 호출하기 위해서는 `sdk_key`를 발급 받아야 합니다.

```Java
...

  NewscreenAD newscreenAD = new NewscreenAD(this);           //뉴스크린 정의
  newscreenAD.init("뉴스크린 담당자에게 발급받은 sdk_key");         //뉴스크린 시작
  newscreenAD.stopAd();                                      //뉴스크린 종료
  newscreenAD.isRunningHnsAd();                              //뉴스크린 동작중인 여부 확인
  //(테스트 sdk_key : d67d8ab4f4c10bf22aa353e27879133c)
...
```
