# NEWSCREEN SDK for Android

* 뉴스크린을 안드로이드 어플리케이션에 연동하기 위한 라이브러리
* 안드로이드 버전 지원 : Android 4.0.3(API Level 15) 이상
* 연동을 하기 위해 발급받아야 하는 키
* `sdk_key` : 뉴스크린 담당자에게 받아야합니다 ++++++++++++++++(테스트 sdk_key : d67d8ab4f4c10bf22aa353e27879133c)


## 뉴스크린 SDK 연동 가이드 - 기본

### 1. 설정

#### newscreen_sample 프로젝트 또는 [`newscreen_sdk_.zip`](https://github.com/HNSCommunication/docs/raw/master/newscreen_sdk_1.0.zip) 파일을 다운로드 합니다
- newscreen_.jar : 뉴스크린 jar 파일
- AndroidManifest.xml : 뉴스크린 메니페스트 샘플파일
- newscreen_activity.xml : 뉴스크린 레이아웃 파일
- newscreen_sample.apk : 뉴스크린 샘플 apk
- newscreen_sample_project.zip : 뉴스크린 샘플 프로젝트
  

#### 안드로이드 프로젝트에 `newscreen_.jar` 을 import 합니다

#### `AndroidManifest.xml` 에 다음 코드를 추가합니다.
```Xml
<manifest>
  
    <!--S:NEWSCREEN 퍼미션 설정  -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_SERVICE" />
    <!--E:NEWSCREEN 퍼미션 설정  -->
    
    <application>
        ...
        
  
        <!--S: NEWSCREEN 설정 사항 -->
        <activity
            android:name="com.hnscom.hns_endad.AD.HNSADWebViewActivity"
            android:launchMode="singleTask"
        />
        <activity android:name="com.hnscom.hns_endad.AD.HNSAvoidPatternActivity" />
        <service android:name="com.hnscom.hns_endad.Service.HNSEndADService">
        </service>
        <service android:exported="false" android:name="com.hnscom.hns_endad.Service.HNSForeGroundService" android:process=":locker" />
        <receiver
            android:name="com.hnscom.hns_endad.Service.HNSRestartService"
            android:enabled="true"
            android:exported="false"
            android:label="RestartService"
            android:process=":remote" >
            <intent-filter>
                <action android:name="ACTION.RESTART.HNSEndADService" />
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
        </receiver>
        <!--E: NEWSCREEN 설정 사항 -->
    </application>
</manifest>
```

#### `newscreen_activity.xml` 파일을 res > layout 폴더에 복사 합니다.
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
        android:layout_alignParentEnd="true"
        android:layout_alignParentStart="true"
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
