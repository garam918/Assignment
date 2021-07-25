# 아우토크립트 사전 과제

## 요구 사항
- Splash
  - API를 통해 1페이지에 10개씩 순서대로 10개 페이지 호출 하여 데이터 저장
    - Retrofit2을 이용해 공공 데이터 포털에서 데이터를 받아오고, Room을 이용하여 로컬 DB에 데이터를 저장하였습니다.
  - 저장이 완료되면 Map 화면으로 이동
    - 저장 후 2초의 딜레이를 설정했습니다.
- Map // Naver Map을 사용했습니다.
  - 마커 생성
    - 저장된 리스트의 데이터를 통해 마커 생성
    - centerType에 따라 마커 색상 구분
      - centerType이 지역이면 녹색, 중앙/권역이면 파란색으로 구분
  - 마커 클릭
    - 해당 마커의 data 토스트로 출력

</br >

## 사용 기술

- Kotlin
- Retrofit2 2.9.0
- DataBinding
- Room 2.3.0
- Coroutine 1.4.2
- ViewModel
- Naver Map API

## 시연 영상

https://youtu.be/oFAXBGyi_qc
